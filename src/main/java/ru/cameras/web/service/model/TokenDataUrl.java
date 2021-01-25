package ru.cameras.web.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
@RequiredArgsConstructor
public class TokenDataUrl {
    private UUID value;
    private Integer ttl;
}
