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
@RequestMapping("/api/v1/park")
public class themeParksManagement {
    private ArrayList<Park>ridelist=new ArrayList();

@GetMapping
    public ResponseEntity getRides(){
    return ResponseEntity.status(200).body(ridelist);
}
@PostMapping
    public ResponseEntity addRide(@RequestBody @Valid Park ride, Errors error){
    if(error.hasFieldErrors()){
        String err_msg=error.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
    }
    ridelist.add(ride);
    return ResponseEntity.status(201).body(new ResponseApi("Ride added successfully",201));
}
@PutMapping("{id}")
    public ResponseEntity updateRide(@RequestBody @Valid Park ride,@PathVariable String id, Errors error){
    if(error.hasFieldErrors()){
        String err_msg=error.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
    }
    Integer ride_idx=getIndexFromId(id);
    if(ride_idx==null){
        return ResponseEntity.status(400).body(new ResponseApi("Ride not found",400));
    }


    ridelist.set(ride_idx,ride);
    return ResponseEntity.status(201).body(new ResponseApi("Ride updated successfully",201));
}
    @DeleteMapping("{id}")
    public ResponseEntity deleteRide(@RequestBody @Valid Park ride, @PathVariable String id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Integer ride_idx=getIndexFromId(id);
        if(ride_idx==null){
            return ResponseEntity.status(400).body(new ResponseApi("Index not found",400));
        }
        ridelist.remove((int)ride_idx);
        return ResponseEntity.status(201).body(new ResponseApi("Ride deleted successfully",201));
    }
    @PutMapping("buy/{rideid}/{amount}")
    public ResponseEntity buy(@PathVariable String rideid,@PathVariable Integer amount){
        Park curr_ride=ridelist.get((int)getIndexFromId(rideid));
        System.out.println(curr_ride);
        if(curr_ride==null){
            return ResponseEntity.status(400).body(new ResponseApi("Ride not found",400));
        }
        if(curr_ride.getTicket()<=0){
            return ResponseEntity.status(400).body(new ResponseApi("Ride tickets not available",400));

        }

        if(amount<curr_ride.getPrice()){
            return ResponseEntity.status(400).body(new ResponseApi("Amount entered is less than ride price",400));

        }
        Integer n_tickets=curr_ride.getTicket();
        if(n_tickets-1<=0){
            curr_ride.setTicket(0);
        }else {
            curr_ride.setTicket(curr_ride.getTicket() - 1);
        }


    return ResponseEntity.status(201).body(new ResponseApi("Ticket purchased",201));
    }
    @PutMapping("refill/{rideid}/{refillamount}")
    public ResponseEntity refill(@PathVariable String rideid,@PathVariable Integer refillamount){
        Park curr_ride=ridelist.get(getIndexFromId(rideid));
        if(curr_ride==null){
            return ResponseEntity.status(400).body(new ResponseApi("Ride not found",400));
        }
        if(curr_ride.getTicket()==0){
            curr_ride.setTicket(refillamount);
            return ResponseEntity.status(201).body(new ResponseApi("Ride refilled",201));

        }



        return ResponseEntity.status(400).body(new ResponseApi("There are enough tickets",400));
    }

    public Integer getIndexFromId(String id){

        for (int i = 0; i <ridelist.size(); i++) {
            if(ridelist.get(i).getRideId().equals(id)){
                return i;
            }
        }
        return null;
    }
}
