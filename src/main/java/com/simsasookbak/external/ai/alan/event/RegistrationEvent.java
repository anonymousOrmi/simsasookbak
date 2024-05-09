package com.simsasookbak.external.ai.alan.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegistrationEvent extends ApplicationEvent {

    private final Long memberId;

    public RegistrationEvent(Object source, Long memberId) {
        super(source);
        this.memberId = memberId;
    }

}
