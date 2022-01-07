package com.osintsev.market.rest.controller;

import com.osintsev.market.goods.BeatService;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import static org.mockito.ArgumentMatchers.any;


public class BeatControllerTest {

    @Test
    void testBeats() {
        BeatService beatService = Mockito.mock(BeatService.class);
        Beats beats = new Beats();
        Mockito.doReturn(beats).when(beatService).getBeats();
        BeatController beatController = new BeatController(beatService);
        Beats beatsTest = beatController.getBeats();
        AssertionErrors.assertEquals("", beatsTest, beats);
        Mockito.verify(beatService, Mockito.times(1)).getBeats();
    }

    @Test
    void testBeatById() {
        BeatService beatService = Mockito.mock(BeatService.class);
        Beat beat = new Beat();
        Mockito.doReturn(beat).when(beatService).getBeat(any());
        BeatController beatController = new BeatController(beatService);
        Long id = 2L;
        Beat beatTest = beatController.getBeat(id);
        AssertionErrors.assertEquals("", beatTest, beat);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(beatService, Mockito.times(1)).getBeat(argumentCaptor.capture());
        AssertionErrors.assertEquals("", argumentCaptor.getValue(), id);

    }
}
