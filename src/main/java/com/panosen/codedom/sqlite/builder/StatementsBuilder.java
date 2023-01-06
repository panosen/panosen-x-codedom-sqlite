package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Statement;
import com.panosen.codedom.sqlite.Statements;

import java.util.ArrayList;

public class StatementsBuilder {

    private final Statements statements;

    public StatementsBuilder(Statements statements) {
        this.statements = statements;
    }

    public StatementsBuilder set(String fieldName, Object value) {
        if (statements.getStatementList() == null) {
            statements.setStatementList(new ArrayList<>());
        }
        Statement statement = new Statement();
        statement.setFieldName(fieldName);
        statement.setValue(value);
        statements.getStatementList().add(statement);

        return this;
    }
}
