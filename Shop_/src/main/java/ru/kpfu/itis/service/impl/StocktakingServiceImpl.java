package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.AddQuantityForm;
import ru.kpfu.itis.form.AddStocktakingForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Stocktaking;
import ru.kpfu.itis.repository.StocktakingRepository;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StocktakingService;
import ru.kpfu.itis.util.transform.AddQuantityFormTransform;
import ru.kpfu.itis.util.transform.AddStocktakingFormTransform;

import java.util.LinkedList;
import java.util.List;

@Service
public class StocktakingServiceImpl implements StocktakingService {

    @Autowired
    private StocktakingRepository stocktakingRepository;
    private StocktakingService stocktakingService;
    private ProductService productService = new ProductServiceImpl();

    @Override
    public List<Stocktaking> getAll() {
        return stocktakingRepository.findAll();
    }

    @Override
    public Stocktaking getById(long id) {
        return stocktakingRepository.findById(id);
    }

    @Override
    public Stocktaking getByProduct(Product product) {
        return stocktakingRepository.findByProduct(product);
    }

    @Override
    public Stocktaking getByProductId(long id) {
        return stocktakingRepository.findByProductId(id);
    }

    @Override
    public List<Stocktaking> getAllByWarehouseId(long id) {
        return stocktakingRepository.findAllByWarehouseId(id);
    }

    @Override
    public List<Stocktaking> getAllByProductId(long id) {
        return stocktakingRepository.findAllByProductId(id);
    }

    @Override
    public List<Stocktaking> getAllByProductName(String name) {
        return stocktakingRepository.findAllByProductName(name);
    }

    @Override
    public int getAllQuantityOnWarehouse(List<Stocktaking> stocktaking) {
        int quantity = 0;
        for (Stocktaking stocktaking_example : stocktaking) {
            quantity += stocktaking_example.getQuantity_product();
        }
        return quantity;
    }

    @Override
    public List<Long> getIdsProducts(List<Stocktaking> stocktaking) {
        List<Long> ids = new LinkedList<Long>();
        for (Stocktaking stocktaking_example : stocktaking) {
            ids.add(stocktaking_example.getId());
        }
        return ids;
    }

    @Override
    public void save(AddStocktakingForm form) {
        Stocktaking stocktaking = AddStocktakingFormTransform.transform(form);
        stocktakingRepository.save(stocktaking);
    }

    @Override
    public void save(Stocktaking stocktaking) {
        stocktakingRepository.save(stocktaking);
    }

    @Override
    public void putQuantity(AddQuantityForm form) {
        Stocktaking stocktaking = AddQuantityFormTransform.transform(form);
        stocktakingRepository.save(stocktaking);
    }

    @Override
    public void delete(long id) {
        stocktakingRepository.delete(id);
    }


}
