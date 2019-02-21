package com.material.controller;

import com.material.entity.material.MaterialAddRequest;
import com.material.entity.material.MaterialDeleteRequest;
import com.material.entity.material.MaterialEditRequest;
import com.material.entity.material.MaterialRequest;
import com.material.entity.page.DataGrid;
import com.material.entity.page.PageHelper;
import com.material.mapper.MaterialMapper;
import com.material.service.MaterialService;
import com.material.utils.Result;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/materialmanagement"})
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialMapper materialMapper;

    @RequestMapping({"/materialmanage"})
    public String materialManage() {
        return "/views/material/materialmanage";
    }
    @RequestMapping({"/addPage"})
    public String addPage() { return "/views/material/materialAdd"; }
    @RequestMapping({"/editPage"})
    public String editPage(HttpServletRequest request, @Param("materialId") long materialId) {
        request.setAttribute("material", this.materialMapper.selectMaterialById(materialId));
        return "/views/material/materialEdit";
    }
    @RequestMapping({"/materialImport"})
    public String materialImport() {
        return "/views/material/materialImport";
    }
    @RequestMapping({"/datagrid"})
    @ResponseBody
    public DataGrid dataGrid(MaterialRequest materialRequest, PageHelper pageHelper) {
        return this.materialService.dataGrid(materialRequest, pageHelper);
    }
    @RequestMapping({"/materialAdd"})
    @ResponseBody
    public Result materialAdd(MaterialAddRequest materialAddRequest) {
        return this.materialService.materialAdd(materialAddRequest);
    }
    @RequestMapping({"/materialEdit"})
    @ResponseBody
    public Result materialEdit(MaterialEditRequest materialEditRequest) {
        return this.materialService.materialEdit(materialEditRequest);
    }
    @RequestMapping({"/materialDelete"})
    @ResponseBody
    public Result materialDelete(@RequestBody MaterialDeleteRequest materialDeleteRequest) {
        return this.materialService.materialDelete(materialDeleteRequest);
    }
    @RequestMapping({"/materialImportFile"})
    @ResponseBody
    public Result materialImportFile(@RequestParam("materialFile") MultipartFile multipartFile) {
        return this.materialService.materialImportFile(multipartFile);
    }
}