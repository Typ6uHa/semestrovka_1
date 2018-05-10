package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.AddWarehouseForm;
import ru.kpfu.itis.model.Warehouse;

import java.util.List;

@Service
public interface WarehouseService {

    List<Warehouse> getAll();

    Warehouse getById(long id);

    void save(AddWarehouseForm form);

    void delete(long id);
}
