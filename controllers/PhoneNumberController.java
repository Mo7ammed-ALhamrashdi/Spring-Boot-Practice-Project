package com.cl.demo.controllers;

import com.cl.demo.entities.PhoneNumber;
import com.cl.demo.requestobjects.PhoneNumberCreateRequest;
import com.cl.demo.requestobjects.PhoneNumberUpdateRequest;
import com.cl.demo.responseobjects.PhoneNumberCreateResponse;
import com.cl.demo.responseobjects.PhoneNumberUpdateResponse;
import com.cl.demo.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phoneNumber")
public class PhoneNumberController {

    @Autowired
    public PhoneNumberService phoneNumberService;

    @PostMapping("add")
    public PhoneNumberCreateResponse addPhoneNumber(@RequestBody PhoneNumberCreateRequest req) {
        PhoneNumber phoneNumber =
                phoneNumberService.addPhoneNumber(req);
        return PhoneNumberCreateResponse.convert(phoneNumber);
    }

    @GetMapping("getById")
    public PhoneNumberCreateResponse getPhoneNumberById(@RequestParam String uuid) {
        PhoneNumber phoneNumber = phoneNumberService.getPhoneNumberById(uuid);
        return PhoneNumberCreateResponse.convert(phoneNumber);
    }

    @GetMapping("getAll")
    public List<PhoneNumberCreateResponse> getAllPhoneNumbers() {
        return PhoneNumberCreateResponse.convert(
                phoneNumberService.getAllPhoneNumbers()
        );
    }
    @PutMapping("update")
    public PhoneNumberUpdateResponse updatePhoneNumber(@RequestBody PhoneNumberUpdateRequest updateObj) {
        PhoneNumber phoneNumber =
                phoneNumberService.updatePhoneNumber(updateObj);
        return PhoneNumberUpdateResponse.convert(phoneNumber);
    }

    @DeleteMapping("deleteById")
    public Boolean deletePhoneNumberById(@RequestParam String id) {
        return phoneNumberService.deleteById(id);
    }
}