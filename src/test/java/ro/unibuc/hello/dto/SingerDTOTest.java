package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SingerDTOTest {
    String id;
    String name;
    int age;
    String genre;
    int popularity;
    String[] hitSongs;
    SingerDTO singerDTO;
    String singerDTOString;

    @BeforeEach
    void setUp() {
        id = "413456b37d7e23385bfe7f1";
        name = "John Doe";
        age = 35;
        genre = "Rock";
        popularity = 85;
        hitSongs = new String[]{"Hit Song 1", "Hit Song 2"};
        singerDTO = new SingerDTO();
        singerDTO.setId(id);
        singerDTO.setName(name);
        singerDTO.setAge(age);
        singerDTO.setGenre(genre);
        singerDTO.setPopularity(popularity);
        singerDTO.setHitSongs(hitSongs);
        singerDTOString = "SingerDTO{" +
                "id='" + id + '\'' +
                ", title='" + name + '\'' +
                ", author='" + age + '\'' +
                ", genre='" + genre + '\'' +
                ", popularity='" + popularity + '\'' +
                ", hitSongs='" + Arrays.toString(hitSongs) + '\'' +
                '}';
    }

    @Test
    void getId() {
        Assertions.assertEquals(id, singerDTO.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals(name, singerDTO.getName());
    }

    @Test
    void getAge() {
        Assertions.assertEquals(age, singerDTO.getAge());
    }

    @Test
    void getGenre() {
        Assertions.assertEquals(genre, singerDTO.getGenre());
    }

    @Test
    void getPopularity() {
        Assertions.assertEquals(popularity, singerDTO.getPopularity());
    }

    @Test
    void setPopularity() {
        assertThrows(IllegalArgumentException.class, () -> singerDTO.setPopularity(-1));
        assertThrows(IllegalArgumentException.class, () -> singerDTO.setPopularity(101));
    }

    @Test
    void getHitSongs() {
        Assertions.assertArrayEquals(hitSongs, singerDTO.getHitSongs());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(singerDTOString, singerDTO.toString());
    }
}
