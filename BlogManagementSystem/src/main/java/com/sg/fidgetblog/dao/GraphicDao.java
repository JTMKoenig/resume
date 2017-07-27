/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.Graphic;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jono
 */
public interface GraphicDao {

    public int createGraphic(MultipartFile graphic);

    public Graphic readGraphicById(int graphicId);

    public void deleteGraphicById(int graphicId);

    public void updateGraphicPostId(Graphic graphic);
}
