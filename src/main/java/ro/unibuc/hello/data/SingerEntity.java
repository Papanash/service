package ro.unibuc.hello.data;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

public class SingerEntity {
    @Id
    private String id;

    private String name;
    private int age;
    private String genre;
    private int popularity;
    private String[] hitSongs;
    public SingerEntity() {}

    public SingerEntity(String name, int age, String genre, int popularity, String[] hitSongs) {
        this.name = name;
        this.age = age;
        this.genre = genre;
        this.popularity = popularity;
        this.hitSongs = hitSongs;

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
        return String.format(
                "Singer[id='%s', name='%s', age=%d, genre='%s', popularity=%d, hitSongs=%s]",
                id, name, age, genre, popularity, Arrays.toString(hitSongs));
    }
}
