package com.demo.pojo.wx.event;

import com.demo.pojo.wx.event.base.BaseEvent;

/**
 * <p>微信请求事件之扫描带参数二维码事件</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class QRCodeEvent extends BaseEvent {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -6213132277870649136L;
    /** 二维码的ticket(用于换取二维码图片) **/
    private String Ticket;
    /** 事件KEY值 **/
    private String EventKey;
	
    /**
	 * <p>获取二维码的ticket</p>
	 * @return 二维码的ticket<br>
	 */
    public String getTicket() {
		return Ticket;
	}
	
    /**
	 * <p>设置二维码的ticket</p>
	 * @param ticket 二维码的ticket<br>
	 */
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
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
