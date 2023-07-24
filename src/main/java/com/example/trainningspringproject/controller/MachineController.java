package com.example.trainningspringproject.controller;

import com.example.trainningspringproject.entity.Machine;
import com.example.trainningspringproject.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MachineController {

    @Autowired
    private MachineService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Machine> listOfMachines = service.listAll();
        model.addAttribute("listOfMachines", listOfMachines);
        System.out.print("Get / ");
        return "index";
    }

    @RequestMapping("/tenant/{id}/machine/device")
    public String deleteMachine(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("Machine", new Machine());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMachine(@ModelAttribute("Machine") Machine std) {
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditMachinePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Machine std = service.get(id);
        mav.addObject("Machine", std);
        return mav;

    }
}
