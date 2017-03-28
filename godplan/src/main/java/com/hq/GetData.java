package com.hq;

import java.util.Map;

public class GetData<T> {
	public T get(Map<Long, T> map, long id) {
		if (map.containsKey(id)) {
			return map.get(id);
		} else {
			return null;
		}
	}

}
