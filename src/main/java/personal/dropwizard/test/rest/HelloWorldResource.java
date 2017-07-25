package personal.dropwizard.test.rest;

import com.google.common.base.*;
import com.yammer.metrics.annotation.*;
import personal.dropwizard.test.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.concurrent.atomic.*;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	public HelloWorldResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		return new Saying(counter.incrementAndGet(),
		                  String.format(template, name.or(defaultName)));
	}
}
