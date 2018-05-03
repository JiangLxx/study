package com.demo.pojo.wx.resp;

import java.util.List;
import com.demo.pojo.wx.resp.model.Article;
import com.demo.pojo.wx.resp.base.BaseRespMessage;

/**
 * <p>微信响应消息之回复图文消息</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class NewsRespMessage extends BaseRespMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 6702678660307853390L;
	/** 图文消息个数 **/
    private int ArticleCount; //限制为10条以内
    /** 多条图文消息信息 **/
    private List<Article> Articles; //默认第一个item为大图
	
    /**
   	 * <p>获取图文消息个数</p>
   	 * @return 图文消息个数<br>
   	 */
    public int getArticleCount() {
		return ArticleCount;
	}
	
    /**
	 * <p>设置图文消息个数</p>
	 * @param articleCount 图文消息个数<br>
	 */
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	
    /**
   	 * <p>获取多条图文消息信息</p>
   	 * @return 多条图文消息信息<br>
   	 */
	public List<Article> getArticles() {
		return Articles;
	}
	
	/**
	 * <p>设置多条图文消息信息</p>
	 * @param articles 多条图文消息信息<br>
	 */
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
