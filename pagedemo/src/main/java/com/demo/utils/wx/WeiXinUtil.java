package com.demo.utils.wx;

import java.net.URL;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Formatter;
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
import com.idiot.spg.ContextHelper;
import com.idiot.utils.base.StringHelper;

import java.security.MessageDigest;
import javax.net.ssl.SSLSocketFactory;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.wx.model.TokenResp;
import javax.net.ssl.HttpsURLConnection;
import com.demo.pojo.wx.button.base.Menu;
import com.alibaba.fastjson.JSONException;
import java.io.UnsupportedEncodingException;
import com.demo.utils.wx.constant.Constants;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import com.frame.redis.IObjectRedisHelperService;
import com.frame.redis.IStringRedisHelperService;
import com.demo.pojo.wx.authorization.WeixinUserInfo;

/**
 * <p>获取微信token测试类</p>
 * @author jianglan @date 2018-05-07<br>
 * @version 1.0<br>
 */
public final class WeiXinUtil {
	/** 获取日志实例 **/
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	/** 字符缓存实例 **/
	private static IStringRedisHelperService stringRedisLogicBean = null;
	/** 本站缓存实例 **/
	private static IObjectRedisHelperService objectRedisLogicBean = null;
	
	/**
	 * <p>获取公有缓存操作工具实例</p>
	 * @return 公有缓存操作工具实例<br>
	 */
	public static IStringRedisHelperService getStringRedisLogicBean() {
		if (CommHelper.isNull(stringRedisLogicBean)) {
			stringRedisLogicBean = ContextHelper.getBean(IStringRedisHelperService.class);
		}
		return stringRedisLogicBean;
	}
	
	/**
	 * <p>获取私有缓存操作工具实例</p>
	 * @return 公有缓存操作工具实例<br>
	 */
	public static IObjectRedisHelperService getObjectRedisLogicBean() {
		if (CommHelper.isNull(objectRedisLogicBean)) {
			objectRedisLogicBean = ContextHelper.getBean(IObjectRedisHelperService.class);
		}
		return objectRedisLogicBean;
	}
	
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
        if (CommHelper.isNotNull(jsonObject)) {
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
    
    /**
     * <p>获取微信配置信息</p>
     * @param request 网络请求<br>
     * @return 返回值含义<br>
     */
    public static Map<String, Object> getWxConfig(HttpServletRequest request){
    	Map<String, Object> rtn = new HashMap<String, Object>();
    	String access_token = "", jsapi_ticket = "", signature = "", url = "";
    	String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
    	String requestUrl = request.getRequestURL().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
    	
        // 获取access_token
        access_token = getStringRedisLogicBean().get(Constants.WX_ACCESS_TOKEN);
        if(StringHelper.isEmpty(access_token)) {
        	url = Constants.WX_ACCESS_TOKEN_URL.replace("APPID", Constants.WX_ACCESS_TOKEN_APPID).replace("APPSECRET", Constants.WX_ACCESS_TOKEN_SECRET);
        	JSONObject atObj = httpsRequest(url, "GET", null);
        	if(CommHelper.isNotNull(atObj)) {
        		//要注意，access_token需要缓存
                access_token = atObj.getString("access_token"); getStringRedisLogicBean().set(Constants.WX_ACCESS_TOKEN, access_token);
        	}
        } 
        // 获取jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
        else {
        	url = Constants.WX_GET_JSAPI_TICKET.replace("ACCESS_TOKEN", access_token);
        	JSONObject jsapiTicket = httpsRequest(url, "GET", null);
            if (CommHelper.isNotNull(jsapiTicket)) {
                jsapi_ticket = jsapiTicket.getString("ticket");
            }
        }
        
    	// 注意这里参数名必须全部小写，且必须有序
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset(); crypt.update(sign.getBytes("UTF-8"));
	        signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		rtn.put("nonceStr", nonceStr);
		rtn.put("timestamp", timestamp);
		rtn.put("signature", signature);
		rtn.put("appId", Constants.WX_ACCESS_TOKEN_APPID);
		
		return rtn;
    }
    
    /**
     * <p>字符串加密辅助方法</p>
     * @param hash<br>
     * @return 说明返回值含义<br>
     * @throws 说明发生此异常的条件<br>
      */
     private static String byteToHex(final byte[] hash) {
         Formatter formatter = new Formatter();
         for (byte b : hash) {
             formatter.format("%02x", b);
         }
         String result = formatter.toString();
         formatter.close();
         
         return result;
     }
	
	/** 测试 **/
	public static void main(String[] args){
		WeiXinUtil.getAccessToken(Constants.WX_ACCESS_TOKEN_APPID, Constants.WX_ACCESS_TOKEN_SECRET);
		
//		TokenResp tokenResp = getAccessToken(Constants.WX_ACCESS_TOKEN_APPID, Constants.WX_ACCESS_TOKEN_SECRET);
//		WeixinUserInfo userInfo = getUserInfo(tokenResp.getAccess_token(), "oY1iZ1dMIQQDO1jB8xUDbHYRs1tI");
//		System.out.println(JSON.toJSONString(userInfo));
	}
}
