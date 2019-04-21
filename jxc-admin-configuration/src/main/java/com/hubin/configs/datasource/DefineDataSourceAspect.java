package com.hubin.configs.datasource;

import com.hubin.utils.enums.DBTypeEnum;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <br>
 *
 * @author hubin
 * @title: 数据源动态切面切换
 * @description:
 * @date: 2019/4/15 10:44
 */
@Component
@Order(1)
@Aspect
public class DefineDataSourceAspect{

    private static final Logger logger = LoggerFactory.getLogger(DefineDataSourceAspect.class);

    /**
     * 切点: 所有配置 DataSource 注解的方法
     */
    @Pointcut("execution(public * com.hubin.services.impl.finance..*.*(..))")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DefineDataSourceAnnotation ds = method.getAnnotation(DefineDataSourceAnnotation.class);
        if(Objects.nonNull(ds)){
            // 通过判断 DataSource 中的值来判断当前方法应用哪个数据源
            DefineDataSourceHolder.setDataSource(ds.value());
            System.err.println("当前数据源: " + ds.value());
            logger.debug("set datasource is " + ds.value());
            try {
                return point.proceed();
            } finally {
                DefineDataSourceHolder.clearDataSource();
                logger.debug("clean datasource");
            }
        }
        return null;
    }

}
