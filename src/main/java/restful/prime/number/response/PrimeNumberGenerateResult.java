package restful.prime.number.response;

import java.util.List;
import java.util.UUID;
import com.google.common.base.MoreObjects;

public class PrimeNumberGenerateResult {
    private final UUID resultId;
	private final Long lower;
    private final Long upper;
    private final List<Long> result;
    private final String errorMessage;

    public PrimeNumberGenerateResult(Long lower, Long upper, List<Long> result, String errorMessage) {
        this.resultId = UUID.randomUUID();
        this.lower = lower;
        this.upper = upper;
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public String getResultId() {
		return resultId.toString();
	}

	public Long getLower() {
		return lower;
	}

	public Long getUpper() {
		return upper;
	}

	public List<Long> getResult() {
		return result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("resultId", resultId)
                .add("lower", lower)
                .add("upper", upper)
                .add("result", result)
                .add("errorMessage", errorMessage)
                .toString();
    }

    @Override
    public int hashCode() {
        return resultId.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PrimeNumberGenerateResult))
            return false;
        return resultId.equals(((PrimeNumberGenerateResult)obj).resultId);
    }
}
