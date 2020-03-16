package com.hb0730.cloud.admin.server.role.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RoleEvent extends ApplicationEvent {
    @Getter
    @Setter
    private ApplicationContext applicationContext;

    public RoleEvent(Object source, ApplicationContext applicationContext) {
        super(source);
        this.applicationContext = applicationContext;
    }
}
