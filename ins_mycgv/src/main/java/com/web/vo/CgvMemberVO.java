package com.web.vo;

/**
 * 1. ������ �Ѿ���� ������ ����
 * 2. ���̺� CRUD �۾��� �ʿ��� ������ ����
 */
public class CgvMemberVO {
	//Field
	String id, pass, name, gender, email, hp, hobbylist,intro, mdate,
				email1, email2, hp1, hp2, hp3;
	String[] hobby;
	int join_status, rno;	
	
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getJoin_status() {
		return join_status;
	}
	public void setJoin_status(int join_status) {
		this.join_status = join_status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {  //DAO��ü���� SQL insert ����� ȣ���ϴ� �޼ҵ� 
		if(email1 != null) { //������ �Է��� �޴� ���
			email = email1 +"@"+email2;
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHp() {
		if(hp1 != null) {
			hp = hp1 +"-"+hp2+"-"+hp3;
		}
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getHobbylist() {
		//hobby �迭�� ����ִ� �����͵��� �޸�(,)�� �����Ͽ� ���ڿ��� ��ȯ	
		if(hobby != null) {
			hobbylist = String.join(",", hobby);
		}
		return hobbylist;
	}
	public void setHobbylist(String hobbylist) {
		this.hobbylist = hobbylist;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	
	
}
