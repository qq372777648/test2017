package zhima;

import java.net.URLDecoder;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.internal.util.RSACoderUtil;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertifyApplyRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCustomerCertifyInitialRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertifyInitialResponse;
import java.io.File;
import javax.imageio.stream.FileImageOutputStream;
/**
 * 芝麻认证移动端sdk模拟代码
 * @author zmxy
 * @version $Id: CertifyDemo.java, v 0.1 2016年3月29日 下午7:51:35 zmxy Exp $
 */
public class Test1 {
    // 图片解析常量定义-用于解析图片的demo
    private static final byte[] S_DECODETABLE = new byte[128];
    private static final char   S_BASE64PAD   = '=';
    //芝麻服务器地址
    private final static String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //应用ID
    private static String       appId          = "xxx";
    //芝麻公钥,请联系技术支持
    private static String       zhimaPublicKey = "xxxx";
    //商户私钥
    private static String       privateKey     = "xxxx";
    //字符编码
    private static String       charset        = "UTF-8";
    
    /**
     * 整体流程模拟测试
     * @param args
     */
    public static void main(String[] args) {
        try {
            /** 【商户服务端】逻辑开始 */
            //  获取认证的token
            String token = getToken();
            System.out.println("获取到的" + token);
            //  将token等入参进行签名，返回签名后的params和sign
            String param = getCertifyParam(token);
            System.out.println(param);
            /** 【商户服务端】逻辑结束 */
 
            /** 【商户客户端】逻辑开始 */
            // 返回给客户端，由客户端发起调用芝麻sdk
            // 监听芝麻sdk认证结果回调，并上报商户服务端
            /** 【商户服务端】逻辑结束 */
            
            /** 【商户服务端】逻辑开始 */            
            // 将返回的结果进行解密解签
            decryptAndVerifySign("params", "sign");
            /** 【商户服务端】逻辑结束 */
            
            /** 将认证结果回查获取的人脸图片face_picture属性的byte解析成图片 */
            parseImgeByte("xxxx");
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 根据用户相关信息发起认证请求获取token
     * @param name 用户姓名
     * @param certNo 用户证件号
     * @param mobile 用户手机
     * @return 认证标识token，可以用来发起认证页面请求
     * @throws ZhimaApiException
     */
    private static String getToken() {
        ZhimaCustomerCertifyInitialRequest req = new ZhimaCustomerCertifyInitialRequest();
        req.setTransactionId("xxxxxxxxx");//业务流水号
        req.setContractFlag("xxx");//设置合约外标，签约后才有，详情联系技术支持
        req.setProductCode("xxx"); //产品码，请联系技术支持
        req.setState("{\"state\":\"1234567890\"}"); //商户透传字段
        req.setIdentityType("BY_CERTNO_AND_NAME");// 或BY_MOBILE_NO        
        req.setIdentityParam("{\"certNo\":\"xxx\",\"name\":\"张三\",\"certType\":\"IDENTITY_CARD\",\"mobileNo\":\"13901234567\"}");// 或 {\"mobileNo\":\"13901234567\"}
        req.setSourceType("sdk");
 
        try {
            DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
                zhimaPublicKey);
            ZhimaCustomerCertifyInitialResponse response = (ZhimaCustomerCertifyInitialResponse) client
                .execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getBody());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            return response.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
     * 获取认证参数
     * @param token
     * @return
     * @throws ZhimaApiException
     */
    private static String getCertifyParam(String token) throws ZhimaApiException {
        ZhimaCustomerCertifyApplyRequest request = new ZhimaCustomerCertifyApplyRequest();
        request.setToken(token);
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
            zhimaPublicKey);
        String url = client.generatePageRedirectInvokeUrl(request);
        return getParamsFromUrl(url);
    }
 
    /**
     * 对url进行分割,提取参数sign,app_id,params
     * @param url
     * @return
     */
    private static String getParamsFromUrl(String url) {
        String paramUrl = "";
        String[] temp = url.split("\\?");
        System.out.println(temp[1]);
        String[] paramsString = temp[1].split("&");
        for (String attr : paramsString) {
            String[] keyAndValue = attr.split("=");
            if (("params||app_id||sign").indexOf(keyAndValue[0]) > -1) {
                paramUrl += keyAndValue[0] + "=" + keyAndValue[1] + "&";
            }
        }
        return paramUrl.substring(0, paramUrl.length() - 1);
    }
 
    /**
     * 返回的结果进行解密解签
     * 
     * @param params  返回结果
     * @param sign    签名
     * @throws Exception
     */
    public static void decryptAndVerifySign(String params, String sign) throws Exception {
 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey,
            zhimaPublicKey);
        //解密验签，返回结果
        String decryptedParam = client.decryptAndVerifySign(URLDecoder.decode(params, charset),
            URLDecoder.decode(sign, charset));
 
        //通过浏览器返回时，不需要decode
        System.out.println(URLDecoder.decode(decryptedParam, charset));
        //示例结果如下
        //certify_result=true&token=cdc394da0ee7c48bfb3867dec4b1a564e&open_id=268812407828113441734640502&real_name_flag=true
    }
    
    /** 解析图片byte-demo样例*/
     private static void parseImgeByte(String b) {
        try {
            byte[] bytes = decode(b);
            FileImageOutputStream imageOutput1 = new FileImageOutputStream(new File(
                "D:/123_tes2t"+System.currentTimeMillis()+".jpg"));
            imageOutput1.write(bytes, 0, bytes.length);
            imageOutput1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
     public static byte[] decode(String data) {
        
        char[] ibuf = new char[4];
        int ibufcount = 0;
        byte[] obuf = new byte[data.length() / 4 * 3 + 3];
        int obufcount = 0;
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == '=' || ch < S_DECODETABLE.length
                && S_DECODETABLE[ch] != Byte.MAX_VALUE) {
                ibuf[ibufcount++] = ch;
                if (ibufcount == ibuf.length) {
                    ibufcount = 0;
                    obufcount += decode0(ibuf, obuf, obufcount);
                }
            }
        }
        if (obufcount == obuf.length)
            return obuf;
        byte[] ret = new byte[obufcount];
        System.arraycopy(obuf, 0, ret, 0, obufcount);
        return ret;
    }
    private static int decode0(char[] ibuf, byte[] obuf, int wp) {
        int outlen = 3;
        if (ibuf[3] == S_BASE64PAD)
            outlen = 2;
        if (ibuf[2] == S_BASE64PAD)
            outlen = 1;
        int b0 = S_DECODETABLE[ibuf[0]];
        int b1 = S_DECODETABLE[ibuf[1]];
        int b2 = S_DECODETABLE[ibuf[2]];
        int b3 = S_DECODETABLE[ibuf[3]];
        switch (outlen) {
            case 1:
                obuf[wp] = (byte) (b0 << 2 & 0xfc | b1 >> 4 & 0x3);
                return 1;
            case 2:
                obuf[wp++] = (byte) (b0 << 2 & 0xfc | b1 >> 4 & 0x3);
                obuf[wp] = (byte) (b1 << 4 & 0xf0 | b2 >> 2 & 0xf);
                return 2;
            case 3:
                obuf[wp++] = (byte) (b0 << 2 & 0xfc | b1 >> 4 & 0x3);
                obuf[wp++] = (byte) (b1 << 4 & 0xf0 | b2 >> 2 & 0xf);
                obuf[wp] = (byte) (b2 << 6 & 0xc0 | b3 & 0x3f);
                return 3;
            default:
                throw new RuntimeException("Couldn't decode.");
        }
    }
}