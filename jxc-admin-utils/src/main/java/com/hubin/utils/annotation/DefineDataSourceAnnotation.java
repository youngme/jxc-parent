package com.hubin.utils.annotation;
import com.hubin.utils.enums.DBTypeEnum;

import java.lang.annotation.*;

/**
 * <br>
 *
 * @author hubin
 * @title: 数据源配置注解
 * @description:
 * @date: 2019/4/15 10:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefineDataSourceAnnotation {
    DBTypeEnum value() default DBTypeEnum.defaultSource;
}
