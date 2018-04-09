package com.demo.utils;

import java.io.File;
import java.util.Map;
import java.io.Writer;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import freemarker.template.Template;
import com.google.common.collect.Lists;
import com.idiot.utils.base.StringHelper;
import freemarker.template.Configuration;

/**
 * <p>freemarker模板生成word测试类</p>
 * @author JL @date 2018-02-26<br>
 */
@SuppressWarnings("deprecation")
public class WordTest {
	/** freemarker配置类 **/
    private Configuration configuration = null;

    /**
     * <p>无参构造函数</p>
     */
	public WordTest() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

	/**
	 * <p>生成word</p>
	 * @param data 数据<br>
	 * @param orgPath 模板所在目录<br>
	 * @param templetNm 模板名称<br>
	 * @param tarPath 目标文件存放路径<br>
	 */
    public void createWord(Map<String, Object> data, String orgPath, String templetNm, String tarPath) {
        try {
        	// 判断数据是否为空
        	if(CommHelper.isEmptyMap(data) || StringHelper.isEmpty(orgPath) || 
        			StringHelper.isEmpty(templetNm) || StringHelper.isEmpty(tarPath)) { return; }
        	configuration.setClassForTemplateLoading(this.getClass(), orgPath); // FTL文件目录所存在的位置
        	Template template = configuration.getTemplate(templetNm); File outFile = new File(tarPath);
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        	template.process(data, out);  out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>测试</p>
     */
    public static void main(String[] args) {
        WordTest test = new WordTest();
        //构造测试数据
        List<Entity> entities = Lists.newArrayList();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Entity entity = null;
		for (int i = 0; i < 7; i++) {
			entity = new Entity(); entity.setCode("CODE" + (i+1));
			entity.setName("NAME" + (i+1)); entity.setNus("NUS" + (i+1));
			entity.setRe("RE" + (i+1)); entity.setNu("NU" + (i+1));
			entity.setSample_name("SAMPLE_NAME" + (i+1)); entity.setAddre("ADDRE" + (i+1));
			entity.setDan("DAN" + (i+1)); entity.setTy("TY" + (i+1)); entity.setTel("TEL" + (i+1));
			entities.add(entity);
		}
    	dataMap.put("according", "hehe1");
    	dataMap.put("method", "hehe2");
    	dataMap.put("company_name", "hehe3");
    	dataMap.put("call_phone", "hehe4");
    	dataMap.put("sign_name", "hehe5");
    	dataMap.put("dataList", entities);
        // 执行方法
    	long currentTime1 = System.currentTimeMillis();
        test.createWord(dataMap, "/", "cs.ftl", "D:/cs2.doc");
        long currentTime2 = System.currentTimeMillis();
        System.out.println("花费时间：" + (currentTime2-currentTime1));
    }
}
