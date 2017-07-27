/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author jono
 */
public class DVDLibraryDaoInMemImpl implements DVDLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap();
    private static int dvdIdCounter;

    DVDLibraryDaoInMemImpl() {
        dvdIdCounter = 0;
    }

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setId(dvdIdCounter);
        dvdIdCounter += 1;
        dvdMap.put(dvd.getId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> d = dvdMap.values();
        return new ArrayList(d);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String titleSearchCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateSearchCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String directorSearchCriteria = criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria = criteria.get(SearchTerm.RATING);

        Predicate<Dvd> titleMatchPredicate;
        Predicate<Dvd> releaseDateMatchPredicate;
        Predicate<Dvd> directorMatchPredicate;
        Predicate<Dvd> ratingMatchPredicate;

        Predicate<Dvd> truePredicate = (d) -> {
            return true;
        };

        if (titleSearchCriteria == null
                || titleSearchCriteria.isEmpty()) {
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate
                    = (d) -> d.getTitle().equals(titleSearchCriteria);
        }

        if (releaseDateSearchCriteria == null
                || releaseDateSearchCriteria.isEmpty()) {
            releaseDateMatchPredicate = truePredicate;
        } else {
            releaseDateMatchPredicate
                    = (d) -> d.getReleaseDate().equals(releaseDateSearchCriteria);
        }

        if (directorSearchCriteria == null
                || directorSearchCriteria.isEmpty()) {
            directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate
                    = (d) -> d.getDirector().equals(directorSearchCriteria);
        }

        if (ratingSearchCriteria == null
                || ratingSearchCriteria.isEmpty()) {
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate
                    = (d) -> d.getRating().equals(ratingSearchCriteria);
        }

        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                        .and(releaseDateMatchPredicate)
                        .and(directorMatchPredicate)
                        .and(ratingMatchPredicate))
                .collect(Collectors.toList());
    }

}
