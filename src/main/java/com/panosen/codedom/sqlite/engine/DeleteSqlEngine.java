package com.panosen.codedom.sqlite.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.sqlite.DeleteSql;
import com.panosen.codedom.sqlite.builder.DeleteSqlBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class DeleteSqlEngine extends SqlEngine {

    public GenerationResponse generate(DeleteSqlBuilder deleteSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        List<Object> parameters = new ArrayList<>();

        generate(deleteSqlBuilder.getDeleteSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setArgs(parameters.toArray());

        return generationResponse;
    }

    private void generate(DeleteSql deleteSql, CodeWriter codeWriter, List<Object> parameters) {

        // delete from
        codeWriter.write(Keywords.DELETE).write(Marks.WHITESPACE).write(Keywords.FROM).write(Marks.WHITESPACE);

        // `table`
        codeWriter.write(Marks.BACKQUOTE).write(deleteSql.getTableName()).write(Marks.BACKQUOTE);

        // where
        generateWhere(deleteSql.getWhere(), codeWriter, parameters);

        codeWriter.write(Marks.SEMICOLON);
    }
}
