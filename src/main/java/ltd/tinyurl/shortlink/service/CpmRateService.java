package ltd.tinyurl.shortlink.service;

import java.util.List;

import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CpmRateResponse;

public interface CpmRateService {
    public BaseResponse<List<CpmRateResponse>> getCpmRate();
}
