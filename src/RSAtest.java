
import java.math.BigInteger;

public class RSAtest {
    public static  void main(String[]args) throws  Exception
    {
        RsaKeyPair keyPair=RSAGeneratorKey.generatorKey(1024);
        System.out.println("n的值是:"+keyPair.getPublicKey().getN());
        System.out.println("公钥b:"+keyPair.getPublicKey().getB());
        System.out.println("私钥a:"+keyPair.getPrivateKey().getA());


        String cryptdata="E2L+9hcy+HyDXll5Ai9qP97R+GzEObFSc1GWZCmgu/AT/PHlVcEgbZuNBJCV6+BY4sN4fuIQv/sBLe8Z9hvkMucAb3oeleGAohk+dzA6OfWY/GM1iFDBP+J83iK2zqZmDYeqx7Ot1y3RwI+PpXvN0QvAggA69EhNwnTDRGr8+E4=";
        //使用私钥解密
        try {
            String result=RSAUtil.decrypt(cryptdata, keyPair.getPrivateKey(),"UTF-8");
            System.out.println("解密后:"+result);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}










