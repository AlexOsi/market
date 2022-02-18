package com.osintsev.market.database.beat;

import com.osintsev.market.database.converter.Converter;
import com.osintsev.market.exception.BeatNotFoundException;

import com.osintsev.market.rest.dto.Beat;
import com.osintsev.market.rest.dto.BeatDetailed;
import com.osintsev.market.rest.dto.Beats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeatServiceImpl implements BeatService {

    private final BeatRepository beatRepository;
    private final Converter converter;

    @Autowired
    public BeatServiceImpl(BeatRepository beatRepository, Converter converter) {
        this.beatRepository = beatRepository;
        this.converter = converter;
    }

    @Override
    public Beats getBeats() {
        List<BeatEntity> beatEntityList = beatRepository.findAll();
        List<Beat> beatList = beatEntityList.stream()
                .map(converter::beatEntityToBeat).collect(Collectors.toList());
        Beats beats = new Beats();
        beats.setBeatList(beatList);
        return beats;
    }


    @Override
    public Beat getBeat(Long id) throws BeatNotFoundException {

        Optional<BeatEntity> optionalBeat = beatRepository.findById(id);
        if (optionalBeat.isPresent()) {
            BeatEntity beatEntity = optionalBeat.get();
            return converter.beatEntityToBeat(beatEntity);
        }
        else {
            throw new BeatNotFoundException("No such beat exists");
        }
    }

    @Override
    public BeatDetailed getBeatDetailed(Long id) throws BeatNotFoundException {
        Optional<BeatEntity> optionalBeat = beatRepository.findById(id);
        if (optionalBeat.isPresent()) {
            BeatEntity beatEntity = optionalBeat.get();
            return converter.beatEntityToBeatDetailed(beatEntity);
        }
        else {
            throw new BeatNotFoundException("No such beat exists");
        }
    }

    @Override
    public void createDetailedBeat(BeatDetailed beatDetailed) {
        beatRepository.save(converter.beatDetailedToBeatEntity(beatDetailed));
    }

    @Override
    public void deleteBeat(Long id) {
        beatRepository.deleteById(id);
    }

    @Override
    public void changeBeat(BeatDetailed beatDetailed) {
        BeatEntity beatEntity = beatRepository.findById(beatDetailed.getId()).orElseThrow(() ->
                new BeatNotFoundException("Beat doesn't exist"));
        converter.beatDetailedToBeatEntityTransferData(beatDetailed, beatEntity);
        System.out.println(beatEntity.getPrice());
        beatRepository.save(beatEntity);
    }


}
