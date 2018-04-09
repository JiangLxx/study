package com.demo.utils.data;

import com.idiot.utils.base.EnumHelper;
import com.idiot.utils.base.eum.core.CoreEnum;

/**
 * <p>文件类型枚举类</p>
 * @author JL @date 2018-01-20<br>
 * @version 1.0<br>
 */
public enum FileType implements CoreEnum<String, String> {
	/** Windows Bitmap **/
	BMP("424D", "bmp"),
	/** Real Media **/
	RM("2E524D46", "rm"),
	/** JEPG **/
	JPEG("FFD8FF", "jpeg"),
	/** WordPerfect **/
	WPD("FF575043", "wpd"),
	/** Quicken **/
	QDF("AC9EBD8F", "qdf"),
	/** Windows Password **/
	PWL("E3828596", "pwl"),
	/** ZIP Archive **/
	ZIP("504B0304", "zip"),
	/** RAR Archive **/
	RAR("52617221", "rar"),
	/** Wave **/
	WAV("57415645", "wav"),
	/** AVI **/
	AVI("41564920", "avi"),
	/** Real Audio **/
	RAM("2E7261FD", "ram"),
	/** MPEG (mpg) **/
	MPG("000001BA", "mpg"),
	/** Quicktime  **/
	MOV("6D6F6F76", "mov"),
	/** PNG **/
	PNG("89504E47", "png"),
	/** MIDI **/
	MID("4D546864", "mid"),
	/** GIF **/
	GIF("47494638", "gif"),
	/** Outlook (pst) **/
	PST("2142444E", "pst"),
	/** CAD **/
	DWG("41433130", "dwg"),
	/** Adobe Photoshop **/
	PSD("38425053", "psd"),
	/** TIFF **/
	TIFF("49492A00", "tiff"),
	/** Rich Text Format **/
	RTF("7B5C727466", "rtf"),
	/** XML **/
	XML("3C3F786D6C", "xml"),
	/** HTML **/
	HTML("68746D6C3E", "html"),
	/** Adobe Acrobat **/
	PDF("255044462D312E", "pdf"),
	/** MS Word/Excel **/
	XLS_DOC("D0CF11E0", "xls_doc"),
	/** Outlook Express **/
	DBX("CFAD12FEC5FD746F", "dbx"),
	/** Windows Media **/
	ASF("3026B2758E66CF11", "asf"),
	/** MS Access **/
	MDB("5374616E64617264204A", "mdb"),
	/** Postscript **/
	EPS("252150532D41646F6265", "eps"),
	/** Email [thorough only] **/
	EML("44656C69766572792D646174653A", "eml");
	
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;

	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 */
	private FileType(String value) {
		this.value = value;
	}
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private FileType(String value, String alias) {
		this.value = value;
		this.alias = alias;
	}

	/**
	 * <p>获取枚举对象真实值</p>
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * <p>获取枚举对象显示值</p>
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * <p>获取枚举对象数组</p>
	 */
	public FileType[] getEnums() {
		return FileType.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public FileType getEnumForAlias(String alias) {
		return (FileType) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public FileType getEnumForValue(String value) {
		return (FileType) EnumHelper.getEnumForValue(this, value);
	}
	
}
