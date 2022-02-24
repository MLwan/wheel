package config;

import com.baron.typefilter.DistrictTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

/**
 * spring的配置类
 * 用于替代xml配置
 */
@Configuration
@PropertySource(value = "classpath:district.properties") @ComponentScan(value = "com.baron",
        excludeFilters = @ComponentScan.Filter(type= FilterType.CUSTOM,classes = DistrictTypeFilter.class))
public class SpringConfiguration {
}
