package ru.cameras.web.api.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UrlReq {
    @NotNull
    private String url;
}
