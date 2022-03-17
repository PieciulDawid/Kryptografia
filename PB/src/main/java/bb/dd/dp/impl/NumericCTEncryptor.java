package bb.dd.dp.impl;

import org.apache.commons.lang3.RandomStringUtils;

public class NumericCTEncryptor implements CTEncryptor<int[]> {
	protected final int d;
	private final int[] key;
	
	public NumericCTEncryptor(int[] key, int d) {
		this.d = d;
		this.key = key;
	}
	
	@Override
	public String encrypt(String plainText) {
		final char[][] matrix = new char[key.length][d];
		
		final char[] plainTextChars = plainText.toCharArray();
		
		final char[] padding = RandomStringUtils.randomAlphabetic(key.length * d - plainTextChars.length).toCharArray();
		
		final var builder = new StringBuilder(key.length * d);
		
		int i, j = 0, n;
		int pLen = plainTextChars.length;
		int mLen = matrix.length;
		int m0Len = matrix[0].length;
		
		for (i = 0, n = 0; i < mLen && n < pLen; i++, n++) {
			for (j = 0; j < m0Len && n < pLen; j++, n++) {
				matrix[i][j] = plainTextChars[n];
			}
		}
		
		for (n = 0; i < mLen && n < pLen; i++, n++) {
			for (; j < m0Len && n < pLen; j++, n++) {
				matrix[i][j] = padding[n];
			}
			j = 0;
		}
		
		final char[][] transposedMatrix = new char[key.length][];
		
		for (i = 0; i < transposedMatrix.length; i++) {
			transposedMatrix[i] = matrix[key[i]];
		}
		
		for (i = 0, n = 0; i < mLen && n < pLen; i++, n++) {
			for (j = 0; j < m0Len && n < pLen; j++, n++) {
				builder.append(transposedMatrix[i][j]);
			}
		}
		
		return builder.toString();
	}
	
	@Override
	public String decrypt(String cipherText) {
		final char[][] matrix = new char[key.length][d];
		
		final char[] cipherTextChars = cipherText.toCharArray();
		
		final var builder = new StringBuilder(key.length * d);
		
		int i, j, n;
		int pLen = cipherTextChars.length;
		int mLen = matrix.length;
		int m0Len = matrix[0].length;
		
		for (i = 0, n = 0; i < mLen && n < pLen; i++, n++) {
			for (j = 0; j < m0Len && n < pLen; j++, n++) {
				matrix[i][j] = cipherTextChars[n];
			}
		}
		
		final char[][] transposedMatrix = new char[key.length][];
		
		for (i = 0; i < transposedMatrix.length; i++) {
			transposedMatrix[key[i]] = matrix[i];
		}
		
		for (i = 0, n = 0; i < mLen && n < pLen; i++, n++) {
			for (j = 0; j < m0Len && n < pLen; j++, n++) {
				builder.append(transposedMatrix[i][j]);
			}
		}
		
		return builder.toString();
	}
	
}
