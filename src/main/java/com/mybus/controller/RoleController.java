package com.mybus.controller;

import com.mybus.controller.util.ControllerUtils;
import com.mybus.dao.RoleDAO;
import com.mybus.model.Role;
import com.mybus.service.RoleManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CrazyNaveen on 4/27/16.
 */
@Api(value = "Role Controller")
@RequestMapping(value = "/api/v1/")
@RestController
public class RoleController extends MyBusBaseController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleManager roleManager;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "roles", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    @ApiOperation(value = "Get all the roles available", response = Role.class, responseContainer = "list")
    public Iterable<Role> getRoles(HttpServletRequest request) {
        return roleDAO.findAll();
    }


    @RequestMapping(value = "createRole", method = RequestMethod.POST, produces = ControllerUtils.JSON_UTF8,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a role")
    public ResponseEntity createRole(HttpServletRequest request,
                                     @ApiParam(value = "JSON for Role to be created") @RequestBody final Role role){
        logger.debug("create role called");
        return new ResponseEntity<>(roleManager.saveRole(role), HttpStatus.OK);
    }


    @RequestMapping(value = "role/{id}", method = RequestMethod.PUT)
    @ApiOperation(value ="Update role", response = Role.class)
    public ResponseEntity<Role> updateRole(HttpServletRequest request,
                                     @ApiParam(value = "Id of the Role to be found") @PathVariable final String id,
                                     @ApiParam(value = "Role JSON") @RequestBody final Role role) {
        logger.debug("update role called");
        return new ResponseEntity<>(roleManager.updateRole(role), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "role/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value ="Delete a role")
    public JSONObject deleteRole(HttpServletRequest request,
                                 @ApiParam(value = "Id of the role to be deleted") @PathVariable final String id) {
        logger.debug("delete role called");
        JSONObject response = new JSONObject();
        response.put("deleted", roleManager.deleteRole(id));
        return response;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "role/{id}", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
    public Role getRole(HttpServletRequest request, @PathVariable final String id
    ) {
        Role  role = (Role)roleDAO.findOne(id);
        return role;
    }
}

