package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.engine.BatchInsertSqlEngine;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;
import java.util.List;

public class BatchInsertSqlBuilderTest {

    @Test
    public void test() {
        BatchInsertSqlBuilder batchInsertSqlBuilder = new BatchInsertSqlBuilder()
                .intoTable("student");

        batchInsertSqlBuilder
                .addColumn("name", Types.VARCHAR)
                .addColumn("age", Types.INTEGER);

        batchInsertSqlBuilder.addBatch()
                .value("name", "zhangsan")
                .value("age", 17);

        batchInsertSqlBuilder.addBatch()
                .value("age", 19)
                .value("name", "lisi");

        GenerationResponse generationResponse = new BatchInsertSqlEngine().generate(batchInsertSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "insert into `student` (`name`, `age`) values (?, ?), (?, ?);";

        Assert.assertEquals(expected, actual);

        Object[] parameters = generationResponse.getArgs();
        Assert.assertEquals(4, parameters.length);

        Assert.assertEquals("zhangsan", parameters[0]);
        Assert.assertEquals(17, parameters[1]);
        Assert.assertEquals("lisi", parameters[2]);
        Assert.assertEquals(19, parameters[3]);
    }
}