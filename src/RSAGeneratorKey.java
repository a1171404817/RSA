
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class RSAGeneratorKey {

    private static BigInteger x; //存储临时的位置变量x，y 用于递归

    private static BigInteger y;


    //欧几里得扩展算法
    public static BigInteger ex_gcd(BigInteger a,BigInteger b){
        if(b.intValue()==0){
            x=new BigInteger("1");
            y=new BigInteger("0");
            return a;
        }
        BigInteger ans=ex_gcd(b,a.mod(b));
        BigInteger temp=x;
        x=y;
        y=temp.subtract(a.divide(b).multiply(y));
        return ans;

    }

    //求反模
    public static BigInteger cal(BigInteger a,BigInteger k){
        BigInteger gcd=ex_gcd(a,k);
        if(BigInteger.ONE.mod(gcd).intValue()!=0){
            return new BigInteger("-1");
        }
        //由于我们只求乘法逆元 所以这里使用BigInteger.One,实际中如果需要更灵活可以多传递一个参数,表示模的值来代替这里
        x=x.multiply(BigInteger.ONE.divide(gcd));
        k=k.abs();
        BigInteger ans=x.mod(k);
        if(ans.compareTo(BigInteger.ZERO)<0) ans=ans.add(k);
        return ans;

    }

    public static RsaKeyPair generatorKey(int bitlength){
        SecureRandom random=new SecureRandom();
        random.setSeed(new Date().getTime());
        BigInteger bigPrimep,bigPrimeq;
//        while(!(bigPrimep=BigInteger.probablePrime(bitlength, random)).isProbablePrime(1)){
//            continue;
//        }//生成大素数p
        bigPrimep=new BigInteger("57092957057384230690303198761058034094859258539355954234059064085097060056186338893145278836857007868453350859297747381734217426552410127089962523253805231253327423");




//        while(!(bigPrimeq=BigInteger.probablePrime(bitlength, random)).isProbablePrime(1)){
//            continue;
//        }//生成大素数q
        bigPrimeq=new BigInteger("2841175794376981485685944329309369604591631077159915516404228304178440077816194532730501118291350227769623123843923435156398000132729751621749907"

        );

        BigInteger n=bigPrimep.multiply(bigPrimeq);//生成n
        //生成k
        BigInteger k=bigPrimep.subtract(BigInteger.ONE).multiply(bigPrimeq.subtract(BigInteger.ONE));
        //生成一个比k小的b,或者使用65537
//        BigInteger b=BigInteger.probablePrime(bitlength-1, random);
        BigInteger b=new BigInteger("65537");
        //根据扩展欧几里得算法生成b
        BigInteger a=cal(b,k);
        //存储入 公钥与私钥中
        PrivateKey privateKey=new PrivateKey(n, a);
        PublicKey  publicKey=new PublicKey(n, b);

        //生成秘钥对 返回密钥对
        return new RsaKeyPair(publicKey, privateKey);

    }
}