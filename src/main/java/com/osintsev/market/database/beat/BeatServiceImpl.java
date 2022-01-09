package com.osintsev.market.database.beat;

import com.osintsev.market.exception.BeatNotFoundException;
import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.Beats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeatServiceImpl implements BeatService {

    private final BeatRepository beatRepository;

    @Autowired
    public BeatServiceImpl(BeatRepository beatRepository) {
        this.beatRepository = beatRepository;
    }

    @Override
    public Beats getBeats() {
        List<BeatEntity> beatEntityList = beatRepository.findAll();
        List<Beat> beatList = beatEntityList.stream()
                .map(this::createBeatFromBeatEntity).collect(Collectors.toList());
        Beats beats = new Beats();
        beats.setBeatList(beatList);
        return beats;
    }

    @Override
    public Beat getBeat(Long id) throws BeatNotFoundException {

        Optional<BeatEntity> optionalBeat = beatRepository.findById(id);
        if (optionalBeat.isPresent()) {
            BeatEntity beatEntity = optionalBeat.get();
            return createBeatFromBeatEntity(beatEntity);
        }
        else {
            throw new BeatNotFoundException("No such beat exists");
        }
    }

    private Beat createBeatFromBeatEntity(BeatEntity beatEntity) {
        Beat beat = new Beat();
        beat.setId(beatEntity.getId());
        beat.setName(beatEntity.getName());
        beat.setGenre(beatEntity.getGenre());
        beat.setImage(beatEntity.getImage());
        beat.setPrice(beatEntity.getPrice());
        return beat;
    }
}
