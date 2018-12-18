package Algorithm;

import java.math.BigInteger;
import java.util.BitSet;

public class RSA {
    KeysGenerator keysGenerator;

BigInteger[] orginalSignature;
    BigInteger[] values;
    public String[] getValues() {
        String [] strings = new String[this.values.length];
        int i=0;
        for (BigInteger x:this.values) {
            strings[i] = x.toString();
        i++;
        }
        return strings;
    }
    public String[] getOryginalSignature() {
        String [] strings = new String[this.orginalSignature.length];
        int i=0;
        for (BigInteger x:this.orginalSignature) {
            strings[i] = x.toString();
            i++;
        }
        return strings;
    }
    public void saveStringValuesToBigInteger(String[]array)
    {
this.values = new BigInteger[array.length];
        for (int i=0; i<array.length; i++) {
            BigInteger x = new BigInteger(array[i]);
            this.values[i]=x;
        }

    }

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
        System.out.println("jazda");


    }

    public void startBlinding()
    {
        System.out.println("START BLINDING");
        this.orginalSignature = new BigInteger[this.values.length];
        for (int i =0; i<this.values.length; i++)
        {
            this.orginalSignature[i]= values[i].modPow(keysGenerator.getD(),keysGenerator.getN());
        }

        for (int i =0; i<this.values.length; i++)
        {
           this.values[i] = this.encrypt(this.values[i]);
        }

    }

    public void startSigning()
    {
        System.out.println("Start signing");
        for (BigInteger x :this.values) {
            System.out.println(x);
        }
        System.out.println("**********************************");
        for (int i =0; i<this.values.length; i++)
        {
            this.values[i] = this.decrypt(this.values[i]);
        }
        for (BigInteger x :this.values) {
            System.out.println(x);
        }


    }
    public void startChecking()
    {
        for (int i =0; i<this.values.length; i++)
        {
            this.values[i] = this.decrypt2(this.values[i]);
        }
    }
public BigInteger decrypt(BigInteger value) {
    //return (value.pow((int)keysGenerator.getD().longValue())).mod(keysGenerator.getN());
    return (value.modPow(keysGenerator.getD(), keysGenerator.getN())).mod(keysGenerator.getN());
}

public BigInteger decrypt2(BigInteger value)
{
    //return (value.divide(keysGenerator.getR())).mod(keysGenerator.getN());
    return  (value.multiply(keysGenerator.getR().modInverse(keysGenerator.getN()))).mod(keysGenerator.getN());
}

    public BigInteger[] getPublicKey()
    {BigInteger bigIntegers[]={keysGenerator.getE(),keysGenerator.getN()};

        return bigIntegers;
    }

    public BigInteger[] getPrivateKey()
    {
        BigInteger bigIntegers[] = { keysGenerator.getN(), keysGenerator.getD()};
        return bigIntegers;
    }

    public static BigInteger[] bytesTabtoBigInt(byte [][] bytes)
    {
        BigInteger bigInteger[] = new BigInteger[bytes.length];

        for (int i=0; i<bytes.length; i++) {
            BitSet bitSet = BitSet.valueOf(bytes[i]);

            long[] temp = new long[1];
            temp = bitSet.toLongArray();
            BigInteger ret = BigInteger.valueOf(temp[0]);
            bigInteger[i]=ret;
        }
        return bigInteger;
    }


    public BigInteger encrypt(BigInteger value)
    {
        return (value.multiply(this.keysGenerator.getR().modPow(this.keysGenerator.getE(),this.keysGenerator.getN()))).mod(this.keysGenerator.getN());
    }

    public void setTab(BigInteger[] tab)
    {
        this.values=tab;
    }

}
