/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Sighting;
import com.sg.metabeingfinder.dto.SightingMetabeing;
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
public class SightingMetabeingDaoDbImpl implements SightingMetabeingDao {

    JdbcTemplate jdbc;

    public SightingMetabeingDaoDbImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SQL_INSERT_SIGHTING_META
            = "insert into Sighting_Metabeing (SightingId, MetabeingId) values(?,?)";
    private static final String SQL_DELETE_SIGHTING_META
            = "delete from Sighting_Metabeing where Sighting_MetabeingId=?";
    private static final String SQL_DELETE_SIGHTING_META_BY_META_ID
            = "delete from Sighting_Metabeing where MetabeingId=?";
    private static final String SQL_UPDATE_SIGHTING_META
            = "update Sighting_Metabeing set SightingId=?, MetabeingId=?"
            + " where Sighting_MetabeingId=? ";
    private static final String SQL_SELECT_SIGHTING_META
            = "select * from Sighting_Metabeing where Sighting_MetabeingId=?";
    private static final String SQL_SELECT_SIGHTING_META_BY_SIGHTING_ID
            = "select * from Sighting_Metabeing where SightingId=?";
    private static final String SQL_SELECT_SIGHTING_META_BY_META_ID
            = "select * from Sighting_Metabeing where sightingId in "
            + "(select SightingId from Sighting_Metabeing where MetabeingId = ?) "
            + "limit 10";
    private static final String SQL_SELECT_SIGHTING_METAS
            = "select * from Sighting_Metabeing";
    private static final String SQL_SELECT_RECENT_SIGHTING_METAS
            = "SELECT sm.Sighting_MetabeingId, sm.SightingId, sm.MetabeingId "
            + "FROM MetabeingFinder.Sighting_Metabeing sm "
            + "join Sighting s on sm.SightingId = s.SightingId "
            + "order by s.SightingDate, sm.Sighting_MetabeingId, sm.SightingId, "
            + "sm.MetabeingId limit 10";

    @Override
    public void addSightingMeta(SightingMetabeing sightingMeta) {
        jdbc.update(SQL_INSERT_SIGHTING_META,
                sightingMeta.getSighting().getSightingId(),
                sightingMeta.getMetabeing().getMetabeingId());
        sightingMeta.setSightingMetabeingId(jdbc.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteSightingMeta(String sightingMetabeingId) {
        jdbc.update(SQL_DELETE_SIGHTING_META, sightingMetabeingId);
    }

    @Override
    public void deleteSightingMetaByMetaId(String metabeingId) {
        jdbc.update(SQL_DELETE_SIGHTING_META_BY_META_ID, metabeingId);
    }

    @Override
    public void updateSightingMeta(SightingMetabeing sightingMeta) {
        jdbc.update(SQL_UPDATE_SIGHTING_META,
                sightingMeta.getSighting().getSightingId(),
                sightingMeta.getMetabeing().getMetabeingId(),
                sightingMeta.getSightingMetabeingId());
    }

    @Override
    public SightingMetabeing getOneSightingMeta(String sightingMetabeingId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_SIGHTING_META, new SightingMetabeingMapper(), sightingMetabeingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SightingMetabeing> getSightMetasBySightingId(String sightingId) {
        return jdbc.query(SQL_SELECT_SIGHTING_META_BY_SIGHTING_ID, new SightingMetabeingMapper(), sightingId);
    }

    @Override
    public List<SightingMetabeing> getAllSightingMetas() {
        return jdbc.query(SQL_SELECT_SIGHTING_METAS, new SightingMetabeingMapper());
    }

    @Override
    public List<SightingMetabeing> getRecentSightingMetas() {
        return jdbc.query(SQL_SELECT_RECENT_SIGHTING_METAS, new SightingMetabeingMapper());
    }

    @Override
    public List<SightingMetabeing> getSightMetasByMetaId(String metabeingId) {
        return jdbc.query(SQL_SELECT_SIGHTING_META_BY_META_ID, new SightingMetabeingMapper(), metabeingId);
    }

    private static final class SightingMetabeingMapper implements RowMapper<SightingMetabeing> {

        @Override
        public SightingMetabeing mapRow(ResultSet rs, int i) throws SQLException {
            SightingMetabeing sm = new SightingMetabeing();
            Sighting s = new Sighting();
            s.setSightingId(rs.getString("SightingId"));
            sm.setSighting(s);

            Metabeing m = new Metabeing();
            m.setMetabeingId(rs.getString("MetabeingId"));
            sm.setMetabeing(m);
            sm.setSightingMetabeingId(rs.getString("Sighting_MetabeingId"));
            return sm;
        }
    }

}
