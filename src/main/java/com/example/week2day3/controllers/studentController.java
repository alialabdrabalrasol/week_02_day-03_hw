package com.example.week2day3.controllers;

import com.example.week2day3.model.ResponseApi;
import com.example.week2day3.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.ServletSecurity;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class studentController {
    private ArrayList<Student> studentlist=new ArrayList<>();
    @GetMapping
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentlist);
    }
    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid Student student , Errors error){
        if(error.hasFieldErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(new ResponseApi(message,400));
        }

        studentlist.add(student);
        return ResponseEntity.status(201).body(new ResponseApi("Student added successfully",201));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable @Valid Integer id,@RequestBody Student student, Errors error){
        if(error.hasFieldErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(new ResponseApi(message,400));
        }
        //search by id
        Integer student_index=getStudentbyID(id);
        if(student_index==null){
            return ResponseEntity.status(400).body(new ResponseApi("Student not found",400));
        }

        studentlist.set(student_index,student);

     return ResponseEntity.status(201).body(new ResponseApi("Student updated",201)) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable @Valid Student student, Errors error){
        if(error.hasFieldErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(new ResponseApi(message,400));
        }
        Integer student_index=getStudentbyID(student.getId());

        if(student_index==null){
            return ResponseEntity.status(400).body(new ResponseApi("Student not found",400));
        }
        studentlist.remove((int)student_index);

        return ResponseEntity.status(201).body(new ResponseApi("Student updated",201)) ;
    }

    public Integer getStudentbyID(int id){

        for (int i = 0; i <studentlist.size() ; i++) {
            if(studentlist.get(i).getId()==id){
                return i;
            }
        }
        return null;
    }
}
