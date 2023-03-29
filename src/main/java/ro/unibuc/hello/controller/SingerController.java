package ro.unibuc.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.SingerDTO;
import ro.unibuc.hello.service.SingerService;

import java.util.ArrayList;
@Controller
public class SingerController {
    @Autowired
    private SingerService singerService;

    @PostMapping("/singer/create")
    @ResponseBody
    public SingerDTO createSinger(@RequestParam(name = "name") String name, @RequestParam(name = "age") int age, @RequestParam(name = "genre") String genre, @RequestParam(name = "popularity") int popularity, @RequestParam(name = "hitSongs") String[] hitSongs) {
        return singerService.createSinger(name, age, genre, popularity, hitSongs);
    }

    @GetMapping("/singer/getAll")
    @ResponseBody
    public ArrayList<SingerDTO> getSingers() {
        return singerService.getSingers();
    }

    @GetMapping("/singer/get")
    @ResponseBody
    public SingerDTO getSinger(@RequestParam(name = "id") String id) {
        return singerService.getSinger(id);
    }
}
