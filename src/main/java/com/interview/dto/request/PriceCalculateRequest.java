package com.interview.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceCalculateRequest {
    private int type;
    private int unitQuantity;
    private int cartonQuantity;
}
