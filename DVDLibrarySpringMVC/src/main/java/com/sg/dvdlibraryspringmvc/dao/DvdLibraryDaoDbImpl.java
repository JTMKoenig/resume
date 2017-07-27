/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jono
 */
public class DvdLibraryDaoDbImpl implements DVDLibraryDao {

    private JdbcTemplate jdbcTemplate;

    public DvdLibraryDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_DVD
            = "insert into dvds (title, releaseDate, director, rating, notes) "
            + "values(?,?,?,?,?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvds where dvdId = ?";
    private static final String SQL_UPDATE_DVD
            = "update dvds set title=?, releaseDate=?, director=?, rating=?, notes=? "
            + "where dvdId=?";
    private static final String SQL_SELECT_DVD
            = "select * from dvds where dvdId=?";
    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvds";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(), dvd.getReleaseDate(), dvd.getDirector(),
                dvd.getRating(), dvd.getNotes());
        dvd.setId(jdbcTemplate.queryForObject("select last_insert_id()", int.class));

        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(), dvd.getReleaseDate(), dvd.getDirector(),
                dvd.getRating(), dvd.getNotes(), dvd.getId());
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        throw new UnsupportedOperationException("TODO");
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd d = new Dvd();
            d.setTitle(rs.getString("title"));
            d.setReleaseDate(rs.getString("releaseDate"));
            d.setDirector(rs.getString("director"));
            d.setRating(rs.getString("rating"));
            d.setNotes(rs.getString("notes"));
            d.setId(rs.getInt("dvdId"));

            return d;
        }
    }

}
