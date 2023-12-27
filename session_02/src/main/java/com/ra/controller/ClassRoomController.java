package com.ra.controller;

import com.ra.model.entity.ClassRoom;
import com.ra.model.service.classRoom.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("/class-room")
    public String classRoom(Model model) {
        List<ClassRoom> classRooms = classRoomService.findAll();
        model.addAttribute("classRooms", classRooms);
        return "/classRoom/class-room";
    }

    @GetMapping("/create-class")
    public String createClassRoom(Model model) {
        ClassRoom classRoom = new ClassRoom();
        model.addAttribute("classRoom", classRoom);
        return "/classRoom/create-class";
    }

    @PostMapping("/create-class")
    public String handleCreateClassRoom(@ModelAttribute("classRoom") ClassRoom classRoom) {
        if (classRoomService.sevaOrUpdate(classRoom)) {
            return "redirect:/class-room";
        }
        return "/classRoom/create-class";
    }

}
