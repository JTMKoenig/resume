/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
import com.sg.metabeingfinder.dto.PowerMetabeing;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jono
 */
public class PowerMetabeingDaoDbImpl implements PowerMetabeingDao {

    JdbcTemplate jdbc;

    public PowerMetabeingDaoDbImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SQL_INSERT_POWER_META
            = "insert into Power_Metabeing (PowerId, MetabeingId) values(?,?)";
    private static final String SQL_DELETE_POWER_META
            = "delete from Power_Metabeing where Power_MetabeingId=?";
    private static final String SQL_DELETE_POWER_META_BY_META_ID
            = "delete from Power_Metabeing where MetabeingId=?";
    private static final String SQL_UPDATE_POWER_META
            = "update Power_Metabeing set PowerId=?, MetabeingId=?"
            + " where Power_MetabeingId=? ";
    private static final String SQL_SELECT_POWER_META
            = "select * from Power_Metabeing where Power_MetabeingId=?";
    private static final String SQL_SELECT_POWER_METAS
            = "select * from Power_Metabeing";
    private static final String SQL_SELECT_POWER_META_BY_META_ID
            = "select * from Power_Metabeing where MetabeingId=?";

    @Override
    public void addPowerMeta(PowerMetabeing powerMeta) {
        jdbc.update(SQL_INSERT_POWER_META,
                powerMeta.getPower().getPowerId(),
                powerMeta.getMetabeing().getMetabeingId());
        powerMeta.setPowerMetabeingId(jdbc.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deletePowerMeta(String powerMetaId) {
        jdbc.update(SQL_DELETE_POWER_META, powerMetaId);
    }

    @Override
    public void deletePowerMetaByMetaId(String metabeingId) {
        jdbc.update(SQL_DELETE_POWER_META_BY_META_ID, metabeingId);
    }

    @Override
    public void updatePowerMeta(PowerMetabeing powerMeta) {
        jdbc.update(SQL_UPDATE_POWER_META,
                powerMeta.getPower().getPowerId(),
                powerMeta.getMetabeing().getMetabeingId(),
                powerMeta.getPowerMetabeingId());
    }

    @Override
    public PowerMetabeing getOnePowerMeta(String powerMetabeingId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_POWER_META, new PowerMetabeingMapper(), powerMetabeingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<PowerMetabeing> getAllPowerMetas() {
        return jdbc.query(SQL_SELECT_POWER_METAS, new PowerMetabeingMapper());
    }

    @Override
    public PowerMetabeing getPowersByMetaId(String metabeingId) {
        return jdbc.queryForObject(SQL_SELECT_POWER_META_BY_META_ID, new PowerMetabeingMapper(), metabeingId);
    }

    private static final class PowerMetabeingMapper implements RowMapper<PowerMetabeing> {

        @Override
        public PowerMetabeing mapRow(ResultSet rs, int i) throws SQLException {
            PowerMetabeing pm = new PowerMetabeing();
            Power p = new Power();
            p.setPowerId(rs.getString("PowerId"));
            pm.setPower(p);

            Metabeing m = new Metabeing();
            m.setMetabeingId(rs.getString("MetabeingId"));
            pm.setMetabeing(m);
            pm.setPowerMetabeingId(rs.getString("Power_MetabeingId"));
            return pm;
        }
    }

}
