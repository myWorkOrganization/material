package com.material.controller;

import com.material.entity.dictionary.Dept;
import com.material.service.DictionaryService;
import com.material.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/listDept")
    public List<Dept> getDeptList(){
        return dictionaryService.getDeptList();
    }
}
