package com.mybus.controller;

import com.mybus.controller.util.ControllerUtils;
import com.mybus.dao.ServiceComboDAO;
import com.mybus.model.Amenity;
import com.mybus.model.ServiceCombo;
import com.mybus.service.ServiceComboManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/api/v1/")
@Api(value="ServiceComboController")
public class ServiceComboController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceComboController.class);

	@Autowired
	private ServiceComboDAO serviceComboDAO;


	@Autowired
	private ServiceComboManager serviceComboManager;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "serviceCombos", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value = "Get all the ServicCombos available", response = Amenity.class, responseContainer = "List")
	public Iterable<ServiceCombo> getAll() {
		LOGGER.debug("Get all the ServicCombos available");
		return serviceComboDAO.findAll();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "serviceCombo", method = RequestMethod.POST, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value = "add serviceCombo")
	public ServiceCombo addServiceCombo(HttpServletRequest request,@RequestBody ServiceCombo serviceCombo) {
		LOGGER.debug("add serviceCombo");
		return serviceComboDAO.save(serviceCombo);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "serviceCombo", method = RequestMethod.PUT, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value = "add serviceCombo")
	public ServiceCombo updateAmenity(HttpServletRequest request,@RequestBody ServiceCombo serviceCombo) {
		LOGGER.debug("update serviceCombo");
		return serviceComboManager.update(serviceCombo);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "serviceCombo/{id}", method = RequestMethod.GET)
	@ApiOperation(value ="get serviceCombo by id")
	public ServiceCombo getServiceComboID(HttpServletRequest request,
			@ApiParam(value = "Id of the serviceCombo") @PathVariable final String id) {
		LOGGER.debug("get serviceCombo by id");
		return serviceComboDAO.findOne(id);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "serviceCombo/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value ="Delete a serviceCombo")
	public void deleteAmenity(HttpServletRequest request,
			@ApiParam(value = "Id of the serviceCombo to be deleted") @PathVariable final String id) {
		LOGGER.debug("delete serviceCombo called");
		serviceComboDAO.delete(id);
	}
}