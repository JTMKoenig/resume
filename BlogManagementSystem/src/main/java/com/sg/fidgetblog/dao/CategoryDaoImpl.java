/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jono
 */
public class CategoryDaoImpl implements CategoryDao {

    private static final String SQL_INSERT_CATEGORY
            = "insert ignore Category (CategoryId) values (?)";

    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where CategoryId = ?";

    private static final String SQL_SELECT_CATEGORY
            = "select * from Category where CategoryId = ?";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Category";
    private static final String SQL_SELECT_ALL_CATEGORIES_BY_POST_ID = "SELECT Category.* FROM Category"
            + " inner join Category_Post on Category_Post.CategoryId=Category.CategoryId"
            + " inner join Post on Post.PostId=Category_Post.PostId"
            + " where Category_Post.PostId = ? order by Category.CategoryId;";

    private JdbcTemplate jdbcTemplate;

    @Inject
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getCategoryId());

    }

    @Override
    public Category readCategoryById(String categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY,
                    new CategoryMapper(),
                    categoryId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> readAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public List<Category> readAllCategoriesByPostId(int postId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES_BY_POST_ID, new CategoryMapper(), postId);
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getString("CategoryId"));
            return category;
        }
    }

}
