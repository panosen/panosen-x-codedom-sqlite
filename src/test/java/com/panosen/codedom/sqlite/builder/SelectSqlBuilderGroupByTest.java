package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.engine.GenerationResponse;
import com.panosen.codedom.sqlite.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderGroupByTest {

    @Test
    public void simple() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student");

        selectSqlBuilder.groupBy().columns("name", "age");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` group by `name`, `age`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void having() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student");

        selectSqlBuilder
                .groupBy().columns("name", "age")
                .having().equal("a", 14);

        GenerationResponse response = new SelectSqlEngine().generate(selectSqlBuilder);

        String actual = response.getSql();
        String expected = "select * from `student` group by `name`, `age` having `a` = ?;";
        Assert.assertEquals(expected, actual);

        Object[] parameters = response.getArgs();

        Assert.assertEquals(1, parameters.length);
        Assert.assertEquals(14, parameters[0]);
    }

}
