/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.service.StaticPageService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vincentsiciliano
 */

@CrossOrigin
@Controller
public class RESTController {
    
    
    StaticPageService staticPageService;
    
    @Inject
    public RESTController(StaticPageService staticPageService){
        this.staticPageService = staticPageService;
    }
    
    @RequestMapping(value = "/getstaticpagelist", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> loadStaticPagesinNavBar(){
        
        
        
        return staticPageService.readAllStaticPage();
        
        
    }
    
}
