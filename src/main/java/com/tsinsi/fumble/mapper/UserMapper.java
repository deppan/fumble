package com.tsinsi.fumble.mapper;

import com.tsinsi.fumble.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    User findById(Long id);

    @Results({
            @Result(property = "id.value", column = "id")
    })
    @Select("select id, name from tb_user where id = #{id}")
    User findBy(Long id);
}
