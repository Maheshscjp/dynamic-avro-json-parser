package com.purple.json.converter;

import org.apache.avro.AvroTypeException;

public class FailOnUnknownField implements UnknownFieldListener {

	@Override
	public void onUnknownField(String name, Object value, String path) {
		throw new AvroTypeException("Field " + path + " is unknown");
	}

}
