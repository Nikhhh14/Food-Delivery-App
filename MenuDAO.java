package com.food.dao;

import java.util.List;
import com.food.model.Menu;

public interface MenuDAO {

    List<Menu> fetchByRestaurant(int restaurantId);

    Menu fetchById(int menuId);

    int insert(Menu menu); // ⭐ NEW METHOD
}
