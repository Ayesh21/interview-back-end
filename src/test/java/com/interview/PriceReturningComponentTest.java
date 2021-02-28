package com.interview;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.interview.component.PriceCalculateComponent;
import com.interview.component.PriceReturningComponent;
import com.interview.dto.ProductsDTO;
import com.interview.repository.PriceRepository;
import com.interview.repository.entities.Price;
import com.interview.repository.entities.Product;

@RunWith(MockitoJUnitRunner.class)
class PriceReturningComponentTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Mock
	private PriceRepository priceRepository;

	@Mock
	private PriceCalculateComponent priceCalculateComponent;

	@InjectMocks
	private PriceReturningComponent priceReturningComponent;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Product p = new Product();
        p.setId(1);
        p.setProductName("penguinEars");
        
        Price price = new Price();
        price.setId(1);
        price.setCartonPrice(175);
        price.setProduct(p);
        price.setCartonSize("1");
        
    	when(priceRepository.findById(1)).thenReturn(Optional.of(price));
    }
    
	@Test
    public void getPriceListForPenguinEarsCaseOne() {
		
        Product p = new Product();
        p.setId(1);
        p.setProductName("penguinEars");
        
        Price price = new Price();
        price.setId(1);
        price.setCartonPrice(175);
        price.setProduct(p);
        price.setCartonSize("20");
        
		when(priceCalculateComponent.getTotalUnitPrice(25, 0, price)).thenReturn(231.875);
		

		// when
		 List<ProductsDTO> response = priceReturningComponent.priceList(20,p);
		 
		 
		// then
		collector.checkThat((response.stream().filter(item -> item.getQuantity().equals(25))), equalTo("231.875"));
        
    }
	
	@Test
    public void getPriceListForPenguinEarsCasetwo() {
		
        Product p = new Product();
        p.setId(1);
        p.setProductName("penguinEars");
        
        Price price = new Price();
        price.setId(1);
        price.setCartonPrice(175);
        price.setProduct(p);
        price.setCartonSize("20");
        
		when(priceCalculateComponent.getTotalUnitPrice(65, 0, price)).thenReturn(529.375);
		

		// when
		 List<ProductsDTO> response = priceReturningComponent.priceList(20,p);
		 
		 
		// then
		collector.checkThat((response.stream().filter(item -> item.getQuantity().equals(65))), equalTo("529.375"));
        
    }
	
	
	

}
