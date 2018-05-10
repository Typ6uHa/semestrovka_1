package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.OrderModifyForm;
import ru.kpfu.itis.model.Order;

public class OrderModifyFormTransform {

    public static Order transform(OrderModifyForm form){
        Order order = form.getOrder();
        order.setTypeOrder(form.getTypeOrder());
        return order;
    }
}
