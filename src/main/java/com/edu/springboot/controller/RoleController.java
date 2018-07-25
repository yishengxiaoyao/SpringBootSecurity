package com.edu.springboot.controller;


import com.edu.springboot.entity.Role;
import com.edu.springboot.model.RoleQo;
import com.edu.springboot.repository.RoleRepository;
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
@RequestMapping("/role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "role/index";
    }

    @RequestMapping(value="/{id}")
    public String show(ModelMap model, @PathVariable Long id) {
        Role role = roleRepository.findById(id).get();
        model.addAttribute("role",role);
        return "role/show";
    }

    @RequestMapping("/new")
    public String create(){
        return "role/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Role role) throws Exception{
        roleRepository.save(role);
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public String update(ModelMap model, @PathVariable Long id){
        Role role = roleRepository.findById(id).get();
        model.addAttribute("role",role);
        return "role/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(Role role) throws Exception{
        roleRepository.save(role);
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        roleRepository.deleteById(id);
        return "1";
    }

}
