package com.mybus.service;

import com.mybus.controller.AbstractControllerIntegrationTest;
import com.mybus.dao.RoleDAO;
import com.mybus.model.Role;
import junit.framework.Assert;
import org.apache.commons.collections.IteratorUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by CrazyNaveen on 4/27/16.
 */
public class RoleManagerTest extends AbstractControllerIntegrationTest{


    private Role currentRole;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleManager roleManager;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        cleanup();
    }

    @After
    public void tearDown() throws Exception {
        cleanup();
    }

    void cleanup(){
        roleDAO.deleteAll();
    }


    public Role createRole(){
        Role role = new Role("test");
        return roleDAO.save(role);
    }


    @Test
    public void testSaveRole() throws Exception {
        Role role = createRole();
        Role duplicateRole = new Role("test");
        roleDAO.save(role);
        Assert.assertNotNull(roleDAO.findOne(role.getId()));
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Role  already exists with this same name");
        roleManager.saveRole(duplicateRole);

    }


    @Test
    public void testUpdateRole() throws Exception {
        Role role = createRole();
        Role r = roleDAO.findOne(role.getId());
        ArrayList roles = (ArrayList) IteratorUtils.toList(roleDAO.findAll().iterator());
        Assert.assertEquals(1, roles.size());
        role.setName("kee");
        Assert.assertNotNull(r);
        roleDAO.save(r);
        Assert.assertEquals("kee", role.getName());
        
    }

    @Test
    public void testDeleteRole() throws Exception {
        Role role = createRole();
        Assert.assertNotNull(role);
        Assert.assertNotNull(role.getId());
        roleDAO.delete(role);
        Assert.assertNull(roleDAO.findOne(role.getId()));

    }

    @Test
    public void getRole() throws Exception {
        Role role = createRole();
        Assert.assertNotNull(role);
        Assert.assertNotNull(role.getId());
        roleDAO.findOne(role.getId());
        Assert.assertNotNull(roleDAO.findOne(role.getId()));


    }
}