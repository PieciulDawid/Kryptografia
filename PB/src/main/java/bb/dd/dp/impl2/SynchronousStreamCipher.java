package bb.dd.dp.impl2;

import java.math.BigInteger;

public interface SynchronousStreamCipher {
	void setSeed(BigInteger seed);
	
	byte[] encrypt(byte[] plainText);
	
	byte[] decrypt(byte[] cipherText);
	
}
