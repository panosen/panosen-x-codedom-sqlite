package com.panosen.codedom.sqlite.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.sqlite.InsertSql;
import com.panosen.codedom.sqlite.builder.InsertSqlBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class InsertSqlEngine {

    public GenerationResponse generate(InsertSqlBuilder insertSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        List<Object> parameters = new ArrayList<>();

        generate(insertSqlBuilder.getInsertSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setArgs(parameters.toArray());

        return generationResponse;
    }

    private void generate(InsertSql insertSql, CodeWriter codeWriter, List<Object> parameters) {

        //insert into
        codeWriter.write(Keywords.INSERT).write(Marks.WHITESPACE).write(Keywords.INTO).write(Marks.WHITESPACE);

        // tableName
        codeWriter.write(Marks.BACKQUOTE).write(insertSql.getTableName()).write(Marks.BACKQUOTE).write(Marks.WHITESPACE);

        //(
        codeWriter.write(Marks.LEFT_BRACKET);

        //columns
        if (insertSql.getColumnNameList() != null && !insertSql.getColumnNameList().isEmpty()) {
            for (int index = 0, length = insertSql.getColumnNameList().size(); index < length; index++) {
                codeWriter.write(Marks.BACKQUOTE).write(insertSql.getColumnNameList().get(index)).write(Marks.BACKQUOTE);
                if (index < length - 1) {
                    codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                }
            }
        }

        //)
        codeWriter.write(Marks.RIGHT_BRACKET).write(Marks.WHITESPACE);

        //values
        codeWriter.write(Keywords.VALUES).write(Marks.WHITESPACE);

        //(
        codeWriter.write(Marks.LEFT_BRACKET);

        //column values
        if (insertSql.getColumnNameList() != null && !insertSql.getColumnNameList().isEmpty()) {
            for (int index = 0, length = insertSql.getColumnNameList().size(); index < length; index++) {
                codeWriter.write(Marks.QUESTION);
                if (index < length - 1) {
                    codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                }
            }
        }

        //);
        codeWriter.write(Marks.RIGHT_BRACKET).write(Marks.SEMICOLON);

        if (insertSql.getColumnNameList() != null && !insertSql.getColumnNameList().isEmpty()) {
            for (int index = 0, length = insertSql.getColumnNameList().size(); index < length; index++) {
                parameters.add(insertSql.getValueList().get(index));
            }
        }
    }
}
