package com.interview;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.interview.component.PriceCalculateComponent;
import com.interview.component.PriceReturningComponent;
import com.interview.config.PriceCalculateProperties;
import com.interview.dto.ProductsDTO;
import com.interview.dto.request.PriceCalculateRequest;
import com.interview.dto.response.PriceCalculateResponse;
import com.interview.dto.response.PriceListResponse;
import com.interview.repository.ProductRepository;
import com.interview.repository.entities.Product;
import com.interview.service.impl.PriceManagementServiceImpl;
import org.junit.Rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceManagementServiceTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Rule
	public ExpectedException expectation = ExpectedException.none();

	@Mock
	private ProductRepository productRepository;

	@Mock
	private PriceCalculateProperties priceCalculateProperties;

	@Mock
	private PriceCalculateComponent priceCalculateComponent;

	@Mock
	private PriceReturningComponent priceReturningComponent;

	@InjectMocks
	private PriceManagementServiceImpl priceCalculateService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void priceCalculateTestCaseForTypeOne() {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");
		when(priceCalculateComponent.finalAmount(any(), anyInt())).thenReturn(472.5);

		// when
		List<PriceCalculateRequest> list = new ArrayList<PriceCalculateRequest>();
		PriceCalculateRequest price = new PriceCalculateRequest(1, 20, 2);
		list.add(price);
		PriceCalculateResponse response = priceCalculateService.priceCalculate(list);

		// then
		collector.checkThat(response.getTotalPrice(), equalTo(472.5));
		verify(priceCalculateProperties).getPenguinEars();
		verify(priceCalculateProperties).getHorseShoes();
	}

	@Test
	public void priceCalculateTestCaseForTypeTwo() {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");
		when(priceCalculateComponent.finalAmount(any(), anyInt())).thenReturn(4455.0);

		// when
		List<PriceCalculateRequest> list = new ArrayList<PriceCalculateRequest>();
		PriceCalculateRequest price = new PriceCalculateRequest(2, 20, 2);
		list.add(price);
		PriceCalculateResponse response = priceCalculateService.priceCalculate(list);

		// then
		collector.checkThat(response.getTotalPrice(), equalTo(4455.0));
		verify(priceCalculateProperties).getPenguinEars();
		verify(priceCalculateProperties).getHorseShoes();
	}

	@Test
	public void priceCalculateTestCaseForAll() {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");
		when(priceCalculateComponent.finalAmount(any(), anyInt())).thenReturn(4927.5);

		// when
		List<PriceCalculateRequest> list = new ArrayList<PriceCalculateRequest>();
		PriceCalculateRequest price1 = new PriceCalculateRequest(1, 20, 2);
		PriceCalculateRequest price2 = new PriceCalculateRequest(2, 20, 2);
		list.add(price1);
		list.add(price2);
		PriceCalculateResponse response = priceCalculateService.priceCalculate(list);

		// then
		collector.checkThat(response.getTotalPrice(), equalTo(4927.5));

	}

	@Test
	public void getAllPenguinPricesTestCase() throws Exception {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");

		List<Product> productList = new ArrayList<>();
		Product prod1 = new Product();
		prod1.setId(1);
		prod1.setProductName("PenguinEars");
		Product prod2 = new Product();
		prod2.setId(2);
		prod1.setProductName("HorseShoes");
		productList.add(prod1);
		productList.add(prod2);
		when(productRepository.findAll()).thenReturn(productList);

		List<ProductsDTO> mockList = new ArrayList<>();
		ProductsDTO p1 = new ProductsDTO("1", "11.375");
		ProductsDTO p2 = new ProductsDTO("2", "22.75");
		ProductsDTO p3 = new ProductsDTO("3", "34.125");
		mockList.add(p1);
		mockList.add(p2);
		mockList.add(p3);
		when(priceReturningComponent.priceList(anyInt(), any())).thenReturn(mockList);

		// when
		PriceListResponse response = priceCalculateService.getPenguinEarsPrices();

		// then
		collector.checkThat(response.getProductList(), equalTo(mockList));

	}

	@Test
	public void getAllHorsePricesTestCase() throws Exception {

		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");

		List<Product> productList = new ArrayList<>();
		Product prod1 = new Product();
		prod1.setId(1);
		prod1.setProductName("PenguinEars");
		Product prod2 = new Product();
		prod2.setId(2);
		prod1.setProductName("HorseShoes");
		productList.add(prod1);
		productList.add(prod2);
		when(productRepository.findAll()).thenReturn(productList);

		List<ProductsDTO> mockList = new ArrayList<>();
		ProductsDTO p1 = new ProductsDTO("1", "214.5");
		ProductsDTO p2 = new ProductsDTO("2", "429.0");
		ProductsDTO p3 = new ProductsDTO("3", "643.5");
		mockList.add(p1);
		mockList.add(p2);
		mockList.add(p3);
		when(priceReturningComponent.priceList(anyInt(), any())).thenReturn(mockList);

		// when
		PriceListResponse response = priceCalculateService.getHorseShoePrices();

		// then
		collector.checkThat(response.getProductList(), equalTo(mockList));

	}

	@Test
	public void priceCalculateTestCaseForTypeWrong() {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("10");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("20");
		when(priceCalculateComponent.finalAmount(any(), anyInt())).thenReturn(400.0);

		// when
		List<PriceCalculateRequest> list = new ArrayList<PriceCalculateRequest>();
		PriceCalculateRequest price = new PriceCalculateRequest(1, 20, 2);
		list.add(price);
		PriceCalculateResponse response = priceCalculateService.priceCalculate(list);

		// then
		collector.checkThat(response.getTotalPrice(), equalTo(0.0));
		verify(priceCalculateProperties).getPenguinEars();
		verify(priceCalculateProperties).getHorseShoes();
	}

	// test again with correct method
	@Test
	public void getAllPenguinPricesExceptionTestCase() throws Exception {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");
		when(productRepository.findAll()).thenReturn(null);
		when(priceReturningComponent.priceList(anyInt(), any())).thenReturn(null);

		// when
		PriceListResponse response = priceCalculateService.getPenguinEarsPrices();

		// then
		collector.checkThat(response.getProductList(), equalTo(null));

	}

	@Test
	public void getAllHorsePricesExceptionTestCase() throws Exception {
		// given
		when(priceCalculateProperties.getPenguinEars()).thenReturn("1");
		when(priceCalculateProperties.getHorseShoes()).thenReturn("2");
		when(productRepository.findAll()).thenReturn(null);
		when(priceReturningComponent.priceList(anyInt(), any())).thenReturn(null);

		// when
		PriceListResponse response = priceCalculateService.getHorseShoePrices();

		// then
		collector.checkThat(response.getProductList(), equalTo(null));

	}

}
