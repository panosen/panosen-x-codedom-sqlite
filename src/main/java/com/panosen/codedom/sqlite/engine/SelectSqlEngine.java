package com.panosen.codedom.sqlite.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.sqlite.GroupBy;
import com.panosen.codedom.sqlite.Having;
import com.panosen.codedom.sqlite.OrderBy;
import com.panosen.codedom.sqlite.SelectSql;
import com.panosen.codedom.sqlite.builder.SelectSqlBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SelectSqlEngine extends SqlEngine {

    public GenerationResponse generate(SelectSqlBuilder selectSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        List<Object> parameters = new ArrayList<>();

        generate(selectSqlBuilder.getSelectSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setArgs(parameters.toArray());

        return generationResponse;
    }

    public void generate(SelectSql selectSql, CodeWriter codeWriter, List<Object> parameters) {
        // select
        codeWriter.write(Keywords.SELECT).write(Marks.WHITESPACE);

        // columns
        if (selectSql.getColumnNameList() != null && !selectSql.getColumnNameList().isEmpty()) {
            for (int index = 0, length = selectSql.getColumnNameList().size(); index < length; index++) {
                codeWriter.write(Marks.BACKQUOTE).write(selectSql.getColumnNameList().get(index)).write(Marks.BACKQUOTE);
                if (index < length - 1) {
                    codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                }
            }
        } else {
            codeWriter.write(Marks.STAR);
        }

        // from
        codeWriter.write(Marks.WHITESPACE).write(Keywords.FROM).write(Marks.WHITESPACE);

        //tableSchema
        if (selectSql.getTableSchema() != null && !selectSql.getTableSchema().isEmpty()) {
            codeWriter.write(Marks.BACKQUOTE).write(selectSql.getTableSchema()).write(Marks.BACKQUOTE);
            codeWriter.write(Marks.DOT);
        }

        // tableName
        codeWriter.write(Marks.BACKQUOTE).write(selectSql.getTableName()).write(Marks.BACKQUOTE);

        // where
        generateWhere(selectSql.getWhere(), codeWriter, parameters);

        // order by
        generateOrderBy(selectSql.getOrderByList(), codeWriter);

        // group by
        generateGroupBy(selectSql.getGroupBy(), codeWriter, parameters);

        // limit
        if (selectSql.getLimitSize() != null && selectSql.getLimitSize() > 0) {
            codeWriter.write(Marks.WHITESPACE).write(Keywords.LIMIT);

            if (selectSql.getLimitFrom() != null && selectSql.getLimitFrom() > 0) {
                codeWriter.write(Marks.WHITESPACE).write(selectSql.getLimitFrom().toString()).write(Marks.COMMA);
            }

            codeWriter.write(Marks.WHITESPACE).write(selectSql.getLimitSize().toString());
        }

        // ;
        codeWriter.write(Marks.SEMICOLON);
    }

    private void generateOrderBy(List<OrderBy> orderByList, CodeWriter codeWriter) {
        if (orderByList == null || orderByList.isEmpty()) {
            return;
        }
        codeWriter.write(Marks.WHITESPACE).write(Keywords.ORDER_BY);
        for (int index = 0, length = orderByList.size(); index < length; index++) {
            codeWriter.write(Marks.WHITESPACE);
            OrderBy orderBy = orderByList.get(index);
            codeWriter.write(Marks.BACKQUOTE).write(orderBy.getColumnName()).write(Marks.BACKQUOTE);
            if (orderBy.getDesc() != null && orderBy.getDesc()) {
                codeWriter.write(Marks.WHITESPACE).write(Keywords.DESC);
            }
            if (index < length - 1) {
                codeWriter.write(Marks.COMMA);
            }
        }
    }

    private void generateGroupBy(GroupBy groupBy, CodeWriter codeWriter, List<Object> parameters) {
        if (groupBy == null) {
            return;
        }

        if (groupBy.getColumnNames() == null || groupBy.getColumnNames().isEmpty()) {
            return;
        }

        generateGroupByColumns(groupBy.getColumnNames(), codeWriter);

        if (groupBy.getHaving() != null) {
            generateHaving(groupBy.getHaving(), codeWriter, parameters);
        }
    }

    private void generateHaving(Having having, CodeWriter codeWriter, List<Object> parameters) {
        codeWriter.write(Marks.WHITESPACE).write(Keywords.HAVING).write(Marks.WHITESPACE);
        generateCondition(having.getCondition(), codeWriter, parameters, false);
    }

    private void generateGroupByColumns(List<String> columnNames, CodeWriter codeWriter) {
        codeWriter.write(Marks.WHITESPACE).write(Keywords.GROUP_BY);
        for (int index = 0, length = columnNames.size(); index < length; index++) {
            codeWriter.write(Marks.WHITESPACE);
            String groupBy = columnNames.get(index);
            codeWriter.write(Marks.BACKQUOTE).write(groupBy).write(Marks.BACKQUOTE);
            if (index < length - 1) {
                codeWriter.write(Marks.COMMA);
            }
        }
    }
}
