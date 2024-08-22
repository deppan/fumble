package com.tsinsi.user.adapter.out.mapper.reader;

import com.tsinsi.user.configuration.JsonTypeHandler;
import com.tsinsi.user.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryReaderMapper {

    @Results({
            @Result(property = "property", column = "property", typeHandler = JsonTypeHandler.class)
    })
    @Select("select id, property from tb_inventories where uid = #{uid}")
    List<Inventory> find(long uid);

}
