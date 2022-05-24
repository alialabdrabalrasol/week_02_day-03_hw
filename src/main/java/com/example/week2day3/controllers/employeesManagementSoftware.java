package com.example.week2day3.controllers;

import com.example.week2day3.model.Employee;
import com.example.week2day3.model.Park;
import com.example.week2day3.model.ResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class employeesManagementSoftware {
    private ArrayList<Employee> employees=new ArrayList();

    @GetMapping
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(200).body(employees);
    }
    @PostMapping
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }

        employees.add(employee);
        return ResponseEntity.status(201).body(new ResponseApi("Employee added successfully",201));
    }
    @PutMapping("{id}")
    public ResponseEntity updateEmployee(@RequestBody @Valid  Employee employee,@PathVariable String id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Integer employee_idx=getIndexFromId(id);
        if(employee_idx==null){
            return ResponseEntity.status(400).body(new ResponseApi("Index not found",400));
        }
        employees.set(employee_idx,employee);

        return ResponseEntity.status(201).body(new ResponseApi("Employee updated successfully",201));
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@RequestBody @Valid  Employee employee,@PathVariable String id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Integer employee_idx=getIndexFromId(id);
        if(employee_idx==null){
            return ResponseEntity.status(400).body(new ResponseApi("Index not found",400));
        }
        employees.remove((int)employee_idx);
        return ResponseEntity.status(201).body(new ResponseApi("Employee deleted successfully",201));
    }
    @PutMapping("apply/{id}")
    public ResponseEntity applyForAnnualLeave(@RequestBody @Valid  Employee employee,@PathVariable String id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Integer employee_idx=getIndexFromId(id);
        if(employee_idx==null){
            return ResponseEntity.status(400).body(new ResponseApi("Index not found",400));
        }

        Employee curr_emp=employees.get(employee_idx);

        if(curr_emp.getAnnualLeave()<1){
            return ResponseEntity.status(400).body(new ResponseApi("no annualleave available",400));

        }

        curr_emp.setAnnualLeave(curr_emp.getAnnualLeave()-1);
        curr_emp.setOnLeave(true);
        return ResponseEntity.status(201).body(new ResponseApi("annual leave updated",201));

    }
    public Integer getIndexFromId(String id){

        for (int i = 0; i <employees.size(); i++) {
            if(employees.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

}
