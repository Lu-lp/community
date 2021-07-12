package sysu.lulp.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
public class ALiCloudProvider {
    @Value("${ALiCloud.OSS.AccessKey.ID}")
    private String accessKeyId;
    @Value("${ALiCloud.OSS.AccessKey.Secret}")
    private String accessKeySecret;
    @Value("${ALiCloud.OSS.bucketName}")
    private String bucketName;
    @Value("${ALiCloud.OSS.endpoint}")
    private String endpoint;

    public String upload(InputStream inputStream, String contentType, String originalFilename) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String[] fileNameSpilt = originalFilename.split("\\.");
        String newFileName;
        if (fileNameSpilt.length > 1) {
            newFileName = UUID.randomUUID().toString().replace("-", "")
                    + "." + fileNameSpilt[fileNameSpilt.length - 1];
        } else {
            newFileName = UUID.randomUUID().toString().replace("-", "")
                    + ".png";
        }
        // 有可能会有网络错误
        ossClient.putObject(bucketName, newFileName, inputStream);

        ossClient.shutdown();

        String url = "https://" + bucketName + "." + endpoint + "/" + newFileName;

        return url;
    }
}
