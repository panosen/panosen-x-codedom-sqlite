package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.GroupBy;
import com.panosen.codedom.sqlite.Having;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupByBuilder {

    private final GroupBy groupBy;

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public GroupByBuilder(GroupBy groupBy) {
        this.groupBy = groupBy;
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

    public GroupByBuilder having(Having having) {
        groupBy.setHaving(having);
        return this;
    }

    public HavingBuilder having() {
        Having having = new Having();
        HavingBuilder havingBuilder = new HavingBuilder(having);
        groupBy.setHaving(having);
        return havingBuilder;
    }
}
