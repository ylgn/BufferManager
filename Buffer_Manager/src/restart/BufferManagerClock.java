package restart;

import java.util.LinkedList;

import factory_buffer.Frame;

public class BufferManagerClock {
	final static int MAX_SIZE = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] pageToRead = { "A", "B", "C", "D", "E", "A", "B", "C", "D", "E" };
		LinkedList<Frame> listFrame = new LinkedList<Frame>();
		// int[] secondChance = {0,0,0};
		// boolean[] hasToken = {true,false,false} ;

		int pagesmisses = bufferClock(pageToRead, listFrame);
		System.out.println("Voici l'état final " + toString(listFrame) + " avec " + pagesmisses+" page misses");

	}

	public static int bufferClock(String[] pageToRead, LinkedList<Frame> listFrame) {
		int actualSize = 0;
		int pagesmisses = 0;
		for (String page : pageToRead) {
			if (listFrame.contains(page)) { // Ça bug pcq
				// Récupérer la position du Page dans les Frame
				// A partir de la position => Mettre 1 à cette position dans le tableau
				// secondChance
				Frame currentFrame = getFrameByPage(page, listFrame);
				currentFrame.setFlagSecondeChance(1);

			} else {
				if (listFrame.size() == MAX_SIZE) {
					// System.out.println(listFrame.get(0) + " a été supproimé" + page + " a été
					// ajouté");
					for (Frame frame : listFrame) {
						if (frame.getFlagSecondeChance() == 0) {
							frame.setPage(page);
							frame.setFlagSecondeChance(1);
							nextClock(listFrame);
							pagesmisses++;
							System.out.println(page + " a été ajouté");
							break;
						} else {
							frame.setFlagSecondeChance(1);
							nextClock(listFrame);
						}
					}

				} else {

					listFrame.add(new Frame(page));
					nextClock(listFrame);
					pagesmisses++;

					System.out.println(page + " a été ajouté");
				}

			}

		}
		return pagesmisses;

	}

	public static int nextClock(LinkedList<Frame> listFrame) {
		int indexClock = 0;
		for (Frame frame : listFrame) {
			if (frame.isHasTheClock()) {
				indexClock = listFrame.indexOf(frame);
				if (indexClock == MAX_SIZE) {
					listFrame.get(0).setHasTheClock(true);
				} else {
					listFrame.get(indexClock + 1).setHasTheClock(true);
				}
				frame.setHasTheClock(false);
				return indexClock;
			}
		}
		return indexClock;
	}

	public static Frame getFrameByPage(String page, LinkedList<Frame> listFrame) {
		for (Frame frame : listFrame) {
			if (frame.getPage().equals(page)) {
				return frame;
			}
		}
		return null;
	}

	public static String toString(LinkedList<Frame> listFrame) {
		StringBuilder stBuilder = new StringBuilder();
		stBuilder.append("[");
		for (Frame frame : listFrame) {
			stBuilder.append(frame.getPage() + ", ");
		}

		stBuilder.append("]");
		return stBuilder.toString();
	}

}
