package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.data.SingerRepository;
import ro.unibuc.hello.dto.SingerDTO;

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
}
