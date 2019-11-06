package com.uifuture.chapter17;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * MyBatis Plus代码生成
 */
public class CodeGenerator {

    /**
     * 注意，这里的mysql链接和依赖是mysql8.0的配置
     *
     * @param args
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "ssm-mybatis-transaction";
        //文件生成目录
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("chenhx");
        gc.setOpen(false);
        //实体类后缀
        gc.setEntityName("%sEntity");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/ssm_test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
        //mysql8.0+的驱动地址
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        //配置父类包
        packageConfig.setParent("com.uifuture.chapter17");
        //配置实体包
        packageConfig.setEntity("domain.entity");
        mpg.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置实体类的父类
        strategy.setSuperEntityClass("com.uifuture.chapter17.domain.base.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //表名
        strategy.setInclude("users");
        //设置父类公共属性，注意是数据库列名
        strategy.setSuperEntityColumns("id", "create_time", "update_time", "delete_time");
        strategy.setEntityColumnConstant(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        mpg.execute();
    }
}
