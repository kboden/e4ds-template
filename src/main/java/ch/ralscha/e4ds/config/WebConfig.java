package ch.ralscha.e4ds.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.ImmutableMap;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Bean
	public ch.ralscha.extdirectspring.controller.Configuration configuration() {
		ch.ralscha.extdirectspring.controller.Configuration config = new ch.ralscha.extdirectspring.controller.Configuration();
		config.setSendStacktrace(environment.acceptsProfiles("development"));
		config.setExceptionToMessage(new ImmutableMap.Builder<Class<?>,String>()
				.put(AccessDeniedException.class, "accessdenied")
				.build());
		return config;
	}
	
	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setFallbackToSystemLocale(false);

		//development
		//messageSource.setCacheSeconds(60);

		return messageSource;
	}

	@Bean
	public ConfigurableWroFilter wroFilter() {
		ConfigurableWroFilter filter = new ConfigurableWroFilter();
		Properties properties = new Properties();

		//If true, it is DEVELOPMENT mode, by default this value is true
		properties.setProperty(ConfigConstants.debug.name(), "false");
		
		// Default is true
		properties.setProperty(ConfigConstants.gzipResources.name(), "true");
		properties.setProperty(ConfigConstants.jmxEnabled.name(), "false");
		
		// MBean name to be used if JMX is enabled
		properties.setProperty(ConfigConstants.mbeanName.name(), "wro");
		
		// Default is 0
		properties.setProperty(ConfigConstants.cacheUpdatePeriod.name(), "0");
		
		// Default is 0
		properties.setProperty(ConfigConstants.modelUpdatePeriod.name(), "0");
		
		// Default is false.
		properties.setProperty(ConfigConstants.disableCache.name(), "false");
		
		// Default is UTF-8
		properties.setProperty(ConfigConstants.encoding.name(), "UTF-8");

		filter.setProperties(properties);

		return filter;
	}

}