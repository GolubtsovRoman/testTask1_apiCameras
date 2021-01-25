package ru.cameras.web.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@RequiredArgsConstructor
public class SourceDataUrl {
    private UrlType urlType;
    private String videoUrl;
}
