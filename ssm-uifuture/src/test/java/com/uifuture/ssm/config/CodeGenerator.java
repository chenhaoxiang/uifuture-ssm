/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 自动生成模块代码
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author chenhx
 * @version CodeGenerator.java, v 0.1 2019-09-11 23:18 chenhx
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String projectPath = System.getProperty("user.dir");
        //子模块
        String model = "/ssm-uifuture";
        projectPath = projectPath + model;
        //1.全局配置
        GlobalConfig config = new GlobalConfig();
        //生成的service接口名字首字母是否为I，这样设置就没有I  配置 %s 为占位符
        config.setServiceName("%sService");
        //设置生成的Service接口的名字的首字母是否为“I”即生成IEmployeeService
        config.setServiceImplName("%sServiceImpl");
        config.setEntityName("%sEntity");
        //是否打开输出目录
        config.setOpen(false);
        //是否支持AR模式
        config.setActiveRecord(false);
        //设置作者名字
        config.setAuthor("chenhx");
        //设置文件生成的路径
        config.setOutputDir(projectPath + "/src/main/java");
        //是否文件覆盖
        config.setFileOverride(true);
        //主键策略
        config.setIdType(IdType.AUTO);
        //是否生成基础的映射结果
        config.setBaseResultMap(true);
        //生成基础的SQL列，即SQL片段
        config.setBaseColumnList(true);


        //2.数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)//设置数据库的类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/uifuture?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&remarks=true")
                .setUsername("root")
                .setPassword("12345678");


        //3.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.uifuture.ssm")     //父包
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
        ;


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String path = projectPath;
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                if (StringUtils.isEmpty(pkConfig.getModuleName())) {
                    return path + "/src/main/resources/mapper/" + tableInfo.getMapperName() + StringPool.DOT_XML;
                }
                return path + "/src/main/resources/mapper/" + pkConfig.getModuleName()
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.uifuture.ssm.base.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.uifuture.ssm.base.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id",
                "create_time",
                "update_time",
                "delete_time");

        //生成的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //为表添加前缀
        strategy.setTablePrefix(pkConfig.getModuleName() + "_");
        //是否生成字段常量
        strategy.setEntityColumnConstant(true);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //跳过controller生成，设置不覆盖文件
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);

        //5.整合配置
        mpg.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(strategy)
                .setPackageInfo(pkConfig);
        mpg.setTemplate(templateConfig);
        mpg.setCfg(cfg);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

}
