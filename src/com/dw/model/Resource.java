package com.dw.model;

public class Resource {
 private String openid;
 private String originName;
 private String newfileName;
 private String size;
 private int  type;




public Resource(String openid, String originName, String newfileName,
	String size, int type, String createtime) {
    super();
    this.openid = openid;
    this.originName = originName;
    this.newfileName = newfileName;
    this.size = size;
    this.type = type;
    this.createtime = createtime;
}

public Resource() {
    // TODO Auto-generated constructor stub
}

@Override
public String toString() {
    return "Resource [openid=" + openid + ", originName=" + originName
	    + ", newfileName=" + newfileName + ", size=" + size + ", type="
	    + type + ", createtime=" + createtime + "]";
}

public void setOpenid(String openid) {
    this.openid = openid;
}

public String getOriginName() {
    return originName;
}
public void setOriginName(String originName) {
    this.originName = originName;
}
public String getNewfileName() {
    return newfileName;
}
public void setNewfileName(String newfileName) {
    this.newfileName = newfileName;
}
public int getType() {
    return type;
}
public String getSize() {
    return size;
}
public void setSize(String size) {
    this.size = size;
}
public void setType(int type) {
    this.type = type;
}
private String createtime;
public String getOpenid() {
    return openid;
}
public String getCreatetime() {
    return createtime;
}
public void setCreatetime(String createtime) {
    this.createtime = createtime;
}
}
