package com.material.mapper;

import com.material.entity.material.Material;
import com.material.entity.material.MaterialAddRequest;
import com.material.entity.material.MaterialEditRequest;
import com.material.entity.material.MaterialRequest;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialMapper {
    List<Material> listMaterial(MaterialRequest paramMaterialRequest);

    int listMaterialCount(MaterialRequest paramMaterialRequest);

    void insertMaterial(MaterialAddRequest paramMaterialAddRequest);

    void updateMaterial(MaterialEditRequest paramMaterialEditRequest);

    Material selectMaterialById(long paramLong);

    void deleteMaterial(List<Long> paramList);

    void insertBatchMaterial(List<Map<String, Object>> paramList);
}