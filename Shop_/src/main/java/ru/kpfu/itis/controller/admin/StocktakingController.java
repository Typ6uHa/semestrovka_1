package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.form.AddQuantityForm;
import ru.kpfu.itis.form.AddStocktakingForm;
import ru.kpfu.itis.form.collateralForms.QuantityForm;
import ru.kpfu.itis.form.collateralForms.StocktakingForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Stocktaking;
import ru.kpfu.itis.model.Warehouse;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StocktakingService;
import ru.kpfu.itis.service.WarehouseService;

@Controller
@RequestMapping(value = "admin/stocktaking")
public class StocktakingController {

    @Autowired
    private StocktakingService stocktakingService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add_stocktaking(Model model){
        model.addAttribute("stocktaking_form", new StocktakingForm());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("warehouses", warehouseService.getAll());
        return "add_stocktaking";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add_stocktaking(@ModelAttribute("stocktaking_form") StocktakingForm form){
        Product product = productService.getById(form.getProduct());
        Warehouse warehouse = warehouseService.getById(form.getWarehouse());
        AddStocktakingForm addStocktakingForm = new AddStocktakingForm();
        addStocktakingForm.setProduct(product);
        addStocktakingForm.setWarehouse(warehouse);
        addStocktakingForm.setQuantity(form.getQuantity());
        stocktakingService.save(addStocktakingForm);
        return "redirect:/admin/warehouse/all";
    }
}
