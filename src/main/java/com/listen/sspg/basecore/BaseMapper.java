package com.listen.sspg.basecore;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 公共的 Mapper
 * @param <Model> 实体类
 * @param <Example> Example类
 * @param <PrimaryKeyType> 主键类型
 * @param <ModelWithBlob> 含Blob字段类
 */
public interface BaseMapper<Model,Example,PrimaryKeyType,ModelWithBlob> {
    /**
     * 查询条数
     * @param example(条件过滤)
     * @return
     */
    long countByExample(Example example);

    /**
     * 条件删除
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PrimaryKeyType id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Model record);

    /**
     * 添加对象所有字段(含Blob)
     * @param record(ModelWithBlob 或 Model 实体类)
     * @return
     */
    int insertSelective(ModelWithBlob record);

    /**
     * 条件查询
     * @param example
     * @return
     */
    List<Model> selectByExample(Example example);

    /**
     * 主键查询
     * @param id
     * @return
     */
    Model selectByPrimaryKey(PrimaryKeyType id);

    /**
     * 根据条件修改对象含Blob (为空的字段不保存)
     * @param record(ModelWithBlob 或 Model 实体类)
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") ModelWithBlob record, @Param("example") Example example);

    /**
     * 根据条件更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Model record, @Param("example") Example example);

    /**
     * 根据key修改对应字段(空的不修改)
     * @param record(ModelWithBlob实体类)
     * @return
     */
    int updateByPrimaryKeySelective(ModelWithBlob record);

    /**
     * 根据主键修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Model record);

    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<Model> list);

    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateBatch(@Param("list") List<Model> list);
}
