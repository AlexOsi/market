package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "User data")
@Validated
public class User {
    @Null
    @Schema(description = "id")
    @JsonProperty("id")
    Long id;

    @NotNull
    @Schema(description = "id")
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "id")
    @JsonProperty("email")
    private String email;
}
