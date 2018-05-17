package com.demo.utils.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.demo.utils.CommHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.utils.wx.constant.Constants;
import com.demo.pojo.wx.authorization.SNSUserInfo;
import com.demo.pojo.wx.authorization.WeixinOauth2Token;

/**
 * <p>微信-获取网页授权凭证工具类</p>
 * @author jianglan @date 2018-05-17<br>
 * @version 1.0<br>
 */
public final class AdvancedUtil {
	/** 获取日志实例 **/
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	
	/**
	 * <p>获取网页授权凭证</p>
	 * @param appId 公众账号的唯一标示<br>
	 * @param appSecret 公众账号的秘钥<br>
	 * @param code 授权code<br>
	 * @return 授权结果<br>
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token rtn = null; JSONObject result = null;
		try {
			// 拼装请求链接
			String url = Constants.WX_GET_AUTHORIZATION_TOKEN.replace("APPID", appId).replaceAll("SECRET", appSecret).replaceAll("CODE", code);
			// 开始请求
			result = WeiXinUtil.httpsRequest(url, "GET", null);
			if(CommHelper.isNotNull(result)) {
				rtn = new WeixinOauth2Token();
				rtn.setScope(result.getString("scope"));
				rtn.setOpenid(result.getString("openid"));
				rtn.setExpires_in(result.getIntValue("expires_in"));
				rtn.setAccess_token(result.getString("access_token"));
				rtn.setRefresh_token(result.getString("refresh_token"));
			}
		} catch (Exception ex) {
			rtn = null; ex.printStackTrace(); 
			log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", result.getIntValue("errcode"), result.getString("errmsg"));
		}
		return rtn;
	}
	
	/**
     * <p>通过网页授权获取用户信息</p>
     * @param accessToken 网页授权接口调用凭证<br>
     * @param openId 用户标识<br>
     * @return SNSUserInfo 用户信息<br>
     */
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null; JSONObject jsonObject = null;
        try {
        	// 拼接请求地址
            String requestUrl = Constants.WX_GET_AUTHORIZATION_USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
            // 通过网页授权获取用户信息
            jsonObject = WeiXinUtil.httpsRequest(requestUrl, "GET", null);
        	if (CommHelper.isNotNull(jsonObject)) {
        		snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.parseArray(jsonObject.getString("privilege"), String.class));
        	}
        } catch (Exception ex) {
            snsUserInfo = null;  ex.printStackTrace(); 
            log.error("获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
        }
        return snsUserInfo;
    }
}
