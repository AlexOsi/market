package com.osintsev.market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Schema(description = "Detailed beat data")
@Validated
public class BeatDetailed {

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

    @Schema(description = "Genre", required = true)
    @JsonProperty("genre")
    private String genre;

    @NotNull
    @Schema(description = "Load date", required = true)
    @JsonProperty("loadDate")
    private Date loadDate;

    @NotNull
    @Schema(description = "BPM", required = true)
    @JsonProperty("bpm")
    private Integer BPM;

    @Schema(description = "Key", required = true)
    @JsonProperty("key")
    private String key;
}
