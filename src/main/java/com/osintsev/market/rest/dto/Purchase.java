package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osintsev.market.database.order.LicenseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Purchase data")
@Validated
public class Purchase {
    @Null
    @Schema(description = "id")
    @JsonProperty("beatId")
    private Long beatId;

    @NotNull
    @Schema(description = "license type")
    @JsonProperty("license")
    private LicenseType license;

}
