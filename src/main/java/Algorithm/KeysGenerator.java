package Algorithm;

import java.math.BigInteger;

public class KeysGenerator {
    BigInteger p;
    BigInteger q;

    KeysGenerator() {

        this.p = BigInteger.valueOf(2).pow(121).nextProbablePrime();
        this.q = BigInteger.valueOf(3).pow(82).nextProbablePrime();

    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getN() {
        return this.p.multiply(this.q);
    }

    public BigInteger getEulerF() {
        return (this.p.subtract(BigInteger.valueOf(1))).multiply(this.q.subtract(BigInteger.valueOf(1)));
    }

    public BigInteger getE() {
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(this.getEulerF()) == -1; i = i.add(BigInteger.valueOf(1))) {

            if (((i.gcd(this.getEulerF())).compareTo(BigInteger.valueOf(1))) == 0) {
                return i;
            }
        }
        return null;
    }
    public BigInteger getR() {
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(this.getEulerF()) == -1; i = i.add(BigInteger.valueOf(1))) {

            if (((i.gcd(this.getN())).compareTo(BigInteger.valueOf(1))) == 0)
            {
                return i;
            }
        }
        return null;
    }

    public BigInteger getD() {
        return this.getE().modInverse(this.getEulerF());
    }
}

