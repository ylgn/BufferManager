package restart;

import java.util.LinkedList;

public class BufferManagerQueue {
	final static int MAX_SIZE = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] pageToRead = { "A", "B", "C", "D", "E", "A", "B", "C", "D", "E" };
		LinkedList<String> listFrame = new LinkedList<String>();
		
		int pagesmisses = bufferLRU(pageToRead, listFrame);

		System.out.println("Voici l'état final "+ listFrame.toString()+" avec "+pagesmisses+" pages misses");

	}

	public static int bufferLRU(String[] pageToRead, LinkedList<String> listFrame) {
		int actualSize = 0;
		int pagesmisses =0;
		for (String page : pageToRead) {
			if (listFrame.contains(page)) {
				System.out.println("On rajoute" + page + " à la fin");
				listFrame.remove(page);
				listFrame.add(page);
			
			} else {
				if (actualSize == MAX_SIZE) {
					//System.out.println(listFrame.get(listFrame.size()) + " a été supproimé" + page + " a été ajouté");
					listFrame.remove(0);
					pagesmisses++;
					listFrame.add(page);
					System.out.println(listFrame.size());
				
			
				} else {
					// Initialisation
					actualSize++;
					listFrame.add(page);
					pagesmisses++;
					System.out.println(actualSize);
					System.out.println(page + " a été ajouté");
				}

			}

		}
		return pagesmisses;

	}

}
