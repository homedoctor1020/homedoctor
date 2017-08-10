package com.dw.model;

/**
 * Token类是微信接口凭证
 * access_token Token
 * expiresin有效时间
 * @author crossnote
 *
 */
public class Token {

private String access_token;
private int expiresin;
private String time;//时间戳

public String getTime() {
    return time;
}
public void setTime(String time) {
    this.time = time;
}
public String getAccess_token() {
    return access_token;
}
public void setAccess_token(String access_token) {
    this.access_token = access_token;
}
public int getExpiresin() {
    return expiresin;
}
public void setExpiresin(int expiresin) {
    this.expiresin = expiresin;
}
@Override
public String toString() {
    return "Token [access_token=" + access_token + ", expiresin=" + expiresin
	    + ", time=" + time + "]";
}

}

