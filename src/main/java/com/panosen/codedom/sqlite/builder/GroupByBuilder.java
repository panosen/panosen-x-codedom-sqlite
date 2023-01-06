package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.GroupBy;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupByBuilder {

    private final GroupBy groupBy = new GroupBy();

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public GroupByBuilder column(String column) {
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(new ArrayList<>());
        }
        groupBy.getColumnNames().add(column);
        return this;
    }

    public GroupByBuilder columns(String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(new ArrayList<>());
        }
        groupBy.getColumnNames().addAll(Arrays.asList(columns));
        return this;
    }

    public ConditionsBuilder having() {
        ConditionsBuilder conditionsBuilder = new ConditionsBuilder();
        groupBy.setHaving(conditionsBuilder.getConditionStatement());
        return conditionsBuilder;
    }
}
