package ru.cameras.web.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cameras.web.api.json.CameraRes;
import ru.cameras.web.service.CameraDto;
import ru.cameras.web.service.CamerasHandlerService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class Cameras {
    private final CamerasHandlerService camerasHandlerService;
    
    @GetMapping("/get-info")
    public @ResponseBody ResponseEntity<List<CameraRes>> getInfo(HttpServletRequest httpServletRequest) {
        // TODO: 25.01.2021 прописать логику
        
        List<CameraRes> cameraDtoList = camerasHandlerService.getListCameras()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        
        return ok(cameraDtoList);
    }
    
    private CameraRes convert(CameraDto cameraDto) {
        return new CameraRes()
                .setId(cameraDto.getId())
                .setUrlType(cameraDto.getUrlType())
                .setVideoUrl(cameraDto.getVideoUrl())
                .setValue(cameraDto.getValue())
                .setTtl(cameraDto.getTtl());
    }
}
