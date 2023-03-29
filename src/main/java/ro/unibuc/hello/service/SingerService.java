package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.data.SingerEntity;
import ro.unibuc.hello.data.SingerRepository;
import ro.unibuc.hello.dto.SingerDTO;
import java.util.List;
import java.util.ArrayList;

@Component
public class SingerService {
    @Autowired
    private SingerRepository singerRepository;

    public SingerDTO createSinger(String name, int age, String genre, int popularity, String[] hitSongs) {
        SingerEntity singer = new SingerEntity(name, age, genre, popularity, hitSongs);
        return new SingerDTO(singerRepository.save(singer));
    }

    public ArrayList<SingerDTO> getSingers() {
        ArrayList<SingerDTO> singerDTOs = new ArrayList();
        singerRepository.findAll().forEach(singerEntity -> singerDTOs.add(new SingerDTO(singerEntity)));
        return singerDTOs;
    }

    public SingerDTO getSinger(String id) {
        SingerEntity singerEntity = singerRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if (singerEntity != null) {
            return new SingerDTO(singerEntity);
        } else {
            return null;
        }
    }

    public SingerDTO editSinger(String id, String name, Integer age, String genre, Integer popularity, String[] hitSongs) {
        SingerEntity singer = singerRepository.findById(String.valueOf(new ObjectId(id))).orElse(null);
        if (singer != null) {
            if (name != null) {
                singer.setName(name);
            }
            if (age != null) {
                singer.setAge(age);
            }
            if (genre != null) {
                singer.setGenre(genre);
            }
            if (popularity != null) {
                singer.setPopularity(popularity);
            }
            if (hitSongs != null) {
                singer.setHitSongs(hitSongs);
            }
            return new SingerDTO(singerRepository.save(singer));
        } else {
            return null;
        }
    }

    public String deleteSinger(String id) {
        singerRepository.deleteById(String.valueOf(new ObjectId(id)));
        return "Singer " + id + " has been successfully deleted.";

    }
}
