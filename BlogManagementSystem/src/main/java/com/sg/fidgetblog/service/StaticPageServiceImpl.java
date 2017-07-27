/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.StaticPageDao;
import com.sg.fidgetblog.dto.StaticPage;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class StaticPageServiceImpl implements StaticPageService {

    StaticPageDao staticPageDao;

    @Inject
    public StaticPageServiceImpl(StaticPageDao staticPageDao) {
        this.staticPageDao = staticPageDao;
    }

    @Override
    public void createStaticPage(StaticPage staticPage) {
        staticPageDao.createStaticPage(staticPage);
    }
    
    @Override
    public StaticPage readStaticPageById(int staticPageId) {
        return staticPageDao.readStaticPageById(staticPageId);
    }
    
    @Override
    public void updateStaticPage(StaticPage staticPage) {
        staticPageDao.updateStaticPage(staticPage);
    }
    
    
    @Override
    public void updateStaticPageOrder(String[] staticPageOrderList){
        
        staticPageDao.updateStaticPageOrder(staticPageOrderList);
    }
    
    @Override
    public void deleteStaticPageById(int staticPageId) {
        staticPageDao.deleteStaticPageById(staticPageId);
    }
    
    @Override
    public void restoreStaticPageById(int staticPageId){
        staticPageDao.restoreStaticPageById(staticPageId);
    }

    @Override
    public List<StaticPage> readAllStaticPage() {
        
        List<StaticPage> staticPageList = staticPageDao.readAllStaticPage();
        
        for(StaticPage i : staticPageList){
            if(i.isIsArchived()){
                i.setColorStatus("grey");
            }
        }
        
        return staticPageList;
    }
    
}
