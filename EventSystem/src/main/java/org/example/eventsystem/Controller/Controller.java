package org.example.eventsystem.Controller;

import org.example.eventsystem.Api.Handler;
import org.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/eventSystem")
public class Controller {
    ArrayList<Event> eventArrayList = new ArrayList<>();

    @GetMapping("/event")
    public ArrayList<Event> getAllEvents(){
        return eventArrayList;
    }

    @GetMapping("/event/{index}")
    public Event getSingleEvent(@PathVariable int index){
        return eventArrayList.get(index);
    }

    @PostMapping("/event/post")
    public Handler addEvent(@RequestBody Event event){
        eventArrayList.add(event);
        return new Handler("ADDED");
    }

    @PutMapping("/event/{index}/update")
    public Handler updateEvent(@PathVariable int index, @RequestBody Event event){
        eventArrayList.set(index,event);
        return new Handler("UPDATED");
    }

    @DeleteMapping("/event/{index}/delete")
    public Handler deleteEvent(@PathVariable int index){
        eventArrayList.remove(index);
        return new Handler("DELETED");
    }

    @PutMapping("/event/{index}/changeCapacity")
    public Handler changeCapacity(@PathVariable int index, @RequestParam int newCapacity){
        eventArrayList.get(index).setCapacity(newCapacity);
        return new Handler("CAPACITY CHANGED");
    }
}
