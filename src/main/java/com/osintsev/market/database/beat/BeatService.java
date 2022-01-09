package com.osintsev.market.database.beat;

import com.osintsev.market.exception.BeatNotFoundException;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;

public interface BeatService {
    Beats getBeats();
    Beat getBeat(Long id) throws BeatNotFoundException;
}