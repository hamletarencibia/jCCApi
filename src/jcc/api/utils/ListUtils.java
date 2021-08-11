package jcc.api.utils;

import java.util.List;

public class ListUtils {
	public static <E> void merge(List<E> list1, List<E> list2) {
		if(list1.size() > list2.size()) {
			for(E e : list2) {
				if(!list1.contains(e))
					list1.add(e);
			}
		}
		else {
			for(E e : list1) {
				if(!list2.contains(e))
					list2.add(e);
			}
		}
	}
}
