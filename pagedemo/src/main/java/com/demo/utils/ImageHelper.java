package com.demo.utils;

import java.io.File;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Iterator;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import javax.imageio.ImageReadParam;
import com.sun.image.codec.jpeg.JPEGCodec;
import javax.imageio.stream.ImageInputStream;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class ImageHelper {

	/**
	 * <p>根据条件裁剪图片</p>
	 * @param x x坐标<br>
	 * @param y y坐标<br>
	 * @param width 宽度<br>
	 * @param height 高度<br>
	 * @param srcFile 源文件<br>
	 * @param outFile 目标文件<br>
	 * @return 操作结果<br>
	 */
	public static boolean cutPic(String srcFile, String outFile, int x, int y, int width, int height) {
		FileInputStream is = null; ImageInputStream iis = null;
		try {
			// 如果源图片不存在
			if (!new File(srcFile).exists()) return false;
			// 读取图片文件
			is = new FileInputStream(srcFile);
			// 获取文件格式
			String ext = srcFile.substring(srcFile.lastIndexOf(".") + 1);
			// ImageReader声称能够解码指定格式
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			// 输入源中的图像将只按顺序读取
			reader.setInput(iis, true);
			// 描述如何对流进行解码
			ImageReadParam param = reader.getDefaultReadParam();
			// 图片裁剪区域
			Rectangle rect = new Rectangle(x, y, width, height);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标
			param.setSourceRegion(rect);
			// 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			File tempOutFile = new File(outFile);
			if (!tempOutFile.exists()) tempOutFile.mkdirs();
			ImageIO.write(bi, ext, new File(outFile)); return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (is != null) is.close();
				if (iis != null) iis.close();
			} catch (IOException e) {
				e.printStackTrace(); return false;
			}
		}
	}

	/**
	 * <p>是否等比例缩放图片</p>
	 * @param width 宽度<br>
	 * @param height 高度<br>
	 * @param srcFile 源文件<br>
	 * @param outFile 目标文件<br>
	 * @param proportion 是否等比例缩放<br>
	 * @return 操作结果<br>
	 */
	public static boolean compressPic(String srcFile, String outFile, int width, int height, boolean proportion) {
		try {
			// 获得源文件
			File file = new File(srcFile);
			if (!file.exists())
				return false;
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				return false;
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = width; // 输出的图片宽度
					newHeight = height; // 输出的图片高度
				}

				// 如果图片小于目标图片的宽和高则不进行转换
				if (img.getWidth(null) < width && img.getHeight(null) < height) {
					newWidth = img.getWidth(null);
					newHeight = img.getHeight(null);
				}

				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				// Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的,优先级比速度高 生成的图片质量比较好 但速度慢
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outFile);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		// 测试裁剪图片
		// Boolean flag = cutPic("E:\\123.jpg", "E:\\\\1.jpg", 510, 10, 550, 550);
		// if(flag) {
		// System.out.println("裁剪成功");
		// }else {
		// System.out.println("裁剪失败");
		// }

		// 测试缩放图片
		Boolean flag = compressPic("E:\\123.jpg", "E:\\\\2.jpg", 800, 450, true);
		if (flag) {
			System.out.println("缩放成功");
		} else {
			System.out.println("缩放失败");
		}
	}
}
