/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.metabeingfinder.dao;

import com.sg.metabeingfinder.dto.Location;
import com.sg.metabeingfinder.dto.Organization;
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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jdbc;

    public OrganizationDaoDbImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization (OrganizationName, OrganizationDescription, "
            + "LocationId, OrganizationPhone, OrganizationEmail) values(?,?,?,?,?)";
    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationId=?";
    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set OrganizationName=?, OrganizationDescription=?, "
            + "LocationId=?, OrganizationPhone=?, OrganizationEmail=?"
            + " where OrganizationId=? ";
    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where OrganizationId=?";
    private static final String SQL_SELECT_ORGANIZATION_BY_LOCATION_ID
            = "select * from Organization where LocationId=?";
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";
    private static final String SQL_SELECT_ORGANIZATION_BY_META
            = "select o.OrganizationId, o.OrganizationName, o.OrganizationDescription, "
            + "o.LocationId, o.OrganizationPhone, o.OrganizationEmail from Organization o "
            + "join Organization_Metabeing om on o.OrganizationId = om.OrganizationId "
            + "join Metabeing m on om.MetabeingId = m.MetabeingId "
            + "where m.MetabeingId =?";
    private static final String SQL_SELECT_TEN_ORGANIZATIONS
            = "select * from Organization limit 10";

    @Override
    public void addOrganization(Organization organization) {
        jdbc.update(SQL_INSERT_ORGANIZATION,
                organization.getName(), organization.getDescription(),
                organization.getLocation().getLocationId(), organization.getPhone(), organization.getEmail());
        organization.setOrganizationId(jdbc.queryForObject("select last_insert_id()", String.class));
    }

    @Override
    public void deleteOrganization(String organizationId) {
        jdbc.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbc.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(), organization.getDescription(),
                organization.getLocation().getLocationId(), organization.getPhone(),
                organization.getEmail(), organization.getOrganizationId());

    }

    @Override
    public Organization getOneOrganization(String organizationId) {
        try {
            return jdbc.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getOrganizationsByLocation(String locationId) {
        return jdbc.query(SQL_SELECT_ORGANIZATION_BY_LOCATION_ID, new OrganizationMapper(), locationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbc.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public List<Organization> getOrganizationByMeta(String metabeingId) {
        return jdbc.query(SQL_SELECT_ORGANIZATION_BY_META, new OrganizationMapper(), metabeingId);
    }

    @Override
    public List<Organization> getTenOrganizations() {
        return jdbc.query(SQL_SELECT_TEN_ORGANIZATIONS, new OrganizationMapper());
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getString("OrganizationId"));
            o.setName(rs.getString("OrganizationName"));
            o.setDescription(rs.getString("OrganizationDescription"));
            Location l = new Location();
            l.setLocationId(rs.getString("LocationId"));
            o.setLocation(l);
            o.setPhone(rs.getString("OrganizationPhone"));
            o.setEmail(rs.getString("OrganizationEmail"));
            return o;
        }
    }

}
