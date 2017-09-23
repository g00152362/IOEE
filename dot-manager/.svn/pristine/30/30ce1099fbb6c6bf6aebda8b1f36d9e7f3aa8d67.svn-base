package com.dot.mapper;

import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.TbGatewayInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGatewayInfoMapper {
    int countByExample(TbGatewayInfoExample example);

    int deleteByExample(TbGatewayInfoExample example);

    int deleteByPrimaryKey(String serialNumber);

    int insert(TbGatewayInfo record);

    int insertSelective(TbGatewayInfo record);

    List<TbGatewayInfo> selectByExample(TbGatewayInfoExample example);

    TbGatewayInfo selectByPrimaryKey(String serialNumber);

    int updateByExampleSelective(@Param("record") TbGatewayInfo record, @Param("example") TbGatewayInfoExample example);

    int updateByExample(@Param("record") TbGatewayInfo record, @Param("example") TbGatewayInfoExample example);

    int updateByPrimaryKeySelective(TbGatewayInfo record);

    int updateByPrimaryKey(TbGatewayInfo record);
}