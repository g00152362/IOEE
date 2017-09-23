package com.dot.mapper;

import com.dot.pojo.TbDeviceModel;
import com.dot.pojo.TbDeviceModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDeviceModelMapper {
    int countByExample(TbDeviceModelExample example);

    int deleteByExample(TbDeviceModelExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbDeviceModel record);

    int insertSelective(TbDeviceModel record);

    List<TbDeviceModel> selectByExample(TbDeviceModelExample example);

    TbDeviceModel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbDeviceModel record, @Param("example") TbDeviceModelExample example);

    int updateByExample(@Param("record") TbDeviceModel record, @Param("example") TbDeviceModelExample example);

    int updateByPrimaryKeySelective(TbDeviceModel record);

    int updateByPrimaryKey(TbDeviceModel record);
}