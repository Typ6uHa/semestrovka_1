package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.AddQuantityForm;
import ru.kpfu.itis.model.Stocktaking;

public class AddQuantityFormTransform {

    public static Stocktaking transform(AddQuantityForm form) {
        Stocktaking stocktaking = new Stocktaking();
        Stocktaking stocktaking_form = form.getStocktaking();
        stocktaking.setId(stocktaking_form.getId());
        stocktaking.setProduct(stocktaking_form.getProduct());
        stocktaking.setWarehouse(stocktaking_form.getWarehouse());
        stocktaking.setQuantity_product(form.getQuantity());
        return stocktaking;
    }
}
