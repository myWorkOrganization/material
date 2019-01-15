package com.material.service;

import com.material.entity.menu.Tree;
import com.material.mapper.MenuMapper;
import com.material.utils.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public Result tree()
    {
        List treeList = this.menuMapper.listMenu();
        if ((treeList == null) || (treeList.isEmpty())) {
            return new Result("success", "", "");
        }
        Tree t = getTree(treeList, (Tree)treeList.get(0));
        List resultList = new ArrayList();
        resultList.add(t);
        return new Result("success", "获取菜单成功", resultList);
    }

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