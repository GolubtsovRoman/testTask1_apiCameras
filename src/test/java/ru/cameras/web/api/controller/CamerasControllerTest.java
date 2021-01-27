package ru.cameras.web.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.cameras.web.service.CamerasHandlerService;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.model.UrlType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CamerasController.class)
@ExtendWith(SpringExtension.class)
class CamerasControllerTest {
    
    @Autowired private MockMvc mockMvc;
    
    @MockBean private CamerasHandlerService camerasHandlerService;
    
    @Test
    void getInfo_ok() throws Exception {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
    
        List<CameraDto> cameraDtos = new ArrayList<>();
        cameraDtos.add(
                new CameraDto()
                        .setId(20L)
                        .setUrlType(UrlType.ARCHIVE)
                        .setVideoUrl("rtsp://127.0.0.1/2")
                        .setValue(UUID.fromString("fa4b5b22-249b-11e9-ab14-d663bd873d93"))
                        .setTtl(60)
        );
        cameraDtos.add(
                new CameraDto()
                        .setId(1L)
                        .setUrlType(UrlType.LIVE)
                        .setVideoUrl("rtsp://127.0.0.1/1")
                        .setValue(UUID.fromString("fa2b5b22-249b-45e9-ab14-d734bd873d93"))
                        .setTtl(60)
        );
        
        
        when(camerasHandlerService.getListCameras(url)).thenReturn(cameraDtos);
        mockMvc.perform(post("/api/get-info-by-url")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"url\": \"" + url + "\"\n" +
                                "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].urlType").value("LIVE"))
                .andExpect(jsonPath("$[0].videoUrl").value("rtsp://127.0.0.1/1"))
                .andExpect(jsonPath("$[0].value").value("fa2b5b22-249b-45e9-ab14-d734bd873d93"))
                .andExpect(jsonPath("$[0].ttl").value(60));
        
        verify(camerasHandlerService, times(1)).getListCameras(url);
    }
    
    @Test
    void getInfo_notFound() throws Exception {
        String url = "url";
        when(camerasHandlerService.getListCameras(url)).thenReturn(null);
        mockMvc.perform(post("/api/get-info-by-url")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"url\": \"" + url + "\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    
        verify(camerasHandlerService, times(1)).getListCameras(url);
    }
}
