package buffer_package;

import java.util.LinkedList;

import factory_buffer.Frame;

public interface IBuffer {
	
	public int runBuffer(String[] pageToRead, LinkedList<Frame> listFrame, int poolSize);
	public String toStringBuffer(LinkedList<Frame> listFrame);	
	public Frame getFrameByPage(String page, LinkedList<Frame> listFrame);
}
