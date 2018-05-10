package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.form.AddProductForm;
import ru.kpfu.itis.form.ProductForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StocktakingService;
import ru.kpfu.itis.service.WarehouseService;
import ru.kpfu.itis.util.validators.ValidatorAddProductForm;

@Controller
@RequestMapping(value = "admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private StocktakingService stocktakingService;

    private ValidatorAddProductForm validatorAddProductForm = new ValidatorAddProductForm();

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String catalog(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("modify_product", new ProductForm());
        return "list_product";
    }

    @RequestMapping(value = "/{id}")
    public String product_page(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add_product_page(Model model){
        model.addAttribute("product_form", new AddProductForm());
        return "add_product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add_product(@ModelAttribute("product_form") AddProductForm form, BindingResult result){
        validatorAddProductForm.validate(form, result);

        if (result.hasErrors()) {
            return "add_product";
        } else {
            productService.save(form);
            return "redirect:/admin/product/all";
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String mod(@ModelAttribute("modify_product") ProductForm form){
        Product product = productService.getById(form.getId());
        form.setProduct(product);
        productService.modify(form);
        return "redirect:/admin/product/all";
    }
}
