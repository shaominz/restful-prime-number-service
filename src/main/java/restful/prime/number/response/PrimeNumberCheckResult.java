package restful.prime.number.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PrimeNumberCheckResult {

    private final UUID resultId;
    private final Long number;
    private final Boolean result;
    private final String errorMessage;

    public PrimeNumberCheckResult(Long number, Boolean result, String errorMessage) {
        this.resultId = UUID.randomUUID();
        this.number = number;
        this.result = result;
        this.errorMessage = errorMessage;
    }

    @JsonProperty
    public Long getNumber() {
        return number;
    }

    @JsonProperty
    public String getResultId() {
        return resultId.toString();
    }

    @JsonProperty
    public Boolean getResult() {
        return result;
    }

    @JsonProperty
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("resultId", resultId)
                .add("number", number)
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
        if (!(obj instanceof PrimeNumberCheckResult))
            return false;
        return resultId.equals(((PrimeNumberCheckResult) obj).resultId);
    }
}
