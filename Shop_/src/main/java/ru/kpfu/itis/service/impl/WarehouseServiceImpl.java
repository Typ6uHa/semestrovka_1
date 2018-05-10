package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.AddWarehouseForm;
import ru.kpfu.itis.model.Warehouse;
import ru.kpfu.itis.repository.WarehouseRepository;
import ru.kpfu.itis.service.WarehouseService;
import ru.kpfu.itis.util.transform.AddWarehouseFormTransform;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getById(long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public void save(AddWarehouseForm form) {
        Warehouse warehouse = AddWarehouseFormTransform.transform(form);
        warehouseRepository.save(warehouse);
    }

    @Override
    public void delete(long id) {
        warehouseRepository.delete(id);
    }
}
