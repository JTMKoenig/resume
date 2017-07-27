/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Location;
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
public class LocationDaoDbImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public LocationDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into Location(LocationName, LocationDescription, Country, "
            + "City, State, StreetAddress, Latitude, Longitude) values(?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationId=?";
    private static final String SQL_UPDATE_LOCATION
            = "update Location set LocationName=?, LocationDescription=?, "
            + "Country=?, City=?, State=?, StreetAddress=?, Latitude=?, Longitude=? "
            + " where LocationId=? ";
    private static final String SQL_SELECT_LOCATION
            = "select * from Location where LocationId=?";
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";
    private static final String SQL_SELECT_TEN_LOCATIONS
            = "select * from Location limit 10";
    private static final String SQL_SELECT_LOCATION_BY_METABEING
            = "select l.LocationId, l.LocationName, l.LocationDescription, "
            + "l.Country, l.City, l.State, l.StreetAddress, l.Latitude, "
            + "l.Longitude from Location l "
            + "join Sighting s on l.LocationId = s.LocationId "
            + "join Sighting_Metabeing sm on s.SightingId = sm.SightingId "
            + "join Metabeing m on sm.MetabeingId = m.MetabeingId "
            + "where m.MetabeingId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(), location.getDescription(), location.getCountry(),
                location.getCity(), location.getState(), location.getStreetAddress(),
                location.getLatitude(), location.getLongitude());
        location.setLocationId(jdbcTemplate.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteLocation(String locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(), location.getDescription(), location.getCountry(),
                location.getCity(), location.getState(), location.getStreetAddress(),
                location.getLatitude(), location.getLongitude(), location.getLocationId());
    }

    @Override
    public Location getOneLocation(String locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getLocationByMetabeing(String metabeingId) {
        return jdbcTemplate.query(SQL_SELECT_LOCATION_BY_METABEING, new LocationMapper(), metabeingId);

    }

    @Override
    public List<Location> getTenLocations() {
        return jdbcTemplate.query(SQL_SELECT_TEN_LOCATIONS, new LocationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getString("LocationId"));
            l.setName(rs.getString("LocationName"));
            l.setDescription(rs.getString("LocationDescription"));
            l.setCountry(rs.getString("Country"));
            l.setCity(rs.getString("City"));
            l.setState(rs.getString("State"));
            l.setStreetAddress(rs.getString("StreetAddress"));
            l.setLatitude(rs.getString("Latitude"));
            l.setLongitude(rs.getString("Longitude"));

            return l;
        }
    }

}
