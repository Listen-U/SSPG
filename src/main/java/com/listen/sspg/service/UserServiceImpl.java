package com.listen.sspg.service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.dao.UserMapper;
import com.listen.sspg.entity.User;
import com.listen.sspg.iservice.UserService;
import com.listen.sspg.tools.FastJsonUtil;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Listen
 * @date 2019/2/26
 */

@Service
@PropertySource("classpath:static/fileConfig.properties")
public class UserServiceImpl implements UserService {
    @Value("${filePath}")
    private String filePath;
    @Autowired
    public UserMapper userMapper;

    public User getUser(){
        User user = userMapper.selectByPrimaryKey("1");
        return user;
    }

    public ApiReturnObj<Object> testPage(ApiAcceptObj apiAcceptObj){
        ApiReturnObj<Object> apiReturnObj = new ApiReturnObj<>(2);
        String parameterJson = apiAcceptObj.getParameterJson();
        if (parameterJson != null && !"".equals(parameterJson)) {
            int pageNum = StringHelper.isNullOrEmptyString(FastJsonUtil.analyticJsonObjectStr(parameterJson, "pageNum")) ? 1
                    : Integer.valueOf(FastJsonUtil.analyticJsonObjectStr(parameterJson, "pageNum"));
            int pageSize = StringHelper.isNullOrEmptyString(FastJsonUtil.analyticJsonObjectStr(parameterJson, "pageSize")) ? 20
                    : Integer.valueOf(FastJsonUtil.analyticJsonObjectStr(parameterJson, "pageSize"));
            PageHelper.startPage(pageNum,pageSize);
            List<User> users = userMapper.getAll();
            PageInfo<User> userPageInfo = new PageInfo<>(users);
            apiReturnObj = new ApiReturnObj<>(1,userPageInfo);
        }
        return apiReturnObj;
    }

    public User findByOpenid(String id){
        return null;
    }

    public void insert(User user){

    }

    /**
     * 获取当前日期时间的string类型用于文件名防重复
     */
    public String dates(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public String savePicture(HttpServletRequest request,Writer writer) {

        return "";
    }
    public String testPicture(HttpServletRequest request,MultipartFile file) {
        try {
            request.setCharacterEncoding("UTF-8");
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File files  =  new File(filePath,"aaa.jpg");
            file.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
