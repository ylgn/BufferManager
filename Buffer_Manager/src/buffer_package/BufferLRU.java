package buffer_package;

import java.util.LinkedList;

import factory_buffer.Frame;

public class BufferLRU implements IBuffer {

	@Override
	public int runBuffer(String[] pageToRead, LinkedList<Frame> listFrame, int poolSize) {
		int actualSize = 0;
		int pagesmisses =0;
		for (String page : pageToRead) {
			if (listFrame.contains(getFrameByPage(page, listFrame))) {
				System.out.println("On rajoute" + page + " à la fin");
				listFrame.remove(getFrameByPage(page, listFrame));
				listFrame.add(new Frame(page));
			
			} else {
				if (actualSize == poolSize) {
					//System.out.println(listFrame.get(listFrame.size()) + " a été supproimé" + page + " a été ajouté");
					listFrame.remove(0);
					pagesmisses++;
					listFrame.add(new Frame(page));
					System.out.println(listFrame.size());
				
			
				} else {
					// Initialisation
					actualSize++;
					listFrame.add(new Frame(page));
					pagesmisses++;
					System.out.println(actualSize);
					System.out.println(page + " a été ajouté");
				}

			}

		}
		return pagesmisses;
	}

	@Override
	public String toStringBuffer(LinkedList<Frame> listFrame) {
		StringBuilder stBuilder = new StringBuilder();
		stBuilder.append("[");
		for (Frame frame : listFrame) {
			stBuilder.append(frame.getPage() + ", ");
		}

		stBuilder.append("]");
		return stBuilder.toString();
	}

	@Override
	public Frame getFrameByPage(String page, LinkedList<Frame> listFrame) {
		for (Frame frame : listFrame) {
			if (frame.getPage().equals(page)) {
				return frame;
			}
		}
		return null;
	}

}
