package ltd.tinyurl.shortlink.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Data;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CpmRateResponse;
import ltd.tinyurl.shortlink.entity.CpmRate;
import ltd.tinyurl.shortlink.repository.CpmRateRepository;
import ltd.tinyurl.shortlink.service.CpmRateService;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@Service
@Data
public class CpmRateServiceImpl implements CpmRateService {

    private final CpmRateRepository cpmRateRepository;

    @Override
    public BaseResponse<List<CpmRateResponse>> getCpmRate() {
        List<CpmRate> cpmRates = cpmRateRepository.findAll();
        if (cpmRates.isEmpty())
            return new BaseResponse<List<CpmRateResponse>>(WebConstants.BASE_FAIL, null);
        List<CpmRateResponse> cpm = new ArrayList<>();
        for (CpmRate cpmRate : cpmRates) {
            CpmRateResponse cpmRateResponse = new CpmRateResponse();
            cpmRateResponse.setCountry(cpmRate.getCountry());
            cpmRateResponse.setCpm(cpmRate.getCpm());
            cpm.add(cpmRateResponse);
        }
        return new BaseResponse<List<CpmRateResponse>>(WebConstants.BASE_SUCCESS, cpm);
    }

}
