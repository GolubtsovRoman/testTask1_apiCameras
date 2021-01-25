package ru.cameras.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CamerasHandlerService {

    public List<CameraDto> getListCameras() {
        // TODO: 25.01.2021 чтение из json и маппинг Текущая реализация это заглушка
    
        List<CameraDto> cameraDtoList = new ArrayList<>();
        
        cameraDtoList.add(
                new CameraDto()
                        .setId(1L)
                        .setUrlType("urlType")
                        .setVideoUrl("videoUrl")
                        .setValue("value")
                        .setTtl(120L)
        );
    
        cameraDtoList.add(
                new CameraDto()
                        .setId(2L)
                        .setUrlType("urlType")
                        .setVideoUrl("videoUrl")
                        .setValue("value")
                        .setTtl(120L)
        );
        
        return cameraDtoList;
    }
}
