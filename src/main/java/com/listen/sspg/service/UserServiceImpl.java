package com.listen.sspg.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.listen.sspg.dao.UserMapper;
import com.listen.sspg.entity.User;
import com.listen.sspg.iservice.UserService;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    public String savePicture(HttpServletRequest request,MultipartFile file) {
        try {
            request.setCharacterEncoding("UTF-8");
//        logger.info("执行图片上传");
//            String user = request.getParameter("user");
            //logger.info("user:"+user);
            if(!file.isEmpty()) {
//            logger.info("成功获取照片");
                String fileName = file.getOriginalFilename();
//            String path = null;
                String type = null;
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
//            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
                if (type != null) {
                    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                        // 项目在容器中实际发布运行的根路径
//                    String realPath = request.getSession().getServletContext().getRealPath("/");
                        // 自定义的文件名称
                        String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                        // 设置存放图片文件的路径
//                    path = realPath + "/uploads/" + trueFileName;
//                    logger.info("存放图片文件的路径:" + path);
                        file.transferTo(new File(filePath+trueFileName));
//                    logger.info("文件成功上传到指定目录下");
                    }else {
//                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                        return "error";
                    }
                }else {
//                logger.info("文件类型为空");
                    return "error";
                }
            }else {
//            logger.info("没有找到相对应的文件");
                return "error";
            }
        }catch (Exception e){

        }

        return "success";
    }
}
