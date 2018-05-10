package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.OrderModifyForm;
import ru.kpfu.itis.form.collateralForms.OrderForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderType;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;

@Controller
@RequestMapping(value = "basket")
public class BasketController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductInOrderService productInOrderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String basket(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order basket = orderService.getByUserAndTypeOrder(user, OrderType.DIALED);
        model.addAttribute("order", basket);
        model.addAttribute("productsInOrder", productInOrderService.getAllByOrder(basket));
        model.addAttribute("order_form", new OrderForm());
        return "basket";
    }

    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String basket(@ModelAttribute("order_form") OrderForm form) {
        Order order = orderService.getById(form.getOrder_id());
        OrderModifyForm orderModifyForm = new OrderModifyForm();
        orderModifyForm.setOrder(order);
        orderModifyForm.setTypeOrder(OrderType.FORMAD);
        orderService.modify(orderModifyForm);
        return "redirect:/orders/";
    }

    @Transactional
    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public @ResponseBody int minusCount(@RequestParam int id, @RequestParam int count) {
        return orderService.getResponseAboutMinusCount(id, count);
    }

    @Transactional
    @RequestMapping(value = "/plus", method = RequestMethod.GET)
    public @ResponseBody int plusCount(@RequestParam int id, @RequestParam int count) {
        return orderService.getResponseAboutPlusCount(id, count);
    }
}
