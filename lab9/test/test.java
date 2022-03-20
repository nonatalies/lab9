package test;

import org.junit.Assert;
import org.junit.Test;

import numberone.*;

public class test {
    
    @Test
    public void sum() {
        Assert.assertEquals("5/6", calculator.main("1/2+1/3"));
    }

    @Test
    public void sub() {
        Assert.assertEquals("1/6", calculator.main("1/2-1/3"));
    }

    @Test
    public void multi() {
        Assert.assertEquals("1/6", calculator.main("1/2*1/3"));
    }

    @Test
    public void div() {
        Assert.assertEquals("3/2", calculator.main("1/2:1/3"));
    }

    @Test
    public void samePriority() {
        Assert.assertEquals("-2/3", calculator.main("1/2 + 1/3 - 3/2"));
    }

    @Test
    public void diffPriority1() {
        Assert.assertEquals("3/4", calculator.main("1/2 + 1/3 * 3/4"));
    }

    @Test
    public void diffPriority2() {
        Assert.assertEquals("-1/4", calculator.main("-1/-3 * 3/4 - 1/2"));
    }

    @Test
    public void diffPriority3() {
        Assert.assertEquals("1/4", calculator.main("-1/-3 * 3/4 - 1/2 + 2/4"));
    }

    @Test
    public void diffPriority4() {
        Assert.assertEquals("-5/4", calculator.main("-1/3 * 3/4 - 1/2*2/1"));
    }

    @Test
    public void brakets1() {
        Assert.assertEquals("-3/2", calculator.main("(-1/3 * 3/4 - 1/2)*2/1"));
    }

    @Test
    public void brakets2() {
        Assert.assertEquals("-3/2", calculator.main("((-1/3 * 3/4 - 1/2)*2/1)"));
    }

    @Test (expected = IllegalArgumentException.class) 
    public void incorrectSumbols() {
        calculator.main("hello -1/3 * 3/4");
    }

    @Test (expected = IllegalArgumentException.class) 
    public void incorrectFractions() {
        calculator.main("--1/3 * 3/4");
    }

    @Test (expected = IllegalArgumentException.class) 
    public void divideZero() {
        calculator.main("-1/0 * 3/4");
    }

    @Test (expected = IllegalArgumentException.class) 
    public void incorrectValue1() {
        calculator.main("-1/3 * 3/4 - 1/2*:2/1");
    }

    @Test (expected = IllegalArgumentException.class) 
    public void incorrectValue2() {
        calculator.main("(-1/3 * 3/4 - 1/2)*2/1)");
    }
}
