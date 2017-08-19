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
    
    //���浱ǰ΢�Ź��ںŵ�access_token
    private static Token token;

    // ƾ֤��ȡ��GET��
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;

    /**
     * ����https����
     * 
     * @param requestUrl �����ַ
     * @param requestMethod ����ʽ��GET��POST��
     * @param outputStr �ύ������
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // ������SSLContext�����еõ�SSLSocketFactory����
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // ��������ʽ��GET/POST��
            conn.setRequestMethod(requestMethod);

            // ��outputStr��Ϊnullʱ�������д����
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // ע������ʽ
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // ����������ȡ��������
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // �ͷ���Դ
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("���ӳ�ʱ��{}", ce);
        } catch (Exception e) {
            log.error("https�����쳣��{}", e);
        }
        return jsonObject;
    }
    /**
     * ����access_token
     * @return
     */
public  static Token getToken(){
    Token token = new Token();
    // ����GET�����ȡƾ֤
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
            // ��ȡtokenʧ��
            log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
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
 * ��ȡ��ǰ��access_token
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
    //����ʱ����Ч�Ե��ж�
    public static Boolean isAccess_tokenValid(String token_time,String current_time,SimpleDateFormat formate) throws ParseException{
	
	    Date token_date = formate.parse(token_time); 
	    Date current_date = formate.parse(current_time);
	    System.out.println(token_date);
	    System.out.println(current_date);
	    long result=0;
	    if (token_date == null || current_date == null) {
	            return true;
	        }
	       
	            // ���������ȡ���ڲ�X(��λ:����)
	            long millisecond = current_date.getTime() - token_date.getTime();
	            long diff_time=millisecond/1000;
	            System.out.println("�����ϴλ�ȡtoken��ʱ���="+millisecond / (1000));
	            return diff_time>7000;
    }
}
