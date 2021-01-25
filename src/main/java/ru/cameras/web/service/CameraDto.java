package ru.cameras.web.service;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter
@Accessors(chain = true)
public class CameraDto {
    private Long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private Long ttl;
}
