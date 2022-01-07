package com.osintsev.market.goods;

import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;

public interface BeatService {
    Beats getBeats();
    Beat getBeat(Long id);
}
