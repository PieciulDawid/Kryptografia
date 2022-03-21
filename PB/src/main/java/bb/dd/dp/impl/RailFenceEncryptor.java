package bb.dd.dp.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collector;

public class RailFenceEncryptor implements Encryptor {
	private final int key;
	
	public RailFenceEncryptor(int key) {
		this.key = key;
	}
	
	
	@Override
	public String encrypt(String plainText) {
		final int length = plainText.length();

		final char[] plainTextChars = plainText.toCharArray();

		final var intermediateRep = new StringBuilder[key];
		final var concatenatedRep = new StringBuilder(length);

		for (int i = 0; i < key; i++) {
			intermediateRep[i] = new StringBuilder();
		}


		int i = 0, j = 0;//Fixme Jest już lepiej ale dla sypię się dla kluczy parzystych
		while (i < length) {
			for (; j < key - 1 && i < length; j++, i++) {
				intermediateRep[j].append(plainTextChars[i]);
			}
			for (; j > 0 && i < length; j--, i++) {
				intermediateRep[j].append(plainTextChars[i]);
			}
		}

		for (final var row : intermediateRep) {
			concatenatedRep.append(row);
		}

		return concatenatedRep.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public String decrypt(String cipherText) {
		final int length = cipherText.length();
		final int peeks = (length - 1) / (2 * (key - 1)) + 1;
		final int valleys = (length + key - 2) / (2 * (key - 1));
		final int endMark = length - valleys;
		int tail = ((length - 1) % (2 * (key - 1)));
		
		final char[] plainTextChars = new char[length];
		cipherText.getChars(0, length, plainTextChars, 0);
		
		final var intermediateRep = new ArrayDeque[peeks];
		
		for (int i = 0; i < peeks; i++) {
			intermediateRep[i] = new ArrayDeque<Character>();
		}
		
		
		var curr = 0;
		
		for (int i = 0; i < peeks; i++, curr++) {
			intermediateRep[i].add(plainTextChars[curr]);
		}
		
		while (curr < endMark) {
			intermediateRep[0].addLast(plainTextChars[curr++]);
			
			for (int i = 1; i < peeks - 1 && curr < endMark; i++) {
				intermediateRep[i].addFirst(plainTextChars[curr++]);
				
				if (curr >= endMark) {
					break;
				}
				intermediateRep[i].addLast(plainTextChars[curr++]);
			}
			
			if (curr >= endMark) {
				break;
			}
			intermediateRep[peeks - 1].addFirst(plainTextChars[curr++]);
			
			if (curr >= endMark) {
				break;
			}
			if (tail-- > 0) {
				intermediateRep[peeks - 1].addLast(plainTextChars[curr++]);
			}
		}
		
		for (int i = 0; curr < length; i++, curr++) {
			intermediateRep[i].addLast(plainTextChars[curr]);
		}
		
		return (String) Arrays.stream(intermediateRep)
				.flatMap(ArrayDeque::stream)
				.collect(Collector.of(
						StringBuilder::new,
						StringBuilder::append,
						StringBuilder::append,
						StringBuilder::toString));
	}
	
}
