package com.example.week2day3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {
    @NotNull(message = "Id cannot be null")
    @Size(min = 3,message = "Invalid id length min is" )
    private String id;
    @NotNull(message = "name cannot be null")
    @Size(min = 4,message = "Invalid name length min is 4")
    private String name;
    @NotNull(message = "age cannot be null")
    @Min(value = 25,message = "Age cannot be less than 25")
    private Integer age;
    @AssertFalse(message = "onLeave cannot be true")
    private boolean onLeave;
    @NotNull(message = "employmentyear cannot be null")
    @Min(value = 2000,message = "employementyear is not valid")
    private Integer employmentYear;
   @NotNull(message = "annualleave cannot be null")
   private Integer annualLeave;

}
