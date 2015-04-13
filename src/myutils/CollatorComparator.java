package com.rails.vehicle.core.common.Utils;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

/**
 * 比较器。排序用
 * @author WengShengyuan
 *
 */
public class CollatorComparator implements Comparator {
	Collator collator = Collator.getInstance();
	public int compare(Object element1, Object element2) {
	    CollationKey key1 = collator.getCollationKey(element1.toString());
	    CollationKey key2 = collator.getCollationKey(element2.toString());
	    return key1.compareTo(key2);
	}
}
