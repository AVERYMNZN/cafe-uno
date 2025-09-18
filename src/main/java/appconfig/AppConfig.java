package appconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.cafeuno", "components", "modules"})
@PropertySource("classpath:strings.properties")
public class AppConfig {
}
