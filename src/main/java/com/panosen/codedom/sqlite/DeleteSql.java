package com.panosen.codedom.sqlite;

/**
 * delete from table where 1 =1
 */
public class DeleteSql extends Sql {

    /**
     * delete from ${tableName};
     */
    private String tableName;

    /**
     * where
     */
    private ConditionStatement where;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ConditionStatement getWhere() {
        return where;
    }

    public void setWhere(ConditionStatement where) {
        this.where = where;
    }
}
