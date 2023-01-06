package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.MustConditions;
import com.panosen.codedom.sqlite.ShouldConditions;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import com.panosen.codedom.sqlite.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SelectSqlBuilderWhereTest {

    @Test
    public void build4() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .equal("age", 12);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `age` = ? limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.length);
    }

    @Test
    public void build5() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where().must()
                .equal("x", 12)
                .equal("y", 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `x` = ? and `y` = ? limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
    }

    @Test
    public void build6() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        MustConditions must = selectSqlBuilder.where().must();
        must.should()
                .equal("x", 12)
                .equal("y", 13);
        must.should()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where (`x` = ? or `y` = ?) and (`a` = ? or `b` = ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
        Assert.assertEquals(14, parameters[2]);
        Assert.assertEquals(15, parameters[3]);
    }

    @Test
    public void build7() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        ShouldConditions should = selectSqlBuilder.where().should();
        should.must()
                .equal("x", 12)
                .equal("y", 13);
        should.must()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where (`x` = ? and `y` = ?) or (`a` = ? and `b` = ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
        Assert.assertEquals(14, parameters[2]);
        Assert.assertEquals(15, parameters[3]);
    }

    @Test
    public void build8() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", 12, 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `age` in (?, ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
    }

    @Test
    public void build9() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", Arrays.asList(12, 13));

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `age` in (?, ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
    }

    @Test
    public void build10() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", Arrays.asList("A", "B"));

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `age` in (?, ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals("A", parameters[0]);
        Assert.assertEquals("B", parameters[1]);
    }

    @Test
    public void build11() {

        List<String> names = Arrays.asList("A", "B");

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("name", names);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "select * from `student` where `name` in (?, ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals("A", parameters[0]);
        Assert.assertEquals("B", parameters[1]);
    }
}
