package com.demo.pojo.wx.button.base;

import java.io.Serializable;

/**
 * <p>微信菜单项基类</p>
 * @author jianglan @date 2018-05-15<br>
 * @version 1.0<br>
 */
public class Button implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -145847889369126449L;
	/** 菜单名称 **/
	private String name; // 不超过16个字节，子菜单不超过40个字节
	
	/**
	 * <p>获取菜单名称</p>
	 * @return 菜单名称<br>
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>设置菜单名称</p>
	 * @param name 菜单名称<br>
	 */
	public void setName(String name) {
		this.name = name;
	}
}
