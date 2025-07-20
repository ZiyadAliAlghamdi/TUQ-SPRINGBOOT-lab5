package org.example.studentsystem.Controller;

import org.example.studentsystem.Api.Handler;
import org.example.studentsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/studentSystem")
public class StudentController {
    ArrayList<Student> studentArrayList = new ArrayList<>();

    @GetMapping("/students")
    public ArrayList<Student> getAllStudents(){
        if (studentArrayList.isEmpty()){
            System.out.println("LIST IS EMPTY");
        }
        return studentArrayList;
    }


    @GetMapping("/students/{index}")
    public Student getSingleStudent(@PathVariable int index){
        return studentArrayList.get(index);
    }

    @PostMapping("/students/post")
    public Handler addStudent(@RequestBody Student student){
        for (Student studentObj: studentArrayList){
            if (student.getId() ==studentObj.getId()){
                return new Handler("Exception: duplicated id");
            }
        }

        studentArrayList.add(student);
        return new Handler("added successfully");  //return a JSON message
    }



    @PutMapping("/students/{index}/put")
    public Handler updateStudent(@PathVariable int index, @RequestBody Student student){
        studentArrayList.set(index, student);
        return new Handler("updated successfully");  //return a JSON message
    }

    @DeleteMapping("/students/{index}/delete")
    public Handler deleteStudent(@PathVariable int index){
        studentArrayList.remove(index);
        return new Handler("deleted successfully");
    }


    @GetMapping("/students/{index}/honor")
    public String Honor(@PathVariable int index){
        System.out.println("you called honor endpoint");
        double gpa = studentArrayList.get(index).getGPA();
        if (gpa >= 3.75) {
            return "first honor";
        } else if (gpa >= 3.25) {
            return "second honor";
        } else if (gpa >= 2.75) {
            return "third honor";
        } else {
            return "you are not in the honors list";
        }
    }


    @GetMapping("/students/greaterThanAvg")
    public ArrayList<Student> greaterThanAvg(){
        ArrayList<Student> greaterThanAvgArray = new ArrayList<>();

        double sum = 0;
        for (Student student : studentArrayList){
            sum += student.getGPA();
        }

        double avg = sum/studentArrayList.size();

        for(Student student: studentArrayList){
            if (student.getGPA()>avg){
                greaterThanAvgArray.add(student);
            }
        }
        return greaterThanAvgArray;
    }




}
