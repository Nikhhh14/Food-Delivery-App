package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    private static final String FETCH_BY_RESTAURANT =
            "SELECT * FROM menu WHERE restaurant_id=? AND is_available=TRUE";

    private static final String FETCH_BY_ID =
            "SELECT * FROM menu WHERE id=?";

    // ⭐ NEW INSERT QUERY (ADMIN PANEL)
    private static final String INSERT =
            "INSERT INTO menu(restaurant_id,name,description,price,image_path) VALUES(?,?,?,?,?)";

    @Override
    public List<Menu> fetchByRestaurant(int restaurantId) {

        List<Menu> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(FETCH_BY_RESTAURANT);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu m = new Menu();

                m.setId(rs.getInt("id"));
                m.setRestaurantId(rs.getInt("restaurant_id"));
                m.setName(rs.getString("name"));
                m.setDescription(rs.getString("description"));
                m.setPrice(rs.getDouble("price"));
                m.setImagePath(rs.getString("image_path"));

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Menu fetchById(int menuId) {

        Menu m = null;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(FETCH_BY_ID);

            ps.setInt(1, menuId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                m = new Menu();

                m.setId(rs.getInt("id"));
                m.setRestaurantId(rs.getInt("restaurant_id"));
                m.setName(rs.getString("name"));
                m.setDescription(rs.getString("description"));
                m.setPrice(rs.getDouble("price"));
                m.setImagePath(rs.getString("image_path"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    // ⭐ NEW METHOD FOR ADMIN PANEL
    @Override
    public int insert(Menu m) {

        int status = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT);

            ps.setInt(1, m.getRestaurantId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getDescription());
            ps.setDouble(4, m.getPrice());
            ps.setString(5, m.getImagePath());

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
