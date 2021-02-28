package com.interview.dto.response;

import com.interview.dto.ProductsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListResponse {

    private List<ProductsDTO> productList;


}
