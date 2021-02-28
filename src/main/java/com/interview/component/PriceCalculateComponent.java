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

        Price price = this.getProductPrice(items);

        double finalAmount = 0;
        double unitPrice = getTotalUnitPrice(items.getUnitQuantity(), cartonSize, price);
        double cartonPrice = getTotalCartonPrice(items.getCartonQuantity(), cartonSize, price);
        finalAmount = unitPrice + cartonPrice;
        return finalAmount;
    }

    public double getTotalUnitPrice(int unitsAmount, int cartonSize, Price price){

        double totalUnitPrice = 0;
        double cartonPrice = Double.parseDouble(price.getCartonPrice());

        // If units amount is greater than a carton
        if(unitsAmount > cartonSize){

            int cartonsAmountInUnits = unitsAmount / cartonSize;
            int remainderUnits = unitsAmount % cartonSize;

            //Checking if a discount can be given for the cartons
            double filteredCartonPrice = getSingleCartonPrice(cartonsAmountInUnits, cartonPrice);

            /*
             * Calculating price for the remaining units
             * no discounts are added as per the requirement since discounts are valid only for cartons
             */
            double singleUnitPrice = getSingleUnitPrice(cartonPrice, cartonSize);

            double finalUnitsPrice = singleUnitPrice * remainderUnits;
            double finalCartonPrice = filteredCartonPrice * cartonsAmountInUnits;

            //Calculating final unit price
            totalUnitPrice = finalUnitsPrice +  finalCartonPrice;

        }
        else{
            // Calculating amounts for single units
            double singleUnitPrice = getSingleUnitPrice(cartonPrice, cartonSize);
            double finalUnitsPrice = singleUnitPrice * unitsAmount;
            totalUnitPrice = finalUnitsPrice;
        }

        return totalUnitPrice;
    }

    public double getSingleUnitPrice(double cartonPrice, int cartonSize){

        double singleUnitPrice = 0;

        // Single unit price is acquired using the carton price multiplied by an increase of 30% (1.3)
        singleUnitPrice = (cartonPrice* 1.3/cartonSize);

        return singleUnitPrice;
    }

    public double getTotalCartonPrice(int cartonAmount, int cartonSize, Price price){
        double totalCartonPrice = 0;

        double cartonPrice = Double.parseDouble(price.getCartonPrice());

        //Checking if a discount can be given for the cartons
        double discountedCartonPrice = getSingleCartonPrice(cartonAmount, cartonPrice);

        totalCartonPrice = discountedCartonPrice * cartonAmount;

        return totalCartonPrice;
    }

    public double getSingleCartonPrice(int cartonAmount, double cartonPrice){
        double discountedCartonPrice = cartonPrice;

        /*
         * Checking if a discount can be given for the cartons
         * If the carton amount is 3 or more, a 10% discount is given
         */
        if(cartonAmount >= 3){
            discountedCartonPrice = cartonPrice - (cartonPrice * 0.1);
        }
        return discountedCartonPrice;
    }

    public Price getProductPrice(PriceCalculateRequest items){

        //Getting prices list from the database
        Optional<Price> priceOptional = this.priceRepository.findById(items.getType());
        if (!priceOptional.isPresent()) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        return priceOptional.get();
    }
}
