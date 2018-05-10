package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.AddStocktakingForm;
import ru.kpfu.itis.model.Stocktaking;


public class AddStocktakingFormTransform {

    public static Stocktaking transform(AddStocktakingForm form) {
        Stocktaking stocktaking = new Stocktaking();
        stocktaking.setProduct(form.getProduct());
        stocktaking.setWarehouse(form.getWarehouse());
        stocktaking.setQuantity_product(form.getQuantity());
        return stocktaking;
    }

}
