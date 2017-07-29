package com.dw.model;

public class Yuyue {
	private int id;//预约单id
	private String name;//用户名字
	private String sex;//性别
	private String age;//年龄
	private long phone;//电话号码
	private String jztime;//就诊时间
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
