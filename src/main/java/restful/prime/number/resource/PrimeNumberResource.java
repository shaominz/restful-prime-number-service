package restful.prime.number.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import restful.prime.number.model.spi.IPrimeNumberService;
import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;

@Path("/prime-number-service")
@Produces(MediaType.APPLICATION_JSON)
public class PrimeNumberResource {
    
	private IPrimeNumberService primeNumberService;
	
	public PrimeNumberResource() {
    }

    @Path("checking")
    @GET
    public PrimeNumberCheckResult checkPrime(@QueryParam("number") long number) {
        return primeNumberService.checkPrime(number);
    }

    @Path("generating")
    @GET
    public PrimeNumberGenerateResult generatePrimes(@QueryParam("lower") long lower, @QueryParam("upper") long upper) {
        return primeNumberService.generatePrimes(lower, upper);
    }

    public IPrimeNumberService getPrimeNumberService() {
		return primeNumberService;
	}

	public void setPrimeNumberService(IPrimeNumberService primeNumberService) {
		this.primeNumberService = primeNumberService;
	}

}