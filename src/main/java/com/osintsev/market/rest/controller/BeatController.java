package com.osintsev.market.rest.controller;

import com.osintsev.market.goods.BeatService;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@Validated
public class BeatController {
    private final Beats beats = new Beats();

    private static long idOfBeat = 0L;

    private final BeatService beatService;

    @Autowired
    public BeatController(BeatService beatService) {
        beats.setBeatList(new ArrayList<>());
        this.beatService = beatService;
    }

    private long createIdForBeat() {
        return idOfBeat++;
    }


    @GetMapping(value = "beats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beats getBeats() {
        return beatService.getBeats();
    }

    @GetMapping(value = "beat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beat getBeat(@PathVariable Long id) {
        return beatService.getBeat(id);
    }

    @PostMapping(path = "beat/create",
                consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beat> createBeat(@RequestBody @Valid Beat newBeat) {
        newBeat.setId(createIdForBeat());
        beats.getBeatList().add(newBeat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
