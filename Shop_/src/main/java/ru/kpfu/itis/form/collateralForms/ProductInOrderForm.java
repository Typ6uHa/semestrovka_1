package ru.kpfu.itis.form.collateralForms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInOrderForm {

    private long product_id;
    private int quantity;

}
