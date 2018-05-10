package ru.kpfu.itis.form.collateralForms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StocktakingForm {

    private long product;
    private long warehouse;
    private int quantity;

}
