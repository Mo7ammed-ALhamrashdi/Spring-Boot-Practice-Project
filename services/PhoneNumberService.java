package com.cl.demo.services;

import com.cl.demo.DemoApplication;
import com.cl.demo.entities.PhoneNumber;
import com.cl.demo.requestobjects.PhoneNumberCreateRequest;
import com.cl.demo.requestobjects.PhoneNumberUpdateRequest;
import com.cl.demo.utils.HelperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneNumberService {

    public PhoneNumber addPhoneNumber(PhoneNumberCreateRequest req) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.randomUUID()
        );
        phoneNumber.setIsActive(Boolean.TRUE
        );
        phoneNumber.setCreatedDate(new Date()
        );
        phoneNumber.setCountryCode(req.getCountryCode()
        );
        phoneNumber.setPhoneNumber(req.getPhoneNumber()
        );
        DemoApplication.PhoneNumber_List.add(phoneNumber
        );
        return phoneNumber;
    }

    public PhoneNumber getPhoneNumberById(String uuid) {
        for (PhoneNumber phoneNumber : DemoApplication.PhoneNumber_List) {
            if (phoneNumber.getId().toString().equals(uuid) && Boolean.TRUE.equals(phoneNumber.getIsActive())) {
                return phoneNumber;
            }
        }

        return new PhoneNumber();
    }


    public List<PhoneNumber> getAllPhoneNumbers() {
        List<PhoneNumber> resultList = new ArrayList<>();
        for (PhoneNumber phoneNumber :
                DemoApplication.PhoneNumber_List) {
            if (Boolean.TRUE.equals(
                    phoneNumber.getIsActive())) {resultList.add(phoneNumber
                );
            }
        }
        return resultList;
    }
    public PhoneNumber updatePhoneNumber(PhoneNumberUpdateRequest updateObj) {
        PhoneNumber phoneNumber = getPhoneNumberById(updateObj.getUuid());
        if (phoneNumber.getId() == null || !Boolean.TRUE.equals(phoneNumber.getIsActive())) {
            return phoneNumber;
        }
        phoneNumber.setCountryCode(
                HelperUtils.compare(
                        phoneNumber.getCountryCode(),
                        updateObj.getCountryCodeToUpdate()
                )
        );
        phoneNumber.setCountryCode(HelperUtils.compare(
                phoneNumber.getCountryCode(), updateObj.getCountryCodeToUpdate()
                )
        );
        phoneNumber.setUpdatedDate(new Date()
        );
        return phoneNumber;
    }
    public Boolean deleteById(
            String uuid) {PhoneNumber phoneNumber = getPhoneNumberById(uuid);
        if (phoneNumber.getId() == null || !Boolean.TRUE.equals(phoneNumber.getIsActive()))
        {
            return false;
        }
        phoneNumber.setIsActive(Boolean.FALSE
        );
        phoneNumber.setUpdatedDate(new Date()
        );
        return true;
    }
}