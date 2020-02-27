package nmid.smarthouse;

import com.aliyun.oss.internal.OSSUtils;
import nmid.smarthouse.utils.ModPhoto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/21 2:47 PM
 */
@SpringBootTest
public class TestPhoto {
    @Test
    public void photoTest() throws Exception {
        File file = new File("/Users/piwenjing/Desktop/图片保存.png");
        FileInputStream in_file = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("图片保存.png", "图片保存.png", "png", in_file);
        String url = new ModPhoto().uploadPhoto(multipartFile, "123");
        System.out.println(url);
    }
}
