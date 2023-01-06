package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Batch;

import java.util.HashMap;

public class BatchBuilder {

    private final Batch batch = new Batch();

    public Batch getBatch() {
        return batch;
    }

    public BatchBuilder value(String columnName, Object value) {

        if (batch.getValues() == null) {
            batch.setValues(new HashMap<>());
        }

        batch.getValues().putIfAbsent(columnName, value);

        return this;
    }
}
