/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture;

import org.mybatis.generator.api.ShellRunner;

/**
 * generator使用Java方式运行
 * 运行方法二 ShellRunner方式运行
 *
 * @author chenhx
 * @version GeneratorMain2.java, v 0.1 2018-12-04 下午 7:13 chenhx
 */
public class GeneratorMain2 {
    public static void main(String[] args) {
        args = new String[]{"-configfile", "ssm-mybatis-generator\\src\\main\\resources\\generatorConfig.xml", "-overwrite"};
        ShellRunner.main(args);
    }
}