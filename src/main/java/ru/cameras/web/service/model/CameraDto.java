package ru.cameras.web.service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter @Getter
@Accessors(chain = true)
@EqualsAndHashCode
public class CameraDto {
    private Long id;
    private UrlType urlType;
    private String videoUrl;
    private UUID value;
    private Integer ttl;
}
