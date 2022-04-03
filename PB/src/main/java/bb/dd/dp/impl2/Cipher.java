package bb.dd.dp.impl2;

public interface Cipher {
	byte[] encrypt(byte[] plainText);
	
	byte[] decrypt(byte[] cipherText);
	
}
