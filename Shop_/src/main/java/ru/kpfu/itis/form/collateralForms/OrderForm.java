package ru.kpfu.itis.form.collateralForms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

    private long order_id;
    private String typeOrder;

}
