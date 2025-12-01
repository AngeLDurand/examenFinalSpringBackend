package com.levelup.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDTO {
    private Long addressId;
    private List<OrderItemRequestDTO> items;
}
