package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osintsev.market.database.order.OrderStatus;
import com.osintsev.market.database.order.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Schema(description = "Order data")
@Validated
public class Order {
    @Null
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "User data", required = true)
    @JsonProperty("user")
    private User user;

    @NotNull
    @Schema(description = "list of purchases")
    @JsonProperty("purchaseList")
    private List<Purchase> purchaseList;

    @NotNull
    @Schema(description = "status")
    @JsonProperty("status")
    private OrderStatus status;

    @NotNull
    @Schema(description = "payment method")
    @JsonProperty("payment")
    private PaymentMethod payment;
}
