package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.form.collateralForms.OrderForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderType;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "orders")
public class OrderUserController {

    @Autowired
    private ProductInOrderService productInOrderService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String all_orders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderType> orderTypes = new LinkedList<>();
        orderTypes.add(OrderType.COMPLETED);
        orderTypes.add(OrderType.SEARS);
        orderTypes.add(OrderType.DIALED);
        orderTypes.add(OrderType.FORMAD);
        List<Order> orders = orderService.getForList(user.getId(), orderTypes);
        model.addAttribute("order_form", new OrderForm());
        model.addAttribute("orders", orders);
        model.addAttribute("productInOrders", productInOrderService.getAllByOrderIn(orders));
        return "all_orders";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id") Long id, Model model) {
        Order order = orderService.getById(id);
        order.setTypeOrder(OrderType.DELETE);
        orderService.save(order);
        model.addAttribute("id", id);
        return "redirect:/orders/";
    }
}
