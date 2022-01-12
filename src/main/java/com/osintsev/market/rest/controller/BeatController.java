package com.osintsev.market.rest.controller;

import com.osintsev.market.database.beat.BeatService;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.BeatDetailed;
import com.osintsev.market.rest.dto.Beats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class BeatController {


    private final BeatService beatService;

    @Autowired
    public BeatController(BeatService beatService) {
        this.beatService = beatService;
    }

    @GetMapping(value = "beats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beats getBeats() {
        return beatService.getBeats();
    }

    @GetMapping(value = "beat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BeatDetailed getDetailedBeat(@PathVariable Long id) {
        return beatService.getDetailedBeat(id);
    }

    @PostMapping(path = "beat/create",
                consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beat> createBeat(@RequestBody @Valid BeatDetailed beatDetailed) {
        beatService.createDetailedBeat(beatDetailed);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
