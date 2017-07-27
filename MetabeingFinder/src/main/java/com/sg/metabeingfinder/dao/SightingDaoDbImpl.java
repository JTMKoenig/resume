/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Sighting;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jono
 */
public class SightingDaoDbImpl implements SightingDao {

    JdbcTemplate jdbc;

    public SightingDaoDbImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sighting(SightingDescription, SightingDate, LocationId) values(?,?,?)";
    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where SightingId=?";
    private static final String SQL_UPDATE_SIGHTING
            = "update Sighting set SightingDescription=?, SightingDate=?, LocationId=? "
            + " where SightingId=? ";
    private static final String SQL_SELECT_SIGHTING
            = "select * from Sighting where SightingId=?";
    private static final String SQL_SELECT_SIGHTING_BY_LOCATION_ID
            = "select * from Sighting where LocationId=?";
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting";
    private static final String SQL_SELECT_SIGHTING_BY_DATE
            = "select * from Sighting where SightingDate=?";
    private static final String SQL_SELECT_TEN_SIGHTINGS
            = "select * from Sighting limit 10";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        Date date = Date.valueOf(sighting.getDate());
        jdbc.update(SQL_INSERT_SIGHTING,
                sighting.getDescription(), date, sighting.getLocation().getLocationId());
        sighting.setSightingId(jdbc.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteSighting(String sightingId) {
        jdbc.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbc.update(SQL_UPDATE_SIGHTING,
                sighting.getDescription(), sighting.getDate().toString(), sighting.getLocation().getLocationId(),
                sighting.getSightingId());
    }

    @Override
    public Sighting getOneSighting(String sightingId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingByLocation(String locationId) {
        return jdbc.query(SQL_SELECT_SIGHTING_BY_LOCATION_ID, new SightingMapper(), locationId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbc.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate date) {
        return jdbc.query(SQL_SELECT_SIGHTING_BY_DATE, new SightingMapper(), date.toString());
    }

    @Override
    public List<Sighting> getTenSightings() {
        return jdbc.query(SQL_SELECT_TEN_SIGHTINGS, new SightingMapper());
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getString("SightingId"));
            s.setDescription(rs.getString("SightingDescription"));
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
            s.setDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());
            Location l = new Location();
            l.setLocationId(rs.getString("LocationId"));
            s.setLocation(l);
            return s;
        }
    }

}
