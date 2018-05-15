package com.demo.utils.wx.constant;

/**
 * <p>常量配置定义类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public final class Constants {
	/** 微信Access_Token名称 **/
	public final static String WX_ACCESS_TOKEN = "Wx_Access_Token";
	/** 第三方用户唯一凭证 **/
	public final static String WX_ACCESS_TOKEN_APPID = "wxb7f4a377c71a5f1a";
	/** 获取access_token填写client_credential **/
	public final static String WX_ACCESS_TOKEN_GRANT_TYPE = "client_credential";
	/** 第三方用户唯一凭证密钥 **/
	public final static String WX_ACCESS_TOKEN_SECRET = "54a0da5352be54cff4f1505ab6e46c94";
	/** 菜单创建 POST 限100次/天 **/
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /** 获取access_token请求链接(GET 限200次/天) **/
	public final static String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
}