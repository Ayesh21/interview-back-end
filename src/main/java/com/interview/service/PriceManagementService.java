package com.interview.service;

import com.interview.dto.request.PriceCalculateRequest;
import com.interview.dto.response.PriceCalculateResponse;
import com.interview.dto.response.PriceListResponse;

import java.util.List;

public interface PriceManagementService {

    PriceCalculateResponse priceCalculate(List<PriceCalculateRequest> values);
    PriceListResponse getHorseShoePrices();
    PriceListResponse getPenguinEarsPrices();

}
