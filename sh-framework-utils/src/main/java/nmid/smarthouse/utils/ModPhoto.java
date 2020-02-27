package nmid.smarthouse.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/21 11:58 AM
 */
public class ModPhoto {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "http://oss-cn-zhangjiakou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI4Fjxa5GDzbNtmG1yUzNi";
    private static String accessKeySecret = "bZSejJOnbHDwRCANeHT6xyddpf428T";
    private static String SUFFER_URL = "https://smart-house.oss-cn-zhangjiakou.aliyuncs.com/";

    public String uploadPhoto(MultipartFile file, String phone) {


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //获取文件后缀名称
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = "img/userphoto/" + phone + ext;
        //文件地址
        String url = null;
        try {
            ossClient.putObject("smart-house", filename, new ByteArrayInputStream(file.getBytes()));
            url = SUFFER_URL + filename;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return url;
    }

}
