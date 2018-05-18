package com.qcgx.frame.utils.file;

import java.io.*;
import java.util.List;
import com.qcgx.frame.utils.CommHelper;
import com.google.common.collect.Lists;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>文件系统通用操作工具类</p>
 * @author FLY @date 2017-05-26<br>
 * @version 1.0<br>
 */
public class FileHelper {
	/**
	 * <p>构造函数:禁止用户以NEW方式创建对象</p>
	 */
	private FileHelper() { }
	
	/**
	 * <p>关闭文件输入输出流实例对象</p>
	 * @param io 输入输出流<br>
	 */
	public static void closeIO(Object io) {
		try {
			if (CommHelper.isNotNull(io)) {
				// 输出流
				if (io instanceof Reader) {
					((Reader)io).close();
				}
				// 输入流
				if (io instanceof InputStream) {
					((InputStream)io).close();
				}
				// 读写流
				if (io instanceof OutputStream) {
					((OutputStream)io).close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			io = null;
		}
	}
	
	/**
	 * <p>根据文件绝对路径创建文件夹及文件</p>
	 * @param path 文档路径<br>
	 */
	public static void creat(String path) {
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(path)) {
				String[] deeps = StringHelper.split(getLegalPath(path), Constants.FILE_SPR);
				if (CommHelper.isNotEmptyArray(deeps)) {
					String temp = deeps[0]; File file = null;
					int last = path.lastIndexOf(Constants.FILE_SPR);
					for (int i = 1; i < deeps.length; i ++) {
						temp = temp.concat(Constants.FILE_SPR).concat(deeps[i]);
						file = new File(temp);
						if (temp.indexOf(".") > last && isFile(file) == false) {
							file.createNewFile();
						} else {
							if (isDirecotry(file) == false) file.mkdir();
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 删除指定路径下的所有文件<p>
	 * @param path 文件路径<br>
	 */
	public static void delete(String path) {
		try {
			if (StringHelper.isNotEmpty(path)) {
				File file = new File(getLegalPath(path));
				if (isFile(file)) file.delete();
				if (isDirecotry(file)) {
					File[] files = file.listFiles();
					for (File temp : files) {
						delete(temp.getAbsolutePath());
					} file.delete();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 判断文件对象是否存在<p>
	 * @param file 文件对象<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean isFile(File file) {
		return file.exists() && file.isFile();
	}
	
	/**
	 * <p>读取文档内容</p>
	 * @param path 文档路径<br>
	 * @return 文本内容<br>
	 */
	public static String readTxt(String path) {
		StringBuffer strBuf = null; BufferedReader reader = null;
		try {
			// 合法性判断
			path = getLegalPath(path);
			if (isFile(path)) {
				String line = null; strBuf = new StringBuffer();
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
				while((line = reader.readLine()) != null) {
					strBuf.append(line);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeIO(reader);
		}
		return strBuf.toString();
	}

	/**
	 * 判断路径下文件是否存在<p>
	 * @param path 文档路径<p>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean isFile(String path) {
		return isFile(new File(getLegalPath(path)));
	}
	
	/**
	 * 判断路径下文件夹是否存在<p>
	 * @param file 文件对象<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean isDirecotry(File file) {
		return file.exists() && file.isDirectory();
	}
	
	/**
	 * 判断路径下文件夹是否存在<p>
	 * @param path 文件路径<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean isDirecotry(String path) {
		return isDirecotry(new File(getLegalPath(path)));
	}
	
	/**
	 * 获取当前系统合法的文件路径<p>
	 * @param path 文件路径<br>
	 * @return 合法的文件路径<br>
	 */
	public static String getLegalPath(String path) {
		return StringHelper.getLegalString(path).replace("/", Constants.FILE_SPR).replace("\\", Constants.FILE_SPR);
	}
	
	/**
	 * <p>加载指定路径下的所有文档</p>
	 * @param path 文档路径<br>
	 * @return 文档对象<br>
	 */
	public static List<File> getFileList(String path) {
		List<File> rtnList = Lists.newArrayList();
		// 合法性判断
		if (isDirecotry(path) || isFile(path)) {
			collectionFiles(rtnList, new File(getLegalPath(path)));
		}
		return rtnList;
	}
	
	/**
	 * <p>根据文件名获取文件名后缀</p>
	 * @param filename 文件名<br>
	 * @return 文件名后缀字符串<br>
	 */
	public static String getFileSuffix(String filename) {
		String rtnSuffix = "";
		// 合法性验证
		if (StringHelper.isNotEmpty(filename)) {
			rtnSuffix = filename.substring(filename.indexOf(".") + 1);
		}
		return rtnSuffix;
	}

	/**
	 * 组装路径字符串<p>
	 * @param path 基础路径<br>
	 * @param file 文件路径<br>
	 * @return 文档路径<br>
	 */
	public static String concat(String path, String file) {
		String rtnPath = Constants.EMPTY_STRING;
		// 合法性判断
		if (StringHelper.isNotEmpty(path) && StringHelper.isNotEmpty(file)) {
			path = FileHelper.getLegalPath(path); file = FileHelper.getLegalPath(file);
			// 根据路径形式判断
			if (!path.endsWith(Constants.FILE_SPR) && !file.startsWith(Constants.FILE_SPR)) {
				rtnPath = path.concat(Constants.FILE_SPR).concat(file);
			} else if (path.endsWith(Constants.FILE_SPR) && file.startsWith(Constants.FILE_SPR)) {
				rtnPath = path.concat(file.substring(Constants.FILE_SPR.length()));
			} else {
				rtnPath = path.concat(file);
			}
		}
		return rtnPath;
	}
	
	/**
	 * 根据指定的路径将文件进行拷贝<p>
	 * @param srcpath 来源文件路径<br>
	 * @param destpath 目标文件路径<br>
	 */
	public static void copy(String srcpath, String destpath) {
		// 变量声明
		InputStream input = null; OutputStream output = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(srcpath) && StringHelper.isNotEmpty(destpath)) {
				srcpath = getLegalPath(srcpath); destpath = getLegalPath(destpath);
				// 根据路径信息进行拷贝
				File source = new File(srcpath), dest = new File(destpath);
				if (isDirecotry(source) || isFile(source)) {
					// 当目标不存在时，创建
					if (dest.exists() == false) {
						creat(destpath);
					}
					// 来源文件 + 目标文件
					if (isFile(source) && isFile(dest)) {
						input = new FileInputStream(source);
						output = new FileOutputStream(dest);
						// 执行文件拷贝
						int len = 0; byte[] buffer = new byte[1024];
						while((len = input.read(buffer)) != -1) {
							output.write(buffer, 0, len);
						}
					}
					// 来源文件 + 目标文件夹
					if (isFile(source) && isDirecotry(dest)) {
						copy(source.getAbsolutePath(), destpath.concat(Constants.FILE_SPR).concat(source.getName()));
					}
					// 来源文件夹 + 目标文件夹
					if (isDirecotry(source) && isDirecotry(dest)) {
						File[] list = source.listFiles();
						for (File temp : list) {
							copy(temp.getAbsolutePath(), destpath.concat(Constants.FILE_SPR).concat(temp.getName()));
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeIO(input); closeIO(output);
		}
	}
	
	/**
	 * <p>收集文档对象下所有文档对象</p>
	 * @param list 文档容器<br>
	 * @param file 文档对象<br>
	 */
	public static void collectionFiles(List<File> list, File file) {
		// 合法性判断
		if (CommHelper.isNotEmptyList(list) || CommHelper.isNotNull(file)) {
			if (file.isFile()) {
				list.add(file);
			} else {
				File[] files = file.listFiles();
				if (CommHelper.isNotEmptyArray(files)) {
					for (File temp : files) {
						collectionFiles(list, temp);
					}
				}
			}
		}
	}
	
	/**
	 * 将文件输入流以指定的文件名保存到文件夹<p>
	 * @param input 文件输入流<br>
	 * @param path 文件路径<br>
	 * @param name 文件名称<br>
	 */
	public static void save(InputStream input, String path, String name) {
		// 变量声明
		BufferedInputStream bis = null; BufferedOutputStream bos = null;
		try {
			// 合法性验证
			if (CommHelper.isNotNull(input)) {
				path = concat(getLegalPath(path), name);
				// 当文件不存在时，创建文件
				File file = new File(path);
				if (isFile(file) == false) {
					creat(path);
				}
				// 执行文件保存
				int len = 0; bis = new BufferedInputStream(input);
				bos = new BufferedOutputStream(new FileOutputStream(file));
				while((len = bis.read()) != -1) {
					bos.write(len); bos.flush();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeIO(bis); closeIO(bos); closeIO(input);
		}
	}
}
