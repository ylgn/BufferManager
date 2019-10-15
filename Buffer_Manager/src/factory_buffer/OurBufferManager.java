package factory_buffer;

import java.util.LinkedList;

import buffer_package.IBuffer;

public class OurBufferManager {
	final static int POOL_SIZE = 4;

	public static void main(String[] args) {
		String[] pageToRead = { "A", "B", "C", "D", "E", "A", "B", "C", "D", "E" };
		int pageMisses;
		LinkedList<Frame> listFrame;
		BufferFactory bufferFactory = new BufferFactory();

		// LRU

		IBuffer clockLRU = bufferFactory.getBuffer("LRU");
		listFrame = new LinkedList<Frame>();
		pageMisses = clockLRU.runBuffer(pageToRead, listFrame, POOL_SIZE);
		System.out.println(
				"Etat final : " + clockLRU.toStringBuffer(listFrame) + " avec " + pageMisses + " pages misses");


		// FIFO
		
		IBuffer clockFIFO = bufferFactory.getBuffer("FIFO");
		listFrame = new LinkedList<Frame>();
		pageMisses = clockFIFO.runBuffer(pageToRead, listFrame, POOL_SIZE);
		System.out.println(
				"Etat final : " + clockFIFO.toStringBuffer(listFrame) + " avec " + pageMisses + " pages misses");

		// Clock
		
		IBuffer clockManagerSystem = bufferFactory.getBuffer("CLOCK");
		listFrame = new LinkedList<Frame>();
		pageMisses = clockManagerSystem.runBuffer(pageToRead, listFrame, POOL_SIZE);
		System.out.println("Etat final : " + clockManagerSystem.toStringBuffer(listFrame) + " avec " + pageMisses
				+ " pages misses");

	}

}
