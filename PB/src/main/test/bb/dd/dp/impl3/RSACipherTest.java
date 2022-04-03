package bb.dd.dp.impl3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RSACipherTest {
	private RSACipher impl;
	
	@BeforeEach
	void setUp() {
		impl = new RSACipher();
	}
	
	@Test
	void encryptThenDecryptIsSameAsOriginalValue() {
		final var original = BigInteger.valueOf(23934L).toByteArray();
		
		var encrypted = impl.encrypt(original);
		var decrypted = impl.decrypt(encrypted);
		
		assertArrayEquals(original, decrypted);
	}
	
}