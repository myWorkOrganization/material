package com.material.service;

import com.material.entity.material.MaterialAddRequest;
import com.material.entity.material.MaterialDeleteRequest;
import com.material.entity.material.MaterialEditRequest;
import com.material.entity.material.MaterialRequest;
import com.material.entity.page.DataGrid;
import com.material.entity.page.PageHelper;
import com.material.mapper.MaterialMapper;
import com.material.utils.ExcelUtils;
import com.material.utils.Result;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    public DataGrid dataGrid(MaterialRequest materialRequest, PageHelper pageHelper ,HttpServletRequest httpServletRequest){
        DataGrid dataGrid = new DataGrid();
        HttpSession session=httpServletRequest.getSession();
        String belongDeptId=session.getAttribute("deptId")+"";
        if(!"admin".equals(belongDeptId)){
            materialRequest.setBelongDeptId(belongDeptId==null?"":belongDeptId);
        }
        dataGrid.setRows(this.materialMapper.listMaterial(materialRequest));
        dataGrid.setTotal(this.materialMapper.listMaterialCount(materialRequest));
        return dataGrid;
    }

    public Result materialAdd(MaterialAddRequest materialAddRequest,HttpServletRequest httpServletRequest) {
        HttpSession session=httpServletRequest.getSession();
        materialAddRequest.setDeptId(session.getAttribute("deptId")+"");
        this.materialMapper.insertMaterial(materialAddRequest);
        return new Result("success", "新增材料成功");
    }
    public Result materialEdit(MaterialEditRequest materialEditRequest) {
        this.materialMapper.updateMaterial(materialEditRequest);
        return new Result("success", "修改材料成功");
    }
    public Result materialDelete(MaterialDeleteRequest materialDeleteRequest) {
        if ((materialDeleteRequest == null) || (materialDeleteRequest.getMaterialIds() == null) || (materialDeleteRequest.getMaterialIds().isEmpty())) {
            return new Result("fail", "参数为空");
        }
        this.materialMapper.deleteMaterial(materialDeleteRequest.getMaterialIds());
        return new Result("success", "删除材料成功");
    }
    public Result materialImportFile(MultipartFile multipartFile,HttpServletRequest httpServletRequest) {
        if (multipartFile == null || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            return new Result("fail", "请选择材料文件上传");
        }
        String originalFileName = multipartFile.getOriginalFilename();
        if ((!originalFileName.endsWith("xls")) && (!originalFileName.endsWith("xlsx"))) {
            return new Result("fail", "只能上传.xls和.xlsx文件");
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            if (inputStream == null) {
                return new Result("fail", "请选择材料文件上传");
            }
            String filePath = "D:\\materialFile" + File.separator + LocalDate.now().toString() + File.separator + UUID.randomUUID();
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            FileCopyUtils.copy(inputStream, new FileOutputStream(filePath + File.separator + new String(originalFileName.getBytes(), "UTF-8")));
            String[] columnArr = { "materialName", "materialNums", "materialManufacturers", "materialBatchNumber", "productDate", "validdate" };
            List<Map<String,Object>> materialList = ExcelUtils.readExcelByPath(filePath + File.separator + new String(originalFileName.getBytes(), "UTF-8"), columnArr);
            if ((materialList != null) && (!materialList.isEmpty())) {
                HttpSession session=httpServletRequest.getSession();
                Object deptId=session.getAttribute("deptId");
                for(Map<String,Object> map:materialList){
                    map.put("deptId",deptId);
                }
                this.materialMapper.insertBatchMaterial(materialList);
            }else{
                new Result("fail", "材料文件为空");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Result("success", "材料文件上传成功");
    }
}