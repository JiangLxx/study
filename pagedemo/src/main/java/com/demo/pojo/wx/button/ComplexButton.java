package com.demo.pojo.wx.button;

import com.demo.pojo.wx.button.base.Button;

/**
 * <p>微信父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组</p>
 * @author jianglan @date 2018-05-15<br>
 * @version 1.0<br>
 */
public class ComplexButton extends Button {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -675053988786387073L;
	/** 子菜单项集 **/
	private Button[] sub_button;
	
	/**
	 * <p>获取子菜单项集</p>
	 * @return 子菜单项集<br>
	 */
	public Button[] getSub_button() {
		return sub_button;
	}
	
	/**
	 * <p>设置子菜单项集</p>
	 * @param sub_button 子菜单项集<br>
	 */
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
