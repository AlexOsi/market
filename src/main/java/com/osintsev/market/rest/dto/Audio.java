package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Validated
@Schema(description = "Audio data")
public class Audio {
    @Null
    @Schema(description = "id")
    @JsonProperty("id")
    Long id;

    @NotNull
    @Schema(description = "wav url", required = true)
    @JsonProperty("wav")
    private String wav;

    @NotNull
    @Schema(description = "mp3 url", required = true)
    @JsonProperty("mp3")
    private String mp3;

    @NotNull
    @Schema(description = "track out url", required = true)
    @JsonProperty("trackOut")
    private String trackOut;
}
