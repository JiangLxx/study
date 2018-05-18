package com.qcgx.frame.utils.base.cnst;

/**
 * <p>工具包常量定义类</p>
 * @author FLY @date 2017-05-22<br>
 * @version 1.0<br>
 */
public final class Constants {
	/** 零  值  常 量 值 **/
	public static final int  ZERO_INT = 0;
	/** 密匙长度常量 **/
	public static final int KEY_SIZE = 128;
	/** 字符编码常量 **/
	public static final String UTF_8 = "utf-8";
	/** 零字符串常量值 **/
	public static final String ZERO_STRING = "0";
	/** 空字符串值常量 **/
	public static final String EMPTY_STRING = "";
	/** 加密最大长度常量 **/
	public static final int MAX_ENCRYPT_BLOCK = 117;
	/** 解密最大长度常量 **/
	public static final int MAX_DECRYPT_BLOCK = 128;
	/** 图片格式常量值 **/
	public static final String PICTRUE_FORMATE_JPG = "jpg";
	/**身份证号码校验位**/
	public static final int[] IDCardVi = {1,0,'X',9,8,7,6,5,4,3,2};
	/**路径分割符**/
	public static final String FILE_SPR = java.io.File.separator;
	/** 百分比零值常量 **/
	public static final String ZERO_PERCENT_STRING = "0.00%";
	/** 金额零值串常量 **/
	public static final String ZERO_AMOUNT_STRING = "￥0.00";
	/**身份证号码加权因子**/
	public static final int[] IDCardWi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
	/** 默认百分数格式样式 **/
	public static final String DEFAULT_PERCENT_FORMAT = "#0.00##%";
	/** 默认金额格式化样式 **/
	public static final String DEFAULT_AMOUNT_FORMAT = "###,##0.00";
	/** 默认单价格式化样式 **/
	public static final String DEFAULT_PRICE_FORMAT = "###,##0.00######";
	/** 大数据类型零值常量 **/
	public static final java.math.BigInteger BIG_INT_ZERO = new java.math.BigInteger("0");
	public static final java.math.BigDecimal BIG_DEC_ZERO = new java.math.BigDecimal("0");
	/** MD5加密加权因子 **/
	public static final char[] HexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
	/**数字的大写模式**/
	public static final String HanDigiStr[] = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	/**身份证号码地区编码**/
	public static final java.util.HashMap<String, String> IDCardCity = new java.util.HashMap<String, String>();
	static {
		IDCardCity.put("11", "北京"); IDCardCity.put("12", "天津");   IDCardCity.put("13", "河北");
		IDCardCity.put("14", "山西"); IDCardCity.put("15", "内蒙古"); IDCardCity.put("21", "辽宁");
		IDCardCity.put("22", "吉林"); IDCardCity.put("23", "黑龙江"); IDCardCity.put("31", "上海");
		IDCardCity.put("32", "江苏"); IDCardCity.put("33", "浙江");   IDCardCity.put("34", "安徽");
		IDCardCity.put("35", "福建"); IDCardCity.put("36", "江西");   IDCardCity.put("37", "山东");
		IDCardCity.put("41", "河南"); IDCardCity.put("42", "湖北");   IDCardCity.put("43", "湖南");
		IDCardCity.put("44", "广东"); IDCardCity.put("45", "广西");   IDCardCity.put("46", "海南");
		IDCardCity.put("50", "重庆"); IDCardCity.put("51", "四川");   IDCardCity.put("52", "贵州");
		IDCardCity.put("53", "云南"); IDCardCity.put("54", "西藏");   IDCardCity.put("61", "陕西");
		IDCardCity.put("62", "甘肃"); IDCardCity.put("63", "青海");   IDCardCity.put("64", "宁夏");
		IDCardCity.put("65", "新疆"); IDCardCity.put("71", "台湾");   IDCardCity.put("81", "香港");
		IDCardCity.put("82", "澳门"); IDCardCity.put("91", "国外");
	}
	/** 日期格式 **/
	public static final java.util.Map<String, String> DATE_STYLE_MAP = new java.util.HashMap<String, String>();
	static {
		DATE_STYLE_MAP.put("^[1-2]\\d{3}[0-1]\\d{1}[0-3]\\d{1}$", "yyyyMMdd");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}-[0-1]\\d{1}-[0-3]\\d{1}$", "yyyy-MM-dd");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}/[0-1]\\d{1}/[0-3]\\d{1}$", "yyyy/MM/dd");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}年[0-1]\\d{1}月[0-3]\\d{1}日$", "yyyy年MM月dd日");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}[0-1]\\d{1}[0-3]\\d{1}[0-2]\\d{1}[0-6]\\d{1}[0-6]\\d{1}$", "yyyyMMddHHmmss");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}-[0-1]\\d{1}-[0-3]\\d{1} [0-2]\\d{1}:[0-6]\\d{1}:[0-6]\\d{1}$", "yyyy-MM-dd HH:mm:ss");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}/[0-1]\\d{1}/[0-3]\\d{1} [0-2]\\d{1}:[0-6]\\d{1}:[0-6]\\d{1}$", "yyyy/MM/dd HH:mm:ss");
		DATE_STYLE_MAP.put("^[1-2]\\d{3}年[0-1]\\d{1}月[0-3]\\d{1}日 [0-2]\\d{1}时[0-6]\\d{1}分[0-6]\\d{1}秒$", "yyyy年MM月dd日 HH时mm分ss秒");
	}
	/** 数字字串常量 **/
	public static final String NUMBER_CHAR_STRING = "0123456789";
	/** 字母字串常量 **/
	public static final String LETTER_CHAR_STRING = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** 全字符串常量 **/
	public static final String ALL_CHAR_STRING = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** 特殊字符过滤规则 **/
	public static final String SPECIAL_CHAR = "[`~!#$%^&*()+=|{}':;',\\[\\]<>/?~！『』#￥%……&*（）《》——+|{}【】‘；_：”“’。，、？×～·-]";
	/**SQL防注入字符串**/
	public static final String SQL_INJECTION = "'|like |exec |insert |select |delete |update |union |count( |%|*|chr |mid |master |truncate |char |declare |; |or |+|=|'";
	/**金额的财务大写**/
	public static final String HanDiviStr[] = new String[]{"","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰","仟" };
}
