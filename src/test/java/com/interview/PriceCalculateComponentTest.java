package com.interview;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.component.PriceCalculateComponent;
import com.interview.dto.request.PriceCalculateRequest;
import com.interview.repository.PriceRepository;
import com.interview.repository.entities.Price;
import com.interview.repository.entities.Product;



class PriceCalculateComponentTest {

	@Rule
    public ErrorCollector collector = new ErrorCollector();
	
	@Mock
    private PriceRepository priceRepository;
	
	@InjectMocks
    private PriceCalculateComponent priceCalculateComponent;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Product p = new Product();
        p.setId(1);
        p.setProductName("penguinEars");
        
        Price price = new Price();
        price.setId(1);
        price.setCartonPrice("175");
        price.setProduct(p);
        price.setUnitPrice("11.375");
        
    	when(priceRepository.findById(1)).thenReturn(Optional.of(price));
    }
    
	@Test
    public void priceWhenUnitZeroAndCartonNotZeroForTypeOneCaseOne() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(2);
		results.setType(1);
		results.setUnitQuantity(0);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(315));
        
    }
	
	@Test
    public void priceWhenUnitZeroAndCartonNotZeroForTypeOneCasetwo() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(2);
		results.setType(1);
		results.setUnitQuantity(0);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(472.5));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonNotZeroForTypeOneCaseOne() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(2);
		results.setType(1);
		results.setUnitQuantity(20);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(472.5));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonNotZeroForTypeOneCaseTwo() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(2);
		results.setType(1);
		results.setUnitQuantity(21);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(483.875));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonNotZeroForTypeOneCaseThree() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(1);
		results.setType(1);
		results.setUnitQuantity(41);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(483.875));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonNotZeroForTypeOneCaseFour() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(1);
		results.setType(1);
		results.setUnitQuantity(10);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(288.75));
        
    }
	
	
	@Test
    public void priceWhenUnitNotZeroAndCartonZeroForTypeOneCaseOne() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(0);
		results.setType(1);
		results.setUnitQuantity(60);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(472.5));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonZeroForTypeOneCaseTwo() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(0);
		results.setType(1);
		results.setUnitQuantity(61);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(483.875));
        
    }
	
	@Test
    public void priceWhenUnitNotZeroAndCartonZeroForTypeOneCaseThree() {
        // given
		PriceCalculateRequest results = new PriceCalculateRequest();
		results.setCartonQuantity(0);
		results.setType(1);
		results.setUnitQuantity(41);

        // when
        Double response = priceCalculateComponent.finalAmount(results, 20);
        
        // then
        collector.checkThat(response, equalTo(361.375));
        
    }


}
