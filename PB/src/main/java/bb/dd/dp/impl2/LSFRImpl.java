package bb.dd.dp.impl2;

import java.math.BigInteger;

public class LSFRImpl implements LSFR {
	private final static int CUTOFF = 256;
	
	private final int[] polynomialCoefficients;
	private BigInteger state;
	
	public LSFRImpl(int[] polynomialCoefficients) {
		this.polynomialCoefficients = polynomialCoefficients;
		state = BigInteger.ZERO;
	}
	
	@Override
	public void setSeed(BigInteger seed) {
		state = seed;
	}
	
	@Override
	public BigInteger next() {
		nextBit();
		
		return state;
	}
	
	public byte next8BitSequence() {
		byte returned = 0;
		
		returned += nextBit() ? 0b0000_0001 : 0;
		returned += nextBit() ? 0b0000_0010 : 0;
		returned += nextBit() ? 0b0000_0100 : 0;
		returned += nextBit() ? 0b0000_1000 : 0;
		
		returned += nextBit() ? 0b0001_0000 : 0;
		returned += nextBit() ? 0b0010_0000 : 0;
		returned += nextBit() ? 0b0100_0000 : 0;
		returned += nextBit() ? 0b1000_0000 : 0;
		
		return returned;
	}
	
	private boolean nextBit() {
		boolean next = false;
		
		for (int bit : polynomialCoefficients) {
			next ^= state.testBit(bit);
		}
		
		state = state.shiftLeft(1).clearBit(CUTOFF);
		state = next ? state.setBit(0) : state;
		
		return next;
	}
	
}
