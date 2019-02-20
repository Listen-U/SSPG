package com.listen.sspg.dao;

import com.listen.sspg.basecore.BaseMapper;
import com.listen.sspg.entity.blocknoinfo;
import com.listen.sspg.entity.blocknoinfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface blocknoinfoMapper extends BaseMapper<blocknoinfo,blocknoinfoExample,String> {

}