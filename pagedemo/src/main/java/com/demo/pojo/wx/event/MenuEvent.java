package com.demo.pojo.wx.event;

import com.demo.pojo.wx.event.base.BaseEvent;

/**
 * <p>微信请求事件之自定义菜单事件</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class MenuEvent extends BaseEvent {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 6015886478414871075L;
	/** 事件KEY值(与自定义菜单接口中KEY值对应) **/
    private String EventKey;
	
    /**
	 * <p>获取事件KEY值</p>
	 * @return 事件KEY值<br>
	 */
    public String getEventKey() {
		return EventKey;
	}
	
    /**
	 * <p>设置事件KEY值</p>
	 * @param eventKey 事件KEY值<br>
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
