package com.demo.quartz;

import com.demo.utils.CommHelper;
import org.quartz.JobExecutionContext;
import com.alibaba.fastjson.JSONObject;
import com.demo.quartz.base.BaseQuartz;
import org.quartz.JobExecutionException;
import com.demo.utils.wx.test.TokenTest;
import com.demo.utils.wx.constant.Constants;

/**
 * <p>获取微信Access_Token定时器实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public class GetWxAccessTokenQuartz extends BaseQuartz {
	/**
	 * <p>执行定时任务</p>
	 */
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 拼装请求链接
		String url = Constants.WX_ACCESS_TOKEN_URL + "?grant_type=" + Constants.WX_ACCESS_TOKEN_GRANT_TYPE 
				+ "&appid=" + Constants.WX_ACCESS_TOKEN_APPID + "&secret=" + Constants.WX_ACCESS_TOKEN_SECRET;
		JSONObject rtn = TokenTest.httpsRequest(url, "GET", null); 
		if(CommHelper.isNotNull(rtn)) {
			if(rtn.containsKey("expires_in") && "7200".equalsIgnoreCase(rtn.getString("expires_in"))) {
				getStringRedisLogicBean().set(Constants.WX_ACCESS_TOKEN, rtn.getString("access_token"));
			}
		}
	}

}
