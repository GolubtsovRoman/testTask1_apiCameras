package ru.cameras.web.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter @Setter
@RequiredArgsConstructor
public class CameraSrcDto {
    private Long id;
    private URL sourceDataUrl;
    private URL tokenDataUrl;
}
