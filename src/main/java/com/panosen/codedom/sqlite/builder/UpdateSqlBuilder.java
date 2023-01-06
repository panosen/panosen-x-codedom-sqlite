package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Statements;
import com.panosen.codedom.sqlite.UpdateSql;
import com.panosen.codedom.sqlite.Where;

public class UpdateSqlBuilder {

    private final UpdateSql updateSql = new UpdateSql();

    public UpdateSql getUpdateSql() {
        return updateSql;
    }

    public UpdateSqlBuilder table(String tableName) {
        updateSql.setTableName(tableName);
        return this;
    }

    public WhereBuilder where() {
        Where where = new Where();
        updateSql.setWhere(where);
        return new WhereBuilder(where);
    }

    public StatementsBuilder set() {
        Statements statements = new Statements();
        updateSql.setStatements(statements);
        return new StatementsBuilder(statements);
    }
}
