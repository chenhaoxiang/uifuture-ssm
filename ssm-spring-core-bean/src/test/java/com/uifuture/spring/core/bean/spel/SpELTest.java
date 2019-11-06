package com.uifuture.spring.core.bean.spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 对应章节15.8  SpEL表达式
 *
 * @author chenhx
 * @version SpELTest.java, v 0.1 2019-05-08 23:56 chenhx
 */
public class SpELTest {

    /**
     * 代码清单15-73
     */
    @Test
    public void helloWorld1() {
        ExpressionParser parser = new SpelExpressionParser();
        //连接字符串，并且通过#end赋值变量
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        Assert.assertEquals("Hello World!", expression.getValue(context));
    }

    /**
     * 代码清单15-75
     * 演示字面量表达式的double和null类型
     */
    @Test
    public void spelGrammarTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("2.5");
        System.out.println(expression.getValue(Double.class));
        expression = parser.parseExpression("null");
        Object obj = expression.getValue();
        //对象为null
        System.out.println(obj == null);
        expression = parser.parseExpression("null");
        String str = expression.getValue(String.class);
        //字段串的值为null
        System.out.println(str);
    }

    /**
     * 代码清单15-76
     * 演示算术运算表达式的除和求余（MOD）
     */
    @Test
    public void spelArithmeticTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("5.0/3.0");
        System.out.println(expression.getValue(Double.class));
        expression = parser.parseExpression("7 MOD 4");
        System.out.println(expression.getValue(Integer.class));
    }


    /**
     * 代码清单15-77
     * 演示关系表达式大于等于和区间
     */
    @Test
    public void spelRelationalTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("4>=5");
        System.out.println(expression.getValue(Boolean.class));
        expression = parser.parseExpression("1 between {1, 2}");
        System.out.println(expression.getValue(Boolean.class));
    }

    /**
     * 演示List
     */
    @Test
    public void spelListTest() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        Expression expression = parser.parseExpression("{1,2,3,4}");
        // 从Expression中获取list
        List numbers = (List) expression.getValue(context);
        System.out.println(numbers);
        //集合嵌套
        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'c','d'}}").getValue(context);
        System.out.println(listOfLists);
    }

    /**
     * 演示Map
     */
    @Test
    public void spelMapTest() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        Map inventorInfo = (Map) parser.parseExpression("{name:'jack',age:22}").getValue(context);
        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'tom',last:'ming'},age:{first:11,last:22}}").getValue(context);
        System.out.println(inventorInfo);
        System.out.println(mapOfMaps);
    }


    /**
     * 演示类类型的使用
     */
    @Test
    public void testClassTypeExpressionTest() {
        ExpressionParser parser = new SpelExpressionParser();
        //其他类的访问
        Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
        Assert.assertEquals(Date.class, dateClass);
        //lang包下的类访问
        Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
        Assert.assertEquals(String.class, stringClass);
        //类静态字段访问
        int integer = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        Assert.assertEquals(Integer.MAX_VALUE, integer);
        //类静态方法调用
        int intParse = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        Assert.assertEquals(1, intParse);
    }

    /**
     * 类的实例化
     */
    @Test
    public void testConstructorExpressionTest() {
        ExpressionParser parser = new SpelExpressionParser();
        String string = parser.parseExpression("new String('类的实例化')").getValue(String.class);
        Assert.assertEquals("类的实例化", string);
        Date date = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        System.out.println(date);
    }

    /**
     * 变量的使用
     */
    @Test
    public void testVariablesTest() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("variable", "spring");
        context.setVariable(" name", "#variable");
        String name = parser.parseExpression("#variable").getValue(context, String.class);
        System.out.println(name);

        context = new StandardEvaluationContext("mybatis");
        System.out.println(parser.parseExpression("#root").getValue(context, String.class));
        System.out.println(parser.parseExpression("#this").getValue(context, String.class));
    }

}