package com.demo.utils;

import java.util.List;
import java.io.IOException;
import java.io.FileOutputStream;
import com.google.common.collect.Lists;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>POI操作工具类</p>
 * @author JL @date 2018-02-11<br>
 * @version 1.0<br>
 */
public final class POIHelper {
	
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private POIHelper() {}
	
	/**
	 * <p>生成excel</p>
	 * @param object 数据对象<br>
	 * @param contents 格式化内容<br>
	 * @param path 存储路径<br>
	 * @throws IOException 
	 */
	public static void createExcel(EntityP object, List<List<String>> contents, String path) throws IOException {
		// 获取主体内容行数，列数
		int x_line = contents.size() + 1; int y_line = object.getTitles().size();
		int columnWidth = 255*20; int heightInPoints = 30;
		List<Entity> entities = object.getEntities(); List<XSSFRow> titleAcontent = Lists.newArrayList(); 
		// 判断对象是否为空
		if(CommHelper.isNotNull(object)) {
			// 创建工作薄
			XSSFWorkbook wb = new XSSFWorkbook();
			// 工作表
			XSSFSheet sheet = wb.createSheet(object.getWb_title());
			//标头行，代表第一行
		    XSSFRow header = sheet.createRow(0); titleAcontent.add(header);
		    List<String> titles = object.getTitles();
		    // 判断标题集合是否为空
		    if(CommHelper.isNotEmptyList(titles)) {
		    	//录入第一行标题数据
		    	for (int i = 0; i < titles.size(); i++) {
				    header.createCell(i).setCellValue(titles.get(i));
				}
		    }
		    // 判断数据集合是否为空
		    if(CommHelper.isNotEmptyList(contents)) {
		    	XSSFRow content = null; 
		    	// 填充主体内容
		    	for (int i = 1; i < contents.size() + 1; i++) {
					if(CommHelper.isNotEmptyList(contents.get(i-1))) {
			    		content = sheet.createRow(i); 
			    		for (int j = 0; j < contents.get(i-1).size(); j++) {
			    			content.createCell(j).setCellValue(contents.get(i-1).get(j));
						}
			    		titleAcontent.add(content);
					}
				}
		    }
		    // 设置主体内容列宽
		    for(int i=0;i<header.getPhysicalNumberOfCells();i++){
		        //POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
		        //因此我们要想让列能够盛的下20个字符的话，就需要用255*20
		        sheet.setColumnWidth(i, columnWidth);
		    }
		    // 设置主体内容行高,30像素
		    for (XSSFRow tc : titleAcontent) {
				tc.setHeightInPoints(heightInPoints);
			}
		    XSSFRow sub = sheet.createRow(x_line + 1);
		    sub.createCell(0).setCellValue(object.getAccordingNm());
		    sub.createCell(1).setCellValue(object.getAccording());
		    sub.createCell(2).setCellValue(object.getMethodNm());
		    sub.createCell(3).setCellValue(object.getMethod());
		    sub = sheet.createRow(x_line + 2);
		    sub.createCell(0).setCellValue(object.getCompany_nameNm());
		    sub.createCell(1).setCellValue(object.getCompany_name());
		    sub.createCell(2).setCellValue(object.getCall_phoneNm());
		    sub.createCell(3).setCellValue(object.getCall_phone());
		    // 设置副体内容列宽
		    for(int i=0;i<sub.getPhysicalNumberOfCells();i++){
		        //POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
		        //因此我们要想让列能够盛的下20个字符的话，就需要用255*20
		        sheet.setColumnWidth(i, (columnWidth*x_line)/4);
		    }
		    // 设置副体内容行高,30像素
		    sub.setHeightInPoints(heightInPoints);
		    
		    
		    FileOutputStream fos= new FileOutputStream(path);
		    //向指定文件写入内容
		    wb.write(fos); fos.close();
		}
	}
	
	/**
	 * <p>获取合法数据</p>
	 * @param entities
	 * @return
	 */
	public static List<List<String>> getContent (List<Entity> entities){
		List<String> content = null;
		List<List<String>> contents = Lists.newArrayList();
		for (Entity entity : entities) {
			content = Lists.newArrayList();
			content.add(entity.getCode()); content.add(entity.getName());
			content.add(entity.getNus()); content.add(entity.getRe());
			content.add(entity.getNu()); content.add(entity.getSample_name());
			content.add(entity.getAddre()); content.add(entity.getDan());
			content.add(entity.getTy()); content.add(entity.getTel()); contents.add(content);
		}
		return contents;
	}
	
	public static void main(String[] args) throws IOException {
		List<Entity> entities = Lists.newArrayList(); Entity entity = null;
		for (int i = 0; i < 10; i++) {
			entity = new Entity(); entity.setCode("CODE" + (i+1));
			entity.setName("NAME" + (i+1)); entity.setNus("NUS" + (i+1));
			entity.setRe("RE" + (i+1)); entity.setNu("NU" + (i+1));
			entity.setSample_name("SAMPLE_NAME" + (i+1)); entity.setAddre("ADDRE" + (i+1));
			entity.setDan("DAN" + (i+1)); entity.setTy("TY" + (i+1)); entity.setTel("TEL" + (i+1));
			entities.add(entity);
		}
		EntityP entityP = new EntityP(); List<String> titles = Lists.newArrayList();
		titles.add("抽样编号");titles.add("样品名称");titles.add("抽样数量");
		titles.add("样品来源");titles.add("抽样基数");titles.add("被抽样对象名称");
		titles.add("详细地址");titles.add("生产单位类别");titles.add("种植方式");titles.add("联系电话");
		entityP.setAccording("According"); entityP.setCall_phone("Call_phone"); 
		entityP.setCompany_name("Company_name"); entityP.setEntities(entities);
		entityP.setMethod("Method"); entityP.setSign_name("Sign_name");
		entityP.setTitles(titles); entityP.setWb_title("Wb_title");
		entityP.setAccordingNm("检测任务依据"); entityP.setCall_phoneNm("联系电话");
		entityP.setCompany_nameNm("抽样单位信息"); entityP.setMethodNm("抽样方法"); 
		entityP.setSign_nameNm("抽样人及被抽样单位（人）仔细阅读下面文字，确认后签字："); 
		entityP.setOption1("我认真负责地填写（提供）了以上内容，确认填写内容及所抽样品的真实、可靠。 经办人：_________、_________ "
				+ "年____月____日（签字或盖公章）");
		
		entityP.setOption2("本次抽样已按照要求执行完毕，样品经双方人员共同确认，并做记录如上。 抽样人：_________、_________ "
				+ "年____月____日（公章）");
		entityP.setDescription("备注");
		entityP.setSubTitle("苍溪县农产品质量安全检验检测站制");
		createExcel(entityP, getContent(entityP.getEntities()) , "d:/cs.xlsx");
	}
}
