package com.osintsev.market.rest.controllers;

import com.osintsev.market.goods.BeatsServiceImpl;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;
import com.osintsev.market.exception.BeatNotFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@Validated
public class BeatController {
    private final Beats beats = new Beats();

    private static long idOfBeat = 0L;

    private final BeatsServiceImpl beatService;

    @Autowired
    public BeatController(BeatsServiceImpl beatService) {
        beats.setBeatList(new ArrayList<>());
        this.beatService = beatService;
    }

    private long createIdForBeat() {
        return idOfBeat++;
    }


    @GetMapping(value = "beats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beats beats() {
        return beatService.getBeats();
    }

    @GetMapping(value = "beat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beat beat(@PathVariable Long id) {
        return beatService.getBeats().getBeatList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BeatNotFoundException("No such beat exists"));
    }

    @PostMapping(path = "beat/create",
                consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beat> create(@RequestBody @Valid Beat newBeat) {
        newBeat.setId(createIdForBeat());
        beats.getBeatList().add(newBeat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
