package org.example.projecttrackersystem.Controller;

import org.example.projecttrackersystem.Api.Handler;
import org.example.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/projectTracker")
public class Controller {
    ArrayList<Project> projectArrayList = new ArrayList<>();


    @GetMapping("/project")
    public ArrayList<Project> getAllProject(){
        if (projectArrayList.isEmpty()){
            System.out.println("array is empty");
        }
        return projectArrayList;
    }

    @GetMapping("/project/{index}")
    public Project getSingleProject(@PathVariable int index){
        return projectArrayList.get(index);
    }

    @PostMapping("/project/post")
    public Handler addProject(@RequestBody Project project){
        projectArrayList.add(project);
        return new Handler("project added successfully");
    }
    @PutMapping("/project/{index}/update")
    public Handler updateProject(@PathVariable int index ,@RequestBody Project project){
        projectArrayList.set(index, project);
        return new Handler("project updated successfully");
    }


    @DeleteMapping("/project/{index}/delete")
    public Handler deleteProject(@PathVariable int index){
        projectArrayList.remove(index);
        return new Handler("project deleted successfully");
    }

    @PutMapping("/project/{index}/changeStatus")
    public Handler changeStatus(@PathVariable int index){
        projectArrayList.get(index).setStatus(!projectArrayList.get(index).getStatus());
        if (projectArrayList.get(index).getStatus()){
            return new Handler("status: true");
        }else{
            return new Handler("status: false");
        }
    }

    @GetMapping("/project/search")
    public Project search(@RequestParam(value = "title") String title){
        for (Project project : projectArrayList){
            if(project.getTitle().contains(title)){
                return project;
            }
        }
        return null;
    }

    @GetMapping("/project/companyProjects/{companyName}")
    public ArrayList<Project> companyProjects(@PathVariable String companyName){
        ArrayList<Project> companyProjects = new ArrayList<>();
        for(Project project : projectArrayList){
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                companyProjects.add(project);
            }
        }
        return companyProjects;
    }
}
