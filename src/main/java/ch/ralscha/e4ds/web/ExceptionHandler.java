package ch.ralscha.e4ds.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ch.ralscha.extdirectspring.bean.ExtDirectResponse;
import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private Environment environment;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse res, Object handler,
			Exception ex) {

		logger.error("error", ex);

		ExtDirectResponseBuilder builder = new ExtDirectResponseBuilder(request);
		builder.unsuccessful();

		ExtDirectResponse response = builder.build();
		response.setType("exception");
		response.setMessage("server error");
		
		if (environment.acceptsProfiles("development")) {
			response.setWhere(ex.toString());
		}
				
		try {
			res.getOutputStream().print(mapper.writeValueAsString(response));
			res.getOutputStream().flush();
		} catch (IOException e) {
			logger.error("error writing response", e);
		}

		return null;

	}

}
