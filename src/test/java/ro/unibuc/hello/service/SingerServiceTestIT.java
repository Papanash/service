package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.data.SingerRepository;
import ro.unibuc.hello.dto.SingerDTO;

import java.util.ArrayList;

@SpringBootTest
@Tag("IT")
public class SingerServiceTestIT {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    SingerService singerService;

    @Test
    void getSinger() {
        SingerDTO singer = singerService.getSinger("6426a93f486aad563cf82a80");
        Assertions.assertEquals(singer.getId(), "6426a93f486aad563cf82a80");
        Assertions.assertEquals(singer.getName(), "Alex Velea");
        Assertions.assertEquals(singer.getAge(), 38);
        Assertions.assertEquals(singer.getGenre(), "Urban");
        Assertions.assertEquals(singer.getPopularity(), 70);
        String[] expectedHitSongs = {"Minim Doi"};
        Assertions.assertArrayEquals(singer.getHitSongs(), expectedHitSongs);

    }

    @Test
    void createSinger() {
        SingerDTO newSinger = singerService.createSinger("Test Singer", 25, "Pop", 80, new String[]{"Test Song"});
        Assertions.assertNotNull(newSinger.getId());
        Assertions.assertEquals(newSinger.getName(), "Test Singer");
        Assertions.assertEquals(newSinger.getAge(), 25);
        Assertions.assertEquals(newSinger.getGenre(), "Pop");
        Assertions.assertEquals(newSinger.getPopularity(), 80);
        String[] expectedHitSongs = {"Test Song"};
        Assertions.assertArrayEquals(newSinger.getHitSongs(), expectedHitSongs);
    }

    @Test
    void editSinger() {
        SingerDTO singer = singerService.editSinger("6426a93f486aad563cf82a80", "Alexandru Velea", 39, "Urban Pop", 75, new String[]{"Minim Doi", "Hit Song"});
        Assertions.assertEquals(singer.getId(), "6426a93f486aad563cf82a80");
        Assertions.assertEquals(singer.getName(), "Alexandru Velea");
        Assertions.assertEquals(singer.getAge(), 39);
        Assertions.assertEquals(singer.getGenre(), "Urban Pop");
        Assertions.assertEquals(singer.getPopularity(), 75);
        String[] expectedHitSongs = {"Minim Doi", "Hit Song"};
        Assertions.assertArrayEquals(singer.getHitSongs(), expectedHitSongs);
    }

    @Test
    void getSingers() {
        ArrayList<SingerDTO> singers = singerService.getSingers();
        Assertions.assertFalse(singers.isEmpty());
        //Assertions.assertTrue(singers.size() > 0);
    }

    @Test
    void deleteSinger() {
        SingerDTO newSinger = singerService.createSinger("Delete Singer Test", 70, "Pop", 80, new String[]{"Test Song"});
        String deleteResult = singerService.deleteSinger(newSinger.getId());
        Assertions.assertEquals("Delete successful", deleteResult);
    }

    @Test
    void deleteNonExistentSinger() {
        String deleteResult1 = singerService.deleteSinger("6432443243242432");
        Assertions.assertEquals("Error: The provided ID 6432443243242432 is not a valid ObjectId", deleteResult1);
        String deleteResult2 = singerService.deleteSinger("6426a93f486aad563cf82a01");
        Assertions.assertEquals("Error: The singer with the ID 6426a93f486aad563cf82a01 does not exist", deleteResult2);
    }
}
