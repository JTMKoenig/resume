/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import static com.sg.fidgetblog.dao.PostDaoImpl.formatter;
import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jono
 */




public class StaticPageDaoImpl implements StaticPageDao {
    
        /////FOR STATIC PAGES
    private static final String SQL_INSERT_STATICPAGE
            = "insert into StaticPage (staticPageIndex, staticPageTitle, staticPageBody, isArchived) values(?,?,?,?)";
    
    private static final String SQL_DELETE_STATICPAGE
            = "update StaticPage set isArchived = 1 where StaticPageId = ?";
    
    private static final String SQL_RESTORE_STATICPAGE
            = "update StaticPage set isArchived = 0 where StaticPageId = ?";

    private static final String SQL_UPDATE_STATICPAGE_ORDER
            = "update StaticPage set StaticPageIndex = ? where StaticPageId = ?";

    private static final String SQL_SELECT_STATICPAGE
            = "select * from StaticPage where StaticPageId = ?";
    
    private static final String SQL_SELECT_ALL_STATICPAGES
            = "select * from StaticPage order by StaticPageIndex";
    
    private static final String SQL_SELECT_ALL_STATICPAGES_ORDERBY_ID
            = "select * from StaticPage order by StaticPageId";
    
    private JdbcTemplate jdbcTemplate;

    public StaticPageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    

    @Override
    public void createStaticPage(StaticPage staticPage) {
        jdbcTemplate.update(SQL_INSERT_STATICPAGE, staticPage.getStaticPageIndex(), staticPage.getStaticPageTitle(), staticPage.getStaticPageBody(), staticPage.isIsArchived());
        int staticPageId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        staticPage.setStaticPageId(staticPageId);
        
        
    }

    @Override
    public StaticPage readStaticPageById(int staticPageId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGE,new StaticPageMapper(), staticPageId);
    }
    
    @Override
    public List<StaticPage> readAllStaticPage(){
        return jdbcTemplate.query(SQL_SELECT_ALL_STATICPAGES, new StaticPageMapper());
    }

    @Override
    public void updateStaticPage(StaticPage staticPage) {
        jdbcTemplate.update(SQL_UPDATE_STATICPAGE_ORDER, staticPage.getStaticPageIndex(), staticPage.getStaticPageId());
    }
    
    
    @Override
    public void updateStaticPageOrder(String[] staticPageOrderList){
        throw new UnsupportedOperationException("TODO");
        
    }

    @Override
    public void deleteStaticPageById(int staticPageId) {
        jdbcTemplate.update(SQL_DELETE_STATICPAGE, staticPageId);
    }
    
    
    public void restoreStaticPageById(int staticPageId) {
        jdbcTemplate.update(SQL_RESTORE_STATICPAGE, staticPageId);
    }
    
    
    private static final class StaticPageMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
            StaticPage staticPage = new StaticPage();
            
            staticPage.setStaticPageId((rs.getInt("StaticPageId")));
            staticPage.setStaticPageIndex(rs.getInt("StaticPageIndex"));
            staticPage.setStaticPageTitle(rs.getString("StaticPageTitle"));
            staticPage.setStaticPageBody(rs.getString("StaticPageBody"));
            staticPage.setIsArchived(rs.getBoolean("isArchived"));
            
 
            return staticPage;

        }
    }

}
