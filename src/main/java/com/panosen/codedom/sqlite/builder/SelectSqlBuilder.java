package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.GroupBy;
import com.panosen.codedom.sqlite.OrderBy;
import com.panosen.codedom.sqlite.SelectSql;
import com.panosen.codedom.sqlite.Where;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectSqlBuilder {

    private final SelectSql selectSql = new SelectSql();

    public SelectSql getSelectSql() {
        return selectSql;
    }

    public SelectSqlBuilder column(String column) {
        if (selectSql.getColumnNameList() == null) {
            selectSql.setColumnNameList(new ArrayList<>());
        }
        selectSql.getColumnNameList().add(column);
        return this;
    }

    public SelectSqlBuilder columns(String... columns) {
        if (selectSql.getColumnNameList() == null) {
            selectSql.setColumnNameList(new ArrayList<>());
        }
        selectSql.getColumnNameList().addAll(Arrays.asList(columns));
        return this;
    }

    public SelectSqlBuilder from(String tableName) {
        return from(tableName, null);
    }

    public SelectSqlBuilder from(String tableName, String tableSchema) {
        selectSql.setTableSchema(tableSchema);
        selectSql.setTableName(tableName);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitSize) {
        selectSql.setLimitFrom(null);
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitFrom, Integer limitSize) {
        selectSql.setLimitFrom(limitFrom);
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public SelectSqlBuilder orderBy(String columnName) {
        return orderBy(columnName, false);
    }

    public SelectSqlBuilder orderBy(String columnName, Boolean desc) {
        if (selectSql.getOrderByList() == null) {
            selectSql.setOrderByList(new ArrayList<>());
        }

        OrderBy orderBy = new OrderBy();
        orderBy.setColumnName(columnName);
        orderBy.setDesc(desc);
        selectSql.getOrderByList().add(orderBy);

        return this;
    }

    public GroupByBuilder groupBy() {
        GroupBy groupBy = new GroupBy();
        selectSql.setGroupBy(groupBy);
        return new GroupByBuilder(groupBy);
    }

    public WhereBuilder where() {
        Where where = new Where();
        selectSql.setWhere(where);
        return new WhereBuilder(where);
    }
}
