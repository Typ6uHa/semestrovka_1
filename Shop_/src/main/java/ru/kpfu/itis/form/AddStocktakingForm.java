package ru.kpfu.itis.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.method.P;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Warehouse;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.WarehouseService;
import ru.kpfu.itis.service.impl.ProductServiceImpl;
import ru.kpfu.itis.service.impl.WarehouseServiceImpl;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStocktakingForm {

    private Product product;
    private Warehouse warehouse;
    private int quantity;

}
