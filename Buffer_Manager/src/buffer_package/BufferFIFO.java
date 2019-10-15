package buffer_package;

import java.util.LinkedList;

import factory_buffer.Frame;

public class BufferFIFO implements IBuffer {

	@Override
	public int runBuffer(String[] pageToRead, LinkedList<Frame> listFrame, int poolSize) {
		int pagesmisses = 0;
		for (String page : pageToRead) {
			if (listFrame.contains(getFrameByPage(page, listFrame))) {
				// Il se passe rien normal c'est la fifo.
			} else {
				if (listFrame.size() == poolSize) {
					System.out.println(listFrame.get(0).getPage() + " a été supprimé et " + page + " a été ajouté.");
					listFrame.remove(0);
					pagesmisses++;
					listFrame.add(new Frame(page));
					System.out.println("L'état du pool est : " + toStringBuffer(listFrame));

				} else {
					// Initialisation
					listFrame.add(new Frame(page));
					pagesmisses++;
					System.out.println(page + " a été ajouté à liste des frames ");
				}

			}

		}
		return pagesmisses;

	}

	public Frame getFrameByPage(String page, LinkedList<Frame> listFrame) {
		for (Frame frame : listFrame) {
			if (frame.getPage().equals(page)) {
				return frame;
			}
		}
		return null;
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

}
