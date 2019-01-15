package com.material.mapper;

import com.material.entity.menu.Tree;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface MenuMapper {
    List<Tree> listMenu();
}