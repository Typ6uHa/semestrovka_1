package ru.kpfu.itis.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.model.Stocktaking;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddQuantityForm {

    private Stocktaking stocktaking;
    private int quantity;

}
