package com.mitac.mihealth.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.AnswerWorker;
import com.mitac.mihealth.client.api.A001;
import com.mitac.mihealth.client.api.A002;
import com.mitac.mihealth.client.api.A003;
import com.mitac.mihealth.client.api.A004;
import com.mitac.mihealth.client.controller.IMiHealthListener;
import com.mitac.mihealth.client.controller.MiHealthClient;
import com.mitac.mihealth.client.model.Student;
import com.mitac.mihealth.client.utils.MiHealthProperty;

import john.MiHEALTH.MiHEALTHproperties;
import okhttp3.Response;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements IMiHealthListener,ActionListener{
	private boolean isServiceStarted = false;
	
	// Change the default host URL later.. when it is fixed..
	private JTextField fieldHostUrl = new JTextField("ws://localhost:8080/query");//ws://104.199.128.74/query
	private ApplicationContext msSqlcontext;
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	private MiHealthClient mihealthClient;
	private JButton btnStart = new JButton("Start");
	private JButton btnExportStuExcel = new JButton("Export Student info.");
	/**
	 * Constructor
	 */
	public MainFrame(){
		checkConnect();
		initUi();
	}
	/**確認教育部連線狀況*/
	private void checkConnect(){
		try{
			msSqlcontext = 
//					new ClassPathXmlApplicationContext("spring2.xml");//in debug
					new FileSystemXmlApplicationContext("conf/spring2.xml");//export Runnable jar file
			lblconnectEdu.setText("教育部連線狀態:"+connect[2]);
		}catch(BeansException e){
			lblconnectEdu.setText("教育部連線狀態:"+connect[3]);
			JOptionPane.showMessageDialog(null, "教育部連線狀態:"+connect[3]);
			btnStart.setEnabled(false);
			btnExportStuExcel.setEnabled(false);
		}
	}
	
	/**
	 * Initialize UI elements
	 */
	private void initUi() {
		// Initialize window property
        ImageIcon webIcon = null;
		try {
			webIcon = new ImageIcon(
//        		getClass().getResource("../res/image/icon.png")//in debug
				(new java.io.File("../res/image/icon.png")).toURI().toURL()//export Runnable jar file
					);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        setIconImage(webIcon.getImage());
        setTitle("MiHealth Client");
        setSize(300, 200);
        this.setResizable(false);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        JLabel lblHostUrl = new JLabel("Host URL:"); 
        
        // Initialize Start and end button...
		
        btnStart.addActionListener((ActionEvent event) -> {
        	startActionSwitch();
        });
        btnExportStuExcel.addActionListener(this);
        createLayout(btnExportStuExcel,lblHostUrl, fieldHostUrl, btnStart, 
        		lblconnectEdu,lblconnectMiHealth,lblcountEdu,lblcountMiHealth);		
    }
	private void startActionSwitch(){
		if (isServiceStarted){
    		if (mihealthClient != null){
    			mihealthClient.close();
    			isServiceStarted = !isServiceStarted;
        		btnStart.setText("Start");
    		}
    	}else{
    		String hostUrl = fieldHostUrl.getText().trim();
    		if (!Strings.isNullOrEmpty(hostUrl) && hostUrl.startsWith("ws")){
    			mihealthClient = new MiHealthClient(hostUrl, MiHEALTHproperties.getSchoolId(),
    					MiHEALTHproperties.getAuthKey());
        		mihealthClient.setListener(this);
        		mihealthClient.open();
            	isServiceStarted = !isServiceStarted;
        		btnStart.setText("Stop");
    		}
    	}
	}
	private int countMiHealth = 0;
	private int countEdu = 0;
	private String[] connect = {"連線中","未連線","連線成功","連線失敗"};
	private JLabel lblconnectEdu = new JLabel("教育部連線狀態:");
	private JLabel lblconnectMiHealth = new JLabel("MiHealth連線狀態:"+connect[1]);
	private JLabel lblcountMiHealth = new JLabel("目前已從MiHealth接收:"+countMiHealth+"筆");
	private JLabel lblcountEdu = new JLabel("目前已傳送至教育部:"+countEdu+"筆"); 
	
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)*/
	@Override
	public void actionPerformed(ActionEvent e)
    {
		btnExportStuExcel.setEnabled(false);
        new AnswerWorker(this).execute();
    }
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)(完成處理)*/
	public void done()
    {
		btnExportStuExcel.setEnabled(true);
		JOptionPane.showMessageDialog(this, "done! Path:"+MiHEALTHproperties.getExcelPath());
    }
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)(失敗處理)*/
	public void error(Exception e)
    {
		btnExportStuExcel.setEnabled(true);
		JOptionPane.showMessageDialog(this,"Error! Please contact RD!","Error!",JOptionPane.ERROR_MESSAGE,null);
    }
	/**
	 * Create layout
	 * @param arg
	 */
	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addComponent(arg[4])
                .addComponent(arg[5])
                .addComponent(arg[6])
                .addComponent(arg[7])
                .addGap(250)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
        		.addComponent(arg[0], GroupLayout.DEFAULT_SIZE, 
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addComponent(arg[4])
                .addComponent(arg[5])
                .addComponent(arg[6])
                .addComponent(arg[7])
                .addGap(150)
        );

        pack();
    }

	/**
	 * Measurement data occurred event handler
	 */
	@Override
	public void onMeasurementData(Student student, Date date, JsonObject jData){
		countMiHealth++;lblcountMiHealth.setText("目前已從MiHealth接收:"+countMiHealth+"筆");
		JsonObject SightData = new JsonObject();
		JsonObject WHData = new JsonObject();
//		final String studentNo = student.getStudentNo();
		// If you need other keys.. do with them...
		for(Entry<String, JsonElement> jEntry : jData.entrySet()){
			final String property = jEntry.getKey();
			final JsonElement jValue = jEntry.getValue();
			switch(property){
			case MiHealthProperty.PROP_HEIGHT:
				WHData.add("Height", jValue);
				break;
			case MiHealthProperty.PROP_WEIGHT:
				WHData.add("Weight", jValue);
				break;
			case MiHealthProperty.PROP_LEFT_EYE:
				try{
					SightData.addProperty("Sight0L", parseInt(jValue.getAsFloat()));
				}catch(Exception e){
					SightData.addProperty("Sight0L", -1);
				}
				break;
			case MiHealthProperty.PROP_RIGHT_EYE:
				try{
					SightData.addProperty("Sight0R", parseInt(jValue.getAsFloat()));
				}catch(Exception e){
					SightData.addProperty("Sight0R", -1);
				}
				break;
			}
			
		}
		onSightData( student,SightData);
		onWHData( student, WHData);
	}
	
	/**將MiHEALTH 視力資料 轉成教育部格式*/
	 /* 條件設定:(參考HealthDB_Schema說明.xlsx)
	  * -1: <0.1
		1: 0.1
		2: 0.2
		3: 0.3
		4: 0.4
		5: 0.5
		6: 0.6
		7: 0.7
		8: 0.8
		9: 0.9
		10:1.0
		12:1.2
		15:1.5
		20:2.0*/
	private static int parseInt(float value){
		if(value<0.1){
			return -1;
		}
		String v = String.valueOf(value);
		switch(v){
		case "0.1":
			return 1;
		case "0.2":
			return 2;
		case "0.3":
			return 3;
		case "0.4":
			return 4;
		case "0.5":
			return 5;
		case "0.6":
			return 6;
		case "0.7":
			return 7;
		case "0.8":
			return 8;
		case "0.9":
			return 9;
		case "1.0":
		case "1":
			return 10;
		case "1.2":
			return 12;
		case "1.5":
			return 15;
		case "2.0":
			return 20;
		default:
			return -1;
		}
	}
	
	/**
	 * Care data occurred event handler
	 */
	@Override
	public void onCareData(Student student, Date date, JsonObject jData){
		JsonObject AccData = new JsonObject();
//		final String studentNo = student.getStudentNo();
		// If you need other keys.. do with them...
		for(Entry<String, JsonElement> jEntry : jData.entrySet()){
			final String property = jEntry.getKey();
			final JsonElement jValue = jEntry.getValue();
		}
		onAccData( student,AccData);
	}
	/**傳送教育部學生資料(目前未使用)*/
	public void onStData(Student student, JsonObject jData) {
		A001 a001 = new A001(msSqlcontext,gson);
		a001.post(jData);
	}
	/**傳送教育部視力量測資料*/
	public void onSightData(Student student, JsonObject jData){
		A002 a002 = new A002(msSqlcontext,gson);
		jData.addProperty("PID", student.getNationalId());
		jData.addProperty("GradeID", student.getGrade());
		jData.addProperty("Sem", MiHEALTHproperties.getSem());
		a002.post(jData);
		countEdu++;lblcountEdu.setText("目前已傳送至教育部:"+countEdu+"筆"); 
	}
	/**傳送教育部身高體重量測資料*/
	public void onWHData(Student student, JsonObject jData){
		A003 a003 = new A003(msSqlcontext,gson);
		jData.addProperty("PID", student.getNationalId());
		jData.addProperty("GradeID", student.getGrade());
		jData.addProperty("Sem", MiHEALTHproperties.getSem());
		a003.post(jData);
		countEdu++;lblcountEdu.setText("目前已傳送至教育部:"+countEdu+"筆"); 
	}
	/**傳送教育部傷病資料*/
	public void onAccData(Student student, JsonObject jData){
		A004 a004 = new A004(msSqlcontext,gson);
		jData.addProperty("Sem", MiHEALTHproperties.getSem());
		a004.post(jData);
	}
	
	@Override
	public void onOpen(){
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[2]);
	}
	@Override
	public void onClosing(int code, String reason){
		switch(code){
		case 1000:
			break;
		default:
			JOptionPane.showMessageDialog(this,"Error! Please contact RD!\n code:"+code+"\n reason:"+reason,"Error!",JOptionPane.ERROR_MESSAGE,null);
			if (mihealthClient != null){
    			mihealthClient.close();
    			isServiceStarted = false;
        		btnStart.setText("Start");
    		}
		}
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[1]);
	}
	@Override
	public void onFailure(Response response){
		String message="Error! Please contact RD!";
		if(response!=null)
			message = message+"\n response:"+response.message();
		JOptionPane.showMessageDialog(this,message,"Error!",JOptionPane.ERROR_MESSAGE,null);
		if (mihealthClient != null){
			mihealthClient.close();
			isServiceStarted = false;
    		btnStart.setText("Start");
		}
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[1]);
	}
}
