package com.purple.json.converter.types;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Deque;

import org.apache.avro.Schema;

import com.purple.json.converter.JsonToAvroReader;

public class ArrayConverter extends AvroTypeConverterWithStrictJavaTypeCheck<Collection> {
    private final JsonToAvroReader jsonToAvroReader;

    public ArrayConverter(JsonToAvroReader jsonToAvroReader) {
        super(Collection.class);
        this.jsonToAvroReader = jsonToAvroReader;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object convertValue(Schema.Field field, Schema schema, Collection value, Deque<String> path, boolean silently) {
        return ((Collection<Object>) value).stream()
                .map(item -> this.jsonToAvroReader.read(field, schema.getElementType(), item, path, false))
                .collect(toList());
    }

    @Override
    public boolean canManage(Schema schema, Deque<String> path) {
        return schema.getType().equals(Schema.Type.ARRAY);
    }
}
