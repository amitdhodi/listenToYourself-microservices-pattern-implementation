package com.example.listenToYourself.event.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedCommand extends BaseCommand{
    private String orderId;
    private int version;
    private String customerId;
    private String fulfilmentGroupId;
    private String deliveryGroupId;

    @Override
    public void invoke(CommandListener target) {
        target.process(this);
    }
}
