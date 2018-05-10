package ru.kpfu.itis.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModifyForm {

    private Order order;
    private OrderType typeOrder;

}
