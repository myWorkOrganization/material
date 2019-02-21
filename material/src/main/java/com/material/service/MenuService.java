package com.material.service;

import com.material.entity.menu.Tree;
import com.material.mapper.MenuMapper;
import com.material.utils.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public Result tree() {
        List treeList = this.menuMapper.listMenu();
        if ((treeList == null) || (treeList.isEmpty())) {
            return new Result("success", "", "");
        }
        List resultList = new ArrayList();
        List<Tree> parentMenuList=getParentMenu(treeList);
        if(parentMenuList!=null&&parentMenuList.size()>0){
            for(Tree t:parentMenuList){
                Tree tree = getTree(treeList, t);
                resultList.add(tree);
            }
        }
        return new Result("success", "获取菜单成功", resultList);
    }

    /**
     * 获取所有的父级菜单
     * @param treeList
     * @return
     */
    private List<Tree> getParentMenu(List<Tree> treeList){
        List<Tree> resultList=new ArrayList<>();
        if(treeList!=null&&treeList.size()>0){
            resultList=treeList.stream().filter(t->t.getPid()==0).collect(Collectors.toList());
        }
        return resultList;
    }

    /**
     * 获取子级菜单
     * @param treeList
     * @param p
     * @return
     */
    private Tree getTree(List<Tree> treeList, Tree p) {
        List childList = new ArrayList();
        if ((treeList != null) && (!treeList.isEmpty())) {
            for (Tree t : treeList) {
                if (p.getId() == t.getPid()) {
                    Map map = new HashMap();
                    map.put("url", t.getUrl());
                    t.setAttributes(map);
                    childList.add(t);
                }
            }
            p.setChildren(childList);
        }
        return p;
    }
}