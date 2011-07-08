package ch.ralscha.e4ds.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages={"ch.ralscha.extdirectspring", "ch.ralscha.e4ds.service"}, excludeFilters={ @Filter(Configuration.class)})
public class ComponentConfig {
	//nothing here
}