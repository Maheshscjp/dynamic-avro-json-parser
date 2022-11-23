package com.purple.json.converter.types;

import org.apache.avro.Schema;

public abstract class AbstractIntDateTimeConverter extends AbstractDateTimeConverter {

    @Override
    protected Object convertNumber(Number numberValue) {
        return numberValue.intValue();
    }

    @Override
    protected Schema.Type getUnderlyingSchemaType() {
        return Schema.Type.INT;
    }

}
