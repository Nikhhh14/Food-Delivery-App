package com.food.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String FETCH_ALL =
            "SELECT * FROM restaurants WHERE is_active=TRUE";

    private static final String INSERT =
            "INSERT INTO restaurants(name,cuisine,rating,image_path) VALUES(?,?,?,?)";

    @Override
    public List<Restaurant> fetchAll() {

        List<Restaurant> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(FETCH_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setCuisine(rs.getString("cuisine"));
                r.setRating(rs.getDouble("rating"));
                r.setImagePath(rs.getString("image_path"));
                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int insert(Restaurant r) {

        int status = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT);

            ps.setString(1, r.getName());
            ps.setString(2, r.getCuisine());
            ps.setDouble(3, r.getRating());
            ps.setString(4, r.getImagePath());

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
