package service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;
import java.util.concurrent.ExecutorService;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/18 16:17
 * Modified By:
 */
public class OssServiceImpl implements OssService {

    @Override
    public void saveFile(File file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9axa9VBR7rPFn1Fv7k";
        String accessKeySecret = "g6Skkvj9KqSnjEmCMZ8qPlo8ZPhp8u";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PutObjectResult object =  ossClient.putObject("mads-bucket-study", "s1/test", inputStream);

        System.out.println(object);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void main(String[] args) {
        OssService service = new OssServiceImpl();
        File file = new File("D:\\work\\study\\Spring入门学习\\spring-boot-examples\\spring-boot-OSS\\src\\main\\resources\\test.txt");
        service.saveFile(file);
    }
}
