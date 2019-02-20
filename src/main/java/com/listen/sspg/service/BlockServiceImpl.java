package com.listen.sspg.service;

import com.github.pagehelper.PageInfo;
import com.listen.sspg.dao.blocknoinfoMapper;
import com.listen.sspg.entity.blocknoinfo;
import com.listen.sspg.iservice.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("test")
public class BlockServiceImpl implements BlockService {
    @Autowired
    public blocknoinfoMapper bddd;

    public blocknoinfo getBlock(String str){
        blocknoinfo b = bddd.selectByPrimaryKey("031196d5-d663-449c-b6cc-9f95dee67379");
        return b;
    }
}
