package com.material.entity.material;

public class Material {
    private long materialId;
    private String materialName;
    private String productDate;
    private String validdate;
    private String materialNums;
    private String materialManufacturers;
    private String materialBatchNumber;
    private String hadExpired;
    private String createTime;
    private String lastUpdateTime;

    public long getMaterialId()
    {
        return this.materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getProductDate() {
        return this.productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValiddate() {
        return this.validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getMaterialNums() {
        return this.materialNums;
    }

    public void setMaterialNums(String materialNums) {
        this.materialNums = materialNums;
    }

    public String getMaterialManufacturers() {
        return this.materialManufacturers;
    }

    public void setMaterialManufacturers(String materialManufacturers) {
        this.materialManufacturers = materialManufacturers;
    }

    public String getMaterialBatchNumber() {
        return this.materialBatchNumber;
    }

    public void setMaterialBatchNumber(String materialBatchNumber) {
        this.materialBatchNumber = materialBatchNumber;
    }

    public String getHadExpired() {
        return this.hadExpired;
    }

    public void setHadExpired(String hadExpired) {
        this.hadExpired = hadExpired;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}