package restart;

import java.util.LinkedList;

public class BufferManagerFIFO {
	final static int MAX_SIZE = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] pageToRead = { "A", "B", "C", "D", "E", "A", "B", "C", "D", "E" };
		LinkedList<String> listFrame = new LinkedList<String>();
		int pagesmisses = bufferFIFO(pageToRead, listFrame);
		System.out.println("Voici l'état final " + listFrame.toString() + " avec " + pagesmisses + " pages misses");

	}

	public static int bufferFIFO(String[] pageToRead, LinkedList<String> listFrame) {
		int pagesmisses = 0;
		for (String page : pageToRead) {
			if (listFrame.contains(page)) {

			} else {
				if (listFrame.size() == MAX_SIZE) {
					System.out.println(listFrame.get(0) + " a été supprimé et " + page + " a été ajouté.");
					listFrame.remove(0);
					pagesmisses++;
					listFrame.add(page);
					System.out.println("L'état du pool est : " + listFrame.toString());

				} else {
					// Initialisation
					listFrame.add(page);
					pagesmisses++;
					System.out.println(page + " a été ajouté à liste des frames " + listFrame.toString());
				}

			}

		}
		return pagesmisses;

	}

}
