package com.osintsev.market.database.beat;

import com.osintsev.market.exception.BeatNotFoundException;
import com.osintsev.market.rest.dto.Audio;
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

    @Autowired
    public BeatServiceImpl(BeatRepository beatRepository) {
        this.beatRepository = beatRepository;
    }

    @Override
    public Beats getBeats() {
        List<BeatEntity> beatEntityList = beatRepository.findAll();
        List<Beat> beatList = beatEntityList.stream()
                .map(this::beatEntityToBeat).collect(Collectors.toList());
        Beats beats = new Beats();
        beats.setBeatList(beatList);
        return beats;
    }


    @Override
    public Beat getBeat(Long id) throws BeatNotFoundException {

        Optional<BeatEntity> optionalBeat = beatRepository.findById(id);
        if (optionalBeat.isPresent()) {
            BeatEntity beatEntity = optionalBeat.get();
            return beatEntityToBeat(beatEntity);
        }
        else {
            throw new BeatNotFoundException("No such beat exists");
        }
    }

    @Override
    public BeatDetailed getDetailedBeat(Long id) throws BeatNotFoundException {
        Optional<BeatEntity> optionalBeat = beatRepository.findById(id);
        if (optionalBeat.isPresent()) {
            BeatEntity beatEntity = optionalBeat.get();
            return beatEntityToBeatDetailed(beatEntity);
        }
        else {
            throw new BeatNotFoundException("No such beat exists");
        }
    }

    @Override
    public void createDetailedBeat(BeatDetailed beatDetailed) {
        beatRepository.save(beatDetailedToBeatEntity(beatDetailed));
    }

    private AudioEntity audioToAudioEntity(Audio audio) {
        AudioEntity audioEntity = new AudioEntity();
        audioEntity.setMp3(audio.getMp3());
        audioEntity.setWav(audio.getWav());
        audioEntity.setTrackOut(audio.getTrackOut());
        return audioEntity;
    }

    private Audio audioEntityToAudio(AudioEntity audioEntity) {
        Audio audio = new Audio();
        audio.setId(audioEntity.getId());
        audio.setMp3(audioEntity.getMp3());
        audio.setWav(audioEntity.getWav());
        audio.setTrackOut(audioEntity.getTrackOut());
        return audio;
    }
    private Beat beatEntityToBeat(BeatEntity beatEntity) {
        Beat beat = new Beat();
        beat.setId(beatEntity.getId());
        beat.setName(beatEntity.getName());
        beat.setImage(beatEntity.getImage());
        beat.setPrice(beatEntity.getPrice());
        beat.setAudio(audioEntityToAudio(beatEntity.getAudio()));
        return beat;
    }

    private BeatDetailed beatEntityToBeatDetailed(BeatEntity beatEntity) {
        BeatDetailed beatDetailed = new BeatDetailed();
        beatDetailed.setId(beatEntity.getId());
        beatDetailed.setName(beatEntity.getName());
        beatDetailed.setImage(beatEntity.getImage());
        beatDetailed.setPrice(beatEntity.getPrice());
        beatDetailed.setAudio(audioEntityToAudio(beatEntity.getAudio()));
        beatDetailed.setBPM(beatEntity.getBPM());
        beatDetailed.setGenre(beatEntity.getGenre());
        beatDetailed.setKey(beatEntity.getKey());
        beatDetailed.setLoadDate(beatEntity.getLoadDate());
        return beatDetailed;
    }

    private BeatEntity beatDetailedToBeatEntity(BeatDetailed beatDetailed) {
        BeatEntity beatEntity = new BeatEntity();
        beatEntity.setName(beatDetailed.getName());
        beatEntity.setImage(beatDetailed.getImage());
        beatEntity.setPrice(beatDetailed.getPrice());
        beatEntity.setAudio(audioToAudioEntity(beatDetailed.getAudio()));
        beatEntity.setBPM(beatDetailed.getBPM());
        beatEntity.setGenre(beatDetailed.getGenre());
        beatEntity.setKey(beatDetailed.getKey());
        beatEntity.setLoadDate(beatDetailed.getLoadDate());
        return beatEntity;
    }

}
