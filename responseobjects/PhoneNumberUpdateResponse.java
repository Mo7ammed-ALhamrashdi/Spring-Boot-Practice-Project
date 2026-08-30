package com.cl.demo.responseobjects;

import com.cl.demo.entities.PhoneNumber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PhoneNumberUpdateResponse {

    private String phoneNumberId;
    private String countryCode;
    private Long phoneNumber;

    public static PhoneNumberUpdateResponse convert(PhoneNumber phoneNumber) {
        if (phoneNumber == null || phoneNumber.getId() == null) {
            return null;
        }
        PhoneNumberUpdateResponse response = new PhoneNumberUpdateResponse();
        response.setPhoneNumberId(phoneNumber.getId().toString()
        );
        response.setCountryCode(phoneNumber.getCountryCode()
        );
        response.setPhoneNumber(phoneNumber.getPhoneNumber()
        );
        return response;
    }
    public static List<PhoneNumberUpdateResponse> convert(List<PhoneNumber> phoneNumberList) {
        List<PhoneNumberUpdateResponse> responseList = new ArrayList<>();
        for (PhoneNumber phoneNumber : phoneNumberList) {
            PhoneNumberUpdateResponse response = convert(phoneNumber);
            if (response != null) {responseList.add(response);
            }
        }
        return responseList;
    }
}