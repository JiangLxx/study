package com.demo.pojo.wx.authorization;

import java.util.List;
import com.demo.pojo.wx.authorization.base.BaseUserInfo;

/**
 * <p>微信-通过网页授权获取的用户信息类</p>
 * @author jianglan @date 2018-05-17<br>
 * @version 1.0<br>
 */
public class SNSUserInfo extends BaseUserInfo {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -5341296029027155182L;
    /** 用户特权信息 **/
    private List<String> privilegeList;
	
	/**
	 * <p>获取用户特权信息</p>
	 * @return 用户特权信息<br>
	 */
	public List<String> getPrivilegeList() {
		return privilegeList;
	}
	
	/**
	 * <p>设置用户特权信息</p>
	 * @param privilegeList 用户特权信息<br>
	 */
	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}
}
