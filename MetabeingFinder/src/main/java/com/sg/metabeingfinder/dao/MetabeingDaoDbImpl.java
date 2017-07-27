/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Power;
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
public class MetabeingDaoDbImpl implements MetabeingDao {

    private JdbcTemplate jdbcTemplate;

    public MetabeingDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_METABEING
            = "insert into Metabeing (MetabeingName, MetabeingAlias, MetabeingDescription) "
            + " values(?,?,?)";
    private static final String SQL_DELETE_METABEING
            = "delete from Metabeing where MetabeingId=?";
    private static final String SQL_UPDATE_METABEING
            = "update Metabeing set MetabeingName=?, MetabeingAlias=?, MetabeingDescription=? "
            + " where MetabeingId=? ";
    private static final String SQL_SELECT_METABEING
            = "select * from Metabeing where MetabeingId=?";
    private static final String SQL_SELECT_ALL_METABEINGS
            = "select * from Metabeing";
    private static final String SQL_FIND_META_BY_LOCATION
            = "select m.MetabeingId, m.MetabeingName, m.MetabeingAlias, m.MetabeingDescription from Metabeing m "
            + "join Sighting_Metabeing sm on m.MetabeingId = sm.MetabeingId "
            + "join Sighting s on sm.SightingId = s.SightingId "
            + "join Location l on s.LocationId = l.LocationId "
            + "where l.LocationId=?";
    private static final String SQL_FIND_META_BY_ORGANIZATION
            = "select m.MetabeingId, m.MetabeingName, m.MetabeingAlias, "
            + "m.MetabeingDescription from Metabeing m "
            + "join Organization_Metabeing om on m.MetabeingId = om.MetabeingId "
            + "join Organization o on om.OrganizationId = o.OrganizationId "
            + "where o.OrganizationId = ?";
    private static final String SQL_SELECT_TEN_METAS
            = "select m.MetabeingId, m.MetabeingName, m.MetabeingAlias, m.MetabeingDescription "
            + "from Metabeing m "
            + "order by m.MetabeingName "
            + "limit 10";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMetabeing(Metabeing metabeing) {
        jdbcTemplate.update(SQL_INSERT_METABEING,
                metabeing.getName(), metabeing.getAlias(), metabeing.getDescription());

        metabeing.setMetabeingId(jdbcTemplate.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteMetabeing(String metabeingId) {
        jdbcTemplate.update(SQL_DELETE_METABEING, metabeingId);
    }

    @Override
    public void updateMetabeing(Metabeing metabeing) {
        jdbcTemplate.update(SQL_UPDATE_METABEING,
                metabeing.getName(), metabeing.getAlias(),
                metabeing.getDescription(), metabeing.getMetabeingId());
    }

    @Override
    public Metabeing getOneMetabeing(String metabeingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_METABEING, new MetabeingMapper(), metabeingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Metabeing> getAllMetabeings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_METABEINGS, new MetabeingMapper());
    }

    @Override
    public List<Metabeing> findMetaByLocation(String locationId) {
        return jdbcTemplate.query(SQL_FIND_META_BY_LOCATION, new MetabeingMapper(), locationId);
    }

    @Override
    public List<Metabeing> findMetaByOrganization(String organizationId) {
        return jdbcTemplate.query(SQL_FIND_META_BY_ORGANIZATION, new MetabeingMapper(), organizationId);
    }

    @Override
    public List<Metabeing> getTenMetas() {
        return jdbcTemplate.query(SQL_SELECT_TEN_METAS, new MetabeingMapper());
    }

    private static final class MetabeingMapper implements RowMapper<Metabeing> {

        @Override
        public Metabeing mapRow(ResultSet rs, int i) throws SQLException {
            Metabeing m = new Metabeing();
            m.setMetabeingId(rs.getString("MetabeingId"));
            m.setName(rs.getString("MetabeingName"));
            m.setAlias(rs.getString("MetabeingAlias"));
            m.setDescription(rs.getString("MetabeingDescription"));
            return m;
        }
    }

}
