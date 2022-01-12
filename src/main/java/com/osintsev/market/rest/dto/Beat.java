package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Schema(description = "Beat data")
@Validated
public class Beat {

    @Null
    @Schema(description = "id")
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "Name", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "Relative url of image", required = true)
    @JsonProperty("image")
    private String image;

    @NotNull
    @Schema(description = "Price", required = true)
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Audio files URLs", required = true)
    @JsonProperty("audio")
    private Audio audio;
}
