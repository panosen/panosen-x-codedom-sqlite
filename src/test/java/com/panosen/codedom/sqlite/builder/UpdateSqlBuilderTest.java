package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.Parameters;
import com.panosen.codedom.sqlite.engine.UpdateSqlEngine;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;

public class UpdateSqlBuilderTest {

    @Test
    public void test2() {
        UpdateSqlBuilder updateSqlBuilder = new UpdateSqlBuilder()
                .table("student");

        updateSqlBuilder.set()
                .set("age", 17)
                .set("value", 19);

        updateSqlBuilder.where().equal("id", 13);

        GenerationResponse generationResponse = new UpdateSqlEngine().generate(updateSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();
        String expected = "update `student` set `age` = ?, `value` = ? where `id` = ?;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(3, parameters.size());
        Assert.assertEquals(17, parameters.get(0).getValue());
        Assert.assertEquals(19, parameters.get(1).getValue());
        Assert.assertEquals(13, parameters.get(2).getValue());
    }
}