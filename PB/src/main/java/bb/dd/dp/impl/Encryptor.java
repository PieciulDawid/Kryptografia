package bb.dd.dp.impl;

public interface Encryptor {
	String encrypt(String plainText);
	
	String decrypt(String cipherText);
	
}
