//package com.zyh.server.datasource;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataSourceBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
//    public static final String DATA_SOURCE_INITIALIZER_POST_PROCESSOR = "dataSourceInitializerPostProcessor";
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//
//        if (registry.containsBeanDefinition(DATA_SOURCE_INITIALIZER_POST_PROCESSOR)) {
//            registry.removeBeanDefinition(DATA_SOURCE_INITIALIZER_POST_PROCESSOR);
//        }
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//    }
//}