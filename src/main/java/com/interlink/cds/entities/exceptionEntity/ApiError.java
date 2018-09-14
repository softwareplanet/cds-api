package  com.interlink.cds.entities.exceptionEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties("httpStatus")
public class ApiError {

    private HttpStatus httpStatus;
    private int status;
    private String statusText;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        super();
        this.httpStatus = status;
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        super();
        this.httpStatus = status;
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        errors = Arrays.asList(error);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
