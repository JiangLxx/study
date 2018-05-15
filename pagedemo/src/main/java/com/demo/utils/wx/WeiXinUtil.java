package com.demo.utils.wx;

import java.net.URL;
import org.slf4j.Logger;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import org.slf4j.LoggerFactory;
import javax.net.ssl.SSLContext;
import java.net.ConnectException;
import java.io.InputStreamReader;
import com.alibaba.fastjson.JSON;
import com.demo.utils.CommHelper;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLSocketFactory;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.wx.model.TokenResp;
import javax.net.ssl.HttpsURLConnection;
import com.demo.pojo.wx.button.base.Menu;
import com.alibaba.fastjson.JSONException;
import com.demo.utils.wx.constant.Constants;

/**
 * <p>获取微信token测试类</p>
 * @author jianglan @date 2018-05-07<br>
 * @version 1.0<br>
 */
public class WeiXinUtil {
	/** 获取日志实例 **/
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	
	 /**
     * <p>创建菜单</p>
     * @param menu 菜单实例 <br>
     * @param accessToken 有效的access_token<br>
     * @return 0表示成功，其他值表示失败<br>
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }
    
    /**
     * 获取access_token
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static TokenResp getAccessToken(String appid, String appsecret) {
        String requestUrl = Constants.WX_ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject rtn = httpsRequest(requestUrl, "GET", null); TokenResp accessToken = null;
        // 如果请求成功
        if (CommHelper.isNotNull(rtn)) {
            try {
                accessToken = JSON.toJavaObject(rtn, TokenResp.class);
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", rtn.getIntValue("errcode"), rtn.getString("errmsg"));
            }
        }
        return accessToken;
    }
	
	/**
	 * <p>发送https请求</p>
     * @param requestUrl 请求地址<br>
     * @param requestMethod 请求方式（GET、POST）<br>
     * @param outputStr 提交的数据<br>
     * @return JSONObject 返回数据<br>
     */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject rtnObject = null;
        try {
        	// 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
    		//建立连接
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            
            if ("GET".equalsIgnoreCase(requestMethod)) {
            	conn.connect();
            }
            
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            // 取得输入流
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //读取响应内容
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
            System.out.println(buffer);
            rtnObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException e) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
        return rtnObject;
	}
	
	/** 测试 **/
	public static void main(String[] args){
		// 第三方用户唯一凭证
		String appid = "wxb7f4a377c71a5f1a";
		// 第三方用户唯一凭证密钥，即appsecret
		String secret = "54a0da5352be54cff4f1505ab6e46c94";
		// 获取access_token填写client_credential
		String grant_type = "client_credential";
		// 拼装请求链接
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret;
		System.err.println(tokenUrl);
		
		JSONObject rtn = httpsRequest(tokenUrl, "GET", null); TokenResp tokenResp = null;
		//失败 {"errcode":40125,"errmsg":"invalid appsecret, view more at http:\/\/t.cn\/RAEkdVq hint: [EXUzia07273064]"}
		//成功 {"access_token":"9_69XQCsaUsZT9gSHBs7cyWmA-vYMIx8npHCrK3qwMfNtOI3oleQrf5jq44dtIgJ0HiV8zdC-ys9C9OYlJVku-ZSp4S8PzA0sp88ZnkuY6Gpgh73riwULsZ0UaLFmqrdoItd4uiZlAXEyypVh8PTCgAIATXG","expires_in":7200}
		if(CommHelper.isNotNull(rtn)) {
			if(rtn.containsKey("expires_in") && "7200".equalsIgnoreCase(rtn.getString("expires_in"))) {
				tokenResp = JSON.toJavaObject(rtn, TokenResp.class);
			}
		}
		System.out.println("access__token : " + tokenResp.getAccess_token());
		System.out.println("expires_in : " + tokenResp.getExpires_in());
	}
}
