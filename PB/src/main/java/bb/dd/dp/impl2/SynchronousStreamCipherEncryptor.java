package bb.dd.dp.impl2;

import bb.dd.dp.impl.Encryptor;

public class SynchronousStreamCipherEncryptor extends SynchronousStreamCipherImpl implements Encryptor {
	
	public SynchronousStreamCipherEncryptor(int[] polynomialCoefficients) {
		super(polynomialCoefficients);
	}
	
	@Override
	public String encrypt(String plainText) {
		var encryptedBytes = encrypt(plainText.getBytes());
		var asHexString = new StringBuilder(encryptedBytes.length * 3);
		
		for (byte encryptedByte : encryptedBytes) {
			asHexString.append(String.format("%02X ", encryptedByte));
		}
		
		return asHexString.toString();
	}
	
	@Override
	public String decrypt(String cipherText) {
		var hexStringBytes = cipherText.split("\\s{1,5}");
		var bytes = new byte[hexStringBytes.length];
		
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseUnsignedInt(hexStringBytes[i], 16);
		}
		
		return new String(bytes);
	}
	
}
