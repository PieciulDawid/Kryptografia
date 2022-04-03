package bb.dd.dp.impl3;

import bb.dd.dp.impl2.Cipher;
import com.google.common.primitives.Bytes;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class RSACipher implements Cipher {
	private static final BigInteger e = BigInteger.valueOf((1 << 16) + 1);
	
	private final RSAPrivateKey privateKey;
	
	private final RSAPublicKey publicKey;
	
	
	public RSACipher() {
		final var rng = new Random();
		
		final var p = BigInteger.probablePrime(rng.nextInt(1020,1024), rng);
		final var q = BigInteger.probablePrime(rng.nextInt(1010,1014), rng);
		
		final var n = p.multiply(q);
		final var fi = p.min(BigInteger.ONE).multiply(q.min(BigInteger.ONE));
		final var d = e.modInverse(fi);
		
		
		this.privateKey = new RSAPrivateKeyImpl(n, d);
		this.publicKey = new RSAPublicKeyImpl(n, e);
	}
	
	
	@Override
	public byte[] encrypt(byte[] plainText) {
		return encryptDecryptImpl(plainText, this::encode);
	}
	
	@Override
	public byte[] decrypt(byte[] cipherText) {
		return encryptDecryptImpl(cipherText, this::decode);
	}
	
	
	private byte[] encryptDecryptImpl(byte[] plainText, Function<BigInteger, BigInteger> encoder) {
		final var unconcatenated = new byte[(plainText.length / 2000) + 1][];
		
		for (int i = 0; i < plainText.length; i++) {
			final var slice = getIthDataSlice(plainText, i);
			
			unconcatenated[i] = encoder
					.apply(new BigInteger(slice))
					.toByteArray();
		}
		
		
		return Bytes.concat(unconcatenated);
	}
	
	
	private BigInteger encode(BigInteger content) {
		return content.modPow(publicKey.getPublicExponent(), publicKey.getModulus());
	}
	
	private BigInteger decode(BigInteger content) {
		return content.modPow(privateKey.getPrivateExponent(), privateKey.getModulus());
	}
	
	private byte[] getIthDataSlice(byte[] source, int i) {
		i *= 2000;
		
		var slice = Arrays.copyOfRange(source, i, Math.min(i + 2000, source.length));
		
		slice = Bytes.ensureCapacity(slice,
				2000,
				Math.max(2000 - slice.length, 0));
		
		return slice;
	}
	
	
	private record RSAPrivateKeyImpl(BigInteger n, BigInteger d) implements RSAPrivateKey {
		@Override
		public BigInteger getPrivateExponent() {
			return d;
		}
		
		@Override
		public BigInteger getModulus() {
			return n;
		}
		
		@Override
		public String getAlgorithm() {
			return "RSA";
		}
		
		@Override
		public String getFormat() {
			return null;
		}
		
		@Override
		public byte[] getEncoded() {
			return null;
		}
		
	}
	
	private record RSAPublicKeyImpl(BigInteger n, BigInteger e) implements RSAPublicKey {
		@Override
		public BigInteger getPublicExponent() {
			return e;
		}
		
		@Override
		public BigInteger getModulus() {
			return n;
		}
		
		@Override
		public String getAlgorithm() {
			return "RSA";
		}
		
		@Override
		public String getFormat() {
			return null;
		}
		
		@Override
		public byte[] getEncoded() {
			return null;
		}
		
	}
	
}