package ltd.tinyurl.shortlink.dto.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String message;
    private T data;

    public BaseResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

}
