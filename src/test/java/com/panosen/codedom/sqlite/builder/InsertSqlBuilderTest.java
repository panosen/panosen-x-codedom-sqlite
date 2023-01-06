package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Parameters;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import com.panosen.codedom.sqlite.engine.InsertSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class InsertSqlBuilderTest {

    @Test
    public void build1() {

        InsertSqlBuilder insertSqlBuilder = new InsertSqlBuilder()
                .intoTable("student")
                .value("name", "zhangsan")
                .value("age", 17);

        GenerationResponse generationResponse = new InsertSqlEngine().generate(insertSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "insert into `student` (`name`, `age`) values (?, ?);";

        Assert.assertEquals(expected, actual);

        Parameters parameters = generationResponse.getParameters();
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals("zhangsan", parameters.get(0).getValue());
        Assert.assertEquals(17, parameters.get(1).getValue());
    }
}