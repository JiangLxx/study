package com.qcgx.frame.utils.base.data;

import java.util.*;
import java.text.*;

/**
 * <p>通用对象比较实现类</p>
 * @author FLY @date 2017-06-07<br>
 * @version 1.0<br>
 */
public class CollatorComparator implements Comparator<Object> {
	Collator collator = Collator.getInstance();
	
	/**
	 * <p>比较接口函数</p>
	 */
	public int compare(Object o1, Object o2) {
		CollationKey key1 = collator.getCollationKey(o1.toString().toLowerCase());
		CollationKey key2 = collator.getCollationKey(o2.toString().toLowerCase());
		return key1.compareTo(key2);
	}
}
