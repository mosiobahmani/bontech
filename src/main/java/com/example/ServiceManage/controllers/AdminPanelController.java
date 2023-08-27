package com.example.ServiceManage.controllers;

import com.example.ServiceManage.commons.BaseCriteria;
import com.example.ServiceManage.commons.BaseDto;
import com.example.ServiceManage.commons.SmartResponse;
import com.example.ServiceManage.dtos.UserServicesInput;
import com.example.ServiceManage.dtos.UserServicesOutput;
import com.example.ServiceManage.services.UserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AdminPanelController {


    @Autowired
    UserServiceService<UserServicesInput, UserServicesOutput> userServiceService;


    @GetMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<SmartResponse> show(
            HttpServletRequest request,
            @ModelAttribute BaseCriteria baseCriteria) throws Exception {

        return new ResponseEntity<>(userServiceService.findByCriteria(baseCriteria), HttpStatus.OK);
    }


    @PostMapping(value = "assignUserServices")
    public ResponseEntity<BaseDto> AssignUserToService(@RequestBody UserServicesInput input) throws Exception {
        userServiceService.save(input);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<BaseDto> update(@RequestBody UserServicesInput input) throws Exception {
        userServiceService.save(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<BaseDto> getOne(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(userServiceService.findOne(id), HttpStatus.OK);
    }


}
