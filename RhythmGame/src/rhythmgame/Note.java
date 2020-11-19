package rhythmgame;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png"))
			.getImage();
	private Image noteBasicImageSpace1 = new ImageIcon(Main.class.getResource("../images/noteBasicSpace.png"))
			.getImage();
	private Image noteBasicImageSpace2 = new ImageIcon(Main.class.getResource("../images/noteBasicSpace2.png"))
			.getImage();
	
	private int x,y = 580- (1000 /Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	public Note(String noteType) {
		if(noteType.contentEquals("S")) {
			x=228;
		}
		else if(noteType.contentEquals("D")) {
			x=332;
		}
		else if(noteType.contentEquals("F")) {
			x=436;
		}
		else if(noteType.contentEquals("Space")) {
			x=540;
		}
		else if(noteType.contentEquals("J")) {
			x=744;
		}
		else if(noteType.contentEquals("K")) {
			x=848;
		}
		else if(noteType.contentEquals("L")) {
			x=952;
		}
		this.noteType=noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) { //spacebar ���� �� Ű
			g.drawImage(noteBasicImage, x+10, y, null);
		}
		else //spacebar�ΰ��
		{
			g.drawImage(noteBasicImageSpace1, x+30, y, null);
			g.drawImage(noteBasicImageSpace2, x+100, y, null);
		}
	}
	
	public void drop() {
		y+= Main.NOTE_SPEED; 
		if(y>620) { //��Ʈ�� �����ٸ� ����� ����
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try { 
			while(true) { //�迭�� ����� ��Ʈ�� ����Ʈ���ٰ���
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else { // ��Ʈ�� ������ ������ ����
					interrupt();
					break;
				}
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y>=633) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if(y>=613) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=593) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y>=565) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y>=553) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y>=533) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=513) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
		
}
	

