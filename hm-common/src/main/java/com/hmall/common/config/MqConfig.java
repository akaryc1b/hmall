package com.hmall.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(){
            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                Long userId=message.getMessageProperties().getHeader("user_info");
                if(ObjectUtil.isNotNull(userId)){
                    UserContext.setUser(userId);
                }
                return super.fromMessage(message);
            }
        };
        jackson2JsonMessageConverter.setCreateMessageIds(true);
        return jackson2JsonMessageConverter;
    }
}
