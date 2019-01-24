package com.mitac.mihealth.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Acc")
public class Acc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AccID")
	private Integer AccID;
	
	@Column(name="PID")
	private String PID;
	
	@Column(name="SexID")
	private Integer SexID;
	
	@Column(name="GradeID")
	private Integer GradeID;
	
	@Column(name="SchYears")
	private Integer SchYears;
	
	@Column(name="Sem")
	private Integer Sem;
	
	@Column(name="Months")
	private Integer Months;
	
	@Column(name="Dates")
	private Date Dates;
	
	@Column(name="Place")
	private Integer Place;
	
	@Column(name="Import")
	private String Import;
	
	@Column(name="Mins")
	private Integer Mins;
	
	@Column(name="Moment")
	private Integer Moment;
		
	//@Type(type = "numeric_boolean")
	@Column(name="Head")
	private Boolean Head;
	
	@Column(name="Eye")
	private String Eye;
	
	@Column(name="Mouth")
	private String Mouth;
	
	@Column(name="Face")
	private String Face;
	
	@Column(name="Nouse")
	private String Nouse;
	
	@Column(name="Chest")
	private String Chest;
	
	@Column(name="Belly")
	private String Belly;
	
	@Column(name="Back")
	private String Back;
	
	@Column(name="Shoulder")
	private String Shoulder;
	
	@Column(name="Arm")
	private String Arm;

	@Column(name="Waist")
	private String Waist;
	
	@Column(name="Leg")
	private String Leg;
	
	@Column(name="Friction")
	private String Friction;
	
	@Column(name="Slash")
	private String Slash;
	
	@Column(name="Press")
	private String Press;
	
	@Column(name="Strick")
	private String Strick;
	
	@Column(name="Twist")
	private String Twist;
	
	@Column(name="Burn")
	private String Burn;
	
	@Column(name="Sting")
	private String Sting;
	
	@Column(name="Fracture")
	private String Fracture;
	
	@Column(name="Old")
	private String Old;
	
	@Column(name="AOther")
	private String AOther;
	
	@Column(name="Fever")
	private String Fever;
	
	@Column(name="Dizzy")
	private String Dizzy;
	
	@Column(name="Puke")
	private String Puke;
	
	@Column(name="Headache")
	private String Headache;
	
	@Column(name="Toothache")
	private String Toothache;
	
	@Column(name="Stomachache")
	private String Stomachache;
	
	@Column(name="Bellyache")
	private String Bellyache;
	
	@Column(name="Diarrhoea")
	private String Diarrhoea;
	
	@Column(name="Menses")
	private String Menses;
	
	@Column(name="Pant")
	private String Pant;
	
	@Column(name="NoseBlood")
	private String NoseBlood;
	
	@Column(name="Rash")
	private String Rash;
	
	@Column(name="Eyeache")
	private String Eyeache;
	
	@Column(name="HOther")
	private String HOther;
	
	@Column(name="WoundCare")
	private String WoundCare;
	
	@Column(name="Ice")
	private String Ice;
	
	@Column(name="Hot")
	private String Hot;
	
	@Column(name="Rest")
	private String Rest;
	
	@Column(name="NoteParent")
	private String NoteParent;
	
	@Column(name="BackHome")
	private String BackHome;
	
	@Column(name="Hospital")
	private String Hospital;
	
	@Column(name="Education")
	private String Education;
	
	@Column(name="MOther")
	private String MOther;
	
	@Column(name="Heat")
	private BigDecimal Heat;
	
	@Column(name="Neck")
	private String Neck;
	
	@Column(name="Buttock")
	private String Buttock;
	
	@Column(name="Perineum")
	private String Perineum;
	
	@Column(name="Memos")
	private String Memos;

	public Integer getAccID() {
		return AccID;
	}

	public void setAccID(Integer accID) {
		AccID = accID;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public Integer getSexID() {
		return SexID;
	}

	public void setSexID(Integer sexID) {
		SexID = sexID;
	}

	public Integer getGradeID() {
		return GradeID;
	}

	public void setGradeID(Integer gradeID) {
		GradeID = gradeID;
	}

	public Integer getSchYears() {
		return SchYears;
	}

	public void setSchYears(Integer schYears) {
		SchYears = schYears;
	}

	public Integer getSem() {
		return Sem;
	}

	public void setSem(Integer sem) {
		Sem = sem;
	}

	public Integer getMonths() {
		return Months;
	}

	public void setMonths(Integer months) {
		Months = months;
	}

	public Date getDates() {
		return Dates;
	}

	public void setDates(Date dates) {
		Dates = dates;
	}

	public Integer getPlace() {
		return Place;
	}

	public void setPlace(Integer place) {
		Place = place;
	}

	public String getImport() {
		return Import;
	}

	public void setImport(String import1) {
		Import = import1;
	}

	public Integer getMins() {
		return Mins;
	}

	public void setMins(Integer mins) {
		Mins = mins;
	}

	public Integer getMoment() {
		return Moment;
	}

	public void setMoment(Integer moment) {
		Moment = moment;
	}

	public Boolean getHead() {
		return Head;
	}

	public void setHead(Boolean head) {
		Head = head;
	}

	public String getEye() {
		return Eye;
	}

	public void setEye(String eye) {
		Eye = eye;
	}

	public String getMouth() {
		return Mouth;
	}

	public void setMouth(String mouth) {
		Mouth = mouth;
	}

	public String getFace() {
		return Face;
	}

	public void setFace(String face) {
		Face = face;
	}

	public String getNouse() {
		return Nouse;
	}

	public void setNouse(String nouse) {
		Nouse = nouse;
	}

	public String getChest() {
		return Chest;
	}

	public void setChest(String chest) {
		Chest = chest;
	}

	public String getBelly() {
		return Belly;
	}

	public void setBelly(String belly) {
		Belly = belly;
	}

	public String getBack() {
		return Back;
	}

	public void setBack(String back) {
		Back = back;
	}

	public String getShoulder() {
		return Shoulder;
	}

	public void setShoulder(String shoulder) {
		Shoulder = shoulder;
	}

	public String getArm() {
		return Arm;
	}

	public void setArm(String arm) {
		Arm = arm;
	}

	public String getWaist() {
		return Waist;
	}

	public void setWaist(String waist) {
		Waist = waist;
	}

	public String getLeg() {
		return Leg;
	}

	public void setLeg(String leg) {
		Leg = leg;
	}

	public String getFriction() {
		return Friction;
	}

	public void setFriction(String friction) {
		Friction = friction;
	}

	public String getSlash() {
		return Slash;
	}

	public void setSlash(String slash) {
		Slash = slash;
	}

	public String getPress() {
		return Press;
	}

	public void setPress(String press) {
		Press = press;
	}

	public String getStrick() {
		return Strick;
	}

	public void setStrick(String strick) {
		Strick = strick;
	}

	public String getTwist() {
		return Twist;
	}

	public void setTwist(String twist) {
		Twist = twist;
	}

	public String getBurn() {
		return Burn;
	}

	public void setBurn(String burn) {
		Burn = burn;
	}

	public String getSting() {
		return Sting;
	}

	public void setSting(String sting) {
		Sting = sting;
	}

	public String getFracture() {
		return Fracture;
	}

	public void setFracture(String fracture) {
		Fracture = fracture;
	}

	public String getOld() {
		return Old;
	}

	public void setOld(String old) {
		Old = old;
	}

	public String getAOther() {
		return AOther;
	}

	public void setAOther(String aOther) {
		AOther = aOther;
	}

	public String getFever() {
		return Fever;
	}

	public void setFever(String fever) {
		Fever = fever;
	}

	public String getDizzy() {
		return Dizzy;
	}

	public void setDizzy(String dizzy) {
		Dizzy = dizzy;
	}

	public String getPuke() {
		return Puke;
	}

	public void setPuke(String puke) {
		Puke = puke;
	}

	public String getHeadache() {
		return Headache;
	}

	public void setHeadache(String headache) {
		Headache = headache;
	}

	public String getToothache() {
		return Toothache;
	}

	public void setToothache(String toothache) {
		Toothache = toothache;
	}

	public String getStomachache() {
		return Stomachache;
	}

	public void setStomachache(String stomachache) {
		Stomachache = stomachache;
	}

	public String getBellyache() {
		return Bellyache;
	}

	public void setBellyache(String bellyache) {
		Bellyache = bellyache;
	}

	public String getDiarrhoea() {
		return Diarrhoea;
	}

	public void setDiarrhoea(String diarrhoea) {
		Diarrhoea = diarrhoea;
	}

	public String getMenses() {
		return Menses;
	}

	public void setMenses(String menses) {
		Menses = menses;
	}

	public String getPant() {
		return Pant;
	}

	public void setPant(String pant) {
		Pant = pant;
	}

	public String getNoseBlood() {
		return NoseBlood;
	}

	public void setNoseBlood(String noseBlood) {
		NoseBlood = noseBlood;
	}

	public String getRash() {
		return Rash;
	}

	public void setRash(String rash) {
		Rash = rash;
	}

	public String getEyeache() {
		return Eyeache;
	}

	public void setEyeache(String eyeache) {
		Eyeache = eyeache;
	}

	public String getHOther() {
		return HOther;
	}

	public void setHOther(String hOther) {
		HOther = hOther;
	}

	public String getWoundCare() {
		return WoundCare;
	}

	public void setWoundCare(String woundCare) {
		WoundCare = woundCare;
	}

	public String getIce() {
		return Ice;
	}

	public void setIce(String ice) {
		Ice = ice;
	}

	public String getHot() {
		return Hot;
	}

	public void setHot(String hot) {
		Hot = hot;
	}

	public String getRest() {
		return Rest;
	}

	public void setRest(String rest) {
		Rest = rest;
	}

	public String getNoteParent() {
		return NoteParent;
	}

	public void setNoteParent(String noteParent) {
		NoteParent = noteParent;
	}

	public String getBackHome() {
		return BackHome;
	}

	public void setBackHome(String backHome) {
		BackHome = backHome;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getMOther() {
		return MOther;
	}

	public void setMOther(String mOther) {
		MOther = mOther;
	}

	public BigDecimal getHeat() {
		return Heat;
	}

	public void setHeat(BigDecimal heat) {
		Heat = heat;
	}

	public String getNeck() {
		return Neck;
	}

	public void setNeck(String neck) {
		Neck = neck;
	}

	public String getButtock() {
		return Buttock;
	}

	public void setButtock(String buttock) {
		Buttock = buttock;
	}

	public String getPerineum() {
		return Perineum;
	}

	public void setPerineum(String perineum) {
		Perineum = perineum;
	}

	public String getMemos() {
		return Memos;
	}

	public void setMemos(String memos) {
		Memos = memos;
	}	
}
