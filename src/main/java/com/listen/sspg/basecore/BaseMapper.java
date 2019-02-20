package com.listen.sspg.basecore;

import java.util.List;
import com.listen.sspg.entity.blocknoinfo;
import com.listen.sspg.entity.blocknoinfoExample;
import org.apache.ibatis.annotations.Param;

/**
 * 公共的 Mapper
 * @param <Model> 实体类
 * @param <Example> Example类
 * @param <PrimaryKeyType> 主键类型
 */
public interface BaseMapper<Model,Example,PrimaryKeyType> {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(PrimaryKeyType id);

    int insert(Model record);

    int insertSelective(Model record);

    List<blocknoinfo> selectByExample(Example example);

    blocknoinfo selectByPrimaryKey(PrimaryKeyType id);

    int updateByExampleSelective(@Param("record") Model record, @Param("example") Example example);

    int updateByExample(@Param("record") Model record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    int insertBatch(@Param("list") List<Model> list);

    int updateBatch(@Param("list") List<Model> list);
}
