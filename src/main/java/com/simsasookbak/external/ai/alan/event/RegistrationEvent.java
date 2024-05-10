package com.simsasookbak.external.ai.alan.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegistrationEvent extends ApplicationEvent {

    private final Long AccommodationId;

    public RegistrationEvent(Object source, Long AccommodationId) {
        super(source);
        this.AccommodationId = AccommodationId;
    }

}
