package com.dot.mapper;

import com.dot.pojo.TbNode;
import com.dot.pojo.TbNodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbNodeMapper {
    int countByExample(TbNodeExample example);

    int deleteByExample(TbNodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbNode record);

    int insertSelective(TbNode record);

    List<TbNode> selectByExampleWithBLOBs(TbNodeExample example);

    List<TbNode> selectByExample(TbNodeExample example);

    TbNode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbNode record, @Param("example") TbNodeExample example);

    int updateByExampleWithBLOBs(@Param("record") TbNode record, @Param("example") TbNodeExample example);

    int updateByExample(@Param("record") TbNode record, @Param("example") TbNodeExample example);

    int updateByPrimaryKeySelective(TbNode record);

    int updateByPrimaryKeyWithBLOBs(TbNode record);

    int updateByPrimaryKey(TbNode record);
}