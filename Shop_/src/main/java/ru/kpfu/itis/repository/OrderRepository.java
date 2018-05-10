package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderType;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();

    List<Order> findAllByUserId(long id);

    Order findById(long id);

    Order findByUserAndTypeOrder(User user, OrderType orderType);

    List<Order> findByUser(User user);

    List<Order> findByUserIdAndTypeOrderIn(long id, List<OrderType> orderTypes);
}
