package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.DeleteSql;

public class DeleteSqlBuilder {

    private final DeleteSql deleteSql = new DeleteSql();

    public DeleteSql getDeleteSql() {
        return deleteSql;
    }

    public DeleteSqlBuilder from(String tableName) {
        deleteSql.setTableName(tableName);
        return this;
    }

    public ConditionsBuilder where() {
        ConditionsBuilder conditionsBuilder = new ConditionsBuilder();
        deleteSql.setWhere(conditionsBuilder.getConditionStatement());
        return conditionsBuilder;
    }
}
