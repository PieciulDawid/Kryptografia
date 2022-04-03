package bb.dd.dp.impl3;

import bb.dd.dp.impl.Encryptor;
import bb.dd.dp.impl2.Cipher;

public interface CipherToEncryptorAdapter extends Cipher, Encryptor {
	
	default String encodeCipherTextAsString(byte[] encryptedBytes) {
		var asHexString = new StringBuilder(encryptedBytes.length * 3);
		
		for (byte encryptedByte : encryptedBytes) {
			asHexString.append(String.format("%02X ", encryptedByte));
		}
		
		return asHexString.toString();
	}
	
	default byte[] decodeCipherTextFromString(String cipherText) {
		var hexStringBytes = cipherText.split("\\s{1,5}");
		var bytes = new byte[hexStringBytes.length];
		
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseUnsignedInt(hexStringBytes[i], 16);
		}
		return bytes;
	}
	
	@Override
	default String encrypt(String plainText) {
		var encryptedBytes = encrypt(plainText.getBytes());
		return encodeCipherTextAsString(encryptedBytes);
	}
	
	@Override
	default String decrypt(String cipherText) {
		byte[] bytes = decodeCipherTextFromString(cipherText);
		
		return new String(decrypt(bytes));
	}
	
}
