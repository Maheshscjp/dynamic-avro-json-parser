package com.purple.json.converter;

public interface UnknownFieldListener {

	void onUnknownField(String name, Object value, String path);
}
