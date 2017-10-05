package com.dot.mapper;

import com.dot.pojo.TbLoginUser;
import com.dot.pojo.TbLoginUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLoginUserMapper {
    int countByExample(TbLoginUserExample example);

    int deleteByExample(TbLoginUserExample example);

    int deleteByPrimaryKey(String username);

    int insert(TbLoginUser record);

    int insertSelective(TbLoginUser record);

    List<TbLoginUser> selectByExample(TbLoginUserExample example);

    TbLoginUser selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") TbLoginUser record, @Param("example") TbLoginUserExample example);

    int updateByExample(@Param("record") TbLoginUser record, @Param("example") TbLoginUserExample example);

    int updateByPrimaryKeySelective(TbLoginUser record);

    int updateByPrimaryKey(TbLoginUser record);
}