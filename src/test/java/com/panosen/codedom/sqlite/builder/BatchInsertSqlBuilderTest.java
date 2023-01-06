package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Parameters;
import com.panosen.codedom.sqlite.engine.BatchInsertSqlEngine;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;

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

        Parameters parameters = generationResponse.getParameters();
        Assert.assertEquals(4, parameters.size());

        Assert.assertEquals("zhangsan", parameters.get(0).getValue());

        Assert.assertEquals(17, parameters.get(1).getValue());

        Assert.assertEquals("lisi", parameters.get(2).getValue());

        Assert.assertEquals(19, parameters.get(3).getValue());
    }
}