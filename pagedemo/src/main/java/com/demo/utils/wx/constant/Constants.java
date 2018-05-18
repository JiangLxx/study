package com.demo.utils.wx.constant;

/**
 * <p>常量配置定义类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public final class Constants {
	/** 微信Access_Token名称 **/
	public final static String WX_ACCESS_TOKEN = "Wx_Access_Token";
	/** 第三方用户唯一凭证（正式） **/
//	public final static String WX_ACCESS_TOKEN_APPID = "wxb7f4a377c71a5f1a"; // 正式暂无权限，不能自定义菜单
	/** 第三方用户唯一凭证（测式） **/
	public final static String WX_ACCESS_TOKEN_APPID = "wx687e19d3cb8cf9de";
	/** 获取access_token填写client_credential **/
	public final static String WX_ACCESS_TOKEN_GRANT_TYPE = "client_credential";
	/** 第三方用户唯一凭证密钥（正式） **/
//	public final static String WX_ACCESS_TOKEN_SECRET = "54a0da5352be54cff4f1505ab6e46c94";
	/** 第三方用户唯一凭证密钥（测） **/
	public final static String WX_ACCESS_TOKEN_SECRET = "8844e90fa3ec884aa51eb90533fbdcf9";
    /** 菜单搜索 GET 限100次/天 **/
    public final static String MENU_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    /** 菜单删除 GET 限100次/天 **/
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/** 菜单创建 POST 限100次/天 **/
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /** http GET方式请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket） **/
    public final static String WX_GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    /** 通过网页授权获取用户信息(GET) **/
	public final static String WX_GET_AUTHORIZATION_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    /** 获取用户信息请求链接 GET **/
    public final static String WX_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /** 获取access_token请求链接(GET 限200次/天) **/
	public final static String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 通过code换取网页授权access_token(GET) **/
	public final static String WX_GET_AUTHORIZATION_TOKEN="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
}