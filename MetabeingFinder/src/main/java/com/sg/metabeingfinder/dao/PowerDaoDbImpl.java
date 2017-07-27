/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class PowerDaoDbImpl implements PowerDao {

    private JdbcTemplate jdbcTemplate;

    public PowerDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_POWER
            = "insert into Power (PowerType, PowerDescription) values(?,?)";
    private static final String SQL_DELETE_POWER
            = "delete from Power where PowerId=?";
    private static final String SQL_UPDATE_POWER
            = "update Power set PowerType=?, PowerDescription=?"
            + " where PowerId=? ";
    private static final String SQL_SELECT_POWER
            = "select * from Power where PowerId=?";
    private static final String SQL_SELECT_ALL_POWERS
            = "select * from Power";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPowerType(), power.getPowerDescription());
        power.setPowerId(jdbcTemplate.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deletePower(String powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getPowerType(), power.getPowerDescription(), power.getPowerId());
    }

    @Override
    public Power getOnePower(String powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }

    public static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerId(rs.getString("PowerId"));
            p.setPowerType(rs.getString("PowerType"));
            p.setPowerDescription(rs.getString("PowerDescription"));

            return p;
        }
    }

}
