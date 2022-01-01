package cn.superdevops.dependency.lookup;

import cn.superdevops.dependency.lookup.annotation.Super;
import cn.superdevops.dependency.lookup.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @description:通过名称依赖查找示例
 * @author: superdevops
 * @time: 2022/1/1 10:51
 */

public class DependencyLookup {
    public static void main(String[] args) {
        //配置Xml文件
        //启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/dependency-lookup-context.xml");
        //按照名称查找
        dependencyLookupByName(beanFactory);
        //按照类型查找
        dependencyLookupByType(beanFactory);
        //根据 Bean 名称 + 类型查找
        dependencyLookupByNameAndType(beanFactory);
        //根据 Java 注解查找
        dependencyLookupByAnnotation(beanFactory);

    }

    //根据 Java 注解查找
    private static void dependencyLookupByAnnotation(BeanFactory beanFactory) {

        singleBeanDependencyLookupByAnnotation(beanFactory, "superUser");
        collectionBeanDependencyLookupByAnnotation(beanFactory);
    }

    //根据 Java 注解按集合Bean对象查找
    private static void collectionBeanDependencyLookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beans = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据 Java 注解按集合Bean对象查找：" + beans);
        }
    }

    //根据 Java 注解按单个Bean对象查找
    private static void singleBeanDependencyLookupByAnnotation(BeanFactory beanFactory, String beanName) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beans = listableBeanFactory.getBeansWithAnnotation(Super.class);
            if (beans.containsKey(beanName)) {
                Object object = beans.get(beanName);
                System.out.println("根据 Java 注解按单个Bean对象查找：" + object);
            }
        }
    }

    //根据 Bean 名称 + 类型查找
    private static void dependencyLookupByNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("根据 Bean 名称 + 类型查找：" + user);
        User rootUser = beanFactory.getBean("rootUser", User.class);
        System.out.println("根据 Bean 名称 + 类型查找：" + rootUser);
    }

    //按照类型查找
    private static void dependencyLookupByType(BeanFactory beanFactory) {
        singleBeanDependencyLookupByType(beanFactory);
        collectionBeanDependencyLookupByType(beanFactory);
    }

    //集合Bean对象查找
    private static void collectionBeanDependencyLookupByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> ofType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合Bean对象查找：" + ofType);
        }

    }

    //单个Bean对象查找
    private static void singleBeanDependencyLookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("单个Bean对象查找：" + user);
    }

    //按照名称查找
    private static void dependencyLookupByName(BeanFactory beanFactory) {
        //实时查找
        realTimeDependencyLookup(beanFactory);
        //延迟查找
        lazyDependencyLookup(beanFactory);
    }

    //实时查找
    private static void realTimeDependencyLookup(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    //延迟查找
    private static void lazyDependencyLookup(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }
}
