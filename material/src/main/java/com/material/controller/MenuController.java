package com.material.controller;

import com.material.service.MenuService;
import com.material.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/menu"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping({"/tree"})
    public Result tree(){
        return this.menuService.tree();
    }
}