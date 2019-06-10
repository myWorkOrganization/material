package com.material.entity.material;

import com.material.entity.page.PageHelper;
import java.util.List;

public class MaterialRequest extends PageHelper {
    private String materialName;
    private String productDateBegin;
    private String productDateEnd;
    private String validdateBegin;
    private String validdateEnd;
    private List<Long> materialIdList;
    private int hadExpired = -1;
    private String deptId;
    private String belongDeptId;

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getProductDateBegin() {
        return this.productDateBegin;
    }

    public void setProductDateBegin(String productDateBegin) {
        this.productDateBegin = productDateBegin;
    }

    public String getProductDateEnd() {
        return this.productDateEnd;
    }

    public void setProductDateEnd(String productDateEnd) {
        this.productDateEnd = productDateEnd;
    }

    public String getValiddateBegin() {
        return this.validdateBegin;
    }

    public void setValiddateBegin(String validdateBegin) {
        this.validdateBegin = validdateBegin;
    }

    public String getValiddateEnd() {
        return this.validdateEnd;
    }

    public void setValiddateEnd(String validdateEnd) {
        this.validdateEnd = validdateEnd;
    }

    public List<Long> getMaterialIdList() {
        return this.materialIdList;
    }

    public void setMaterialIdList(List<Long> materialIdList) {
        this.materialIdList = materialIdList;
    }

    public int getHadExpired() {
        return this.hadExpired;
    }

    public void setHadExpired(int hadExpired) {
        this.hadExpired = hadExpired;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(String belongDeptId) {
        this.belongDeptId = belongDeptId;
    }
}