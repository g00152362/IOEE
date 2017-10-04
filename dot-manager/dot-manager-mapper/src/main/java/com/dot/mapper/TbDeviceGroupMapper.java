package com.dot.mapper;

import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbDeviceGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDeviceGroupMapper {
    int countByExample(TbDeviceGroupExample example);

    int deleteByExample(TbDeviceGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDeviceGroup record);

    int insertSelective(TbDeviceGroup record);

    List<TbDeviceGroup> selectByExample(TbDeviceGroupExample example);

    TbDeviceGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDeviceGroup record, @Param("example") TbDeviceGroupExample example);

    int updateByExample(@Param("record") TbDeviceGroup record, @Param("example") TbDeviceGroupExample example);

    int updateByPrimaryKeySelective(TbDeviceGroup record);

    int updateByPrimaryKey(TbDeviceGroup record);
    
    List<String> selectAllGroupName();
}