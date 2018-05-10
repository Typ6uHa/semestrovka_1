package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.AddWarehouseForm;
import ru.kpfu.itis.model.Warehouse;

public class AddWarehouseFormTransform {

    public static Warehouse transform(AddWarehouseForm form) {
        Warehouse warehouse = new Warehouse();
        warehouse.setCity(form.getCity());
        warehouse.setStreet(form.getStreet());
        return warehouse;
    }
}
