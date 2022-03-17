package bb.dd.dp.impl;

public interface CTEncryptor<K> extends Encryptor {
	
//	<T extends CTEncryptor<K>> T create(K key, int d);
	
	@Override
	String encrypt(String plainText);
	
	@Override
	String decrypt(String cipherText);
	
}
