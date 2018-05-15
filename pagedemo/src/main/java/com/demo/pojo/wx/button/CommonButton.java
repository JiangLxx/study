package com.demo.pojo.wx.button;

import com.demo.pojo.wx.button.base.Button;

/**
 * <p>微信子菜单项:没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单。</p>
 * @author jianglan @date 2018-05-15<br>
 * @version 1.0<br>
 */
public class CommonButton extends Button {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 2145128861189154667L;
	/** 菜单key值 **/
	private String key; // click等点击类型必须    菜单KEY值，用于消息接口推送，不超过128字节
	/** 响应类型 **/
	private String type; // 必须    菜单的响应动作类型

	/**
	 * <p>获取菜单key值</p>
	 * @return 菜单key值<br>
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <p>设置菜单key值</p>
	 * @param key 菜单key值<br>
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * <p>获取响应类型</p>
	 * @return 响应类型<br>
	 */
	public String getType() {
		return type;
	}

	/**
	 * <p>设置响应类型</p>
	 * @param type 响应类型<br>
	 */
	public void setType(String type) {
		this.type = type;
	}
}
