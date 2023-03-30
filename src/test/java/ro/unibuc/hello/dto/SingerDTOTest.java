package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.SingerEntity;

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

    @Test
    void test_defaultConstructor() {
        SingerDTO defaultSingerDTO = new SingerDTO();
        Assertions.assertNull(defaultSingerDTO.getId());
        Assertions.assertNull(defaultSingerDTO.getName());
        Assertions.assertEquals(0, defaultSingerDTO.getAge());
        Assertions.assertNull(defaultSingerDTO.getGenre());
        Assertions.assertEquals(0, defaultSingerDTO.getPopularity());
        Assertions.assertNull(defaultSingerDTO.getHitSongs());
    }

    @Test
    void test_constructorWithSingerEntity() {
        SingerEntity singerEntity = new SingerEntity();
        singerEntity.setId(id);
        singerEntity.setName(name);
        singerEntity.setAge(age);
        singerEntity.setGenre(genre);
        singerEntity.setPopularity(popularity);
        singerEntity.setHitSongs(hitSongs);

        SingerDTO singerDTOFromEntity = new SingerDTO(singerEntity);
        Assertions.assertEquals(id, singerDTOFromEntity.getId());
        Assertions.assertEquals(name, singerDTOFromEntity.getName());
        Assertions.assertEquals(age, singerDTOFromEntity.getAge());
        Assertions.assertEquals(genre, singerDTOFromEntity.getGenre());
        Assertions.assertEquals(popularity, singerDTOFromEntity.getPopularity());
        Assertions.assertArrayEquals(hitSongs, singerDTOFromEntity.getHitSongs());
    }

    @Test
    void test_constructorWithNameAgeGenrePopularityHits() {
        SingerDTO singerDTOWithParams = new SingerDTO(name, age, genre, popularity, hitSongs);
        Assertions.assertNull(singerDTOWithParams.getId());
        Assertions.assertEquals(name, singerDTOWithParams.getName());
        Assertions.assertEquals(age, singerDTOWithParams.getAge());
        Assertions.assertEquals(genre, singerDTOWithParams.getGenre());
        Assertions.assertEquals(popularity, singerDTOWithParams.getPopularity());
        Assertions.assertArrayEquals(hitSongs, singerDTOWithParams.getHitSongs());
    }

    @Test
    void setId() {
        String newId = "123456abcdef7890";
        singerDTO.setId(newId);
        Assertions.assertEquals(newId, singerDTO.getId());
    }

    @Test
    void setName() {
        String newName = "Inna";
        singerDTO.setName(newName);
        Assertions.assertEquals(newName, singerDTO.getName());
    }

    @Test
    void setAge() {
        int newAge = 40;
        singerDTO.setAge(newAge);
        Assertions.assertEquals(newAge, singerDTO.getAge());
    }

    @Test
    void setGenre() {
        String newGenre = "Pop";
        singerDTO.setGenre(newGenre);
        Assertions.assertEquals(newGenre, singerDTO.getGenre());
    }

    @Test
    void setHitSongs() {
        String[] newHitSongs = new String[]{"Undeva in Balcani", "Maidanez"};
        singerDTO.setHitSongs(newHitSongs);
        Assertions.assertArrayEquals(newHitSongs, singerDTO.getHitSongs());
    }
}



