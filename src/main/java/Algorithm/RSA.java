package Algorithm;

import java.math.BigInteger;

public class RSA {
    KeysGenerator keysGenerator;
    public RSA()
    {
        System.out.println("Start");
        keysGenerator=new KeysGenerator();
        System.out.println("END");
        System.out.println(keysGenerator.getP());
        System.out.println(keysGenerator.getQ());
        System.out.println(keysGenerator.getN());
        System.out.println(keysGenerator.getEulerF());
        System.out.println(keysGenerator.getE());
        System.out.println(keysGenerator.getD());

    }
}
