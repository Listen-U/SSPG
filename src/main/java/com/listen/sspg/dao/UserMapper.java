package com.listen.sspg.dao;

import com.listen.sspg.basecore.BaseMapper;
import com.listen.sspg.entity.User;
import com.listen.sspg.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Listen
 * @date 2019/2/26
 */
@Repository
public interface UserMapper extends BaseMapper<User,UserExample,String,User> {
}