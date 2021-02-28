package com.interview.controller;


import com.interview.config.ErrorProperties;
import com.interview.dto.request.PriceCalculateRequest;
import com.interview.dto.response.PriceCalculateResponse;
import com.interview.dto.response.PriceListResponse;
import com.interview.service.PriceManagementService;
import com.interview.utils.UrlUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = UrlUtils.ADMIN_PORTAL_URL)
@RequestMapping({ UrlUtils.ADMINISTRATION_APP_URL })
@Api(value = "PriceCalculate Controller API")
@RequiredArgsConstructor
public class PriceManagementController {

    private final PriceManagementService priceCalculateService;
    private final ErrorProperties errorProperties;

    @ApiOperation(value = "View all available Penguin-ear Prices")
    @GetMapping(UrlUtils.GET_ALL_PENGUIN_EARS_URL)
    public ResponseEntity<PriceListResponse> getPenguinEarsPrices() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.priceCalculateService.getPenguinEarsPrices());
        } catch (Exception e) {
            throw new Exception(this.errorProperties.getAll(), e);
        }

    }

    @ApiOperation(value = "View all available horse-shoe Prices")
    @GetMapping(UrlUtils.GET_ALL_HORSE_SHOES_URL)
    public ResponseEntity<PriceListResponse> getHorseShoePrices() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.priceCalculateService.getHorseShoePrices());
        } catch (Exception e) {
            throw new Exception(this.errorProperties.getAll(), e);
        }

    }

    @ApiOperation(value = "Get Calculated Prices")
    @PostMapping(UrlUtils.GET_CALCULATED_PRICES_URL)
    public ResponseEntity<PriceCalculateResponse> getCalculatedPrices(@RequestBody List<PriceCalculateRequest> values)
            throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.priceCalculateService.priceCalculate(values));
        } catch (Exception e) {
            throw new Exception(this.errorProperties.getAll(), e);
        }

    }

}
