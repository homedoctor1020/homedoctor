package com.dw.model;

public class Yuyue {
	private int id;//ԤԼ��id
	private String name;//�û�����
	private String sex;//�Ա�
	private String age;//����
	private long phone;//�绰����
	private String jztime;//����ʱ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getJztime() {
		return jztime;
	}
	public void setJztime(String jztime) {
		this.jztime = jztime;
	}
	public Yuyue(String name, String sex, String age, long phone, String jztime) {
		
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.jztime = jztime;
	}
	public Yuyue() {
	
	}
	public Yuyue(int id, String name, String sex, String age, long phone,
			String jztime) {
		
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.jztime = jztime;
	}
	
	
	

}
