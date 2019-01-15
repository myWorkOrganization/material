package com.material.entity.material;

import java.util.List;

public class MaterialDeleteRequest {
    private List<Long> materialIds;

    public List<Long> getMaterialIds()
    {
        return this.materialIds;
    }

    public void setMaterialIds(List<Long> materialIds) {
        this.materialIds = materialIds;
    }
}