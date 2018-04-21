package com.dot.mapper;

import com.dot.pojo.TbSensorData;
import com.dot.pojo.TbSensorDataExample;
import com.dot.pojo.TbSensorDataKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSensorDataMapper {
    int countByExample(TbSensorDataExample example);

    int deleteByExample(TbSensorDataExample example);

    int deleteByPrimaryKey(TbSensorDataKey key);

    int insert(TbSensorData record);

    int insertSelective(TbSensorData record);

    List<TbSensorData> selectByExample(TbSensorDataExample example);

    TbSensorData selectByPrimaryKey(TbSensorDataKey key);

    int updateByExampleSelective(@Param("record") TbSensorData record, @Param("example") TbSensorDataExample example);

    int updateByExample(@Param("record") TbSensorData record, @Param("example") TbSensorDataExample example);

    int updateByPrimaryKeySelective(TbSensorData record);

    int updateByPrimaryKey(TbSensorData record);
    
    List<TbSensorData> list(Long start);
}