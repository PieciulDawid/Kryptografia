package bb.dd.dp.impl2;

import java.math.BigInteger;

public interface SynchronousStreamCipher extends Cipher {
	void setSeed(BigInteger seed);
	
}
