package com.example.guitar.dao;

import com.example.guitar.entity.Guitar;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class GuitarDao {
    private Connection conn = SqliteUtil.getConnection();
    private PreparedStatement ps;
    private ResultSet rs;

    //添加
    public void addGuitar(Guitar guitar) throws SQLException {
        String sql = "INSERT INTO guitar(name,price,builder,model,type,back_wood,top_wood) VALUES(?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, guitar.getName());
        ps.setDouble(2, guitar.getPrice());
        ps.setString(3, guitar.getBuilder());
        ps.setString(4, guitar.getModel());
        ps.setString(5, guitar.getType());
        ps.setString(6, guitar.getBackWood());
        ps.setString(7, guitar.getTopWood());
        ps.executeUpdate();
       // SqliteUtil.close(rs, ps, conn);
    }
    //查找所有

    public List<Guitar> findAllGuitar() throws SQLException {
        String sql = "select * from guitar";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return getGuitarList(rs);

    }
    //通过名称查找
    public List<Guitar> findByName(String name) throws SQLException {
        String sql = "select * from guitar where name like ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        return getGuitarList(rs);
    }

    //删除
    public void deleteById(Integer id) throws SQLException {
        String sql = "delete from guitar where id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    private List<Guitar> getGuitarList(ResultSet rs) throws SQLException {
        ArrayList<Guitar> list = new ArrayList<>();
        while (rs.next()){
            Guitar guitar = new Guitar();
            guitar.setId(rs.getInt(1));
            guitar.setName(rs.getString(2));
            guitar.setPrice(rs.getDouble(3));
            guitar.setBuilder(rs.getString(4));
            guitar.setModel(rs.getString(5));
            guitar.setType(rs.getString(6));
            guitar.setBackWood(rs.getString(7));
            guitar.setTopWood(rs.getString(8));
            list.add(guitar);
        }
        return list;
    }
}
