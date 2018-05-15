package com.demo.pojo.wx.button.base;

import java.io.Serializable;

/**
 * <p>微信菜单对象类</p>
 * @author jianglan @date 2018-05-15<br>
 * @version 1.0<br>
 */
public class Menu implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 2420498504756847445L;
	/** 菜单对象集 **/
	private Button[] button;
	
	/**
	 * <p>获取菜单对象集</p>
	 * @return 菜单对象集<br>
	 */
	public Button[] getButton() {
		return button;
	}
	
	/**
	 * <p>设置菜单对象集</p>
	 * @param button 菜单对象集<br>
	 */
	public void setButton(Button[] button) {
		this.button = button;
	}
}
