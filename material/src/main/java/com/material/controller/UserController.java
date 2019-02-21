package com.material.controller;

import com.material.entity.user.Login;
import com.material.entity.user.Register;
import com.material.service.UserService;
import com.material.utils.Result;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value={"/user"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/register"})
    public Result register(@RequestBody @Valid Register register){
        return this.userService.saveUser(register);
    }

    @RequestMapping({"/login"})
    public Result login(@RequestBody @Valid Login login){
        return this.userService.login(login);
    }
}