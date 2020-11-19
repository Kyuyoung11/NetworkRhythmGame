package rhythmgame;
//게임에 대한 컨트롤 작업

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameinfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();

	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	// 다른 사용자 1의 노트 이미지
	private Image noteRouteDImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	
	

	private Image blueFlareImage;
	private Image judgeImage;

	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();

	private String titleName;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>(); // 각 note를 저장할 배열

	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false); // 한번만 실행
	}
	

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteDImage, 100, 90, null);
		g.drawImage(noteRouteFImage, 196, 90, null);
		g.drawImage(noteRouteJImage, 292, 90, null);
		g.drawImage(noteRouteKImage, 388, 90, null);

		g.drawImage(noteRouteLineImage, 100, 90, null);
		g.drawImage(noteRouteLineImage, 196, 90, null);
		g.drawImage(noteRouteLineImage, 292, 90, null);
		g.drawImage(noteRouteLineImage, 388, 90, null);
		g.drawImage(noteRouteLineImage, 484, 90, null);

		g.drawImage(judgementLineImage, 100, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
			note.screenDraw(g);
		}

		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("D", 143, 609);
		g.drawString("F", 239, 609);
		g.drawString("J", 335, 609);
		g.drawString("K", 431, 609);
		
		//점수 표시
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		g.drawString("Score", 150, 70);
		g.drawString("00000", 300, 70);
		g.drawString("User1", 239,680);
		g.drawImage(blueFlareImage, 280, 280, null);
		g.drawImage(judgeImage, 450, 400, null);
		g.drawImage(keyPadDImage, 100, 580, null);
		g.drawImage(keyPadFImage, 196, 580, null);
		g.drawImage(keyPadJImage, 292, 580, null);
		g.drawImage(keyPadKImage, 388, 580, null);
		
		
		
		
		g.drawImage(noteRouteDImageU1, 796, 90, null);
		g.drawImage(noteRouteFImageU1, 892, 90, null);
		g.drawImage(noteRouteJImageU1, 988, 90, null);
		g.drawImage(noteRouteKImageU1, 1084, 90, null);

		g.drawImage(noteRouteLineImage, 796, 90, null);
		g.drawImage(noteRouteLineImage, 892, 90, null);
		g.drawImage(noteRouteLineImage, 988, 90, null);
		g.drawImage(noteRouteLineImage, 1084, 90, null);
		g.drawImage(noteRouteLineImage, 1180, 90, null);
		
		g.drawImage(judgementLineImage, 796, 580, null);
		
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("D", 839, 609);
		g.drawString("F", 935, 609);
		g.drawString("J", 1031, 609);
		g.drawString("K", 1127, 609);
		
		//점수 표시
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		g.drawString("Score", 846, 70);
		g.drawString("00000", 996, 70);
		g.drawString("User2", 935,680);
		//g.drawImage(blueFlareImage, 280, 280, null);
		//g.drawImage(judgeImage, 450, 400, null);
		g.drawImage(keyPadDImage, 796, 580, null);
		g.drawImage(keyPadFImage, 892, 580, null);
		g.drawImage(keyPadJImage, 988, 580, null);
		g.drawImage(keyPadKImage, 1084, 580, null);
	

	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("dSmall.mp3", false).start();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();

	}

	public void releaseD() {

		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();

		new Music("dSmall.mp3", false).start();

	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();

		new Music("dSmall.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();

		new Music("dSmall.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if (titleName.equals("K.K._Idol")) {
			int StartTime = 3500 - Main.REACH_TIME * 1000;
			int gap = 225;
			beats = new Beat[] {

					new Beat(StartTime + gap * 2, "D"), new Beat(StartTime + gap * 4, "F"),
					new Beat(StartTime + gap * 6, "J"), new Beat(StartTime + gap * 10, "K"),
					new Beat(StartTime + gap * 12, "Space"), new Beat(StartTime + gap * 14, "L"),

					new Beat(StartTime + gap * 16, "F"), new Beat(StartTime + gap * 19, "D"),
					new Beat(StartTime + gap * 22, "S"), new Beat(StartTime + gap * 24, "J"),
					new Beat(StartTime + gap * 26, "K"), new Beat(StartTime + gap * 30, "L"),

					new Beat(StartTime + gap * 33, "Space"),

					// 두번째
					new Beat(StartTime + gap * 42, "S"), new Beat(StartTime + gap * 44, "Space"),
					new Beat(StartTime + gap * 46, "L"), new Beat(StartTime + gap * 50, "S"),
					new Beat(StartTime + gap * 52, "Space"), new Beat(StartTime + gap * 54, "L"),

					new Beat(StartTime + gap * 58, "D"), new Beat(StartTime + gap * 60, "Space"),
					new Beat(StartTime + gap * 62, "K"), new Beat(StartTime + gap * 66, "D"),
					new Beat(StartTime + gap * 68, "Space"), new Beat(StartTime + gap * 70, "K"),

					new Beat(StartTime + gap * 72, "F"), new Beat(StartTime + gap * 75, "D"),
					new Beat(StartTime + gap * 78, "S"), new Beat(StartTime + gap * 80, "J"),
					new Beat(StartTime + gap * 82, "K"), new Beat(StartTime + gap * 86, "L"),

					new Beat(StartTime + gap * 89, "Space"),

					new Beat(StartTime + gap * 98, "S"), new Beat(StartTime + gap * 100, "Space"),
					new Beat(StartTime + gap * 102, "L"), new Beat(StartTime + gap * 106, "S"),
					new Beat(StartTime + gap * 108, "Space"), new Beat(StartTime + gap * 110, "L"),

					new Beat(StartTime + gap * 114, "D"), new Beat(StartTime + gap * 116, "Space"),
					new Beat(StartTime + gap * 118, "K"), new Beat(StartTime + gap * 122, "D"),
					new Beat(StartTime + gap * 124, "Space"), new Beat(StartTime + gap * 126, "K"),

					new Beat(StartTime + gap * 128, "F"), new Beat(StartTime + gap * 131, "D"),
					new Beat(StartTime + gap * 134, "S"), new Beat(StartTime + gap * 136, "J"),
					new Beat(StartTime + gap * 138, "K"), new Beat(StartTime + gap * 142, "L"),

					new Beat(StartTime + gap * 145, "Space"),

					new Beat(StartTime + gap * 154, "S"), new Beat(StartTime + gap * 156, "Space"),
					new Beat(StartTime + gap * 158, "L"), new Beat(StartTime + gap * 162, "S"),
					new Beat(StartTime + gap * 164, "Space"), new Beat(StartTime + gap * 166, "L"),

					new Beat(StartTime + gap * 170, "D"), new Beat(StartTime + gap * 172, "Space"),
					new Beat(StartTime + gap * 174, "K"), new Beat(StartTime + gap * 178, "D"),
					new Beat(StartTime + gap * 180, "Space"), new Beat(StartTime + gap * 182, "K"),

					new Beat(StartTime + gap * 184, "F"), new Beat(StartTime + gap * 187, "D"),
					new Beat(StartTime + gap * 190, "S"), new Beat(StartTime + gap * 192, "J"),
					new Beat(StartTime + gap * 194, "K"), new Beat(StartTime + gap * 198, "L"),

					new Beat(StartTime + gap * 201, "Space"),

					new Beat(StartTime + gap * 210, "S"), new Beat(StartTime + gap * 212, "Space"),
					new Beat(StartTime + gap * 214, "L"), new Beat(StartTime + gap * 218, "S"),
					new Beat(StartTime + gap * 220, "Space"), new Beat(StartTime + gap * 222, "L"),

					new Beat(StartTime + gap * 226, "D"), new Beat(StartTime + gap * 228, "Space"),
					new Beat(StartTime + gap * 230, "K"), new Beat(StartTime + gap * 234, "D"),
					new Beat(StartTime + gap * 236, "Space"), new Beat(StartTime + gap * 238, "K"),

					new Beat(StartTime + gap * 240, "F"), new Beat(StartTime + gap * 243, "D"),
					new Beat(StartTime + gap * 246, "S"), new Beat(StartTime + gap * 248, "J"),
					new Beat(StartTime + gap * 250, "K"), new Beat(StartTime + gap * 254, "L"),

					new Beat(StartTime + gap * 257, "Space"), };

		} else if (titleName.equals("K.K._House")) {
			int StartTime = 3500 - Main.REACH_TIME * 1000;
			int gap = 214;
			beats = new Beat[] { new Beat(StartTime, "K"), new Beat(StartTime + gap * 4, "D"),
					new Beat(StartTime + gap * 6, "J"), new Beat(StartTime + gap * 8, "S"),
					new Beat(StartTime + gap * 11, "F"), new Beat(StartTime + gap * 13, "Space"),

					// 두번쨰
					new Beat(StartTime + gap * 15, "K"), new Beat(StartTime + gap * 19, "D"),
					new Beat(StartTime + gap * 21, "J"), new Beat(StartTime + gap * 23, "S"),
					new Beat(StartTime + gap * 26, "F"), new Beat(StartTime + gap * 28, "Space"),

					new Beat(StartTime + gap * 30, "K"), new Beat(StartTime + gap * 34, "D"),
					new Beat(StartTime + gap * 36, "J"), new Beat(StartTime + gap * 38, "S"),
					new Beat(StartTime + gap * 41, "F"), new Beat(StartTime + gap * 43, "Space"),

					new Beat(StartTime + gap * 45, "K"), new Beat(StartTime + gap * 49, "D"),
					new Beat(StartTime + gap * 51, "J"), new Beat(StartTime + gap * 53, "S"),
					new Beat(StartTime + gap * 56, "F"), new Beat(StartTime + gap * 58, "Space"),

					new Beat(StartTime + gap * 60, "K"), new Beat(StartTime + gap * 64, "D"),
					new Beat(StartTime + gap * 66, "J"), new Beat(StartTime + gap * 68, "S"),
					new Beat(StartTime + gap * 71, "F"), new Beat(StartTime + gap * 73, "Space"),

					new Beat(StartTime + gap * 75, "K"), new Beat(StartTime + gap * 79, "D"),
					new Beat(StartTime + gap * 81, "J"), new Beat(StartTime + gap * 83, "S"),
					new Beat(StartTime + gap * 86, "F"), new Beat(StartTime + gap * 88, "Space"),

					new Beat(StartTime + gap * 90, "K"), new Beat(StartTime + gap * 94, "D"),
					new Beat(StartTime + gap * 96, "J"), new Beat(StartTime + gap * 98, "S"),
					new Beat(StartTime + gap * 101, "F"), new Beat(StartTime + gap * 103, "Space"),

					new Beat(StartTime + gap * 105, "K"), new Beat(StartTime + gap * 109, "D"),
					new Beat(StartTime + gap * 111, "J"), new Beat(StartTime + gap * 113, "S"),
					new Beat(StartTime + gap * 116, "F"), new Beat(StartTime + gap * 118, "Space"),

					new Beat(StartTime + gap * 120, "K"), new Beat(StartTime + gap * 124, "D"),
					new Beat(StartTime + gap * 126, "J"), new Beat(StartTime + gap * 128, "S"),
					new Beat(StartTime + gap * 131, "F"), new Beat(StartTime + gap * 133, "Space"),

					new Beat(StartTime + gap * 135, "K"), new Beat(StartTime + gap * 139, "D"),
					new Beat(StartTime + gap * 141, "J"), new Beat(StartTime + gap * 143, "S"),
					new Beat(StartTime + gap * 146, "F"), new Beat(StartTime + gap * 148, "Space"),

					new Beat(StartTime + gap * 150, "K"), new Beat(StartTime + gap * 154, "D"),
					new Beat(StartTime + gap * 156, "J"), new Beat(StartTime + gap * 158, "S"),
					new Beat(StartTime + gap * 161, "F"), new Beat(StartTime + gap * 163, "Space"),

					new Beat(StartTime + gap * 165, "K"), new Beat(StartTime + gap * 169, "D"),
					new Beat(StartTime + gap * 171, "J"), new Beat(StartTime + gap * 173, "S"),
					new Beat(StartTime + gap * 176, "F"), new Beat(StartTime + gap * 178, "Space"),

					new Beat(StartTime + gap * 180, "K"), new Beat(StartTime + gap * 184, "D"),
					new Beat(StartTime + gap * 186, "J"), new Beat(StartTime + gap * 188, "S"),
					new Beat(StartTime + gap * 191, "F"), new Beat(StartTime + gap * 193, "Space")

			};

		} else if (titleName.equals("K.K._Western")) {
			int startTime = 4450 - Main.REACH_TIME * 1000;
			int gap = 140;
			beats = new Beat[] { 
					new Beat(startTime + gap * 1, "D"), 
					new Beat(startTime + gap * 1, "K"),
					new Beat(startTime + gap * 2, "J"),
					// new Beat(startTime + gap *9,"D"),//+1
					new Beat(startTime + gap * 2, "F"), // +3
					
					new Beat(startTime + gap * 24, "D"),
					// new Beat(startTime + gap *46,"D"),
					new Beat(startTime + gap * 30, "K"), 
					new Beat(startTime + gap * 36, "F"),
					new Beat(startTime + gap * 42, "J"),

					new Beat(startTime + gap * 46, "D"), 
					new Beat(startTime + gap * 48, "F"),
					new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "J"),
					new Beat(startTime + gap * 59, "K"),
					// new Beat(startTime + gap *57,"D"),//+1
					new Beat(startTime + gap * 70, "K"),
					new Beat(startTime + gap * 71, "J"),
					new Beat(startTime + gap * 72, "F"), // +1

					new Beat(startTime + gap * 81, "J"), 
					new Beat(startTime + gap * 85, "D"),
					new Beat(startTime + gap * 94, "K"),
					new Beat(startTime + gap * 95, "J"),
					new Beat(startTime + gap * 96, "K"),
					
					new Beat(startTime + gap * 97, "J"),


					new Beat(startTime + gap * 98, "F"), 
					new Beat(startTime + gap * 105, "D"),
					
					new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 107, "J"), 
					new Beat(startTime + gap * 108, "K"),
					//new Beat(startTime + gap * 109, "F"),
					// new Beat(startTime + gap *94,"K"),
					// new Beat(startTime + gap *95,"J"),
					new Beat(startTime + gap * 114, "K"),
					new Beat(startTime + gap * 114, "D"),
					
					new Beat(startTime + gap * 120, "F"),
					new Beat(startTime + gap * 120, "J"),
					new Beat(startTime + gap * 146, "K"),
					new Beat(startTime + gap * 153, "K"),

					new Beat(startTime + gap * 156, "F"), 
					new Beat(startTime + gap * 157, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 158, "D"),
					new Beat(startTime + gap * 164, "D"),
					new Beat(startTime + gap * 167, "F"),
					new Beat(startTime + gap * 169, "J"),
					new Beat(startTime + gap * 170, "K"),
					
					// new Beat(startTime + gap *109,"K"),
					new Beat(startTime + gap * 181, "K"), 
					new Beat(startTime + gap * 182, "D"),

					new Beat(startTime + gap * 192, "J"), 
					new Beat(startTime + gap * 193, "F"),
					new Beat(startTime + gap * 194, "J"),
					new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 196, "K"), 
					new Beat(startTime + gap * 208, "K"),
					new Beat(startTime + gap * 211, "J"), 
					new Beat(startTime + gap * 213, "F"),
					new Beat(startTime + gap * 216, "D"), 
					new Beat(startTime + gap * 219, "F"),
					new Beat(startTime + gap * 219, "J"),
					//반복
					new Beat(startTime + gap * 242, "D"), 
					new Beat(startTime + gap * 244, "F"),
					new Beat(startTime + gap * 253, "D"),
					new Beat(startTime + gap * 254, "F"),
					new Beat(startTime + gap * 255, "J"),
					new Beat(startTime + gap * 256, "K"),
					
					new Beat(startTime + gap * 266, "K"),
					new Beat(startTime + gap * 267, "J"),
					new Beat(startTime + gap * 268, "F"),
					
					new Beat(startTime + gap * 277, "J"), 
					new Beat(startTime + gap * 281, "D"),
					new Beat(startTime + gap * 290, "K"),
					new Beat(startTime + gap * 293, "F"),
					 
					new Beat(startTime + gap * 300, "D"),					
					new Beat(startTime + gap * 301, "F"),
					new Beat(startTime + gap * 302, "J"), 
					new Beat(startTime + gap * 303, "K"),
					
					new Beat(startTime + gap * 311, "K"),
					new Beat(startTime + gap * 311, "D"),
					
					new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 317, "J"),
					new Beat(startTime + gap * 341, "K"),
					new Beat(startTime + gap * 348, "K"),

					new Beat(startTime + gap * 351, "F"), 
					new Beat(startTime + gap * 352, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 354, "D"),
					new Beat(startTime + gap * 359, "D"),
					new Beat(startTime + gap * 362, "F"),
					new Beat(startTime + gap * 365, "J"),
					new Beat(startTime + gap * 366, "K"),
					
					new Beat(startTime + gap * 378, "K"), 
					new Beat(startTime + gap * 378, "D"),
					
					new Beat(startTime + gap * 388, "J"), 
					//new Beat(startTime + gap * 391, "F"),
					new Beat(startTime + gap * 392, "K"), 
					new Beat(startTime + gap * 404, "K"),
					new Beat(startTime + gap * 408, "J"), 
					new Beat(startTime + gap * 410, "F"),
					new Beat(startTime + gap * 411, "D"), 
					new Beat(startTime + gap * 415, "F"),
					new Beat(startTime + gap * 415, "J")			
					
					
			};
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName(), titleName);
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

				}
			}
		}
	}

	// 판정 함수 - 가장 아래있는 노트만을 판정
	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();
		}
	}

}
