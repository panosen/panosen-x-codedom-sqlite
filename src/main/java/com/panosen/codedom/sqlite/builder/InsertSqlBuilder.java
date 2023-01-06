package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.InsertSql;

import java.util.ArrayList;

public class InsertSqlBuilder {

    private final InsertSql insertSql = new InsertSql();

    public InsertSql getInsertSql() {
        return insertSql;
    }

    public InsertSqlBuilder intoTable(String tableName) {
        insertSql.setTableName(tableName);
        return this;
    }

    public InsertSqlBuilder value(String columnName, Object value) {
        if (insertSql.getColumnNameList() == null) {
            insertSql.setColumnNameList(new ArrayList<>());
        }
        if (insertSql.getValueList() == null) {
            insertSql.setValueList(new ArrayList<>());
        }

        insertSql.getColumnNameList().add(columnName);
        insertSql.getValueList().add(value);

        return this;
    }
}
