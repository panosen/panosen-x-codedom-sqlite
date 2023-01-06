package com.panosen.codedom.sqlite.builder;

import com.panosen.codedom.sqlite.ConditionStatement;
import com.panosen.codedom.sqlite.MustConditions;
import com.panosen.codedom.sqlite.ShouldConditions;
import com.panosen.codedom.sqlite.engine.ConditionsEngine;
import com.panosen.codedom.sqlite.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ConditionSelectSqlBuilderWhereTest {

    @Test
    public void build4() {

        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder
                .equal("age", 12);

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`age` = ?";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.length);
    }

    @Test
    public void build5() {

        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder.must()
                .equal("x", 12)
                .equal("y", 13);

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`x` = ? and `y` = ?";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
    }

    @Test
    public void build6() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        MustConditions must = whereBuilder.must();
        must.should()
                .equal("x", 12)
                .equal("y", 13);
        must.should()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "(`x` = ? or `y` = ?) and (`a` = ? or `b` = ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
        Assert.assertEquals(14, parameters[2]);
        Assert.assertEquals(15, parameters[3]);
    }

    @Test
    public void build7() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        ShouldConditions should = whereBuilder.should();
        should.must()
                .equal("x", 12)
                .equal("y", 13);
        should.must()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "(`x` = ? and `y` = ?) or (`a` = ? and `b` = ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
        Assert.assertEquals(14, parameters[2]);
        Assert.assertEquals(15, parameters[3]);
    }

    @Test
    public void build8() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder
                .in("age", 12, 13);

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`age` in (?, ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
    }

    @Test
    public void build9() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder
                .in("age", Arrays.asList(12, 13));

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`age` in (?, ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals(12, parameters[0]);
        Assert.assertEquals(13, parameters[1]);
    }

    @Test
    public void build10() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder
                .in("age", Arrays.asList("A", "B"));

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`age` in (?, ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals("A", parameters[0]);
        Assert.assertEquals("B", parameters[1]);
    }

    @Test
    public void build11() {
        
        ConditionsBuilder whereBuilder = new ConditionsBuilder();

        whereBuilder
                .in("name", Arrays.asList("A", "B"));

        GenerationResponse generationResponse = new ConditionsEngine().generate(whereBuilder);
        String actual = generationResponse.getSql();
        Object[] parameters = generationResponse.getArgs();

        String expected = "`name` in (?, ?)";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.length);
        Assert.assertEquals("A", parameters[0]);
        Assert.assertEquals("B", parameters[1]);
    }
}
