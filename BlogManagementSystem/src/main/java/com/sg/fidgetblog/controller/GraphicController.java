/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.service.GraphicService;
import com.sg.fidgetblog.service.PostService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class GraphicController {

    PostService postService;
    GraphicService graphicService;

    public GraphicController(PostService postService, GraphicService graphicService) {
        this.postService = postService;
        this.graphicService = graphicService;
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public void saveGraphicToDatabase(HttpServletRequest request,
            Model model,
            @RequestParam("file") MultipartFile graphicFile, HttpServletResponse response) {
        Graphic newGraphic = new Graphic();

        newGraphic.setImageId(graphicService.createGraphic(graphicFile));

        String fileName = graphicFile.getOriginalFilename();
        String ctx = request.getContextPath();
        String jsonString = "{ location : '" + ctx + "/showImage/" + newGraphic.getImageId() + "' }";

        JSONObject jsonObject = new JSONObject(jsonString);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        } catch (IOException e) {

        }

    }

    @RequestMapping(value = "/showImage/{id}", method = RequestMethod.GET)
    public void showGraphicInCreatePost(@PathVariable("id") int graphicId,
            HttpServletResponse response, HttpServletRequest request) {

        Graphic graphic = graphicService.readGraphicById(graphicId);
        response.setContentType("image/jpeg, image/jpg");
        try {
            response.getOutputStream().write(graphic.getImage());
            response.getOutputStream().close();
        } catch (IOException e) {

        }

    }

}
