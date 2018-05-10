package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StocktakingService;

import java.util.List;

@RestController
public class Desktop {

    @Autowired
    private StocktakingService stocktakingService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/stocks", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getItems(){
        return productService.getAll();
    }

    @RequestMapping(value = "/api/update",method = RequestMethod.POST)
    String modify( @RequestBody List<Product> products) {
        try {
            for (Product product: products) {
                productService.save(product);
            }
        }
        catch (Exception e){
            return "Error" + e.getMessage();
        }

        return "OK";
    }
}
