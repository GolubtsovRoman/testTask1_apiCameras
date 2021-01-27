package ru.cameras.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.model.UrlType;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CamerasHandlerServiceTest {
    
    @InjectMocks private CamerasHandlerService subj;
    
    @Test
    void getListCameras_ok() {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
    
        CameraDto camera2 = new CameraDto()
                .setId(20L)
                .setUrlType(UrlType.ARCHIVE)
                .setVideoUrl("rtsp://127.0.0.1/2")
                .setValue(UUID.fromString("fa4b5b22-249b-11e9-ab14-d663bd873d93"))
                .setTtl(60);
        
        List<CameraDto> result = subj.getListCameras(url);
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(camera2, result.get(1));
    }
    
    @Test
    void getListCameras_badUrl() {
        String url = "bad_url";
        
        List<CameraDto> result = subj.getListCameras(url);
        assertNull(result);
    }
}
