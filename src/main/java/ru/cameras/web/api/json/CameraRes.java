package ru.cameras.web.api.json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter
@Accessors(chain = true)
public class CameraRes implements Comparable<CameraRes> {
    private Long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private String ttl;
    
    @Override
    public int compareTo(CameraRes cr) {
        if (this.id.equals(cr.id)) {
            return 0;
        }
        return this.id - cr.id > 0 ? 1 : -1;
    }
}
