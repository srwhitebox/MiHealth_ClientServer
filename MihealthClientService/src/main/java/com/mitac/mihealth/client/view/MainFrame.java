package com.mitac.mihealth.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
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
import com.mitac.mihealth.client.controller.IMiHealthListener_2;
import com.mitac.mihealth.client.controller.MiHealthClient;
import com.mitac.mihealth.client.model.Student;
import com.mitac.mihealth.client.utils.MiHealthProperty;

import ch.qos.logback.core.net.SyslogOutputStream;
import john.MiHEALTH.MiHEALTHproperties;
import okhttp3.Response;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements IMiHealthListener,ActionListener,IMiHealthListener_2{
	private boolean isServiceStarted = false;
	private boolean isServiceStarted_2 = false;
	
	// Change the default host URL later.. when it is fixed..
	private JTextField fieldHostUrl = new JTextField("ws://mihealth.micloud.tw/query");//ws://104.199.128.74/query
	private ApplicationContext msSqlcontext;
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	private MiHealthClient mihealthClient;
	private JButton btnStart = new JButton("Start");
	private JButton btnStart_2 = new JButton("Start(Acc only)");
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
			btnStart_2.setEnabled(false);
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
        
        btnStart_2.addActionListener((ActionEvent event) -> {
        	startActionSwitch_2();
        });
        
        btnExportStuExcel.addActionListener(this);
        createLayout(btnExportStuExcel,lblHostUrl,fieldHostUrl,btnStart,btnStart_2,
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
	
	private void startActionSwitch_2(){
		if (isServiceStarted_2){
    		if (mihealthClient != null){
    			mihealthClient.close();
    			isServiceStarted_2 = !isServiceStarted_2;
        		btnStart_2.setText("Start(Acc only)");
        		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[1]);
    		}
    	}else{
    		String hostUrl = fieldHostUrl.getText().trim();
    		if (!Strings.isNullOrEmpty(hostUrl) && hostUrl.startsWith("ws")){
    			mihealthClient = new MiHealthClient(hostUrl, MiHEALTHproperties.getSchoolId(),
    					MiHEALTHproperties.getAuthKey());
        		mihealthClient.setListener_2(this);
        		mihealthClient.open();
        		isServiceStarted_2 = !isServiceStarted_2;
        		btnStart_2.setText("Stop");
        		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[2]);
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
                .addComponent(arg[8])
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
                .addComponent(arg[8])
                .addGap(150)
        );

        pack();
    }

	/**
	 * Measurement data occurred event handler
	 */
	@Override
	public void onMeasurementData(Student student, Date date, JsonObject jData){
		//JOptionPane.showMessageDialog(null, jData.toString());
//		String bb = jData.toString();
//		try {
//			System.out.println(bb);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			System.out.println(e1.toString());
//		}
		//JOptionPane.showMessageDialog(null, "wait");
		countMiHealth++;lblcountMiHealth.setText("目前已從MiHealth接收:"+countMiHealth+"筆");
		JsonObject SightData = new JsonObject();
		JsonObject WHData = new JsonObject();
		JsonObject AccData = new JsonObject();
		String[] arr_1 = {"Head","Eye","Mouth","Face","Nouse","Chest","Belly",
				"Back","Shoulder","Arm","Waist","Leg",
				"Neck","Buttock","Perineum",
				"Import"}; //0418加入新欄位於"Perineum"之後
		String[] arr_2 = {"Friction","Slash",
				"Press","Strick","Twist","Burn","Sting","Fracture","Old",
				"AOther","Fever","Dizzy","Puke","Headache","Toothache",
				"Stomachache","Bellyache","Diarrhoea",
				"MOther","Menses","Pant",
				"NoseBlood","Rash","Eyeache","HOther"};
		String[] arr_3 = {"WoundCare","Ice",
				"Hot","Rest","NoteParent","BackHome","Hospital","Education"};
		//0904_將處置方式獨立出來
		
		//String[] arr_3 = {"Moment","Months"};
	
		
//		final String studentNo = student.getStudentNo();
		// If you need other keys.. do with them...
		for(Entry<String, JsonElement> jEntry : jData.entrySet()){
			final String property = jEntry.getKey();
			final JsonElement jValue = jEntry.getValue();
			JOptionPane.showMessageDialog(null, "Key:"+property+" Value:"+jValue);
			switch(property){
			case MiHealthProperty.PROP_HEIGHT: 
				WHData.add("Height", jValue);  //{"Height", 180}
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
			case MiHealthProperty.PROP_INJURED_PLACE:
				AccData.addProperty("Place", parsePlace(jValue.toString()));
				break;
			case MiHealthProperty.PROP_INJURED_PART:
				String prop_in = jValue.toString();
				//System.out.println(prop_in);
//				for(int i=0;i<arr_3.length;i++){
//					AccData.addProperty(arr_3[i], 0);
//				}
				for(int i=0;i<arr_1.length;i++){
					if(Objects.equals("\""+arr_1[i].toLowerCase().toString()+"\"", prop_in)){
						//System.out.println(arr_1[i].toString().toLowerCase()+"----------true-----------"+prop_in);
						AccData.addProperty(arr_1[i], true);
						//System.out.println("-------AccData-------"+AccData.toString());						
					}
					else{
						//System.out.println(arr_1[i].toString().toLowerCase()+"----------false-----------"+prop_in);
						AccData.addProperty(arr_1[i], false);
					}
				}
				break;
			case MiHealthProperty.PROP_DISEASE:
				String prop_di = jValue.toString();
				//System.out.println("----------TEST-1---------"+prop_di);
				//System.out.println(prop_di);
//				for(int i=0;i<arr_3.length;i++){
//					AccData.addProperty(arr_3[i], 0);
//				}
				String[] split_di = prop_di.split(",");
				for(String di : split_di){
					String di_trim = di.trim().replace("\"", "");
					//System.out.println("----------TEST-2---------"+di_trim);
					if(di_trim.equals("nosebleed"))
						di_trim = "NoseBlood".toLowerCase();
					for(int i=0;i<arr_2.length;i++){
						//System.out.println(arr_2[i].toString().toLowerCase()+"---"+di_trim);
						if(Objects.equals(arr_2[i].toLowerCase().toString(), di_trim)){
							//System.out.println(arr_2[i].toString().toLowerCase()+"----------true-----------"+di_trim);
							AccData.addProperty(arr_2[i], true);
						}
						else{
							//System.out.println(AccData.get(arr_2[i])+"----------false-----------");
							if(AccData.get(arr_2[i]) == null)
							AccData.addProperty(arr_2[i], false);
						}
					}
				}
				
				//0906_新增內科
				for(int i=0;i<arr_1.length;i++){
					if(AccData.get(arr_1[i]) == null)
					AccData.addProperty(arr_1[i], false);
				}
				
				if(AccData.get("Place") == null)
				AccData.addProperty("Place", 0);
				
				break;
			case MiHealthProperty.PROP_TREATMENT:
				String prop_tr = jValue.toString();
				//System.out.println("----------TEST-1---------"+prop_tr);
				String[] split_tr = prop_tr.split(",");
				for(String tr : split_tr){
					String tr_trim = tr.trim().replace("\"", "");
					
					String parse_Tr = parseTreatment(tr_trim).toLowerCase();
					//System.out.println("==========" + parse_Tr);
					for(int i=0;i<arr_3.length;i++){
						//System.out.println("================" + arr_3[i].toLowerCase().toString() + "====" + parse_Tr);
						if(Objects.equals(arr_3[i].toLowerCase().toString(), parse_Tr)){
							AccData.addProperty(arr_3[i], true);
						}
						else{
							//System.out.println("===========false");
							if(AccData.get(arr_3[i]) == null)
							AccData.addProperty(arr_3[i], false);
						}
					}
				}
				break;
			}
			
		}
		if(WHData.size()!=0)
			onWHData( student, WHData);
		if(SightData.size()!=0)
			onSightData( student, SightData);
		if(AccData.size()!=0){
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdFormat_m = new SimpleDateFormat("HH");
			AccData.addProperty("Dates", sdFormat.format(date));
			LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localdate.getMonthValue();
			
			int HH = Integer.valueOf(sdFormat_m.format(date));
			//System.out.println("----------HERE-1-hr-----------"+HH);
			if( 0<HH && HH<12 ){
				AccData.addProperty("Moment", 1);
			}else if( HH==12 ){
				AccData.addProperty("Moment", 2);
			}else{
				AccData.addProperty("Moment", 3);
			}
			
			AccData.addProperty("Months", month);
			//System.out.println("----------HERE-1-----------"+sdFormat.format(date).toString());
			//System.out.println("----------HERE-1-2-----------"+month);
			//System.out.println("----------HERE-1-3-----------"+AccData.get("Moment"));
			try {
				onAccData( student, AccData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
			
	}
	
	@Override
	public void onMeasurementData_2(Student student, Date date, JsonObject jData){
		countMiHealth++;lblcountMiHealth.setText("目前已從MiHealth接收:"+countMiHealth+"筆");
		JsonObject SightData = new JsonObject();
		JsonObject WHData = new JsonObject();
		JsonObject AccData = new JsonObject();
		String[] arr_1 = {"Head","Eye","Mouth","Face","Nouse","Chest","Belly",
				"Back","Shoulder","Arm","Waist","Leg",
				"Neck","Buttock","Perineum",
				"Import"}; //0418加入新欄位於"Perineum"之後
		String[] arr_2 = {"Friction","Slash",
				"Press","Strick","Twist","Burn","Sting","Fracture","Old",
				"AOther","Fever","Dizzy","Puke","Headache","Toothache",
				"Stomachache","Bellyache","Diarrhoea",
				"MOther","Menses","Pant",
				"NoseBlood","Rash","Eyeache","HOther"};
		String[] arr_3 = {"WoundCare","Ice",
				"Hot","Rest","NoteParent","BackHome","Hospital","Education"};
		//0904_將處置方式獨立出來
		
		//String[] arr_3 = {"Moment","Months"};
	
		
//		final String studentNo = student.getStudentNo();
		// If you need other keys.. do with them...
		for(Entry<String, JsonElement> jEntry : jData.entrySet()){
			final String property = jEntry.getKey();
			final JsonElement jValue = jEntry.getValue();
			JOptionPane.showMessageDialog(null, "Key:"+property+" Value:"+jValue);
			switch(property){
			case MiHealthProperty.PROP_INJURED_PLACE:
				AccData.addProperty("Place", parsePlace(jValue.toString()));
				break;
			case MiHealthProperty.PROP_INJURED_PART:
				String prop_in = jValue.toString();
				//System.out.println(prop_in);
//				for(int i=0;i<arr_3.length;i++){
//					AccData.addProperty(arr_3[i], 0);
//				}
				for(int i=0;i<arr_1.length;i++){
					if(Objects.equals("\""+arr_1[i].toLowerCase().toString()+"\"", prop_in)){
						//System.out.println(arr_1[i].toString().toLowerCase()+"----------true-----------"+prop_in);
						AccData.addProperty(arr_1[i], true);
						//System.out.println("-------AccData-------"+AccData.toString());						
					}
					else{
						//System.out.println(arr_1[i].toString().toLowerCase()+"----------false-----------"+prop_in);
						AccData.addProperty(arr_1[i], false);
					}
				}
				break;
			case MiHealthProperty.PROP_DISEASE:
				String prop_di = jValue.toString();
				//System.out.println("----------TEST-1---------"+prop_di);
				//System.out.println(prop_di);
//				for(int i=0;i<arr_3.length;i++){
//					AccData.addProperty(arr_3[i], 0);
//				}
				String[] split_di = prop_di.split(",");
				for(String di : split_di){
					String di_trim = di.trim().replace("\"", "");
					//System.out.println("----------TEST-2---------"+di_trim);
					if(di_trim.equals("nosebleed"))
						di_trim = "NoseBlood".toLowerCase();
					for(int i=0;i<arr_2.length;i++){
						//System.out.println(arr_2[i].toString().toLowerCase()+"---"+di_trim);
						if(Objects.equals(arr_2[i].toLowerCase().toString(), di_trim)){
							//System.out.println(arr_2[i].toString().toLowerCase()+"----------true-----------"+di_trim);
							AccData.addProperty(arr_2[i], true);
						}
						else{
							//System.out.println(AccData.get(arr_2[i])+"----------false-----------");
							if(AccData.get(arr_2[i]) == null)
							AccData.addProperty(arr_2[i], false);
						}
					}
				}
				
				for(int i=0;i<arr_1.length;i++){
					if(AccData.get(arr_1[i]) == null)
					AccData.addProperty(arr_1[i], false);
				}
				
				if(AccData.get("Place") == null)
				AccData.addProperty("Place", 0);
				
				break;
			case MiHealthProperty.PROP_TREATMENT:
				String prop_tr = jValue.toString();
				//System.out.println("----------TEST-1---------"+prop_tr);
				String[] split_tr = prop_tr.split(",");
				for(String tr : split_tr){
					String tr_trim = tr.trim().replace("\"", "");
					
					String parse_Tr = parseTreatment(tr_trim).toLowerCase();
					//System.out.println("==========" + parse_Tr);
					for(int i=0;i<arr_3.length;i++){
						//System.out.println("================" + arr_3[i].toLowerCase().toString() + "====" + parse_Tr);
						if(Objects.equals(arr_3[i].toLowerCase().toString(), parse_Tr)){
							AccData.addProperty(arr_3[i], true);
						}
						else{
							//System.out.println("===========false");
							if(AccData.get(arr_3[i]) == null)
							AccData.addProperty(arr_3[i], false);
						}
					}
				}
				break;
			}
			
		}
		if(AccData.size()!=0){
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdFormat_m = new SimpleDateFormat("HH");
			AccData.addProperty("Dates", sdFormat.format(date));
			LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localdate.getMonthValue();
			
			int HH = Integer.valueOf(sdFormat_m.format(date));
			//System.out.println("----------HERE-1-hr-----------"+HH);
			if( 0<HH && HH<12 ){
				AccData.addProperty("Moment", 1);
			}else if( HH==12 ){
				AccData.addProperty("Moment", 2);
			}else{
				AccData.addProperty("Moment", 3);
			}
			
			AccData.addProperty("Months", month);
			//System.out.println("----------HERE-1-----------"+sdFormat.format(date).toString());
			//System.out.println("----------HERE-1-2-----------"+month);
			//System.out.println("----------HERE-1-3-----------"+AccData.get("Moment"));
			try {
				onAccData( student, AccData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
			
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
	private static int parsePlace(String injuredPlace){
		switch(injuredPlace){
		case "\"playground\"":
			return 1;
		case "\"other4\"":
			return 2;
		case "\"classroom\"":
			return 3;
		case "\"special_room\"":
			return 4;
		case "\"corridor\"":
			return 5;
		case "\"stair\"":
			return 6;
		case "\"basement\"":
			return 7;
		case "\"other2\"":
			return 8;
		case "\"toilet\"":
			return 9;
		case "\"outside\"":
			return 10;
		default:
			return 99;
		}
	}
	
	private static String parseTreatment(String treatmnet){
		switch(treatmnet){
		case "wound_treatment":
			return "WoundCare";
		case "ice_pack":
			return "Ice";
		case "hot_pack":
			return "Hot";
		case "rest":
			return "Rest";
		case "notify_to_parent":
			return "NoteParent";
		case "parent_bring":
			return "BackHome";
		case "send_to_hospital":
			return "Hospital";
		case "health_education":
			return "Education";
		default:
			return "Other";
		}
	}
	
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
		countMiHealth+=5;lblcountMiHealth.setText("目前已從MiHealth接收:"+countMiHealth+"筆");
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
		String sexID_str = student.getGender().toString();
		int sexID_int = 1;
		if(sexID_str.equals("MALE")){
			sexID_int = 1;
		}else if(sexID_str.equals("FEMALE")){
			sexID_int = 2;
		}
		jData.addProperty("PID", student.getNationalId());
		jData.addProperty("Sem", MiHEALTHproperties.getSem());
		jData.addProperty("SexID", sexID_int);
		jData.addProperty("GradeID", student.getGrade());
		jData.addProperty("SchYears", MiHEALTHproperties.getNowYear());
		jData.addProperty("Mins", 0);
		jData.addProperty("Heat", 0.0);
		jData.addProperty("Memos", "");
		//System.out.println("------------HERE-2---------"+student.getGender().toString());
		a004.post(jData);
		countEdu++;lblcountEdu.setText("目前已傳送至教育部:"+countEdu+"筆");
		System.out.println("acc inserted===============");
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
	
	@Override
	public void onOpen_2(){
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[2]);
	}
	
	@Override
	public void onClosing_2(int code, String reason){
		switch(code){
		case 1000:
			JOptionPane.showMessageDialog(this, "Hello_0424");
			break;
		default:
			JOptionPane.showMessageDialog(this,"Error! Please contact RD!\n code:"+code+"\n reason:"+reason,"Error!",JOptionPane.ERROR_MESSAGE,null);
			if (mihealthClient != null){
    			mihealthClient.close();
    			isServiceStarted = false;
        		btnStart_2.setText("Start(Acc only)");
    		}
		}
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[1]);
	}
	@Override
	public void onFailure_2(Response response){
		String message="Error! Please contact RD!";
		if(response!=null)
			message = message+"\n response:"+response.message();
		JOptionPane.showMessageDialog(this,message,"Error!",JOptionPane.ERROR_MESSAGE,null);
		if (mihealthClient != null){
			mihealthClient.close();
			isServiceStarted = false;
    		btnStart_2.setText("Start(Acc only)");
		}
		lblconnectMiHealth.setText("MiHealth連線狀態:"+connect[1]);
	}
}
