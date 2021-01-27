package ru.cameras.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.model.CameraSrcDto;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class CamerasHandlerService {
    
    public List<CameraDto> getListCameras(String url) {
        List<CameraSrcDto> cameraDtoList;
        try {
            cameraDtoList = new ObjectMapper().readValue(
                    new URL(url),
                    new TypeReference<List<CameraSrcDto>>() { }
            );
        } catch (IOException ioe) {
            return null;
        }
        
        return new LinkedList<>(getResultQueue(cameraDtoList));
    }
    
    private BlockingQueue<CameraDto> getResultQueue(List<CameraSrcDto> cameraDtoList) {
        BlockingQueue<CameraSrcDto> cameraSrcDtoBlockingQueue = new ArrayBlockingQueue<>(
                cameraDtoList.size(),
                true,
                cameraDtoList
        );
        BlockingQueue<CameraDto> cameraDtoBlockingQueue = new ArrayBlockingQueue<>(
                cameraDtoList.size(),
                true
        );
        
        int countThread = Runtime.getRuntime().availableProcessors();
        LinkedList<AggregatorThread> threads = new LinkedList<>();
        for (int i = 0; i < countThread; i++) {
            threads.add(new AggregatorThread(cameraSrcDtoBlockingQueue, cameraDtoBlockingQueue));
            threads.get(i).start();
        }
    
        for (AggregatorThread at : threads) {
            try {
                at.join();
            } catch (InterruptedException ie) {
                at.interrupt();
            }
        }
        
        return cameraDtoBlockingQueue;
    }
}
