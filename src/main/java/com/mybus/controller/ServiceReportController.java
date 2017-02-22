package com.mybus.controller;

import com.mybus.controller.util.ControllerUtils;
import com.mybus.dao.BookingDAO;
import com.mybus.exception.BadRequestException;
import com.mybus.model.ServiceReport;
import com.mybus.model.SubmittedServiceReport;
import com.mybus.service.ServiceReportsManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
@RequestMapping(value = "/api/v1/")
public class ServiceReportController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceReportController.class);

	@Autowired
	private ServiceReportsManager serviceReportsManager;

	@Autowired
	private BookingDAO bookingDAO;

	@RequestMapping(value = "serviceReport/downloadStatus", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value ="Get status of reports download", response = JSONObject.class)
	public JSONObject getDownloadStatus(HttpServletRequest request,
						@ApiParam(value = "Date of travel") @RequestParam final String travelDate) {
		return serviceReportsManager.getDownloadStatus(travelDate);
	}

	@RequestMapping(value = "serviceReport/download", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value ="Download reports for a given date", response = JSONObject.class)
	public JSONObject downloadReports(HttpServletRequest request,
										@ApiParam(value = "Date of travel") @RequestParam final String travelDate) {
		try{
			return serviceReportsManager.downloadReport(travelDate);
		}catch (Exception e) {
			throw new BadRequestException("Error downloading reports");
		}
	}

	@RequestMapping(value = "serviceReport/load", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value ="Load reports for a given date", response = JSONObject.class)
	public Iterable<ServiceReport> loadReports(HttpServletRequest request,
									  @ApiParam(value = "Date of travel") @RequestParam final String travelDate) {
		try{
			return serviceReportsManager.getReports(travelDate);
		}catch (Exception e) {
			throw new BadRequestException("Error loading reports");
		}
	}

	@RequestMapping(value = "serviceReport/{id}", method = RequestMethod.GET, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value ="Load one service report", response = JSONObject.class)
	public ServiceReport getServiceReport(HttpServletRequest request,
											   @ApiParam(value = "id")@PathVariable final String id) {
		try{
			ServiceReport report = serviceReportsManager.getReport(id);
			return report;
		}catch (Exception e) {
			throw new BadRequestException("Error loading report");
		}
	}

	@RequestMapping(value = "serviceReport", method = RequestMethod.POST, produces = ControllerUtils.JSON_UTF8)
	@ApiOperation(value ="Post service report", response = JSONObject.class)
	public void submitReport(HttpServletRequest request,
				@ApiParam(value = "JSON for ServiceReort to be submmitted") @RequestBody final ServiceReport serviceReport) {
		 serviceReportsManager.submitReport(serviceReport);
	}
}
