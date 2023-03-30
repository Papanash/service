package ro.unibuc.hello.dto;

import org.springframework.data.annotation.Id;
import ro.unibuc.hello.data.SingerEntity;
import java.util.Arrays;


public class SingerDTO {
    @Id
    private String id;

    private String name;
    private int age;
    private String genre;
    private int popularity;
    private String[] hitSongs;

    public SingerDTO() {}

    public SingerDTO(SingerEntity singer) {
        this.id = singer.getId();
        this.name = singer.getName();
        this.age = singer.getAge();
        this.genre = singer.getGenre();
        this.popularity = singer.getPopularity();
        this.hitSongs = singer.getHitSongs();
    }

    public SingerDTO(String name, int age, String genre, int pop, String[] hits) {
        this.name = name;
        this.age = age;
        this.genre = genre;
        this.popularity = pop;
        this.hitSongs = hits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        if (popularity < 0 || popularity > 100) {
            throw new IllegalArgumentException("Popularity must be between 0 and 100");
        }
        this.popularity = popularity;
    }

    public String[] getHitSongs() {
        return hitSongs;
    }
    public void setHitSongs(String[] hitSongs) {
        this.hitSongs = hitSongs;
    }

    @Override
    public String toString() {
        return "SingerDTO{" +
                "id='" + id + '\'' +
                ", title='" + name + '\'' +
                ", author='" + age + '\'' +
                ", genre='" + genre + '\'' +
                ", popularity='" + popularity + '\'' +
                ", hitSongs='" + Arrays.toString(hitSongs) + '\'' +
                '}';
    }
}
