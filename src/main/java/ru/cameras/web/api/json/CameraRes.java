package ru.cameras.web.api.json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter
@Accessors(chain = true)
public class CameraRes {
    private Long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private Long ttl;
}
