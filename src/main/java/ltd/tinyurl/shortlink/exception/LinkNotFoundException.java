
package ltd.tinyurl.shortlink.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LinkNotFoundException extends RuntimeException {
    private String errorCode;
    private HttpStatus httpStatus;

    public LinkNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public LinkNotFoundException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}