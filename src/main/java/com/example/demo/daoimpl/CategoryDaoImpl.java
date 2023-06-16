package com.example.demo.daoimpl;

import com.example.demo.dao.CategoryDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> listCategory(){
        String sql = "{call listCategory()}";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> categoryById(int id){
        String sql = "{call listCategory(?)}";
        return jdbcTemplate.queryForList(sql, id);
    }

    @Override
    public int addCategory(String name){
        String sql = "{call demo.addCategory(?)}";
        int affRow= jdbcTemplate.update(sql, name);
        if (affRow==0)
            System.out.println("Thêm không thành công");
        return affRow;
    }

    @Override
    public int editCategory(int id, String name){
        String sql = "{call demo.editCategory(?, ?)}";
        int affRow= jdbcTemplate.update(sql, id, name);
        if (affRow==0)
            System.out.println("Cập nhật không thành công");
        return affRow;
    }

    @Override
    public int deleteCategory(int id){
        String sql = "{call demo.deleteCategory(?)}";
        int affRow= jdbcTemplate.update(sql, id);
        if (affRow==0)
            System.out.println("Xoá không thành công");
        return affRow;
    }
}
