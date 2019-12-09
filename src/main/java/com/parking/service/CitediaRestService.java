package com.parking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.parking.exception.ResourceNotFoundException;
import com.parking.model.Parking;
import com.parking.requestDto.ParkingDto;

@Service
public class CitediaRestService {

	private final RestTemplate restTemplate;
	
	private static final String urlApi = "https://data.rennesmetropole.fr/api/records/1.0/search/?dataset=export-api-parking-citedia";

	public CitediaRestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<ParkingDto> getParkingList() {
		
		Parking parking = this.restTemplate.getForObject(urlApi, Parking.class);

		return mapParkingListToParkingDto(parking);
	}

	public ParkingDto getParkingById(String id) throws ResourceNotFoundException {
		String url = new StringBuilder()
				.append(urlApi)
			    .append("&refine.id=")
			    .append(id)
			    .toString();
		Parking parking = this.restTemplate.getForObject(url, Parking.class);
		if(null == parking.getRecords() || parking.getRecords().isEmpty()) {
			throw new ResourceNotFoundException(
					"Could not find resource [" + id + "] ");
		}
		return mapParkingToParkingDto(parking);
	}

	private List<ParkingDto> mapParkingListToParkingDto(Parking parking) {
		ModelMapper modelMapper = new ModelMapper();
		List<ParkingDto> parkingList = new ArrayList<ParkingDto>();
		parkingList = parking.getRecords().stream().map(record -> {
			ParkingDto parkingDTO = modelMapper.map(record, ParkingDto.class);
			return parkingDTO;
		}).collect(Collectors.toList());
		return parkingList;
	}

	private ParkingDto mapParkingToParkingDto(Parking parking) {
		ModelMapper modelMapper = new ModelMapper();
		ParkingDto parkingDTO = modelMapper.map(parking.getRecords().get(0), ParkingDto.class);
		return parkingDTO;
	}
}
