package com.example.demo.dao;

import java.util.*;


public interface CategoryDao {    List<Map<String, Object>> listCategory();


    List<Map<String, Object>> categoryById(int id);

    int addCategory(String name);
    int editCategory(int id, String name);
    int deleteCategory(int id);

}
