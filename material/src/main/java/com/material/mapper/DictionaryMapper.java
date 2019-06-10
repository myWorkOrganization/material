package com.material.mapper;

import com.material.entity.dictionary.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    /**
     * 获取部门列表
     * @return
     */
    List<Dept> listDept();
}
