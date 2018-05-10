package ru.kpfu.itis.form.collateralForms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityForm {

    private long stocktaking;
    private int quantity;

}
