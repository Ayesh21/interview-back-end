package com.interview.component;

import com.interview.dto.request.PriceCalculateRequest;
import com.interview.repository.PriceRepository;
import com.interview.repository.entities.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceCalculateComponent {

    private final PriceRepository priceRepository;

    public double finalAmount(PriceCalculateRequest items, int cartonSize) {
        Optional<Price> priceOptional = this.priceRepository.findById(items.getType());
        if (!priceOptional.isPresent()) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        Price price = priceOptional.get();

        int numCartons = items.getUnitQuantity() / cartonSize;
        int remainder = items.getUnitQuantity() % cartonSize;
        double finalUnitAmount = 0;
        double finalCartonAmount = 0;
        double finalAmount = 0;

        // when units are zero and cartons are greater than zero
        if (items.getCartonQuantity() != 0 && items.getUnitQuantity() == 0) {

            if (items.getCartonQuantity() >= 3) {
                finalCartonAmount = cartonPriceAmount(price, items.getCartonQuantity());
                double discountPrice = finalCartonAmount * 0.1;
                finalCartonAmount = finalCartonAmount - discountPrice;
            } else {
                finalCartonAmount = cartonPriceAmount(price, items.getCartonQuantity());
            }
            finalAmount = finalCartonAmount + finalUnitAmount;

        } // when units are greater than zero and cartons are greater than zero
        else if (items.getCartonQuantity() != 0 && items.getUnitQuantity() != 0) {

            if (remainder == 0 && numCartons >= 1 && items.getCartonQuantity() >= 1) {
                int newNumOfCarton = numCartons + items.getCartonQuantity();
                if (newNumOfCarton >= 3) {
                    finalCartonAmount = cartonPriceAmount(price, newNumOfCarton);
                    double discountPrice = finalCartonAmount * 0.1;
                    finalCartonAmount = finalCartonAmount - discountPrice;
                } else {
                    finalCartonAmount = cartonPriceAmount(price, newNumOfCarton);
                }
                finalAmount = finalCartonAmount + finalUnitAmount;
            }
            if (remainder != 0 && numCartons >= 1 && items.getCartonQuantity() >= 1) {

                int newNumOfCarton = numCartons + items.getCartonQuantity();
                finalUnitAmount = Double.parseDouble(price.getUnitPrice()) * remainder;
                if (newNumOfCarton >= 3) {
                    finalCartonAmount = cartonPriceAmount(price, newNumOfCarton);
                    double discountPrice = finalCartonAmount * 0.1;
                    finalCartonAmount = finalCartonAmount - discountPrice;
                } else {
                    finalCartonAmount = cartonPriceAmount(price, newNumOfCarton);
                }
                finalAmount = finalCartonAmount + finalUnitAmount;
            } else if (remainder != 0 && numCartons == 0 && items.getCartonQuantity() >= 1) {
                finalUnitAmount = Double.parseDouble(price.getUnitPrice()) * remainder;
                if (items.getCartonQuantity() >= 3) {
                    finalCartonAmount = cartonPriceAmount(price, items.getCartonQuantity());
                    double discountPrice = finalCartonAmount * 0.1;
                    finalCartonAmount = finalCartonAmount - discountPrice;
                } else {
                    finalCartonAmount = cartonPriceAmount(price, items.getCartonQuantity());
                }
                finalAmount = finalCartonAmount + finalUnitAmount;
            }

        } // when units are greater than zero and cartons are zero
        else if (items.getCartonQuantity() == 0 && items.getUnitQuantity() != 0) {

            if (remainder == 0 && numCartons >= 1) {
                if (numCartons >= 3) {
                    finalCartonAmount = cartonPriceAmount(price, numCartons);
                    double discountPrice = finalCartonAmount * 0.1;
                    finalCartonAmount = finalCartonAmount - discountPrice;
                } else {
                    finalCartonAmount = cartonPriceAmount(price, numCartons);

                }

                finalAmount = finalCartonAmount + finalUnitAmount;

            } else if (remainder != 0 && numCartons >= 1) {

                finalUnitAmount = Double.parseDouble(price.getUnitPrice()) * remainder;
                if (numCartons >= 3) {
                    finalCartonAmount = cartonPriceAmount(price, numCartons);
                    double discountPrice = finalCartonAmount * 0.1;
                    finalCartonAmount = finalCartonAmount - discountPrice;
                } else {
                    finalCartonAmount = Double.parseDouble(price.getCartonPrice()) * numCartons;
                }
                finalAmount = finalCartonAmount + finalUnitAmount;

            } else {
                finalUnitAmount = Double.parseDouble(price.getUnitPrice()) * remainder;
                finalAmount = finalCartonAmount + finalUnitAmount;
            }

        }

        return finalAmount;
    }


    private double cartonPriceAmount(Price price, int numCartons) {
        return Double.parseDouble(price.getCartonPrice()) * numCartons;
    }

}
