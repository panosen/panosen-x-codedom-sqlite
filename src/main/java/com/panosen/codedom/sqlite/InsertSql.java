package com.panosen.codedom.sqlite;

import java.util.List;

public class InsertSql extends Sql {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    /**
     * column names
     */
    private List<String> columnNameList;

    /**
     * values to insert
     */
    private List<Object> valueList;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }
}
