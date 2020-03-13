package com.hb0730.cloud.admin.server.router.event;

import com.hb0730.cloud.admin.common.web.response.ResultJson;
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
public class SendResultJsonEvent extends ApplicationEvent {
    @Getter
    @Setter
    private ResultJson resultJson;
    @Getter
    @Setter
    private ApplicationContext applicationContext;

    public SendResultJsonEvent(Object source, ResultJson resultJson, ApplicationContext applicationContext) {
        super(source);
        this.resultJson = resultJson;
        this.applicationContext = applicationContext;
    }
}
