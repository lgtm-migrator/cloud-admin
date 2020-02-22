package com.hb0730.cloud.admin.server.router.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 发送时间
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SendMessageEvent extends ApplicationEvent {
    @Getter
    @Setter
    private String message;

    public SendMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }


}
