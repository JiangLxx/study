//package com.demo.utils;
//
//import java.util.Map;
//import java.awt.Color;
//import java.util.List;
//import java.io.InputStream;
//import com.lowagie.text.Cell;
//import com.lowagie.text.Font;
//import com.lowagie.text.Table;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.Element;
//import com.lowagie.text.Document;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import com.lowagie.text.rtf.RtfWriter2;
//import com.lowagie.text.rtf.style.RtfFont;
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//
///** 学生课表导出word author:yyli Sep 15, 2010 */
//public class StudentCurriculumWordAction extends ActionSupport {
//
//    private static final long serialVersionUID = 2150958354251222076L;
//
//    @Override
//    public String execute() throws Exception {
//        // TODO Auto-generated method stub
//        return SUCCESS;
//    }
//
//    @SuppressWarnings( { "serial", "unchecked" })
//    public InputStream getWordFile() throws Exception {
//        Map<String, Object> session = ActionContext.getContext().getSession();
//        List<StudentCurriculum> leftList = (List<StudentCurriculum>) session.get("stuCurriculumleftList");
//        String[] stuCurriculumArray = (String[]) session .get("stuCurriculumrightArray");
//        float totalXf = 0;
//
//        /** 创建Document对象（word文档） author:yyli Sep 15, 2010 */
//        Document doc = new Document(PageSize.A4);
//        /** 新建字节数组输出流 author:yyli Sep 15, 2010 */
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        /** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 author:yyli Sep 15, 2010 */
//        RtfWriter2.getInstance(doc, baos);
//        doc.open();
//
//        /** 标题字体 author:yyli Sep 15, 2010 */
//        RtfFont titleFont = new RtfFont("仿宋_GB2312", 12, Font.NORMAL,
//                Color.BLACK);
//        /** 正文字体 author:yyli Sep 15, 2010 */
//        RtfFont contextFont = new RtfFont("仿宋_GB2312", 9, Font.NORMAL,
//                Color.BLACK);
//
//        /** 表格设置 author:yyli Sep 15, 2010 */
//        Table table = new Table(12, 16);
//        int[] withs = { 3, 9, 5, 4, 4, 3, 3, 14, 14, 14, 14, 14 };
//        /** 设置每列所占比例 author:yyli Sep 15, 2010 */
//        table.setWidths(withs);
//        /** 表格所占页面宽度 author:yyli Sep 15, 2010 */
//        table.setWidth(100);
//        /** 居中显示 author:yyli Sep 15, 2010 */
//        table.setAlignment(Element.ALIGN_CENTER);
//        /** 自动填满 author:yyli Sep 15, 2010 */
//        table.setAutoFillEmptyCells(true);
//
//        /** 第一行（标题） author:yyli Sep 15, 2010 */
//        String titleString = "东南大学 "
//                + (String) session.get("selectXn")
//                + "-"
//                + String.valueOf(Integer.parseInt((String) session
//                        .get("selectXn"))) + " 学年第 "
//                + (String) session.get("selectXq") + "学期 学生个人课表";
//        Paragraph title = new Paragraph(titleString);
//        // 设置标题格式对其方式
//        title.setAlignment(Element.ALIGN_CENTER);
//        title.setFont(titleFont);
//        doc.add(title);
//
//        /** 第二行（正文） author:yyli Sep 15, 2010 */
//        String contextString = "院系:" + (String) session.get("yxmc") + "    专业:"
//                + (String) session.get("zymc") + "    学号:"
//                + (String) session.get("xh") + "    一卡通号:"
//                + (String) session.get("userId") + "    姓名:"
//                + (String) session.get("stuName");
//        Paragraph context = new Paragraph(contextString);
//        // 正文格式对齐方式
//        context.setAlignment(Element.ALIGN_CENTER);
//        context.setFont(contextFont);
//        // 与上一段落（标题）的行距
//        context.setSpacingBefore(10);
//        // 设置第一行空的列数（缩进）
//        // context.setFirstLineIndent(20);
//        doc.add(context);
//
//        /** 第三行（表格） author:yyli Sep 15, 2010 */
//        Cell[] cellHeaders = new Cell[11];
//        cellHeaders[0] = new Cell(new Phrase("序号", contextFont));
//        cellHeaders[1] = new Cell(new Phrase("课程名称", contextFont));
//        cellHeaders[2] = new Cell(new Phrase("教师", contextFont));
//        cellHeaders[3] = new Cell(new Phrase("学分", contextFont));
//        cellHeaders[4] = new Cell(new Phrase("上课周次", contextFont));
//        cellHeaders[5] = new Cell(new Phrase(" ", contextFont));
//        cellHeaders[5].setColspan(2);
//        cellHeaders[6] = new Cell(new Phrase("星期一", contextFont));
//        cellHeaders[7] = new Cell(new Phrase("星期二", contextFont));
//        cellHeaders[8] = new Cell(new Phrase("星期三", contextFont));
//        cellHeaders[9] = new Cell(new Phrase("星期四", contextFont));
//        cellHeaders[10] = new Cell(new Phrase("星期五", contextFont));
//        for (int i = 0; i < 11; i++) {
//            /** 居中显示 author:yyli Sep 15, 2010 */
//            cellHeaders[i].setHorizontalAlignment(Element.ALIGN_CENTER);
//            /** 纵向居中显示 author:yyli Sep 15, 2010 */
//            cellHeaders[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
//            table.addCell(cellHeaders[i]);
//        }
//        /** 向表格填充数据 author:yyli Sep 15, 2010 */
//        for (int i = 0; i < 15; i++) {
//            /** 第0列 author:yyli Sep 15, 2010 */
//            Cell cell0 = new Cell(
//                    new Phrase(String.valueOf(i + 1), contextFont));
//            cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            table.addCell(cell0);
//
//            /** 第1-4列 author:yyli Sep 15, 2010 */
//            Cell[] cell1_4 = new Cell[4];
//            if (i < leftList.size()) {
//                cell1_4[0] = new Cell(new Phrase(str_changenbsp(leftList.get(i)
//                        .getKcmc()), contextFont));
//                cell1_4[1] = new Cell(new Phrase(str_changenbsp(leftList.get(i)
//                        .getJsxm()), contextFont));
//                cell1_4[2] = new Cell(new Phrase(str_changenbsp(leftList.get(i)
//                        .getXf()), contextFont));
//                cell1_4[3] = new Cell(new Phrase(str_changenbsp(leftList.get(i)
//                        .getJszc()), contextFont));
//            }
//            for (int n = 0; n < cell1_4.length; n++) {
//                cell1_4[n].setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell1_4[n].setVerticalAlignment(Element.ALIGN_MIDDLE);
//                table.addCell(cell1_4[n]);
//            }
//            /** 第5列 author:yyli Sep 15, 2010 */
//            Cell cell5 = null;
//            if (i == 0) {
//                cell5 = new Cell(new Phrase("上午", contextFont));
//                cell5.setRowspan(5);
//            }
//            if (i == 5) {
//                cell5 = new Cell(new Phrase("下午", contextFont));
//                cell5.setRowspan(5);
//            }
//            if (i == 10) {
//                cell5 = new Cell(new Phrase("晚上", contextFont));
//                cell5.setRowspan(2);
//            }
//            if (i == 12) {
//                cell5 = new Cell(new Phrase("周六", contextFont));
//                cell5.setColspan(2);
//            }
//            if (i == 13) {
//                cell5 = new Cell(new Phrase("周日", contextFont));
//                cell5.setColspan(2);
//            }
//            if (i == 14) {
//                cell5 = new Cell(new Phrase("备注", contextFont));
//                cell5.setColspan(2);
//            }
//            if (cell5 != null) {
//                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                table.addCell(cell5);
//            }
//            /** 第6列 author:yyli Sep 15, 2010 */
//            if (i < 12) {
//                Cell cell2 = new Cell(new Phrase(String.valueOf(i + 1),
//                        contextFont));
//                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                table.addCell(cell2);
//            }
//            /** 第7-11列 author:yyli Sep 15, 2010 */
//            if (i == 0 || i == 5 || i == 10) {
//                Cell[] cell7_11 = new Cell[5];
//                for (int n = 0; n < 5; n++) {
//                    cell7_11[n] = new Cell(new Phrase(
//                            str_changebr(stuCurriculumArray[i + n]),
//                            contextFont));
//                    cell7_11[n].setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell7_11[n].setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    if (i == 0 || i == 5) {
//                        cell7_11[n].setRowspan(5);
//                    } else {
//                        cell7_11[n].setRowspan(2);
//                    }
//                    table.addCell(cell7_11[n]);
//                }
//            }
//            Cell cell7 = null;
//            if (i == 12) {
//                cell7 = new Cell(new Phrase(
//                        str_changebr(stuCurriculumArray[15]), contextFont));
//            }
//            if (i == 13) {
//                cell7 = new Cell(new Phrase(
//                        str_changebr(stuCurriculumArray[16]), contextFont));
//            }
//            if (i == 14) {
//                cell7 = new Cell(new Phrase(
//                        str_changebr(stuCurriculumArray[17]), contextFont));
//            }
//            if (cell7 != null) {
//                cell7.setColspan(5);
//                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                table.addCell(cell7);
//            }
//
//        }
//
//        doc.add(table);
//        doc.close();
//
//        // 得到输入流  
//        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//        baos.close();
//        return bais;
//    }
//
//    public String str_changenbsp(String str) {
//        if (str != null) {
//            return str.replaceAll(" ", "");
//        } else {
//            return "";
//        }
//    }
//
//    public String str_changebr(String str) {
//        if (str != null) {
//            return str.replaceAll("<br>", "\n");
//        } else {
//            return "";
//        }
//    }
//}
