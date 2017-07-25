package personal.dropwizard.test.application;

import com.yammer.dropwizard.*;
import com.yammer.dropwizard.config.*;
import personal.dropwizard.test.config.*;
import personal.dropwizard.test.healthcheck.*;
import personal.dropwizard.test.rest.*;

public class HelloWorldService extends Service<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldService().run(args);
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.setName("hello-world");
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) {
		final String template = configuration.getTemplate();
		final String defaultName = configuration.getDefaultName();
		environment.addResource(new HelloWorldResource(template, defaultName));
		environment.addHealthCheck(new TemplateHealthCheck(template));
	}

}
