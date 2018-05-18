package com.qcgx.frame.utils.file;

import java.io.File;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.io.FileInputStream;
import javax.imageio.ImageReader;
import javax.imageio.ImageReadParam;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import javax.imageio.stream.ImageInputStream;

/**
 * <p>基于图片处理的通用工具类</p>
 * @author FLY @date 2017-05-28<br>
 * @version 1.0<br>
 */
public class ImageHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private ImageHelper() {}
	
	/**
	 * <p>图片缩放</p>
	 * @param srcimgpath 图片路径<br>
	 * @param width 宽度<br>
	 * @param height 高度<br>
	 * @param bb 比例不对时是否需要补白<br>
	 */
	public static void resize(String srcimgpath, int width, int height, boolean bb) {
		try {
			// 合法性判断
			if (FileHelper.isFile(srcimgpath)) {
				File src_file = new File(FileHelper.getLegalPath(srcimgpath));
				BufferedImage buffered = ImageIO.read(src_file);
				Image itemp = buffered.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
				// 计算比例
				int src_width = itemp.getWidth(null), src_height = itemp.getHeight(null); double ratio = 0;
				if (src_height > height || src_width > width) {
					if (src_height > src_width) {
						ratio = (new Integer(height)).doubleValue() / src_height;
					} else {
						ratio = (new Integer(width)).doubleValue() / src_width;
					}
					itemp = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null).filter(buffered, null);
				}
				// 根据补白标识进行操作
				if (bb) {
					BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics2D bb_graphics = image.createGraphics(); bb_graphics.setColor(Color.white);
					bb_graphics.fillRect(0, 0, width, height);
					if (width == itemp.getWidth(null)) {
						bb_graphics.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
					} else {
						bb_graphics.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
					}
					bb_graphics.dispose(); itemp = image;
				}
				ImageIO.write((BufferedImage)itemp, Constants.PICTRUE_FORMATE_JPG, src_file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>根据条件裁剪图片</p>
	 * @param srcFile 源图路径<br>
	 * @param outFile 新图路径<br>
	 * @param x X坐标<br>
	 * @param y Y坐标<br>
	 * @param width 宽度<br>
	 * @param height 高度<br>
	 * @return 操作结果<br>
	 */
	public static boolean cutPic(String srcFile, String outFile, int x, int y, int width, int height) {
		FileInputStream is = null; ImageInputStream iis = null; boolean result = Boolean.FALSE;
		try {
			// 合法性验证
			if (StringHelper.isNotEmpty(srcFile) && StringHelper.isNotEmpty(outFile)) {
				// 判断图片是否存在
				srcFile = FileHelper.getLegalPath(srcFile);
				outFile = FileHelper.getLegalPath(outFile);
				if (FileHelper.isFile(srcFile)) {
					is = new FileInputStream(FileHelper.getLegalPath(srcFile));
					// 获取文件格式
					String ext = FileHelper.getFileSuffix(srcFile);
					// 解码指定格式
					Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(ext);
					while(iterator.hasNext()) {
						ImageReader reader = iterator.next();
						// 创建图片流
						iis = ImageIO.createImageInputStream(is);
						// 设置图片流
						reader.setInput(iis, true);
						// 解码图片流
						ImageReadParam param = reader.getDefaultReadParam();
						// 设置裁剪区
						Rectangle rect = new Rectangle(x, y, width, height);
						// 执行裁剪
						param.setSourceRegion(rect);
						// 文件输出
						BufferedImage bi = reader.read(0, param);
						// 初始文件
						File output = new File(outFile);
						if (output.exists() == false) {
							output.mkdirs();
						}
						// 执行保存
						ImageIO.write(bi, ext, new File(outFile)); result = Boolean.TRUE;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(iis); FileHelper.closeIO(is);
		}
		return result;
	}
	
	/**
	 * <p>图片添加图片水印</p>
	 * @param srcimgpath 目标图片路径<br>
	 * @param markimgpath 水印图片路径<br>
	 * @param x 水印图片距离目标图片左侧的偏移量，如果X小于零, 则在正中间<br>
	 * @param y 水印图片距离目标图片上侧的偏移量，如果Y小于零, 则在正中间<br>
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)<br>
	 */
	public static void pictureWaterMark(String srcimgpath, String markimgpath, int x, int y, float alpha) {
		try {
			// 合法性判断
			if (FileHelper.isFile(srcimgpath) && FileHelper.isFile(markimgpath)) {
				// 加载原图
				File srcimage = new File(FileHelper.getLegalPath(srcimgpath));
				Image image = ImageIO.read(srcimage);
				int width = image.getWidth(null), height = image.getHeight(null);
				BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = buffered.createGraphics(); graphics.drawImage(image, 0, 0, width, height, null);
				// 加载水印
				Image mark = ImageIO.read(new File(FileHelper.getLegalPath(markimgpath)));
				int mark_width = mark.getWidth(null), mark_height = mark.getHeight(null);
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
				// 水印位置
				int left_width = width - mark_width, left_height = height - mark_height;
				if (x < 0) {
					x = left_width / 2;
				} else if (x > left_width) {
					x = left_width;
				}
				if (y < 0) {
					y = left_height / 2;
				} else if (y > left_height) {
					y = left_height;
				}
				// 制作水印
				graphics.drawImage(mark, x, y, mark_width, mark_height, null);
				graphics.dispose(); ImageIO.write(buffered, Constants.PICTRUE_FORMATE_JPG, srcimage);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>图片添加文字水印</p>
	 * @param srcimgpath 图片位置<br>
	 * @param text 水印文字<br>
	 * @param font 字体实例<br>
	 * @param color 字体颜色<br>
	 * @param x 水印图片距离目标图片左侧的偏移量，如果X小于零, 则在正中间<br>
	 * @param y 水印图片距离目标图片上侧的偏移量，如果Y小于零, 则在正中间<br>
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)<br>
	 */
	public static void stringWaterMark(String srcimgpath, String text, Font font, Color color, int x, int y, float alpha) {
		try {
			// 合法性判断
			if (FileHelper.isFile(srcimgpath) && StringHelper.isNotEmpty(text)) {
				// 加载原图
				File srcimage = new File(FileHelper.getLegalPath(srcimgpath));
				Image image = ImageIO.read(srcimage);
				int width = image.getWidth(null), height = image.getHeight(null);
				BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = buffered.createGraphics(); graphics.drawImage(image, 0, 0, width, height, null);
				// 字体水印
				graphics.setFont(font); graphics.setColor(color);
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
				// 水印位置
				int mark_width = font.getSize() * getLength(text); int mark_height = font.getSize();
				int left_width = width - mark_width, left_height = height - mark_height;
				if (x < 0) {
					x = left_width / 2;
				} else if (x > left_width) {
					x = left_width;
				}
				if (y < 0) {
					y = left_height / 2;
				} else if (y > left_height) {
					y = left_height;
				}
				// 制作水印
				graphics.drawString(text, x, y + font.getSize()); graphics.dispose();
				ImageIO.write(buffered, Constants.PICTRUE_FORMATE_JPG, srcimage);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
     * <p>获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符</p>
     * @param text 字符串<br>
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.<br>
     */
    private static int getLength(String text) {
        int textLength = text.length(), length = textLength;
        // 计算字符长度
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }
}
