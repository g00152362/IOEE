package com.dot.mapper;

import com.dot.pojo.TbUserSetting;
import com.dot.pojo.TbUserSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserSettingMapper {
    int countByExample(TbUserSettingExample example);

    int deleteByExample(TbUserSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUserSetting record);

    int insertSelective(TbUserSetting record);

    List<TbUserSetting> selectByExample(TbUserSettingExample example);

    TbUserSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserSetting record, @Param("example") TbUserSettingExample example);

    int updateByExample(@Param("record") TbUserSetting record, @Param("example") TbUserSettingExample example);

    int updateByPrimaryKeySelective(TbUserSetting record);

    int updateByPrimaryKey(TbUserSetting record);
}