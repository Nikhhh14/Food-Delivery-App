package com.food.dao;

import java.util.List;
import com.food.model.Restaurant;

public interface RestaurantDAO {

    List<Restaurant> fetchAll();

    int insert(Restaurant restaurant); // ⭐ NEW METHOD
}
