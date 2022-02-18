package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "Get list of beats")
@Validated
public class Beats {
    @NotNull
    @Schema(required = true)
    @JsonProperty("beatList")
    private List<Beat> beatList;

}
