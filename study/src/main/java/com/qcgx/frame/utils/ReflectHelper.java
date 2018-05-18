package com.qcgx.frame.utils;

import java.util.HashMap;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import com.qcgx.frame.utils.base.StringHelper;
import java.lang.reflect.ParameterizedType;

/**
 * <p>基于反射应用的通用工具类</p>
 * @author FLY @date 2017-06-02<br>
 * @version 1.0<br>
 */
public class ReflectHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private ReflectHelper() {}
	
	/**
	 * <p>判断指定方法为抽象方法</p>
	 * @param method 方法对象<br>
	 * @return true:是 false:非<br>
	 */
	public static boolean isAbstractMethod(Method method) {
		return CommHelper.isNotNull(method) && Modifier.isAbstract(method.getModifiers());
	}
	
	/**
	 * <p>获取对象实例中指定属性的值</p>
	 * @param obj 对象实例<br>
	 * @param field 属性名<br>
	 * @return 属性值<br>
	 */
	public static Object getFieldValue(Object obj, String field) {
		Object rtnObj = null;
		try {
			// 合法性验证
			if (CommHelper.isNotNull(obj) && StringHelper.isNotEmpty(field)) {
				String[] fields = StringHelper.split(field, ".");
				// 循环获取属性值
				Field field_obj = null;
				for (String temp : fields) {
					// 合法性判断
					Object value = null;
					if (CommHelper.isNotNull(obj)) {
						field_obj = getDeclaredField(obj.getClass(), temp); 
						if (CommHelper.isNotNull(field_obj)) {
							field_obj.setAccessible(true); value = field_obj.get(obj);
							if (CommHelper.isNull(value)) {
								value = invokeMethod(obj, "get".concat(StringHelper.firstCharToUpperCase(temp)), new Object[]{});
							}
						}
						obj = value;
					}
				}
				rtnObj = obj;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnObj;
	}

	/**
	 * <p>获取类中静态变量的值</p>
	 * @param clz 字节码对象<br>
	 * @param field 属性名<br>
	 * @return 属性值<br>
	 */
	public static Object getStaticFieldValue(Class<?> clz, String field) {
		Object rtnO = null;
		try {
			// 合法性判断
			if (isNotEmptyClass(clz) && StringHelper.isNotEmpty(field)) {
				Field temp = getDeclaredField(clz, field);
				if (CommHelper.isNotNull(temp)) {
					temp.setAccessible(true); rtnO = temp.get(clz);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>根据字节码对象创建实例</p>
	 * @param clz 字节码对象<br>
	 * @param values 参数值<br>
	 * @return 实例对象<br>
	 */
	public static Object getInstance(Class<?> clz, Object... values) {
		Object rtnO = null;
		try {
			if (CommHelper.isNotNull(clz)) {
				Class<?>[] clzs = ReflectHelper.getParameterClasses(values);
				if (CommHelper.isNotEmptyArray(clzs)) {
					Constructor<?> constructor = clz.getConstructor(clzs);
					if (CommHelper.isNotNull(constructor)) {
						rtnO = constructor.newInstance(values);
					}
				} else {
					rtnO = clz.newInstance();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>获取指定的所有属性值</p>
	 * @param obj 指定对象<br>
	 * @return 属性值集<br>
	 */
	public static HashMap<String, Object> getFieldValues(Object obj) {
		HashMap<String, Object> rtnMap = null;
		try {
			if (CommHelper.isNotNull(obj)) {
				rtnMap = new java.util.HashMap<String, Object>();
				Class<?> clz = obj.getClass();
				while(isNotEmptyClass(clz)) {
					Field[] fields = clz.getDeclaredFields(); String name = null;
					for (Field field : fields) {
						name = field.getName();
						if (!"serialVersionUID".equalsIgnoreCase(name)) {
							rtnMap.put(name, getFieldValue(obj, name));
						}
					}
					clz = clz.getSuperclass();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnMap;
	}
	
	/**
	 * 根据指定参数创建实例<p>
	 * @param className 类名<br>
	 * @param values 参数数组<br>
	 * @return 实例对象<br>
	 */
	public static Object getInstance(String className, Object... values) {
		Object rtnO = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(className)) {
				rtnO = getInstance(Class.forName(className), values);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>获取指定对象数组的字节码对象组</p>
	 * @param values 对象数组<br>
	 * @return 字节码对象组<br>
	 */
	public static Class<?>[] getParameterClasses(Object ... values) {
		Class<?>[] rtnCA = null;
		// 合法性判断
		if (CommHelper.isNotNull(values)) {
			rtnCA = new Class<?>[values.length];
			for(int i = 0; i < values.length; i ++) {
				Object temp = values[i];
				if (CommHelper.isNotNull(temp)) {
					rtnCA[i] = temp.getClass();
				}
			}
		}
		return rtnCA;
	}
	
	/**
	 * <p>获取类中指定属性对象</p>
	 * @param clz 字节码对象<br>
	 * @param field 属性名<br>
	 * @return 属性对象<br>
	 */
	public static Field getDeclaredField(Class<?> clz, String field) {
		Field rtnF = null;
		try {
			// 合法性判断
			if (CommHelper.isNotNull(clz) && StringHelper.isNotEmpty(field)) {
				// 循环获取属性对象
				while(isNotEmptyClass(clz) && CommHelper.isNull(rtnF)) {
					Field[] fields = clz.getDeclaredFields();
					for (Field temp : fields) {
						if (temp.getName().equals(field)) {
							rtnF = temp; break;
						}
					}
					clz = clz.getSuperclass();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnF;
	}

	/**
	 * <p>根据索引值获取泛型参数字节对象</p>
	 * @param clazz 子类字节对象<br>
	 * @param index 泛型参数索引<br>
	 * @return 泛型参数字节对象<br>
	 */
	public static Class<?> getGenericType(final Class<?> clazz, final int index) {
		Class<?> rtnClz = Object.class;
		// 合法性判断
		if (CommHelper.isNotNull(clazz) ) {
			Type type = clazz.getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				Type[] params = ((ParameterizedType) type).getActualTypeArguments();
				if (index >=0 && index < params.length) {
					rtnClz = (Class<?>) params[index];
				}
			} 
		}
		return rtnClz;
	}
	
	/**
	 * <p>获取对象实例中指定方法对象</p>
	 * @param clz 字节码对象<br>
	 * @param method 方法名<br>
	 * @return 方法对象<br>
	 */
	public static Method getDeclaredMethod(Class<?> clz, String method) {
		Method rtnM = null;
		try {
			// 合法性判断
			if (CommHelper.isNotNull(clz) && StringHelper.isNotEmpty(method)) {
				// 循环寻找方法对象
				while(isNotEmptyClass(clz) && CommHelper.isNull(rtnM)) {
					if (clz != Object.class) {
						Method[] methods = clz.getDeclaredMethods();
						for (Method temp : methods) {
							if (temp.getName().equals(method)) {
								rtnM = temp; break;
							}
						}
						clz = clz.getSuperclass();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnM;
	}
	
	/**
	 * <p>对象实例属性设置</p>
	 * @param obj 对象实例<br>
	 * @param field 属性名<br>
	 * @param value 属性值<br>
	 */
	public static void setFieldValue(Object obj, String field, Object value) {
		try {
			// 合法性验证
			if (CommHelper.isNotNull(obj) && StringHelper.isNotEmpty(field)) {
				String[] fields = StringHelper.split(field, ".");
				// 循环设值
				Field field_obj = null; int length  = fields.length;
				for (int i = 0; i < length; i ++) {
					if(CommHelper.isNotNull(obj)) {
						field_obj = getDeclaredField(obj.getClass(), fields[i]);
						if (CommHelper.isNotNull(field_obj)) {
							if (i == length - 1) {
								field_obj.setAccessible(true); field_obj.set(obj, value);
							} else {
								field_obj.setAccessible(true); obj = field_obj.get(obj);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>设置对象中静态变量的值</p>
	 * @param clz 字节码对象<br>
	 * @param field 属性名<br>
	 * @param value 属性值<br>
	 */
	public static void setStaticFieldValue(Class<?> clz, String field, Object value) {
		try {
			// 合法性判断
			Field temp = getDeclaredField(clz, field);
			if (CommHelper.isNotNull(temp)) {
				temp.setAccessible(true); temp.set(clz, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>执行指定对象中的指定方法</p>
	 * @param object 实例对象<br>
	 * @param method 方法名称<br>
	 * @param args 参数值<br>
	 * @return 执行结果对象<br>
	 */
	public static Object invokeMethod(Object object, String method, Object... args) {
		Object rtnO = null;
		try {
			// 合法性判断
			if (CommHelper.isNotNull(object) && StringHelper.isNotEmpty(method)) {
				// 获取指定函数对象
				Method temp = getDeclaredMethod(object.getClass(), method);
				if (CommHelper.isNotNull(temp)) {
					rtnO = temp.invoke(object, args);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>判断字节对象为非空</p>
	 * @param clz 字节对象<br>
	 * @return true:非空 false:已空<br>
	 */
	private static boolean isNotEmptyClass(Class<?> clz) {
		return CommHelper.isNotNull(clz) && clz != Object.class;
	}
}
