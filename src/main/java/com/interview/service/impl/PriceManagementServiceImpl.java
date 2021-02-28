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
import com.interview.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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

        log.info("price calculate request {}", values);
        values.forEach(items -> {
            // PenguinEars calculation
            if (items.getType() == Integer.parseInt(priceCalculateProperties.getPenguinEars())) {
                log.info("price calculate for PenguinEars");
                result.setTotalPriceForPenguinEars(priceCalculateComponent.finalAmount(items, Constants.PENGUIN_EARS_CARTON_SIZE));
            }
            // HorseShoes calculation
            if (items.getType() == Integer.parseInt(priceCalculateProperties.getHorseShoes())) {
                log.info("price calculate for HorseShoes");
                result.setTotalPriceForHorseShoes(priceCalculateComponent.finalAmount(items, Constants.HORSE_SHOES_CARTON_SIZE));
            }
        });

        result.setTotalPrice(result.getTotalPriceForHorseShoes() + result.getTotalPriceForPenguinEars());

        return result;
    }

    @Override
    public PriceListResponse getHorseShoePrices(){
        PriceListResponse resultList = new PriceListResponse();
        //get prices of 50 items

        log.info("get horse shoe price list");
        List<Product> product = this.productRepository.findAll();
        product.forEach(item -> {
            if (item.getId() == Integer.parseInt(priceCalculateProperties.getHorseShoes())) {
                resultList.setProductList(priceReturningComponent.priceList(Constants.HORSE_SHOES_CARTON_SIZE, item));
            }
        });

        return resultList;

    }

    @Override
    public PriceListResponse getPenguinEarsPrices() {
        PriceListResponse resultList = new PriceListResponse();
        //get prices of 50 items

        log.info("get penguin ears price list");
        List<Product> product = this.productRepository.findAll();
        product.forEach(item -> {
            if (item.getId() == Integer.parseInt(priceCalculateProperties.getPenguinEars())) {
                resultList.setProductList(priceReturningComponent.priceList(Constants.PENGUIN_EARS_CARTON_SIZE, item));
            }
        });

        return resultList;

    }
}
