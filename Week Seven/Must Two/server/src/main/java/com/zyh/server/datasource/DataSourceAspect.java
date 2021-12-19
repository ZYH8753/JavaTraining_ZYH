package com.zyh.server.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.zyh.server.datasource.DataSourceAnnotation)")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DataSourceAnnotation annotationClass = method.getAnnotation(DataSourceAnnotation.class);
        if(annotationClass == null){
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DataSourceAnnotation.class);
            if(annotationClass == null) return;
        }
        String dataSourceKey = annotationClass.name();
        if (dataSourceKey !=null){
            DynamicDataSourceHolder.setDataSource(dataSourceKey);
        }
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        DynamicDataSourceHolder.clearDataSource();
    }
}
