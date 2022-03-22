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
		final int[][] matrix = new int[key.length][d];
		
		final int[] plainTextCP = plainText.codePoints()
				.sequential()
				.toArray();
		
		final int[] padding = RandomStringUtils
				.randomAlphabetic(Math.max(key.length * d - plainTextCP.length, 0))
				.codePoints()
				.toArray();
		
		final var builder = new StringBuilder(key.length * d);
		
		int i, j = 0, n;
		int pLen = plainTextCP.length;
		int mLen = matrix.length;
		int m0Len = matrix[0].length;
		
		for (i = 0, n = 0; i < m0Len && n < pLen; i++) {
			for (j = 0; j < mLen && n < pLen; j++, n++) {
				matrix[j][i] = plainTextCP[n];
			}
		}
		
		for (n = 0, i--; i < m0Len; i++) {
			for (; j < mLen; j++, n++) {
				matrix[j][i] = 0; /*padding[n];*/
			}
			j = 0;
		}
		
		final int[][] transposedMatrix = new int[key.length][];
		
		for (i = 0; i < mLen; i++) {
			transposedMatrix[i] = matrix[key[i] - 1];
		}
		
		
		for (i = 0, n = 0; i < m0Len; i++) {
			for (j = 0; j < mLen; j++, n++) {
				builder.append(
						Character.toString(transposedMatrix[j][i]));
			}
		}
		
		return builder.toString();
	}
	
	@Override
	public String decrypt(String cipherText) {
		final int[][] matrix = new int[key.length][d];
		
		final int[] cipherTextChars = cipherText.codePoints()
				.sequential()
				.toArray();
		
		final var builder = new StringBuilder(key.length * d);
		
		int i, j, n;
		int pLen = cipherTextChars.length;
		int mLen = matrix.length;
		int m0Len = matrix[0].length;
		
		for (i = 0, n = 0; i < m0Len && n < pLen; i++) {
			for (j = 0; j < mLen && n < pLen; j++, n++) {
				matrix[j][i] = cipherTextChars[n];
			}
		}
		
		final int[][] transposedMatrix = new int[key.length][];
		
		for (i = 0; i < transposedMatrix.length; i++) {
			transposedMatrix[key[i] - 1] = matrix[i];
		}
		
		for (i = 0, n = 0; i < m0Len; i++) {
			for (j = 0; j < mLen; j++, n++) {
				builder.append(
						Character.toString(transposedMatrix[j][i]));
			}
		}
		
		return builder.toString();
	}
	
}
