package bb.dd.dp.impl2;

import java.math.BigInteger;

public interface LSFR {
	void setSeed(BigInteger seed);
	
	BigInteger next();
	
}
