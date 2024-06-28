package com.hmall.cart.listener;

import com.hmall.api.client.CartClient;
import com.hmall.cart.service.ICartService;
import com.hmall.cart.service.Impl.CartServiceImpl;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class deleteCarStausListener {

    private final ICartService cartService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "cart.clear.queue", durable = "true"),
            exchange = @Exchange(name="trade.topic",type = ExchangeTypes.TOPIC),
            key = "order.create"
    ))
    public void listenDeleteCarSuccess(Message message, Set<Long> itemIds){
//        Long userId=message.getMessageProperties().getHeader("user_info");
//        UserContext.setUser(userId);
        cartService.removeByItemIds(itemIds);
    }
}
