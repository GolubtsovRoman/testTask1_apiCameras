package ru.cameras.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.model.CameraSrcDto;
import ru.cameras.web.service.model.SourceDataUrl;
import ru.cameras.web.service.model.TokenDataUrl;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class AggregatorThread extends Thread {
    private final BlockingQueue<CameraSrcDto> originalQueue;
    private final BlockingQueue<CameraDto> resultQueue;
    
    @Override
    public void run() {
        if (!Thread.interrupted()) {
            try {
                while (!originalQueue.isEmpty()) {
                    resultQueue.add(
                            convertToCameraDto(originalQueue.take())
                    );
                }
            } catch (InterruptedException ignore) { }
        }
    }
    
    private CameraDto convertToCameraDto(CameraSrcDto cameraSrcDto) {
        CameraDto cameraDto = new CameraDto()
                .setId(cameraSrcDto.getId());

        try {
            SourceDataUrl sourceDataUrl = new ObjectMapper()
                    .readValue(cameraSrcDto.getSourceDataUrl(), SourceDataUrl.class);
            cameraDto
                    .setUrlType(sourceDataUrl.getUrlType())
                    .setVideoUrl(sourceDataUrl.getVideoUrl());
        } catch (IOException ignore) { }
    
        try {
            TokenDataUrl tokenDataUrl = new ObjectMapper()
                    .readValue(cameraSrcDto.getTokenDataUrl(), TokenDataUrl.class);
            cameraDto
                    .setValue(tokenDataUrl.getValue())
                    .setTtl(tokenDataUrl.getTtl());
        } catch (IOException ignore) { }
        
        return cameraDto;
    }
}
