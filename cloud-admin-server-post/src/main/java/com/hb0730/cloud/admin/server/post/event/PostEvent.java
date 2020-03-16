package com.hb0730.cloud.admin.server.post.event;

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
public class PostEvent extends ApplicationEvent {
    @Getter
    @Setter
    private ApplicationContext applicationContext;

    public PostEvent(Object source, ApplicationContext applicationContext) {
        super(source);
        this.applicationContext = applicationContext;
    }
}
