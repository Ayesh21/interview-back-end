package com.interview.service.impl;

import com.interview.component.PriceCalculateComponent;
import com.interview.component.PriceReturningComponent;
import com.interview.config.PriceCalculateProperties;
import com.interview.dto.request.PriceCalculateRequest;
import com.interview.dto.response.PriceCalculateResponse;
import com.interview.dto.response.PriceListResponse;
import com.interview.repository.ProductRepository;
import com.interview.repository.entities.Product;
import com.interview.service.PriceManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceManagementServiceImpl implements PriceManagementService {

    private final PriceCalculateProperties priceCalculateProperties;
    private final ProductRepository productRepository;
    private final PriceCalculateComponent priceCalculateComponent;
    private final PriceReturningComponent priceReturningComponent;

    @Override
    public PriceCalculateResponse priceCalculate(List<PriceCalculateRequest> values) {
        PriceCalculateResponse result = new PriceCalculateResponse();

        values.forEach(items -> {
            // PenguinEars calculation
            if (items.getType() == Integer.parseInt(priceCalculateProperties.getPenguinEars())) {

                result.setTotalPriceForPenguinEars(priceCalculateComponent.finalAmount(items, 20));
            }
            // HorseShoes calculation
            if (items.getType() == Integer.parseInt(priceCalculateProperties.getHorseShoes())) {

                result.setTotalPriceForHorseShoes(priceCalculateComponent.finalAmount(items, 5));
            }
        });

        result.setTotalPrice(result.getTotalPriceForHorseShoes() + result.getTotalPriceForPenguinEars());

        return result;
    }

    @Override
    public PriceListResponse getHorseShoePrices() throws Exception {
        PriceListResponse resultList = new PriceListResponse();
        //get prices of 50 items
        try {
            List<Product> product = this.productRepository.findAll();
            product.forEach(item -> {
                if (item.getId() == Integer.parseInt(priceCalculateProperties.getHorseShoes())) {
                    resultList.setProductList(priceReturningComponent.priceList(5, item, 10));
                }
            });

            return resultList;

        } catch (Exception e) {
            throw new Exception("Getting Horse Shoe Prices Failed");
        }
    }

    @Override
    public PriceListResponse getPenguinEarsPrices() throws Exception {
        PriceListResponse resultList = new PriceListResponse();
        //get prices of 50 items
        try {
            List<Product> product = this.productRepository.findAll();
            product.forEach(item -> {
                if (item.getId() == Integer.parseInt(priceCalculateProperties.getPenguinEars())) {
                    resultList.setProductList(priceReturningComponent.priceList(20, item, 2));
                }
            });

            return resultList;
        } catch (Exception e) {
            throw new Exception("Getting PenguinEars Prices Failed");
        }

    }
}
