package com.hubin.configs.annotation;

import com.hubin.configs.datasource.DBTypeEnum;

import java.lang.annotation.*;

/**
 * <br>
 *
 * @author hubin
 * @title: 数据源配置注解
 * @description:
 * @date: 2019/4/15 10:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DefineDataSourceAnnotation {
    DBTypeEnum value() default DBTypeEnum.defaultSource;
}
