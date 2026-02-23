package com.food.dao;

import java.util.Map;

import com.food.model.CartItem;
import com.food.model.Order;

public interface OrderDAO {

	int insert(Order order, Map<Integer, CartItem> cartItems);

}
