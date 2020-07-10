package com.modules.bootapplication.modules.api;


import com.modules.bootapplication.common.annotation.IgnoreSecurity;
import com.modules.bootapplication.common.config.Global;
import com.modules.bootapplication.common.oauth.Result;
import com.modules.bootapplication.common.oauth.ResultStatusCode;
import com.modules.bootapplication.common.utils.DateUtils;
import com.modules.bootapplication.common.utils.PathUtil;
import com.modules.bootapplication.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Date: 2018/1/9
 * @Author: wcf
 */
@RestController
@RequestMapping("/api/upload")
@Api(tags="上传图片接口")
public class UploadController extends BaseController {
     /**
      * @description 上传图片
      * @param
      * @author wcf
      * @date 2018/1/9
      * @return
      */
    @PostMapping(value = "uploadImages")
    @ApiOperation("上传图片接口")
    @IgnoreSecurity
    public Result uploadImg(HttpServletRequest request) throws IOException {
        String source = "/uploadimages/" + Global.getConfig("productEnName");
        String date = DateUtils.getDate("yyyyMMdd");
        String path = PathUtil.initDirUpload(source, date);
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//                request.getSession().getServletContext());
//        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    if (file.getSize() > 1024 * 1024 * 10) {
                        return new Result(Global.ERROR_CODE, "图片大小不能超过10M");
                    } else {
                        String oldFileName = file.getOriginalFilename();
                        String extenstion = oldFileName.substring(oldFileName.lastIndexOf(".")).toLowerCase();
                        if (!extenstion.equals(".jpg") && !extenstion.equals(".jpeg") && !extenstion.equals(".bmp") && !extenstion.equals(".png")) {
                            return new Result(Global.ERROR_CODE, "图片格式不正确");
                        } else {
                            String newFileName = UUID.randomUUID().toString();
                            String newFilePath = path +"/"+ newFileName + extenstion;
                            File localFile = new File(newFilePath);
                            file.transferTo(localFile);
                            String result = source+"/" +date+"/"+ newFileName + extenstion;
                            Map<String,String> map = new HashMap<>();
                            map.put("url",result);
                            return new Result(ResultStatusCode.OK, map);
                        }
                    }
                }
//            }
        }
        return new Result(Global.ERROR_CODE, "请上传图片");
    }
}
