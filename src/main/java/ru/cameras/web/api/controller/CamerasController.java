package ru.cameras.web.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cameras.web.api.json.CameraRes;
import ru.cameras.web.api.json.UrlReq;
import ru.cameras.web.service.model.CameraDto;
import ru.cameras.web.service.CamerasHandlerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CamerasController {
    private final CamerasHandlerService camerasHandlerService;
    private static final String BAD_DATA = "bad data";
    
    @PostMapping("/get-info-by-url")
    public @ResponseBody ResponseEntity<List<CameraRes>> getInfo(@RequestBody @Valid UrlReq request,
                                                                 HttpServletRequest httpServletRequest) {
        List<CameraDto> cameraDtoList = camerasHandlerService.getListCameras(request.getUrl());
        if (cameraDtoList == null) {
            return status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        
        List<CameraRes> cameraResList = cameraDtoList.stream()
                .map(this::convertToCameraRes)
                .sorted()
                .collect(Collectors.toList());
        
        return ok(cameraResList);
    }
    
    private CameraRes convertToCameraRes(CameraDto cameraDto) {
        return new CameraRes()
                .setId(cameraDto.getId())
                .setUrlType(cameraDto.getUrlType() != null ? cameraDto.getUrlType().toString() : BAD_DATA)
                .setVideoUrl(cameraDto.getVideoUrl() != null ? cameraDto.getVideoUrl() : BAD_DATA)
                .setValue(cameraDto.getValue() != null ? cameraDto.getValue().toString() : BAD_DATA)
                .setTtl(cameraDto.getTtl() != null ? cameraDto.getTtl().toString() : BAD_DATA);
    }
}
