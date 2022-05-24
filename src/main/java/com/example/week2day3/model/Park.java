package com.example.week2day3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Park {
    @NotNull(message = "Ride id cannot be null")
   @Size(min = 2,message = "Minimum length is 2")
    private String rideId;
    @NotNull(message = "Ride name cannot be null")
    @Size(min = 4,message = "Ride name should at least have 4 characters")
    private  String rideName;
    @NotNull(message = "Ride type cannot be null")
    @Pattern(regexp = "\\b(?:rollercoaster|thriller|water)\\b",message = "You can only choose rollercoaster or thriller or water")
    private String rideType;
    @NotNull(message = "Tikets cannot be null")
    //@Pattern(regexp = ".*[0-9].*",message = "Ticket should only have numbers")
    @Min(value = 0,message = "Invalid number of tickets")
    private Integer ticket;
    @NotNull(message = "Price cannot be null")
    //@Pattern(regexp = "^[1-9]*$",message = "Price should only have numbers")
    @Min(value = 1,message = "Price should at least be 1")
    private Integer price;

}
