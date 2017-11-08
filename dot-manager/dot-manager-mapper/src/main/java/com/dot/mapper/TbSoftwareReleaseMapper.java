package com.dot.mapper;

import com.dot.pojo.TbSoftwareRelease;
import com.dot.pojo.TbSoftwareReleaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSoftwareReleaseMapper {
    int countByExample(TbSoftwareReleaseExample example);

    int deleteByExample(TbSoftwareReleaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSoftwareRelease record);

    int insertSelective(TbSoftwareRelease record);

    List<TbSoftwareRelease> selectByExample(TbSoftwareReleaseExample example);

    TbSoftwareRelease selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSoftwareRelease record, @Param("example") TbSoftwareReleaseExample example);

    int updateByExample(@Param("record") TbSoftwareRelease record, @Param("example") TbSoftwareReleaseExample example);

    int updateByPrimaryKeySelective(TbSoftwareRelease record);

    int updateByPrimaryKey(TbSoftwareRelease record);
}