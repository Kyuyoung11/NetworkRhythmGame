package rhythmgame;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/leaf.png"))
			.getImage();
	
	private int x,y = 580- (1000 /Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private String titleName;
	
	
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
	public Note(String noteType, String titleName) {
		if(noteType.contentEquals("D")) {
			x=90;
		}
		else if(noteType.contentEquals("F")) {
			x=190;
		}
		else if(noteType.contentEquals("J")) {
			x=285;
		}
		else if(noteType.contentEquals("K")) {
			x=380;
		}
		this.noteType=noteType;
		this.titleName= titleName;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x+10, y, null);
		
	}
	
	public void drop() {
	      if (titleName.equals("K.K._Idol")) y+=(Main.NOTE_SPEED+1);
	      else if(titleName.equals("K.K._Western")) y+= (Main.NOTE_SPEED+1); 
	      else if (titleName.equals("K.K._House")) y+=(Main.NOTE_SPEED-1);
	      if(y>620) { //노트가 판정바를 벗어나는 지점
	         System.out.println("Miss");
	         close();
	      }
	   }
	
	@Override
	public void run() {
		try { 
			while(true) { //배열에 저장된 노트를 떨어트려줄거임
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else { // 노트가 끝나면 스레드 정지
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
		else if(y>=593) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=553) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y>=513) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y>=473) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y>=433) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=403) {
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
	

