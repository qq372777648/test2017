package zhima;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.FileItem;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertificationInitializeRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationInitializeResponse;

public class TestZhimaCustomerCertificationInitialize {
    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "***";
    //商户 RSA 私钥
    private String privateKey     = "***";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "***";

    public void  testZhimaCustomerCertificationInitialize() {
        ZhimaCustomerCertificationInitializeRequest req = new ZhimaCustomerCertificationInitializeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId("ZGYD201610252323000001234");// 必要参数 
        req.setProductCode("w1010100000000002978");// 必要参数 
        req.setBizCode("FACE");// 必要参数 
        req.setIdentityParam("{\"identity_type\": \"CERT_INFO\", \"cert_type\": \"IDENTITY_CARD\", \"cert_name\": \"收委\", \"cert_no\":\"260104197909275964\"}");// 必要参数 
        req.setMerchantConfig("{\"need_user_authorization\":\"false\"}");// 
        req.setExtBizParam("{}");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCustomerCertificationInitializeResponse response = client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            
//            response.getBizNo()
            
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestZhimaCustomerCertificationInitialize result = new  TestZhimaCustomerCertificationInitialize();
        result.testZhimaCustomerCertificationInitialize();
    }
}