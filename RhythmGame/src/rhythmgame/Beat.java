package rhythmgame;

import java.util.Random;

public class Beat {
	Random random = new Random();
	private int time;
	private String noteName;
	private String type;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public String getItemType() {
		return type;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
		this.type = setType();
	}
	
	public String setType() {
		
		int rannum = random.nextInt(100);
		if (rannum > 97) return "item1"; //애플
		else if (rannum > 94) return "item2"; //벨주머니
		else if (rannum >91) return "item3"; //사이다
		else if (rannum >88) return "item4"; //벌집
		else return "basic";
		
	}
}
