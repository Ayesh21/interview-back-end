package com.interview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceCalculateResponse {

    private double totalPriceForPenguinEars;
    private double totalPriceForHorseShoes;
    private double totalPrice;
}
