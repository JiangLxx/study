package com.demo.quartz;

import com.demo.utils.CommHelper;
import com.demo.utils.wx.WeiXinUtil;
import org.quartz.JobExecutionContext;
import com.demo.quartz.base.BaseQuartz;
import com.demo.pojo.wx.model.TokenResp;
import org.quartz.JobExecutionException;
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
		TokenResp rtn = WeiXinUtil.getAccessToken(Constants.WX_ACCESS_TOKEN_APPID, Constants.WX_ACCESS_TOKEN_SECRET);
		if(CommHelper.isNotNull(rtn)) {
			if(7200 == rtn.getExpires_in()) {
				getStringRedisLogicBean().set(Constants.WX_ACCESS_TOKEN, rtn.getAccess_token());
				System.out.println("当前Access_Token为：" + rtn.getAccess_token());
			}
		}
	}

}
