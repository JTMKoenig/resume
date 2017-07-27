/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Metabeing;
import com.sg.metabeingfinder.dto.Organization;
import com.sg.metabeingfinder.dto.OrganizationMetabeing;
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
public class OrganizationMetabeingDaoDbImpl implements OrganizationMetabeingDao {

    JdbcTemplate jdbc;

    public OrganizationMetabeingDaoDbImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SQL_INSERT_ORG_META
            = "insert into Organization_Metabeing (OrganizationId, MetabeingId) values(?,?)";
    private static final String SQL_DELETE_ORG_META
            = "delete from Organization_Metabeing where Organization_MetabeingId=?";
    private static final String SQL_DELETE_ORG_META_BY_META_ID
            = "delete from Organization_Metabeing where MetabeingId=?";
    private static final String SQL_DELETE_ORG_META_BY_ORG_ID
            = "delete from Organization_Metabeing where OrganizationId=?";
    private static final String SQL_UPDATE_ORG_META
            = "update Organization_Metabeing set OrganizationId=?, MetabeingId=?"
            + " where Organization_MetabeingId=? ";
    private static final String SQL_SELECT_ORG_META
            = "select * from Organization_Metabeing where Organization_MetabeingId=?";
    private static final String SQL_SELECT_ORG_META_BY_META_ID
            = "select * from Organization_Metabeing where MetabeingId=?";
    private static final String SQL_SELECT_ORG_METAS
            = "select * from Organization_Metabeing";

    @Override
    public void addOrgMeta(OrganizationMetabeing orgMeta) {
        jdbc.update(SQL_INSERT_ORG_META,
                orgMeta.getOrganization().getOrganizationId(),
                orgMeta.getMetabeing().getMetabeingId());
        orgMeta.setOrganizationMetabeingId(jdbc.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteOrgMeta(String organizationMetabeingId) {
        jdbc.update(SQL_DELETE_ORG_META, organizationMetabeingId);
    }

    @Override
    public void deleteOrgMetaByMetaId(String metabeingId) {
        jdbc.update(SQL_DELETE_ORG_META_BY_META_ID, metabeingId);
    }

    @Override
    public void deleteOrgMetaByOrgId(String organizationId) {
        jdbc.update(SQL_DELETE_ORG_META_BY_ORG_ID, organizationId);
    }

    @Override
    public void updateOrgMeta(OrganizationMetabeing orgMeta) {
        jdbc.update(SQL_UPDATE_ORG_META,
                orgMeta.getOrganization().getOrganizationId(),
                orgMeta.getMetabeing().getMetabeingId(),
                orgMeta.getOrganizationMetabeingId());
    }

    @Override
    public OrganizationMetabeing getOneOrgMeta(String organizationMetabeingId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_ORG_META, new OrganizationMetabeingMapper(),
                    organizationMetabeingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<OrganizationMetabeing> getAllOrgMetas() {
        return jdbc.query(SQL_SELECT_ORG_METAS, new OrganizationMetabeingMapper());
    }

    @Override
    public List<OrganizationMetabeing> getOrgMetaByMetaId(String metabeingId) {
        return jdbc.query(SQL_SELECT_ORG_META_BY_META_ID, new OrganizationMetabeingMapper(), metabeingId);
    }

    private static final class OrganizationMetabeingMapper implements RowMapper<OrganizationMetabeing> {

        @Override
        public OrganizationMetabeing mapRow(ResultSet rs, int i) throws SQLException {
            OrganizationMetabeing om = new OrganizationMetabeing();
            Organization o = new Organization();
            o.setOrganizationId(rs.getString("OrganizationId"));
            om.setOrganization(o);

            Metabeing m = new Metabeing();
            m.setMetabeingId(rs.getString("MetabeingId"));
            om.setMetabeing(m);
            om.setOrganizationMetabeingId(rs.getString("Organization_MetabeingId"));
            return om;
        }
    }

}
