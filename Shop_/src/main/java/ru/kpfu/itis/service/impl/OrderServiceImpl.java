package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.OrderModifyForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.Stocktaking;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderType;
import ru.kpfu.itis.repository.OrderRepository;
import ru.kpfu.itis.repository.ProductInOrderRepository;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.StocktakingService;
import ru.kpfu.itis.util.transform.OrderModifyFormTransform;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductInOrderRepository productInOrderRepository;
    @Autowired
    private StocktakingService stocktakingService;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByUserId(long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public Order getById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getByUserAndTypeOrder(User user, OrderType orderType) {
        return orderRepository.findByUserAndTypeOrder(user, orderType);
    }

    @Override
    public List<Order> getByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }


    @Override
    public void save(User user, ProductInOrder productInOrder) {
        if (getByUserAndTypeOrder(user, OrderType.DIALED) != null) {
            Order order = getByUserAndTypeOrder(user, OrderType.DIALED);
            List<ProductInOrder> productInOrders = productInOrderRepository.findAllByOrder(order);
            boolean flag = false;
            for (ProductInOrder productInOrder1 : productInOrders) {
                if (productInOrder1.getProduct().getId() == productInOrder.getProduct().getId()) {
                    productInOrder1.setQuantity(productInOrder1.getQuantity() + 1);
                    Stocktaking stocktaking = stocktakingService.getByProduct(productInOrder.getProduct());
                    stocktaking.setQuantity_product(stocktaking.getQuantity_product() - 1);
                    stocktakingService.save(stocktaking);
                    flag = true;
                }
            }
            if (!flag) {
                productInOrder.setQuantity(1);
                productInOrders.add(productInOrder);
                Stocktaking stocktaking = stocktakingService.getByProduct(productInOrder.getProduct());
                stocktaking.setQuantity_product(stocktaking.getQuantity_product() - 1);
                stocktakingService.save(stocktaking);
            }
            order.setProductInOrders(productInOrders);
            orderRepository.save(order);
            for (ProductInOrder productInOrder1 : productInOrders) {
                productInOrder1.setOrder(orderRepository.findById(order.getId()));
                productInOrderRepository.save(productInOrder1);
            }
        } else {
            List<ProductInOrder> productInOrders_e = new LinkedList<>();
            Order order = new Order();
            order.setTypeOrder(OrderType.DIALED);
            order.setUser(user);
            productInOrder.setQuantity(1);
            productInOrders_e.add(productInOrder);
            order.setProductInOrders(productInOrders_e);
            orderRepository.save(order);
            productInOrder.setOrder(order);
            productInOrderRepository.save(productInOrder);
            Stocktaking stocktaking = stocktakingService.getByProduct(productInOrder.getProduct());
            stocktaking.setQuantity_product(stocktaking.getQuantity_product() - 1);
            stocktakingService.save(stocktaking);
        }


    }


    @Override
    public void modify(OrderModifyForm form) {
        Order order = OrderModifyFormTransform.transform(form);
        orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }

    @Override
    public void delete(List<Order> orders) {
        orderRepository.deleteInBatch(orders);
    }

    public int getResponseAboutMinusCount(long id, int count) {
        int availableCount = stocktakingService.getByProductId(id).getQuantity_product();
        int neededCount = count - 1;
        return setResponse(id, neededCount, availableCount, "minus", count);
    }

    public int getResponseAboutPlusCount(long id, int count) {
        int availableCount = stocktakingService.getByProductId(id).getQuantity_product();
        int neededCount = count + 1;
        return setResponse(id, neededCount, availableCount, "plus", count);
    }

    @Override
    public List<Order> getForList(long id, List<OrderType> orderTypes) {
        return orderRepository.findByUserIdAndTypeOrderIn(id, orderTypes);
    }

    private int setResponse(long id, int neededCount, int availableCount, String name_action, int count) {
        if (neededCount > availableCount + count)
            return -1;
        else if (neededCount == 0)
            return -2;
        else if (neededCount <= availableCount + neededCount) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Stocktaking stocktaking = stocktakingService.getByProductId(id);
            Order order = getByUserAndTypeOrder(user, OrderType.DIALED);
            ProductInOrder productInOrder = productInOrderRepository.findOneByOrderIdAndProductId(order.getId(), id);
            if (Objects.equals(name_action, "plus")) {
                productInOrder.setQuantity(productInOrder.getQuantity() + 1);
                productInOrderRepository.save(productInOrder);
                stocktaking.setQuantity_product(stocktaking.getQuantity_product() - 1);
                stocktakingService.save(stocktaking);
                System.out.println("Кнопка минус нажата запись: stocktaking_id - " + stocktaking.getId() + ", order_id - " + order.getId() + ", productInOrder_id - " + productInOrder.getId());
                return neededCount;
            }
            if (Objects.equals(name_action, "minus")) {
                productInOrder.setQuantity(productInOrder.getQuantity() - 1);
                productInOrderRepository.save(productInOrder);
                stocktaking.setQuantity_product(stocktaking.getQuantity_product() + 1);
                stocktakingService.save(stocktaking);
                System.out.println("Кнопка минус нажата запись: stocktaking_id - " + stocktaking.getId() + ", order_id - " + order.getId() + ", productInOrder_id - " + productInOrder.getId());
                return neededCount;
            }
        }
        return 0;
    }

}
