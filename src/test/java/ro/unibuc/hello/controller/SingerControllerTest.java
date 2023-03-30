package ro.unibuc.hello.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.unibuc.hello.dto.SingerDTO;
import ro.unibuc.hello.service.SingerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
class SingerControllerTest {

    @Mock
    private SingerService singerService;
    @InjectMocks
    private SingerController singerController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(singerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void test_createSinger() throws Exception {
        // Arrange
        SingerDTO singer = new SingerDTO("Alex Velea", 38, "Urban", 70, new String[]{"Yamasha"});

        when(singerService.createSinger(singer.getName(), singer.getAge(), singer.getGenre(), singer.getPopularity(), singer.getHitSongs())).thenReturn(singer);

        // Act
        MvcResult result = mockMvc.perform(post("/singer/create")
                        .param("name", singer.getName())
                        .param("age", String.valueOf(singer.getAge()))
                        .param("genre", singer.getGenre())
                        .param("popularity", String.valueOf(singer.getPopularity()))
                        .param("hitSongs", singer.getHitSongs()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(singer));

    }

    @Test
    void test_getSingers() throws Exception {
        // Arrange
        SingerDTO singer = new SingerDTO("Alex Velea", 38, "Urban", 70, new String[]{"Yamasha"});
        ArrayList<SingerDTO> singerDTOList = new ArrayList<SingerDTO>();
        singerDTOList.add(singer);

        when(singerService.getSingers()).thenReturn(singerDTOList);

        // Act
        MvcResult result = mockMvc.perform(get("/singer/getAll"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(singerDTOList));
    }

    @Test
    void test_getSinger() throws Exception {
        // Arrange
        String id = "1";
        SingerDTO singer = new SingerDTO("Alex Velea", 38, "Urban", 70, new String[]{"Yamasha"});
        singer.setId(id);

        when(singerService.getSinger(id)).thenReturn(singer);

        // Act
        MvcResult result = mockMvc.perform(get("/singer/get")
                        .param("id", id))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(singer));

    }

    @Test
    void test_editSinger() throws Exception {
        SingerDTO originalSinger = new SingerDTO("Alex Velea", 38, "Urban", 70, new String[]{"Yamasha"});
        String id = "1";
        originalSinger.setId(id);

        SingerDTO updatedSinger = new SingerDTO("Alex Velea", 38, "Urban", 75, new String[]{"Yamasha", "New Song"});
        updatedSinger.setId(id);

        when(singerService.editSinger(eq(id), anyString(), anyInt(), anyString(), anyInt(), any(String[].class))).thenReturn(updatedSinger);
        /*
        This exception may occur if matchers are combined with raw values:
    //incorrect:
    someMethod(anyObject(), "raw String");
When using matchers, all arguments have to be provided by matchers.
For example:
    //correct:
    someMethod(anyObject(), eq("String by matcher"));

         */

        // Act
        MvcResult result = mockMvc.perform(put("/singer/edit")
                        .param("id", id)
                        .param("name", updatedSinger.getName())
                        .param("age", String.valueOf(updatedSinger.getAge()))
                        .param("genre", updatedSinger.getGenre())
                        .param("popularity", String.valueOf(updatedSinger.getPopularity()))
                        .param("hitSongs", updatedSinger.getHitSongs()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), objectMapper.writeValueAsString(updatedSinger));
    }

    @Test
    void test_deleteSinger() throws Exception {
        // Arrange
        SingerDTO singer = new SingerDTO("Alex Velea", 38, "Urban", 70, new String[]{"Yamasha"});
        String id = "1";
        singer.setId(id);
        String expectedResponse = "Singer with id " + id + " has been deleted.";

        when(singerService.deleteSinger(id)).thenReturn(expectedResponse);

        // Act
        MvcResult result = mockMvc.perform(delete("/singer/delete")
                        .param("id", id))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Assertions.assertEquals(result.getResponse().getContentAsString(), expectedResponse);

    }

}
