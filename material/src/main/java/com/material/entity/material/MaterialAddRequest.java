package com.material.entity.material;

public class MaterialAddRequest {
    private String materialName;
    private String productDate;
    private String validdate;
    private String materialNums;
    private String materialManufacturers;
    private String materialBatchNumber;

    public String getMaterialName()
    {
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
}