package com.interview.component;

import com.interview.dto.ProductsDTO;
import com.interview.repository.PriceRepository;
import com.interview.repository.entities.Price;
import com.interview.repository.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceReturningComponent {

    private final PriceRepository priceRepository;

    private final PriceCalculateComponent priceCalculateComponent;


    public List<ProductsDTO> priceList(int cartonSize, Product item, int number) {
        List<ProductsDTO> prices = new ArrayList<>();
        Optional<Price> priceOptional = this.priceRepository.findById(item.getId());
        if (!priceOptional.isPresent()) {
            throw new IllegalArgumentException("Price cannot be null");

        }
        Price price = priceOptional.get();

        double total;
        double unitTotal = 0;
        double cartonTotal = 0;
        for (int x = 1; x < 51; x++) {

            double cartonPrice = Double.parseDouble(price.getCartonPrice());

            ProductsDTO product = new ProductsDTO();
            double unitPrice = priceCalculateComponent.getTotalUnitPrice(x, cartonSize, price);

            product.setQuantity(String.valueOf(x));
            product.setValue(String.valueOf(unitPrice));
            prices.add(product);
        }

        return prices;
    }


}
