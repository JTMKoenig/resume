/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.services;

import com.sg.dvdlibraryspringmvc.dao.DVDLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author jono
 */
public class DvdService {

    DVDLibraryDao dao;

    @Inject
    public DvdService(DVDLibraryDao dao) {
        this.dao = dao;
    }

    public Dvd add(Dvd dvd) {
        return dao.addDvd(dvd);
    }

    public void update(Dvd dvd) {
        dao.updateDvd(dvd);
    }

    public Dvd read(int dvdId) {
        return dao.getDvdById(dvdId);
    }

    public void delete(int dvdId) {
        dao.removeDvd(dvdId);
    }

    public List<Dvd> list() {
        return dao.getAllDvds();
    }

    public List<Dvd> searchDvds(Map<SearchTerm, String> criteriaMap) {
        return dao.searchDvds(criteriaMap);
    }

}
