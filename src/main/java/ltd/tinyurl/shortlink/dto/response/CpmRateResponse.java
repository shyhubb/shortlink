package ltd.tinyurl.shortlink.dto.response;

import lombok.Data;

@Data
public class CpmRateResponse {
    private String country;
    private Double cpm;
}
