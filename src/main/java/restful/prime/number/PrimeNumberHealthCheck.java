package restful.prime.number;

import com.codahale.metrics.health.HealthCheck;

public class PrimeNumberHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}