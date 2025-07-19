package ltd.tinyurl.shortlink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import lombok.Data;
import ltd.tinyurl.shortlink.dto.response.BaseResponse;
import ltd.tinyurl.shortlink.dto.response.CpmRateResponse;
import ltd.tinyurl.shortlink.service.impl.CpmRateServiceImpl;
import ltd.tinyurl.shortlink.webconstants.WebConstants;

@RestController
@RequestMapping(WebConstants.CPMRATE_API_V1_PATH)
@Data
public class CpmRateController {
    private final CpmRateServiceImpl cpmRateServiceImpl;

    @SuppressWarnings("unchecked")
    @GetMapping("/findall")
    public ResponseEntity<BaseResponse<List<CpmRateResponse>>> findAllCpm() {
        BaseResponse cpm = cpmRateServiceImpl.getCpmRate();
        String message = cpm.getMessage();
        if (message.equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<BaseResponse<List<CpmRateResponse>>>(cpm, HttpStatus.OK);
        return new ResponseEntity<BaseResponse<List<CpmRateResponse>>>(cpm, HttpStatus.NO_CONTENT);
    }
}
