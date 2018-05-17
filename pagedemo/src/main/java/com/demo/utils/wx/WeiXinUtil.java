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
import java.io.UnsupportedEncodingException;
import com.demo.pojo.wx.authorization.WeixinUserInfo;

/**
 * <p>获取微信token测试类</p>
 * @author jianglan @date 2018-05-07<br>
 * @version 1.0<br>
 */
public final class WeiXinUtil {
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
     * <p>获取access_token</p>
     * @param appid 凭证<br>
     * @param appsecret 密钥<br>
     * @return 微信Access_Token实体类 <br>
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
	
	/**
     * <p>URL编码（utf-8）</p>
     * @param source 参数<br>
     * @return 转码后<br>
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * <p>根据内容类型判断文件扩展名</p>
     * @param contentType 内容类型<br>
     * @return 文件扩展名<br>
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }
    
    /**
     * <p>获取用户信息</p>
     * @param accessToken 接口访问凭证<br>
     * @param openId 用户标识<br>
     * @return 用户信息<br>
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        // 拼接请求地址
        String requestUrl = Constants.WX_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null); WeixinUserInfo weixinUserInfo = null;
        
        if (CommHelper.isNotNull(jsonObject)) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getIntValue("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }
	
	/** 测试 **/
	public static void main(String[] args){
		TokenResp tokenResp = getAccessToken(Constants.WX_ACCESS_TOKEN_APPID, Constants.WX_ACCESS_TOKEN_SECRET);
		WeixinUserInfo userInfo = getUserInfo(tokenResp.getAccess_token(), "oY1iZ1dMIQQDO1jB8xUDbHYRs1tI");
		System.out.println(JSON.toJSONString(userInfo));
	}
}
