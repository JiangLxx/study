package com.demo.utils.wx;

import java.util.Map;
import java.io.Writer;
import java.util.List;
import java.util.HashMap;
import org.dom4j.Element;
import org.dom4j.Document;
import java.io.InputStream;
import org.dom4j.io.SAXReader;
import com.thoughtworks.xstream.XStream;
import com.demo.pojo.wx.resp.NewsRespMessage;
import com.demo.pojo.wx.resp.TextRespMessage;
import com.demo.pojo.wx.resp.ImageRespMessage;
import com.demo.pojo.wx.resp.MusicRespMessage;
import com.demo.pojo.wx.resp.VideoRespMessage;
import com.demo.pojo.wx.resp.VoiceRespMessage;
import com.demo.pojo.wx.resp.model.Article;
import javax.servlet.http.HttpServletRequest;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * <p>微信消息处理工具类</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class MessageUtil {
	// 请求消息类型
	/** 文本 **/
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    /** 链接 **/
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /** 图片 **/
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    /** 语音 **/
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /** 视频 **/
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    /** 事件推送 **/
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /** 地理位置 **/
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /** 小视频 **/
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    // 事件类型
    /** scan(用户已关注时的扫描带参数二维码)**/
    public static final String EVENT_TYPE_SCAN = "scan";
    /** CLICK(自定义菜单) **/
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /** LOCATION(上报地理位置) **/
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /** subscribe(订阅) **/
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /** unsubscribe(取消订阅) **/
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    // 响应消息类型
    /** 文本 **/
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    /** 图文 **/
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /** 图片 **/
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    /** 语音 **/
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    /** 视频 **/
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    /** 音乐 **/
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
	 * <p>解析微信发来的请求（XML）</p>
	 * @param request 传入请求<br>
	 * @return 请求参数<br>
	 * @throws Exception 异常<br>
	 */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close(); inputStream = null; return map;
    }

    /**
     * <p>扩展xstream使其支持CDATA</p>
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA["); writer.write(text); writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
	 * <p>文本消息对象转换成xml</p>
	 * @param textMessage 文本消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(TextRespMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
	 * <p>图片消息对象转换成xml</p>
	 * @param imageMessage 图片消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(ImageRespMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
	 * <p>语音消息对象转换成xml</p>
	 * @param voiceMessage 语音消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(VoiceRespMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
	 * <p>视频消息对象转换成xml</p>
	 * @param videoMessage 视频消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(VideoRespMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
	 * <p>音乐消息对象转换成xml</p>
	 * @param musicMessage 音乐消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(MusicRespMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
	 * <p>图文消息对象转换成xml</p>
	 * @param newsMessage 图文消息对象<br>
	 * @return xml<br>
	 */
    public static String messageToXml(NewsRespMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }
}
