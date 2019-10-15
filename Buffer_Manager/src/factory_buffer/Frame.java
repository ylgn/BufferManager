package factory_buffer;

public class Frame {
	private String page;
	private int flagSecondeChance = 0;
	private boolean hasTheClock;

	public Frame(String page) {
		this.page =page;
	}

	public boolean isHasTheClock() {
		return hasTheClock;
	}

	public void setHasTheClock(boolean hasTheClock) {
		this.hasTheClock = hasTheClock;
	}

	public int getFlagSecondeChance() {
		return flagSecondeChance;
	}

	public void setFlagSecondeChance(int flagSecondeChance) {
		this.flagSecondeChance = flagSecondeChance;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	

}
