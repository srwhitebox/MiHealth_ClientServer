package com.mitac.mihealth.client.api;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.google.gson.JsonObject;

public class TEST {
	
	private static int parsePlace(String injuredPlace){
		switch(injuredPlace){
		case "123":
			return 1;
		default:
			return 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = {"Head","Eye","Mouth","Face","Nouse","Chest","Belly",
				"Back","Shoulder","Arm","Waist","Leg","Friction","Slash",
				"Press","Strick","Twist","Burn","Sting","Fracture","Old",
				"AOther","Fever","Dizzy","Puke","Headache","Toothache",
				"Stomachache","Bellyache","Diarrhoea","Menses","Pant",
				"NoseBlood","Rash","Eyeache","HOther","WoundCare","Ice",
				"Hot","Rest","NoteParent","BackHome","Hospital","Education",
				"MOther","Heat","Neck","Buttock","Perineum"};
		
		JsonObject AccData = new JsonObject();
		AccData.addProperty("123", 123);
		System.out.println(AccData.get("123").toString());
		AccData.addProperty("123", 456);
		System.out.println(AccData.get("123").toString());
		System.out.println("Head".toLowerCase().equals("head"));
		
		String prop = "head";
		
		for(int i=0;i<arr.length;i++){
			if(Objects.equals(arr[i].toLowerCase().toString(), prop)){
				AccData.addProperty(arr[i], 1);
			}
			else{
				AccData.addProperty(arr[i], 0);
			}
		}
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				         Date current = new Date();
				         System.out.println(sdFormat.format(current));
		
		System.out.println(AccData.get(arr[0]).toString());
		System.out.println(arr[0].toLowerCase().toString());
		System.out.println("\""+prop+"\"");
		
		int i = parsePlace("1123");
		System.out.println(i);
		
		String line = "\"NARUTO, One Piece, Detective Conan\", SAKURA, Alive";
		// 將 line 字串以逗號分割，並存在 split_line 字串陣列中
		//, One Piece, Detective Conan, SAKURA, Alive
		String[] split_line = line.split(",");
		for(i=0;i<split_line.length;i++){
			String di_trim = split_line[i].trim().replace("\"", "");
			System.out.println(di_trim);
		}
		
	}

}
