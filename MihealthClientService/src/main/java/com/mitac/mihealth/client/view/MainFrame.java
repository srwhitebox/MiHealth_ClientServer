package com.mitac.mihealth.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.controller.IMiHealthListener;
import com.mitac.mihealth.client.controller.MiHealthClient;
import com.mitac.mihealth.client.model.Student;
import com.mitac.mihealth.client.utils.MiHealthProperty;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements IMiHealthListener{
	private boolean isServiceStarted = false;
	
	// Change the default host URL later.. when it is fixed..
	private JTextField fieldHostUrl = new JTextField("ws://localhost:8080/mihealth/query");
	
	private MiHealthClient mihealthClient;
	
	/**
	 * Constructor
	 */
	public MainFrame(){
		initUi();
	}
	
	/**
	 * Initialize UI elements
	 */
	private void initUi() {
		// Initialize window property
        ImageIcon webIcon = new ImageIcon(getClass().getResource("/res/image/icon.png"));
        setIconImage(webIcon.getImage());
        setTitle("MiHealth Client");
        setSize(300, 200);
        this.setResizable(false);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        JLabel lblHostUrl = new JLabel("Host URL:"); 
        
        // Initialize Start and end button...
		JButton btnStart = new JButton("Start");
        btnStart.addActionListener((ActionEvent event) -> {
        	if (isServiceStarted){
        		if (mihealthClient != null){
        			mihealthClient.close();
            		btnStart.setText("Start");
        		}
        		isServiceStarted = false;
        	}else {
        		String hostUrl = fieldHostUrl.getText().trim();
        		if (!Strings.isNullOrEmpty(hostUrl) && hostUrl.startsWith("ws")){
	        		mihealthClient = new MiHealthClient(hostUrl, "vndemo", "qchkcarH2HPk5LwHgL1zrQ4ZXF5ByoQYgL+qm7f6wtY");
	        		mihealthClient.setListener(this);
	        		mihealthClient.open();
	        		btnStart.setText("Stop");
	            	isServiceStarted = true;
        		}
        	}
        });
        
        createLayout(lblHostUrl, fieldHostUrl, btnStart);		
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
                .addGap(250)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE, 
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addGap(150)
        );

        pack();
    }

	/**
	 * Measurement data occurred event handler
	 */
	@Override
	public void onMeasurementData(Student student, Date date, JsonObject jData) {
		final String studentNo = student.getStudentNo();
		// If you need other keys.. do with them...
		for(Entry<String, JsonElement> jEntry : jData.entrySet()){
			final String property = jEntry.getKey();
			final JsonElement jValue = jEntry.getValue();
			switch(property){
			case MiHealthProperty.PROP_HEIGHT:
			case MiHealthProperty.PROP_WEIGHT:
			case MiHealthProperty.PROP_BMI:
			case MiHealthProperty.PROP_LEFT_EYE:
			case MiHealthProperty.PROP_RIGHT_EYE:
				final float floatValue = jValue.getAsFloat();
				//
				// Do something with the property and value
				//
				break;
			case MiHealthProperty.PROP_SYSTOLIC:
			case MiHealthProperty.PROP_DIASTOLIC:
			case MiHealthProperty.PROP_PULSE:
				final int intValue = jValue.getAsInt();
				//
				// Do something with the property and value
				//
				break;
			}
			
		}
	}
	
	/**
	 * Care data occurred event handler
	 */
	@Override
	public void onCareData(Student student, Date date, JsonObject jData) {
		//
		// Do something with the property and value
		//
	}
}
