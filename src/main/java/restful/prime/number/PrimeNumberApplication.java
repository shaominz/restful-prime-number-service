package restful.prime.number;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import restful.prime.number.resource.PrimeNumberResource;

public class PrimeNumberApplication extends Application<PrimeNumberConfiguration> {
    public static void main(String[] args) throws Exception {
        new PrimeNumberApplication().run(args);
    }

    @Override
    public String getName() {
        return "prime-number-service";
    }

    @Override
    public void run(PrimeNumberConfiguration configuration, Environment environment) {
    	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml")) {
            PrimeNumberResource resource = (PrimeNumberResource)context.getBean("primeNumberResource");
            environment.jersey().register(resource);
   		}    	

        final PrimeNumberHealthCheck healthCheck = new PrimeNumberHealthCheck();
        environment.healthChecks().register("primeNumberService", healthCheck);
    }

}
