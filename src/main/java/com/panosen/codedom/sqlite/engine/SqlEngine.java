package com.panosen.codedom.sqlite.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.sqlite.ConditionStatement;

import java.util.List;

public abstract class SqlEngine {

    protected void generateWhere(ConditionStatement where, CodeWriter codeWriter, List<Object> parameters) {
        if (where == null || where.getCondition() == null) {
            return;
        }

        codeWriter.write(Marks.WHITESPACE).write(Keywords.WHERE).write(Marks.WHITESPACE);

        new ConditionsEngine().generate(where.getCondition(), codeWriter, parameters, false);
    }
}
