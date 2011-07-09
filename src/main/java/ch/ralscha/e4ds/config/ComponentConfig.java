package ch.ralscha.e4ds.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages={"ch.ralscha.extdirectspring", "ch.ralscha.e4ds.service"}, excludeFilters={ @Filter(Configuration.class)})
@PropertySource("default.properties")
public class ComponentConfig {
	//nothing here
}