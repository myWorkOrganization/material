package com.material.service;

import com.material.entity.dictionary.Dept;
import com.material.mapper.DictionaryMapper;
import com.material.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public List<Dept> getDeptList(){

        return dictionaryMapper.listDept();
    }
}
