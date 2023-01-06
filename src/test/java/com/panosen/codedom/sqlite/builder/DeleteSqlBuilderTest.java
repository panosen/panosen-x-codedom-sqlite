package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.engine.DeleteSqlEngine;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

public class DeleteSqlBuilderTest {

    @Test
    public void test1() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "delete from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        deleteSqlBuilder.where().equal("id", 13);

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();
        String expected = "delete from `student` where `id` = ?;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.length);
        Assert.assertEquals(13, parameters[0]);
    }

    @Test
    public void test3() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        deleteSqlBuilder.where().must()
                .equal("m", 7)
                .notEqual("n", 9)
                .gt("a", 15)
                .gte("b", 17)
                .lt("c", 19)
                .lte("d", 21);

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();
        String expected = "delete from `student` where `m` = ? and `n` != ? and `a` > ? and `b` >= ? and `c` < ? and `d` <= ?;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(6, parameters.length);
        Assert.assertEquals(7, parameters[0]);
        Assert.assertEquals(9, parameters[1]);
        Assert.assertEquals(15, parameters[2]);
        Assert.assertEquals(17, parameters[3]);
        Assert.assertEquals(19, parameters[4]);
        Assert.assertEquals(21, parameters[5]);
    }
}