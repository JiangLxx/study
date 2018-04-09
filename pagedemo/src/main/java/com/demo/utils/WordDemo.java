package com.demo.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import java.io.ByteArrayOutputStream;
import com.lowagie.text.rtf.RtfWriter2;

public class WordDemo {
	public static void main(String[] args) {
		Document doc = new Document(PageSize.A4);
		try {
			/** 新建字节数组输出流 author:yyli Sep 15, 2010 */
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        /** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 author:yyli Sep 15, 2010 */
	        RtfWriter2.getInstance(doc, baos);
	        
		} catch (Exception e) {
			
		}
	}
}
