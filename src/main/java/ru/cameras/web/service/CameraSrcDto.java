package ru.cameras.web.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CameraSrcDto {
    private Long id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}