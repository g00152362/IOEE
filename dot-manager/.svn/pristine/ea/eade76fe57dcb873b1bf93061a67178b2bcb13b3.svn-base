package com.dot.mapper;

import com.dot.pojo.TbSensorCat;
import com.dot.pojo.TbSensorCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSensorCatMapper {
    int countByExample(TbSensorCatExample example);

    int deleteByExample(TbSensorCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSensorCat record);

    int insertSelective(TbSensorCat record);

    List<TbSensorCat> selectByExample(TbSensorCatExample example);

    TbSensorCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSensorCat record, @Param("example") TbSensorCatExample example);

    int updateByExample(@Param("record") TbSensorCat record, @Param("example") TbSensorCatExample example);

    int updateByPrimaryKeySelective(TbSensorCat record);

    int updateByPrimaryKey(TbSensorCat record);
}