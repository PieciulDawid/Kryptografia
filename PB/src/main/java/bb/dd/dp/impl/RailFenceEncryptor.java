package bb.dd.dp.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RailFenceEncryptor implements Encryptor {
	private final int key;
	
	public RailFenceEncryptor(int key) {
		this.key = key;
	}
	
	
	@Override
	public String encrypt(String plainText) {
		final int length = plainText.length();

		final var plainTextCP = plainText.codePoints()
				.sequential()
				.mapToObj(Character::toString)
				.toArray(String[]::new);

		final var intermediateRep = new StringBuilder[key];

		for (int i = 0; i < key; i++) {
			intermediateRep[i] = new StringBuilder();
		}
		
		
		int i = 0, j = 0;
		while (i < length) {
			for (; j < key - 1 && i < length; j++, i++) {
				intermediateRep[j].append(plainTextCP[i]);
			}
			for (; j > 0 && i < length; j--, i++) {
				intermediateRep[j].append(plainTextCP[i]);
			}
		}
		
		return String.join("", intermediateRep);
	}

	@Override
	public String decrypt(String cipherText) {
		final int length = cipherText.length();
		final int peeks = (length - 1) / (2 * (key - 1)) + 1;
		final int valleys = (length + key - 2) / (2 * (key - 1));
		final int endMark = length - valleys;
		final int tail = (length - 1) % (2 * (key - 1));
		final int phantomTailStartingLevel = (2 * (key - 1)) - tail - 1;
		int regularTailRemaining = Math.min(tail, key - 1);
		
		final var cipherTextCP = cipherText.codePoints()
				.sequential()
				.toArray();
		
		@SuppressWarnings("unchecked")
		final ArrayDeque<Integer>[] intermediateRep = new ArrayDeque[peeks + 1];
		
		for (int i = 0; i <= peeks; i++) {
			intermediateRep[i] = new ArrayDeque<>();
		}

		
		var curr = 0;
		
		for (int i = 0; i < peeks; i++) {
			intermediateRep[i].add(cipherTextCP[curr++]);
		}
		
		for (int level = 0; curr < endMark; level++) {
			intermediateRep[0].addLast(cipherTextCP[curr++]);
			
			for (int i = 1; i < peeks - 1 && curr < endMark; i++) {
				intermediateRep[i].addFirst(cipherTextCP[curr++]);
				
				if (curr >= endMark) {
					break;
				}
				intermediateRep[i].addLast(cipherTextCP[curr++]);
			}
			
			if (curr >= endMark) {
				break;
			}
			intermediateRep[peeks - 1].addFirst(cipherTextCP[curr++]);
			
			if (curr >= endMark) {
				break;
			}
			if (regularTailRemaining-- > 0) {
				intermediateRep[peeks - 1].addLast(cipherTextCP[curr++]);
			}
			
			if (curr >= endMark) {
				break;
			}
			if (level >= phantomTailStartingLevel) {
				intermediateRep[peeks].addFirst(cipherTextCP[curr++]);
			}
		}
		
		for (int i = 0; curr < length; i++) {
			intermediateRep[i].addLast(cipherTextCP[curr++]);
		}
		
		return Arrays.stream(intermediateRep)
				.flatMap(ArrayDeque::stream)
				.map(Character::toString)
				.collect(Collectors.joining());
	}
	
}
