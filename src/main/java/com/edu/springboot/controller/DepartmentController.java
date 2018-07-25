package com.edu.springboot.controller;

import com.edu.springboot.entity.Department;
import com.edu.springboot.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "department/index";
    }

    @RequestMapping(value="/{id}")
    public String show(ModelMap model, @PathVariable Long id) {
        Department department = departmentRepository.findById(id).get();
        model.addAttribute("department",department);
        return "department/show";
    }

    @RequestMapping("/new")
    public String create(){
        return "department/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Department department) throws Exception{
        departmentRepository.save(department);
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public String update(ModelMap model, @PathVariable Long id){
        Department department = departmentRepository.findById(id).get();
        model.addAttribute("department",department);
        return "department/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(Department department) throws Exception{
        departmentRepository.save(department);
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        departmentRepository.deleteById(id);
        return "1";
    }

}
