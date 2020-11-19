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
	
	private String score;
	private int scorePoint;

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
		String score = Integer.toString(scorePoint);		
		g.drawString("Score", 150, 70);
		g.drawString(score, 300, 70);
		g.drawString("User1", 239,680);
		g.drawImage(blueFlareImage, 100, 400, null);
		g.drawImage(judgeImage, 120, 470, null);
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
			int StartTime = 4700 - Main.REACH_TIME * 1000;
			int gap = 55;
			beats = new Beat[] {
					new Beat(StartTime, "D"), new Beat(StartTime, "K"),
					new Beat(StartTime + gap*10, "D"), new Beat(StartTime + gap*10, "K"),
					new Beat(StartTime + gap*15, "F"), new Beat(StartTime + gap*15, "J"),
					new Beat(StartTime + gap*25, "F"), new Beat(StartTime + gap*25, "J"),
					
					new Beat(StartTime + gap*30, "F"), 
					new Beat(StartTime + gap*36, "J"), 
					new Beat(StartTime + gap*40, "F"), 
					
					new Beat(StartTime + gap*56, "D"), new Beat(StartTime + gap*56, "J"),
					new Beat(StartTime + gap*66, "D"), new Beat(StartTime + gap*66, "J"),
					new Beat(StartTime + gap*71, "F"), new Beat(StartTime + gap*71, "K"),
					new Beat(StartTime + gap*81, "F"), new Beat(StartTime + gap*81, "K"),
					
					new Beat(StartTime + gap*86, "D"), 
					new Beat(StartTime + gap*90, "F"), 
					new Beat(StartTime + gap*94, "J"), 
					new Beat(StartTime + gap*98, "K"), 
					
					new Beat(StartTime + gap*106, "F"),
					
					new Beat(StartTime + gap*125, "D"),
					new Beat(StartTime + gap*132, "F"),
					new Beat(StartTime + gap*136, "J"), 
					new Beat(StartTime + gap*140, "F"), 
					new Beat(StartTime + gap*145, "J"), 
					new Beat(StartTime + gap*150, "K"), 
					
					new Beat(StartTime + gap*185, "K"),
					new Beat(StartTime + gap*190, "J"),
					new Beat(StartTime + gap*194, "F"), 
					new Beat(StartTime + gap*198, "D"), 
					new Beat(StartTime + gap*205, "F"), 
					new Beat(StartTime + gap*210, "D"), 
					
					new Beat(StartTime + gap*243, "D"),
					new Beat(StartTime + gap*247, "D"),
					new Beat(StartTime + gap*251, "F"), 
					new Beat(StartTime + gap*255, "F"), 
					new Beat(StartTime + gap*259, "D"), 
					new Beat(StartTime + gap*263, "F"),
					new Beat(StartTime + gap*268, "J"),
					
					new Beat(StartTime + gap*301, "K"),
					new Beat(StartTime + gap*305, "J"),
					new Beat(StartTime + gap*309, "F"), 
					new Beat(StartTime + gap*313, "D"), 
					new Beat(StartTime + gap*317, "F"), 
					new Beat(StartTime + gap*321, "K"),
					new Beat(StartTime + gap*328, "K"),
					new Beat(StartTime + gap*335, "K"),
					new Beat(StartTime + gap*342, "K"),
					
					new Beat(StartTime + gap*359, "J"),
					new Beat(StartTime + gap*363, "J"),
					new Beat(StartTime + gap*367, "K"), 
					new Beat(StartTime + gap*371, "J"), 
					new Beat(StartTime + gap*375, "F"), 
					new Beat(StartTime + gap*382, "F"), 
					new Beat(StartTime + gap*389, "F"), 
					new Beat(StartTime + gap*396, "F"), 
					
					new Beat(StartTime + gap*425, "F"),
					new Beat(StartTime + gap*429, "F"),
					new Beat(StartTime + gap*433, "J"), 
					new Beat(StartTime + gap*437, "F"), 
					new Beat(StartTime + gap*441, "D"), 
					new Beat(StartTime + gap*448, "D"),
					new Beat(StartTime + gap*455, "D"),
					
					new Beat(StartTime + gap*476, "F"),
					new Beat(StartTime + gap*480, "F"),
					new Beat(StartTime + gap*484, "J"), 
					new Beat(StartTime + gap*488, "K"), 
					new Beat(StartTime + gap*492, "F"), 
					new Beat(StartTime + gap*496, "J"), 
					new Beat(StartTime + gap*503, "J"), 
					new Beat(StartTime + gap*510, "J"), 
					
					new Beat(StartTime + gap*531, "F"),
					new Beat(StartTime + gap*535, "F"),
					new Beat(StartTime + gap*539, "D"), 
					new Beat(StartTime + gap*543, "D"), 
					new Beat(StartTime + gap*547, "F"), 
					new Beat(StartTime + gap*551, "D"), 
					new Beat(StartTime + gap*558, "D"), 
					new Beat(StartTime + gap*565, "D"), 
					
					new Beat(StartTime + gap*575, "D"), new Beat(StartTime + gap*575, "K"), 
					
					new Beat(StartTime + gap*591, "K"),
					new Beat(StartTime + gap*598, "J"),
					new Beat(StartTime + gap*605, "F"), 
					
					new Beat(StartTime + gap*609, "D"), 
					new Beat(StartTime + gap*613, "F"), 
					new Beat(StartTime + gap*617, "D"), 
					new Beat(StartTime + gap*621, "F"), 
					new Beat(StartTime + gap*625, "D"),
					new Beat(StartTime + gap*629, "F"), 
					new Beat(StartTime + gap*633, "D"),
					new Beat(StartTime + gap*637, "F"), 
					
					new Beat(StartTime + gap*640, "J"), 
					new Beat(StartTime + gap*644, "K"), 
					new Beat(StartTime + gap*648, "J"), 
					new Beat(StartTime + gap*652, "K"), 
					new Beat(StartTime + gap*656, "J"),
					new Beat(StartTime + gap*660, "K"), 
					new Beat(StartTime + gap*664, "J"),
					new Beat(StartTime + gap*668, "K"), 
					
					new Beat(StartTime + gap*677, "D"), new Beat(StartTime + gap*677, "K"), 
					new Beat(StartTime + gap*684, "D"), new Beat(StartTime + gap*684, "K"),
					new Beat(StartTime + gap*691, "D"), new Beat(StartTime + gap*691, "K"), 
					new Beat(StartTime + gap*698, "D"), new Beat(StartTime + gap*698, "K"),
					new Beat(StartTime + gap*705, "D"), new Beat(StartTime + gap*705, "K"),
					new Beat(StartTime + gap*712, "D"), new Beat(StartTime + gap*712, "K"),
					new Beat(StartTime + gap*719, "D"), new Beat(StartTime + gap*719, "K"),
					
					new Beat(StartTime + gap*726, "F"), 
					new Beat(StartTime + gap*730, "J"), 
					new Beat(StartTime + gap*734, "F"), 
					new Beat(StartTime + gap*738, "J"), 
					new Beat(StartTime + gap*742, "F"),
					new Beat(StartTime + gap*746, "J"), 
					new Beat(StartTime + gap*750, "F"),
					new Beat(StartTime + gap*754, "J"), 
					
					new Beat(StartTime + gap*758, "D"), 
					new Beat(StartTime + gap*762, "K"), 
					new Beat(StartTime + gap*766, "D"), 
					new Beat(StartTime + gap*770, "K"), 
					new Beat(StartTime + gap*774, "D"),
					new Beat(StartTime + gap*778, "K"), 
					new Beat(StartTime + gap*782, "D"),
					new Beat(StartTime + gap*786, "K"), 
					
					new Beat(StartTime + gap*793, "D"), new Beat(StartTime + gap*793, "F"), 
					new Beat(StartTime + gap*800, "J"), new Beat(StartTime + gap*800, "K"),
					new Beat(StartTime + gap*807, "D"), new Beat(StartTime + gap*807, "F"), 
					new Beat(StartTime + gap*814, "J"), new Beat(StartTime + gap*814, "K"),
					new Beat(StartTime + gap*821, "D"), new Beat(StartTime + gap*821, "F"),
					new Beat(StartTime + gap*828, "J"), new Beat(StartTime + gap*828, "K"),
					new Beat(StartTime + gap*835, "D"), new Beat(StartTime + gap*835, "F"),
					
					new Beat(StartTime + gap*842, "D"), 
					new Beat(StartTime + gap*846, "J"), 
					new Beat(StartTime + gap*850, "F"), 
					new Beat(StartTime + gap*854, "K"), 
					new Beat(StartTime + gap*858, "D"),
					new Beat(StartTime + gap*862, "J"), 
					new Beat(StartTime + gap*866, "F"),
					new Beat(StartTime + gap*870, "K"), 
					
					new Beat(StartTime + gap*874, "D"), 
					new Beat(StartTime + gap*876, "J"), 
					new Beat(StartTime + gap*880, "F"), 
					new Beat(StartTime + gap*884, "K"), 
					new Beat(StartTime + gap*888, "D"),
					new Beat(StartTime + gap*892, "J"), 
					new Beat(StartTime + gap*896, "F"),
					new Beat(StartTime + gap*900, "K"), 
					
					new Beat(StartTime + gap*907, "D"), new Beat(StartTime + gap*907, "F"), 
					new Beat(StartTime + gap*912, "F"), new Beat(StartTime + gap*912, "J"),
					new Beat(StartTime + gap*917, "J"), new Beat(StartTime + gap*917, "K"), 
					new Beat(StartTime + gap*922, "D"), new Beat(StartTime + gap*922, "F"),
					new Beat(StartTime + gap*925, "J"), new Beat(StartTime + gap*925, "K"),
					new Beat(StartTime + gap*927, "D"), new Beat(StartTime + gap*929, "F"),
					new Beat(StartTime + gap*931, "J"), new Beat(StartTime + gap*933, "K"),
					
				

					
					
			};

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
					new Beat(startTime + gap * 4, "D"), 
					new Beat(startTime + gap * 4, "K"),
					new Beat(startTime + gap * 5, "J"),
					// new Beat(startTime + gap *9,"D"),//+1
					new Beat(startTime + gap * 5, "F"), // +3
					
					new Beat(startTime + gap * 26, "D"),
					// new Beat(startTime + gap *46,"D"),
					new Beat(startTime + gap * 32, "K"), 
					new Beat(startTime + gap * 38, "F"),
					new Beat(startTime + gap * 44, "J"),

					new Beat(startTime + gap * 48, "D"), 
					new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 58, "D"),
					new Beat(startTime + gap * 59, "F"),
					new Beat(startTime + gap * 60, "J"),
					new Beat(startTime + gap * 61, "K"),
					// new Beat(startTime + gap *57,"D"),//+1
					new Beat(startTime + gap * 72, "K"),
					new Beat(startTime + gap * 73, "J"),
					new Beat(startTime + gap * 74, "F"), // +13
					new Beat(startTime + gap * 83, "J"), 
					new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 96, "K"),
					new Beat(startTime + gap * 97, "J"),
					new Beat(startTime + gap * 98, "K"),
				
					new Beat(startTime + gap * 99, "J"),


					new Beat(startTime + gap * 100, "F"), 
					new Beat(startTime + gap * 107, "D"),
					
					new Beat(startTime + gap * 108, "F"),
					new Beat(startTime + gap * 109, "J"), 
					new Beat(startTime + gap * 110, "K"),
					//new Beat(startTime + gap * 109, "F"),
					// new Beat(startTime + gap *94,"K"),
					// new Beat(startTime + gap *95,"J"),
					new Beat(startTime + gap * 117, "K"),
					new Beat(startTime + gap * 117, "D"),
					
					new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 123, "J"),
					new Beat(startTime + gap * 148, "K"),
					new Beat(startTime + gap * 155, "K"),

					new Beat(startTime + gap * 158, "F"), 
					new Beat(startTime + gap * 159, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 160, "D"),
					new Beat(startTime + gap * 166, "D"),
					new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 171, "J"),
					new Beat(startTime + gap * 172, "K"),
					
					// new Beat(startTime + gap *109,"K"),
					new Beat(startTime + gap * 183, "K"), 
					new Beat(startTime + gap * 184, "D"),

					new Beat(startTime + gap * 194, "J"), 
					new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 196, "J"),
					new Beat(startTime + gap * 197, "F"),
					new Beat(startTime + gap * 198, "K"), 
					new Beat(startTime + gap * 210, "K"),
					new Beat(startTime + gap * 213, "J"), 
					new Beat(startTime + gap * 215, "F"),
					new Beat(startTime + gap * 218, "D"), 
					new Beat(startTime + gap * 222, "F"),
					new Beat(startTime + gap * 222, "J"),
					//반복
					new Beat(startTime + gap * 244, "D"), 
					new Beat(startTime + gap * 246, "F"),
					new Beat(startTime + gap * 255, "D"),
					new Beat(startTime + gap * 256, "F"),
					new Beat(startTime + gap * 257, "J"),
					new Beat(startTime + gap * 258, "K"),
					
					new Beat(startTime + gap * 268, "K"),
					new Beat(startTime + gap * 269, "J"),
					new Beat(startTime + gap * 270, "F"),
					
					new Beat(startTime + gap * 279, "J"), 
					new Beat(startTime + gap * 283, "D"),
					new Beat(startTime + gap * 292, "K"),
					new Beat(startTime + gap * 295, "F"),
					
					new Beat(startTime + gap * 302, "D"),					
					new Beat(startTime + gap * 303, "F"),
					new Beat(startTime + gap * 304, "J"), 
					new Beat(startTime + gap * 305, "K"),
				
					new Beat(startTime + gap * 314, "K"),
					new Beat(startTime + gap * 314, "D"),
					
					new Beat(startTime + gap * 320, "F"),
					new Beat(startTime + gap * 320, "J"),
					new Beat(startTime + gap * 343, "K"),
					new Beat(startTime + gap * 350, "K"),

					new Beat(startTime + gap * 353, "F"), 
					new Beat(startTime + gap * 354, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 356, "D"),
					new Beat(startTime + gap * 360, "D"),
					new Beat(startTime + gap * 364, "F"),
					new Beat(startTime + gap * 367, "J"),
					new Beat(startTime + gap * 368, "K"),
					
					new Beat(startTime + gap * 380, "K"), 
					new Beat(startTime + gap * 380, "D"),
					
					new Beat(startTime + gap * 390, "J"), 
					//new Beat(startTime + gap * 391, "F"),
					new Beat(startTime + gap * 394, "K"), 
					new Beat(startTime + gap * 406, "K"),
					new Beat(startTime + gap * 410, "J"), 
					new Beat(startTime + gap * 412, "F"),
					new Beat(startTime + gap * 414, "D"), 
					new Beat(startTime + gap * 418, "F"),
					new Beat(startTime + gap * 418, "J")			
					
					
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
			scorePoint += -1;			
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		} else if (judge.equals("Late")) {
			scorePoint += 2;
			judgeImage = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();
		} else if (judge.equals("Good")) {
			scorePoint += 5;
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
		} else if (judge.equals("Great")) {
			scorePoint += 10;
			judgeImage = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();
		} else if (judge.equals("Perfect")) {
			scorePoint += 20;
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
		} else if (judge.equals("Early")) {
			scorePoint += 4;
			judgeImage = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();
		}
	}

}
