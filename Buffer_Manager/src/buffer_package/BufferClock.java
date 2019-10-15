package buffer_package;

import java.util.LinkedList;

import factory_buffer.Frame;

public class BufferClock implements IBuffer {

	@Override
	public int runBuffer(String[] pageToRead, LinkedList<Frame> listFrame,int poolSize) {

		int pagesmisses = 0;
		for (String page : pageToRead) {
			if (listFrame.contains(getFrameByPage(page, listFrame))) {
				Frame currentFrame = getFrameByPage(page, listFrame);
				currentFrame.setFlagSecondeChance(1);
			} else {
				if (listFrame.size() == poolSize) {
					// System.out.println(listFrame.get(0) + " a été supproimé" + page + " a été
					// ajouté");
					for (Frame frame : listFrame) {
						if (frame.getFlagSecondeChance() == 0) {
							frame.setPage(page);
							frame.setFlagSecondeChance(1);
							nextClock(listFrame,poolSize);
							pagesmisses++;
							System.out.println(page + " a été ajouté");
							break;
						} else {
							frame.setFlagSecondeChance(1);
							nextClock(listFrame,poolSize);
						}
					}

				} else {

					listFrame.add(new Frame(page));
					nextClock(listFrame,poolSize);
					pagesmisses++;

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

	public static int nextClock(LinkedList<Frame> listFrame,int poolSize) {
		int indexClock = 0;
		for (Frame frame : listFrame) {
			if (frame.isHasTheClock()) {
				indexClock = listFrame.indexOf(frame);
				if (indexClock == poolSize) {
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

	public Frame getFrameByPage(String page, LinkedList<Frame> listFrame) {
		for (Frame frame : listFrame) {
			if (frame.getPage().equals(page)) {
				return frame;
			}
		}
		return null;
	}

}
