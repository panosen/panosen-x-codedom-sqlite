package com.panosen.codedom.sqlite;

import java.util.List;

public class InCondition extends Condition {

    private String fieldName;

    private List<Object> values;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
