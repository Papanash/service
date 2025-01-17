package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        if (!ObjectId.isValid(id)) {
            return "Error: The provided ID " + id + " is not a valid ObjectId";
        }

        SingerEntity singerEntity = singerRepository.findById(id).orElse(null);

        if (singerEntity == null) {
            return "Error: The singer with the ID " + id + " does not exist";
        }

        try {
            singerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "Error: Failed to delete the singer with the ID " + id;
        } catch (IllegalArgumentException e) {
            return "Error: The provided ID " + id + " is not valid";
        }

        SingerEntity deletedSinger = singerRepository.findById(id).orElse(null);

        if (deletedSinger != null) {
            return "Error: Failed to delete the singer with the ID " + id;
        }

        return "Delete successful";
    }
}
