package com.baron.typefilter;

import com.baron.annotations.District;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Properties;

/**
 * spring的自定义扫描规则过滤器
 */
//为什么继承这个？？？
public class DistrictTypeFilter extends AbstractTypeHierarchyTraversingFilter {

    //定义路径校验类对象 ???
    private PathMatcher pathMatcher;

    //定义区域名称
    //注意:此处数据应读取配置文件获取，但是不能使用@Value注解读取properties配置文件的内容
    //因为Spring的生命周期里，负责填充属性值的InstantiationAwareBeanPostProcessor 与 TypeFilter的实例化过程压根搭不上边。？？？
    @Value("${common.district.name}")
    private String districtName;


    public DistrictTypeFilter() {
        /**
         * 调用父类的构造函数
         * 1.父类第一个参数considerInherited:不考虑基类。
         * 2.父类第二个参数considerInterface:不考虑接口上的信息
         */
        super(false, false);

        //借助Spring默认的Resource通配符路径方式
        pathMatcher = new AntPathMatcher();

        //硬编码读取配置信息
        try {
            Properties loadAllProperties = PropertiesLoaderUtils.loadAllProperties("district.properties");
            //给districtName赋值
            districtName = loadAllProperties.getProperty("common.district.name");
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block e.printStackTrace();
        }
    }

    /**
     * 本类将注册为Exclude, 返回true代表拒绝
     */

    @Override
    protected boolean matchClassName(String className) {
        try {
            /**
             * 判断是否在指定包下的类（只处理和区域相关的业务类）
             */
            if (!isPotentialPackageClass(className)) {
                //不符合路径规则
                return false;
            }
            // 判断当前区域是否和所配置的区域一致, 不一致则阻止注入Spring容器
            Class<?> clazz = ClassUtils.forName(className, DistrictTypeFilter.class.getClassLoader());
            //获取District注解
            District districtAnnotation = clazz.getAnnotation(District.class);
            //判断是否有此注解
            if (null == districtAnnotation) {
                return false;
            }
            //取出注解的属性
            final String districtValue = districtAnnotation.value();
            //校验，如果取出的value属性的值和配置文件中提供的值一致，则注册到ioc容器中
            return (!districtName.equalsIgnoreCase(districtValue));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 定义可以处理类的类名, 指定package下的
    // 希望支持*.*等通配符 ，使用ClassUtils工具类
    private static final String PATTERN_STANDARD = ClassUtils
            .convertClassNameToResourcePath("com.baron.service.*.*");

    /**
     * 本类逻辑中可以处理的类 -- 指定package下的才会进行逻辑判断,
     */
    private boolean isPotentialPackageClass(String className) {
        // 1.将类名转换成为资源路径，以匹配是否符合扫描条件
        final String path = ClassUtils.convertClassNameToResourcePath(className);
        // 2.检验
        return pathMatcher.match(PATTERN_STANDARD, path);
    }
}


