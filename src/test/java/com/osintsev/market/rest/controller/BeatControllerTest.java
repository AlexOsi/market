package com.osintsev.market.rest.controller;

import com.osintsev.market.database.beat.BeatService;
import com.osintsev.market.exception.BeatNotFoundException;
import com.osintsev.market.rest.dto.BeatDetailed;
import com.osintsev.market.rest.dto.Beats;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import static org.mockito.ArgumentMatchers.any;


public class BeatControllerTest {

    @Test
    void testGetBeats() {
        BeatService beatService = Mockito.mock(BeatService.class);
        Beats beats = new Beats();
        Mockito.doReturn(beats).when(beatService).getBeats();
        BeatController beatController = new BeatController(beatService);
        Beats beatsTest = beatController.getBeats();
        AssertionErrors.assertEquals("", beatsTest, beats);
        Mockito.verify(beatService, Mockito.times(1)).getBeats();
    }

    @Test
    void testGetDetailedBeat() {
        BeatService beatService = Mockito.mock(BeatService.class);
        BeatDetailed beat = new BeatDetailed();
        try {
            Mockito.doReturn(beat).when(beatService).getBeat(any());
        } catch (BeatNotFoundException e) {
            e.printStackTrace();
        }
        BeatController beatController = new BeatController(beatService);
        Long id = 2L;
        BeatDetailed beatTest = beatController.getDetailedBeat(id);
        AssertionErrors.assertEquals("", beatTest, beat);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        try {
            Mockito.verify(beatService, Mockito.times(1)).getBeat(argumentCaptor.capture());
        } catch (BeatNotFoundException e) {
            e.printStackTrace();
        }
        AssertionErrors.assertEquals("", argumentCaptor.getValue(), id);

    }
}
