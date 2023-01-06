package com.panosen.codedom.sqlite.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.sqlite.Batch;
import com.panosen.codedom.sqlite.BatchInsertSql;
import com.panosen.codedom.sqlite.builder.BatchInsertSqlBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BatchInsertSqlEngine {

    public GenerationResponse generate(BatchInsertSqlBuilder batchInsertSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        List<Object> parameters = new ArrayList<>();

        generate(batchInsertSqlBuilder.getBatchInsertSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setArgs(parameters.toArray());

        return generationResponse;
    }

    private void generate(BatchInsertSql batchInsertSql, CodeWriter codeWriter, List<Object> parameters) {

        if (batchInsertSql.getBatchList() == null || batchInsertSql.getBatchList().isEmpty()) {
            return;
        }

        //insert into
        codeWriter.write(Keywords.INSERT).write(Marks.WHITESPACE).write(Keywords.INTO).write(Marks.WHITESPACE);

        // tableName
        codeWriter.write(Marks.BACKQUOTE).write(batchInsertSql.getTableName()).write(Marks.BACKQUOTE).write(Marks.WHITESPACE);

        //(
        codeWriter.write(Marks.LEFT_BRACKET);

        //columns
        if (batchInsertSql.getColumnMap() != null && !batchInsertSql.getColumnMap().isEmpty()) {
            Iterator<String> iterator = batchInsertSql.getColumnMap().keySet().iterator();
            boolean hasNext = iterator.hasNext();
            while (hasNext) {
                codeWriter.write(Marks.BACKQUOTE).write(iterator.next()).write(Marks.BACKQUOTE);

                hasNext = iterator.hasNext();
                if (hasNext) {
                    codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                }
            }
        }

        //)
        codeWriter.write(Marks.RIGHT_BRACKET).write(Marks.WHITESPACE);

        //values
        codeWriter.write(Keywords.VALUES).write(Marks.WHITESPACE);

        Iterator<Batch> iterator = batchInsertSql.getBatchList().iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            xxxxx(codeWriter, parameters, batchInsertSql.getColumnMap(), iterator.next().getValues());

            hasNext = iterator.hasNext();
            if (hasNext) {
                codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
            }
        }

        codeWriter.write(Marks.SEMICOLON);
    }

    private void xxxxx(CodeWriter codeWriter, List<Object> parameters,
                       Map<String, Integer> columnMap, Map<String, Object> values) {

        //(
        codeWriter.write(Marks.LEFT_BRACKET);

        Iterator<Map.Entry<String, Integer>> iterator = columnMap.entrySet().iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            Map.Entry<String, Integer> entry = iterator.next();

            parameters.add(values.get(entry.getKey()));
            codeWriter.write(Marks.QUESTION);

            hasNext = iterator.hasNext();
            if (hasNext) {
                codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
            }
        }

        //);
        codeWriter.write(Marks.RIGHT_BRACKET);
    }
}
