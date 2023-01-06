package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.BatchInsertSql;

import java.util.ArrayList;
import java.util.HashMap;

public class BatchInsertSqlBuilder {

    private final BatchInsertSql batchInsertSql = new BatchInsertSql();

    public BatchInsertSql getBatchInsertSql() {
        return batchInsertSql;
    }

    public BatchInsertSqlBuilder intoTable(String tableName) {
        batchInsertSql.setTableName(tableName);
        return this;
    }

    public BatchBuilder addBatch() {

        if (batchInsertSql.getBatchList() == null) {
            batchInsertSql.setBatchList(new ArrayList<>());
        }

        BatchBuilder batchBuilder = new BatchBuilder();
        batchInsertSql.getBatchList().add(batchBuilder.getBatch());

        return batchBuilder;
    }

    public BatchInsertSqlBuilder addColumn(String name, int dbType) {
        if (batchInsertSql.getColumnMap() == null) {
            batchInsertSql.setColumnMap(new HashMap<>());
        }
        batchInsertSql.getColumnMap().putIfAbsent(name, dbType);
        return this;
    }
}
