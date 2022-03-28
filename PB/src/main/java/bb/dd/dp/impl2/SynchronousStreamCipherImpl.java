package bb.dd.dp.impl2;

import java.math.BigInteger;

public class SynchronousStreamCipherImpl implements SynchronousStreamCipher {
	
	private final LSFRImpl generator;
	
	public SynchronousStreamCipherImpl(int[] polynomialCoefficients) {
		this.generator = new LSFRImpl(polynomialCoefficients);
	}
	
	
	@Override
	public void setSeed(BigInteger seed) {
		generator.setSeed(seed);
	}
	
	@Override
	public byte[] encrypt(byte[] plainText) {
		byte[] returned = new byte[plainText.length];
		
		for (int i = 0; i < plainText.length; i++) {
			returned[i] = (byte) (plainText[i] ^ generator.next8BitSequence());
		}
		
		return returned;
	}
	
	@Override
	public byte[] decrypt(byte[] cipherText) {
		return encrypt(cipherText);
	}
	
}
