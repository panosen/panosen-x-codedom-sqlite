package com.panosen.codedom.sqlite;

/**
 * 用于update语句
 */
public class Statement {

    private String fieldName;

    private Object value;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
