package com.dw.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dw.model.Token;




public class CommonUtil {

    
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    private final static String APPID="wx0477eac4f9bf6e06";
    private final static String APPSECRET="5ad132bd08594c99fde584666fd3a60d";
    
    //保存当前微信公众号的access_token
    private static Token token;

    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;

    /**
     * 发送https请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }
    /**
     * 请求access_token
     * @return
     */
public  static Token getToken(){
    Token token = new Token();
    // 发起GET请求获取凭证
    JSONObject jsonObject = httpsRequest(token_url, "GET", null);
    if (null != jsonObject) {
        try {
            token = new Token();
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpiresin(jsonObject.getInt("expires_in"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            String time = format.format(new Date());
            token.setTime(time);
        } catch (JSONException e) {
            token = null;
            // 获取token失败
            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
        }
    }
    return token;
  
    
}
    
    /*public static void main(String[] args) throws ParseException {
	Token t=new Token();
	t=getToken();
	System.out.println(t.toString());
	//isAccess_tokenValid(t.getTime(), "2017-08-08 15:45:55");
    }*/


/**
 * 获取当前的access_token
 * @return
 * @throws ParseException
 */
    public static  String getAccessToken() throws ParseException{
	SimpleDateFormat formate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	String current_time=formate.format(new Date());
	if(token==null||StringUtil.isEmpty(token.getAccess_token())||isAccess_tokenValid(token.getTime(), current_time, formate)){
	    token=getToken();
	    return token.getAccess_token();
	}else{
	    return token.getAccess_token();
	}
    }
    //进行时间有效性的判断
    public static Boolean isAccess_tokenValid(String token_time,String current_time,SimpleDateFormat formate) throws ParseException{
	
	    Date token_date = formate.parse(token_time); 
	    Date current_date = formate.parse(current_time);
	    System.out.println(token_date);
	    System.out.println(current_date);
	    long result=0;
	    if (token_date == null || current_date == null) {
	            return true;
	        }
	       
	            // 日期相减获取日期差X(单位:毫秒)
	            long millisecond = current_date.getTime() - token_date.getTime();
	            long diff_time=millisecond/1000;
	            System.out.println("距离上次获取token的时间差="+millisecond / (1000));
	            return diff_time>7000;
    }
}
