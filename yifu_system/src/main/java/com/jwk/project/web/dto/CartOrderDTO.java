package com.jwk.project.web.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartOrderDTO {

    private String cartId;

    private String jeansId;

    private String addrId;
}
