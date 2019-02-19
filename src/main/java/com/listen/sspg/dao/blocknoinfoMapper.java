package com.listen.sspg.dao;

import com.listen.sspg.entity.blocknoinfo;
import com.listen.sspg.entity.blocknoinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface blocknoinfoMapper {
    long countByExample(blocknoinfoExample example);

    int deleteByExample(blocknoinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(blocknoinfo record);

    int insertSelective(blocknoinfo record);

    List<blocknoinfo> selectByExample(blocknoinfoExample example);

    blocknoinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") blocknoinfo record, @Param("example") blocknoinfoExample example);

    int updateByExample(@Param("record") blocknoinfo record, @Param("example") blocknoinfoExample example);

    int updateByPrimaryKeySelective(blocknoinfo record);

    int updateByPrimaryKey(blocknoinfo record);

    int insertBatch(@Param("list") List<blocknoinfo> list);

    int updateBatch(@Param("list") List<blocknoinfo> list);
}