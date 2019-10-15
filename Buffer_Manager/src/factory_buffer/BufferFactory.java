package factory_buffer;

import buffer_package.BufferClock;
import buffer_package.BufferFIFO;
import buffer_package.BufferLRU;
import buffer_package.IBuffer;

public class BufferFactory {
	   public IBuffer getBuffer(String shapeType){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("LRU")){
	         return new BufferLRU();
	         
	      } else if(shapeType.equalsIgnoreCase("FIFO")){
	         return new BufferFIFO();
	         
	      } else if(shapeType.equalsIgnoreCase("CLOCK")){
	         return new BufferClock();
	      }
	      
	      return null;
	   }
}
