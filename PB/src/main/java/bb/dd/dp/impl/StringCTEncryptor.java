package bb.dd.dp.impl;

import com.google.common.base.Suppliers;
import com.google.common.collect.TreeMultimap;

import java.util.function.Supplier;

public class StringCTEncryptor implements CTEncryptor<String> {
	private final String key;
	private final Supplier<CTEncryptor<int[]>> encryptorGetter;
	
	public StringCTEncryptor(String key, int d) {
		this.key = key;
		this.encryptorGetter =
				Suppliers.memoize(() ->
						new NumericCTEncryptor(generateNumericKey(), d));
	}
	
	@Override
	public String encrypt(String plainText) {
		return encryptorGetter.get().encrypt(plainText);
	}
	
	@Override
	public String decrypt(String cipherText) {
		return encryptorGetter.get().decrypt(cipherText);
	}
	
	@SuppressWarnings("ConstantConditions")
	private int[] generateNumericKey() {
		TreeMultimap<Character, Integer> letters = TreeMultimap.create();
		final char[] keyChars = key.toCharArray();
		final int[] numericKey = new int[keyChars.length];
		
		for (int i = 0; i < keyChars.length; i++) {
			letters.put(keyChars[i], i);
		}
		
		for (int i = 0; i < keyChars.length; i++) {
			numericKey[i] = letters.get(keyChars[i]).pollFirst() + 1;
		}
		
		return numericKey;
	}
	
}
