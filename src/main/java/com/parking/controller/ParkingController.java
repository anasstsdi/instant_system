package com.parking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.exception.ResourceNotFoundException;
import com.parking.requestDto.ParkingDto;
import com.parking.service.CitediaRestService;
import com.parking.validator.ParkingValidator;

@RestController
@RequestMapping("park-rest")
public class ParkingController {

	@Autowired
	private CitediaRestService citediaRestService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ParkingDto>> getAll() {
		return new ResponseEntity<List<ParkingDto>>(citediaRestService.getParkingList(), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ParkingDto> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
		return new ResponseEntity<ParkingDto>(citediaRestService.getParkingById(id), HttpStatus.ACCEPTED);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<ParkingDto> addParking(@Valid @RequestBody ParkingDto post) {
		return new ResponseEntity<ParkingDto>(post, HttpStatus.ACCEPTED);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ParkingValidator());
	}

}
