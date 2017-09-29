package zhima;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.FileItem;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertificationCertifyRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationCertifyResponse;

public class TestZhimaCustomerCertificationCertify {
    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "***";
    //商户 RSA 私钥
    private String privateKey     = "***";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "***";

    public void  testZhimaCustomerCertificationCertify() {
        ZhimaCustomerCertificationCertifyRequest req = new ZhimaCustomerCertificationCertifyRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setBizNo("ZM201705173000000323200000189778");// 必要参数 
        req.setReturnUrl("http://www.taobao.com");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestZhimaCustomerCertificationCertify result = new  TestZhimaCustomerCertificationCertify();
        result.testZhimaCustomerCertificationCertify();
    }
}
