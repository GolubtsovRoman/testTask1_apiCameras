package ru.cameras.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.model.CameraSrcDto;
import ru.cameras.web.service.model.SourceDataUrl;
import ru.cameras.web.service.model.TokenDataUrl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CamerasHandlerService {
    
    public List<CameraDto> getListCameras(String url) {
        try {
            List<CameraSrcDto> cameraDtoList = new ObjectMapper().readValue(
                    new URL(url),
                    new TypeReference<List<CameraSrcDto>>() {
                    }
            );
    
            return cameraDtoList
                    .stream()
                    .map(this::convertToCameraDto)
                    .collect(Collectors.toList());
        } catch (IOException ioe) {
            return null;
        }
    }
    
    private CameraDto convertToCameraDto(CameraSrcDto cameraSrcDto) {
        try {
            SourceDataUrl sourceDataUrl = new ObjectMapper().readValue(cameraSrcDto.getSourceDataUrl(), SourceDataUrl.class);
            TokenDataUrl tokenDataUrl = new ObjectMapper().readValue(cameraSrcDto.getTokenDataUrl(), TokenDataUrl.class);
            
            return new CameraDto()
                    .setId(cameraSrcDto.getId())
                    .setUrlType(sourceDataUrl.getUrlType())
                    .setVideoUrl(sourceDataUrl.getVideoUrl())
                    .setValue(tokenDataUrl.getValue())
                    .setTtl(tokenDataUrl.getTtl());
        } catch (IOException ioe) {
            return null;
        }
    }
}
