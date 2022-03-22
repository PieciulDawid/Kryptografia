package bb.dd.dp.impl;

import com.google.common.base.Suppliers;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
		final int[] numericKey = new int[key.length()];
		
		final var CPToOrdinal = key
				.codePoints()
				.boxed()
				.collect(Collectors.groupingBy(
						Function.identity(),
						TreeMap::new,
						Collectors.summingInt(e -> 1)));
		
		int acc = 0;
		for (final var l : CPToOrdinal.entrySet()) {
			acc += l.getValue();
			l.setValue(acc);
		}
		
		final int[] keyCP = key.codePoints()
				.sequential()
				.toArray();
		
		for (int i = 0; i < keyCP.length; i++) {
			final int numericValue = CPToOrdinal.get(keyCP[i]);
			
			numericKey[i] = numericValue;
			CPToOrdinal.put(keyCP[i], numericValue - 1);
		}
		
		return numericKey;
	}
	
}
