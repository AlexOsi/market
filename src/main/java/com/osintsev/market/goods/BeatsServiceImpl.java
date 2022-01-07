package com.osintsev.market.goods;

import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeatsServiceImpl implements BeatsService {

    private final BeatRepository beatRepository;

    @Autowired
    public BeatsServiceImpl(BeatRepository beatRepository) {
        this.beatRepository = beatRepository;
    }

    @Override
    public Beats getBeats() {
        List<BeatEntity> beatEntityList = beatRepository.findAll();
        List<Beat> beatList = beatEntityList.stream()
                .map(beatEntity -> {
                    Beat beat = new Beat();
                    beat.setId(beatEntity.getId());
                    beat.setName(beatEntity.getName());
                    beat.setGenre(beatEntity.getGenre());
                    beat.setImage(beatEntity.getImage());
                    beat.setPrice(beatEntity.getPrice());
                    return beat;
                }).collect(Collectors.toList());
        Beats beats = new Beats();
        beats.setBeatList(beatList);
        return beats;
    }
}
