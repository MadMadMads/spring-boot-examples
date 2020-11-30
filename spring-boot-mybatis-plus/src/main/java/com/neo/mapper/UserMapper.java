package com.neo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.model.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapper extends BaseMapper<User> {

    @Select("Select user.name,other.id from user left join other on user.id = other.id where user.id = #{id}")
    Object getRelatedSelect(long id);
}