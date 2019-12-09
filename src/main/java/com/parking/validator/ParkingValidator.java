package com.parking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.parking.requestDto.ParkingDto;

@Component("beforeCreateParkingValidator")
public class ParkingValidator implements Validator {
	 
    @Override
    public boolean supports(Class<?> clazz) {
        return ParkingDto.class.equals(clazz);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
    	ParkingDto parkingDto = (ParkingDto) obj;
        if (checkInputString(parkingDto.getFields().getId())) {
            errors.rejectValue("id", "id.empty");
        }
    }
 
    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
