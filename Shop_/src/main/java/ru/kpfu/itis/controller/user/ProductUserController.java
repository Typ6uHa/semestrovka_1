package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.exception.UserIsNotActivate;
import ru.kpfu.itis.form.collateralForms.OrderForm;
import ru.kpfu.itis.form.collateralForms.ProductInOrderForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.Token;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.TokenRepository;
import ru.kpfu.itis.service.*;


@Controller
@RequestMapping(value = "product")
public class ProductUserController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StocktakingService stocktakingService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String catalog(@RequestParam(value = "name", required = false) String productname, Model model) {

        if (productname != null) {
            model.addAttribute("product_form", new OrderForm());
            model.addAttribute("stocktaking", stocktakingService.getAllByProductName(productname));
            return "catalog";
        }
        model.addAttribute("product_form", new OrderForm());
        model.addAttribute("stocktaking", stocktakingService.getAll());
        model.addAttribute("error", "");
        return "catalog";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String catalog(@ModelAttribute("product_form") ProductInOrderForm form, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.is_confirm()){
            model.addAttribute("error", "Пользователь не активен");
            model.addAttribute("stocktaking", stocktakingService.getAll());
            return "catalog";
        }
        Product product = productService.getById(form.getProduct_id());
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        orderService.save(user, productInOrder);
        return "redirect:/basket/";
    }

    @RequestMapping(value = "/{id}")
    public String product_page(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_page";
    }
}
