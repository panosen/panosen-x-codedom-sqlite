package com.panosen.codedom.sqlite;

public abstract class ConditionStatement {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
