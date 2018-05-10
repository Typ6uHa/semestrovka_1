package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.AddQuantityForm;
import ru.kpfu.itis.form.AddWarehouseForm;
import ru.kpfu.itis.form.collateralForms.QuantityForm;
import ru.kpfu.itis.model.Stocktaking;
import ru.kpfu.itis.model.Warehouse;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StocktakingService;
import ru.kpfu.itis.service.WarehouseService;
import ru.kpfu.itis.util.validators.ValidatorAddProductForm;
import ru.kpfu.itis.util.validators.ValidatorAddWarehouseForm;

@Controller
@RequestMapping(value = "admin/warehouse")
public class WarehouseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private StocktakingService stocktakingService;

    private ValidatorAddWarehouseForm validatorAddWarehouseForm = new ValidatorAddWarehouseForm();

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String warehouses(Model model) {
        model.addAttribute("warehouses", warehouseService.getAll());
        return "list_warehouses";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Long id,
                         Model model) {
        for (Stocktaking stocktaking: stocktakingService.getAllByWarehouseId(id)) {
            stocktakingService.delete(stocktaking.getId());
        }
        warehouseService.delete(id);
        model.addAttribute("id", id);
        return "redirect:/admin/warehouse/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String product_page(@PathVariable("id") long id, Model model) {
        model.addAttribute("warehouse", warehouseService.getById(id));
        model.addAttribute("stocktaking", stocktakingService.getAllByWarehouseId(id));
        model.addAttribute("quantity", stocktakingService.getAllQuantityOnWarehouse(stocktakingService.getAllByWarehouseId(id)));
        model.addAttribute("products", productService.getByIdIn(stocktakingService.getIdsProducts(stocktakingService.getAllByWarehouseId(id))));
        model.addAttribute("modify_quantity", new QuantityForm());
        return "warehouse_page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add_warehouse(Model model){
        model.addAttribute("warehouse_form", new AddWarehouseForm());
        return "add_warehouse";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add_warehouse(@ModelAttribute("warehouse_form") AddWarehouseForm form, BindingResult result){
        validatorAddWarehouseForm.validate(form, result);
        if (result.hasErrors()) {
            return "add_warehouse";
        } else {
            warehouseService.save(form);
            return "redirect:/admin/warehouse/all";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String add_warehouse(@ModelAttribute("modify_quantity") QuantityForm form, @PathVariable("id") String id){
        Stocktaking stocktaking = stocktakingService.getById(form.getStocktaking());
        AddQuantityForm addQuantityForm = new AddQuantityForm();
        addQuantityForm.setStocktaking(stocktaking);
        addQuantityForm.setQuantity(form.getQuantity());
        stocktakingService.putQuantity(addQuantityForm);
        return "redirect:/admin/warehouse/" + id;
    }
}
