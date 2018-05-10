package ru.kpfu.itis.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {

    private long id;
    private String name;
    private String description;
    private int price;
    private String type;
    private Product product;
}
