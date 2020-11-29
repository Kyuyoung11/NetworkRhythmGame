package rhythmgame;
//게임에 대한 컨트롤 작업

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import rhythmgame.DynamicBeat.ListenNetwork;

public class Game extends Thread {

	private String UserName;

	private int scorePoint;
	private int otherScorePoint;
	private int appleCount = 0;
	private int saidaCount = 0;
	public static int qq = 1;

	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

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

	private Image itemSaida = new ImageIcon(Main.class.getResource("../images/saida.png")).getImage();
	private Image itemApple = new ImageIcon(Main.class.getResource("../images/apple.png")).getImage();

	private Image blueFlareImage, blueFlareImage2;
	private Image judgeImage, judgeImage2;

	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();

	private Image keyPadDImage1 = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadFImage1 = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadJImage1 = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private Image keyPadKImage1 = new ImageIcon(Main.class.getResource("../images/keypadBasic.png")).getImage();
	private String titleName;
	private String musicTitle;
	private Music gameMusic;

	private String noteType;

	ArrayList<Note> noteList = new ArrayList<Note>(); // 각 note를 저장할 배열
	ArrayList<Note> noteList2 = new ArrayList<Note>();

	public Game(String titleName, String musicTitle, String username, ObjectInputStream ois, ObjectOutputStream oos,
			Socket socket) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		UserName = username;
		this.ois = ois;
		this.oos = oos;
		this.socket = socket;
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

		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("D", 143, 609);
		g.drawString("F", 239, 609);
		g.drawString("J", 335, 609);
		g.drawString("K", 431, 609);

		// 점수 표시
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		String score = Integer.toString(scorePoint);
		g.drawString("Score", 160, 70);
		g.drawString(score, 310, 70);
		g.drawString("User1", 239, 680);
		g.drawImage(blueFlareImage, 100, 400, null);
		g.drawImage(judgeImage, 120, 470, null);
		g.drawImage(keyPadDImage, 100, 580, null);
		g.drawImage(keyPadFImage, 196, 580, null);
		g.drawImage(keyPadJImage, 292, 580, null);
		g.drawImage(keyPadKImage, 388, 580, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		// g.drawString("Score", 150, 70);
		String appCount = Integer.toString(appleCount);
		g.drawString(appCount, 45, 280);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		// g.drawString("Score", 150, 70);
		String saiCount = Integer.toString(saidaCount);
		g.drawString(saiCount, 45, 520);

		g.drawImage(itemSaida, 10, 330, null);
		g.drawImage(itemApple, 8, 150, null);

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
		g.drawImage(blueFlareImage2, 796, 400, null);
		g.drawImage(judgeImage2, 816, 470, null);

		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("D", 839, 609);
		g.drawString("F", 935, 609);
		g.drawString("J", 1031, 609);
		g.drawString("K", 1127, 609);

		// 점수 표시
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		g.drawString("Score", 846, 70);
		String otherScore = Integer.toString(otherScorePoint);
		g.drawString(otherScore, 996, 70);
		g.drawString("User2", 935, 680);
		// g.drawImage(blueFlareImage, 280, 280, null);
		// g.drawImage(judgeImage, 450, 400, null);
		g.drawImage(keyPadDImage1, 796, 580, null);
		g.drawImage(keyPadFImage1, 892, 580, null);
		g.drawImage(keyPadJImage1, 988, 580, null);
		g.drawImage(keyPadKImage1, 1084, 580, null);

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

		for (int i = 0; i < noteList2.size(); i++) {
			Note note = noteList2.get(i);
			if (note.getY() > 620) {
				//judgeImage2 = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList2.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
			note.screenDraw(g);
		}

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
			beats = new Beat[] { new Beat(StartTime, "D"), new Beat(StartTime, "K"), new Beat(StartTime, "D2"),
					new Beat(StartTime, "K2"), new Beat(StartTime + gap * 10, "D"), new Beat(StartTime + gap * 10, "K"),
					new Beat(StartTime + gap * 10, "D2"), new Beat(StartTime + gap * 10, "K2"),
					new Beat(StartTime + gap * 15, "F"), new Beat(StartTime + gap * 15, "J"),
					new Beat(StartTime + gap * 15, "F2"), new Beat(StartTime + gap * 15, "J2"),
					new Beat(StartTime + gap * 25, "F"), new Beat(StartTime + gap * 25, "J"),
					new Beat(StartTime + gap * 25, "F2"), new Beat(StartTime + gap * 25, "J2"),

					new Beat(StartTime + gap * 30, "F"), new Beat(StartTime + gap * 36, "J"),
					new Beat(StartTime + gap * 30, "F2"), new Beat(StartTime + gap * 36, "J2"),
					new Beat(StartTime + gap * 40, "F"), new Beat(StartTime + gap * 40, "F2"),

					new Beat(StartTime + gap * 56, "D"), new Beat(StartTime + gap * 56, "J"),
					new Beat(StartTime + gap * 56, "D2"), new Beat(StartTime + gap * 56, "J2"),
					new Beat(StartTime + gap * 66, "D"), new Beat(StartTime + gap * 66, "J"),
					new Beat(StartTime + gap * 66, "D2"), new Beat(StartTime + gap * 66, "J2"),
					new Beat(StartTime + gap * 71, "F"), new Beat(StartTime + gap * 71, "K"),
					new Beat(StartTime + gap * 71, "F2"), new Beat(StartTime + gap * 71, "K2"),
					new Beat(StartTime + gap * 81, "F"), new Beat(StartTime + gap * 81, "K"),
					new Beat(StartTime + gap * 81, "F2"), new Beat(StartTime + gap * 81, "K2"),

					new Beat(StartTime + gap * 86, "D"), new Beat(StartTime + gap * 86, "D2"),
					new Beat(StartTime + gap * 90, "F"), new Beat(StartTime + gap * 90, "F2"),
					new Beat(StartTime + gap * 94, "J"), new Beat(StartTime + gap * 94, "J2"),
					new Beat(StartTime + gap * 98, "K"), new Beat(StartTime + gap * 98, "K2"),

					new Beat(StartTime + gap * 106, "F"), new Beat(StartTime + gap * 106, "F2"),

					new Beat(StartTime + gap * 125, "D"), new Beat(StartTime + gap * 125, "D2"),
					new Beat(StartTime + gap * 132, "F"), new Beat(StartTime + gap * 132, "F2"),
					new Beat(StartTime + gap * 136, "J"), new Beat(StartTime + gap * 136, "J2"),
					new Beat(StartTime + gap * 140, "F"), new Beat(StartTime + gap * 140, "F2"),
					new Beat(StartTime + gap * 145, "J"), new Beat(StartTime + gap * 145, "J2"),
					new Beat(StartTime + gap * 150, "K"), new Beat(StartTime + gap * 150, "K2"),

					new Beat(StartTime + gap * 185, "K"), new Beat(StartTime + gap * 185, "K2"),
					new Beat(StartTime + gap * 190, "J"), new Beat(StartTime + gap * 190, "J2"),
					new Beat(StartTime + gap * 194, "F"), new Beat(StartTime + gap * 194, "F2"),
					new Beat(StartTime + gap * 198, "D"), new Beat(StartTime + gap * 198, "D2"),
					new Beat(StartTime + gap * 205, "F"), new Beat(StartTime + gap * 205, "F2"),
					new Beat(StartTime + gap * 210, "D"), new Beat(StartTime + gap * 210, "D2"),

					new Beat(StartTime + gap * 243, "D"), new Beat(StartTime + gap * 243, "D2"),
					new Beat(StartTime + gap * 247, "D"), new Beat(StartTime + gap * 247, "D2"),
					new Beat(StartTime + gap * 251, "F"), new Beat(StartTime + gap * 251, "F2"),
					new Beat(StartTime + gap * 255, "F"), new Beat(StartTime + gap * 255, "F2"),
					new Beat(StartTime + gap * 259, "D"), new Beat(StartTime + gap * 259, "D2"),
					new Beat(StartTime + gap * 263, "F"), new Beat(StartTime + gap * 263, "F2"),
					new Beat(StartTime + gap * 268, "J"), new Beat(StartTime + gap * 268, "J2"),

					new Beat(StartTime + gap * 301, "K"), new Beat(StartTime + gap * 301, "K2"),
					new Beat(StartTime + gap * 305, "J"), new Beat(StartTime + gap * 305, "J2"),
					new Beat(StartTime + gap * 309, "F"), new Beat(StartTime + gap * 309, "F2"),
					new Beat(StartTime + gap * 313, "D"), new Beat(StartTime + gap * 313, "D2"),
					new Beat(StartTime + gap * 317, "F"), new Beat(StartTime + gap * 317, "F2"),
					new Beat(StartTime + gap * 321, "K"), new Beat(StartTime + gap * 321, "K2"),
					new Beat(StartTime + gap * 328, "K"), new Beat(StartTime + gap * 328, "K2"),
					new Beat(StartTime + gap * 335, "K"), new Beat(StartTime + gap * 335, "K2"),
					new Beat(StartTime + gap * 342, "K"), new Beat(StartTime + gap * 342, "K2"),

					new Beat(StartTime + gap * 359, "J"), new Beat(StartTime + gap * 359, "J2"),
					new Beat(StartTime + gap * 363, "J"), new Beat(StartTime + gap * 363, "J2"),
					new Beat(StartTime + gap * 367, "K"), new Beat(StartTime + gap * 367, "K2"),
					new Beat(StartTime + gap * 371, "J"), new Beat(StartTime + gap * 371, "J2"),
					new Beat(StartTime + gap * 375, "F"), new Beat(StartTime + gap * 375, "F2"),
					new Beat(StartTime + gap * 382, "F"), new Beat(StartTime + gap * 382, "F2"),
					new Beat(StartTime + gap * 389, "F"), new Beat(StartTime + gap * 389, "F2"),
					new Beat(StartTime + gap * 396, "F"), new Beat(StartTime + gap * 396, "F2"),

					new Beat(StartTime + gap * 425, "F"), new Beat(StartTime + gap * 425, "F2"),
					new Beat(StartTime + gap * 429, "F"), new Beat(StartTime + gap * 429, "F2"),
					new Beat(StartTime + gap * 433, "J"), new Beat(StartTime + gap * 433, "J2"),
					new Beat(StartTime + gap * 437, "F"), new Beat(StartTime + gap * 437, "F2"),
					new Beat(StartTime + gap * 441, "D"), new Beat(StartTime + gap * 441, "D2"),
					new Beat(StartTime + gap * 448, "D"), new Beat(StartTime + gap * 448, "D2"),
					new Beat(StartTime + gap * 455, "D"), new Beat(StartTime + gap * 455, "D2"),

					new Beat(StartTime + gap * 476, "F"), new Beat(StartTime + gap * 476, "F2"),
					new Beat(StartTime + gap * 480, "F"), new Beat(StartTime + gap * 480, "F2"),
					new Beat(StartTime + gap * 484, "J"), new Beat(StartTime + gap * 484, "J2"),
					new Beat(StartTime + gap * 488, "K"), new Beat(StartTime + gap * 488, "K2"),
					new Beat(StartTime + gap * 492, "F"), new Beat(StartTime + gap * 492, "F2"),
					new Beat(StartTime + gap * 496, "J"), new Beat(StartTime + gap * 496, "J2"),
					new Beat(StartTime + gap * 503, "J"), new Beat(StartTime + gap * 503, "J2"),
					new Beat(StartTime + gap * 510, "J"), new Beat(StartTime + gap * 510, "J2"),

					new Beat(StartTime + gap * 531, "F"), new Beat(StartTime + gap * 531, "F2"),
					new Beat(StartTime + gap * 535, "F"), new Beat(StartTime + gap * 535, "F2"),
					new Beat(StartTime + gap * 539, "D"), new Beat(StartTime + gap * 539, "D2"),
					new Beat(StartTime + gap * 543, "D"), new Beat(StartTime + gap * 543, "D2"),
					new Beat(StartTime + gap * 547, "F"), new Beat(StartTime + gap * 547, "F2"),
					new Beat(StartTime + gap * 551, "D"), new Beat(StartTime + gap * 551, "D2"),
					new Beat(StartTime + gap * 558, "D"), new Beat(StartTime + gap * 558, "D2"),
					new Beat(StartTime + gap * 565, "D"), new Beat(StartTime + gap * 565, "D2"),

					new Beat(StartTime + gap * 575, "D"), new Beat(StartTime + gap * 575, "K"),
					new Beat(StartTime + gap * 575, "D2"), new Beat(StartTime + gap * 575, "K2"),

					new Beat(StartTime + gap * 591, "K"), new Beat(StartTime + gap * 591, "K2"),
					new Beat(StartTime + gap * 598, "J"), new Beat(StartTime + gap * 598, "J2"),
					new Beat(StartTime + gap * 605, "F"), new Beat(StartTime + gap * 605, "F2"),

					new Beat(StartTime + gap * 609, "D"), new Beat(StartTime + gap * 609, "D2"),
					new Beat(StartTime + gap * 613, "F"), new Beat(StartTime + gap * 613, "F2"),
					new Beat(StartTime + gap * 617, "D"), new Beat(StartTime + gap * 617, "D2"),
					new Beat(StartTime + gap * 621, "F"), new Beat(StartTime + gap * 621, "F2"),
					new Beat(StartTime + gap * 625, "D"), new Beat(StartTime + gap * 625, "D2"),
					new Beat(StartTime + gap * 629, "F"), new Beat(StartTime + gap * 629, "F2"),
					new Beat(StartTime + gap * 633, "D"), new Beat(StartTime + gap * 633, "D2"),
					new Beat(StartTime + gap * 637, "F"), new Beat(StartTime + gap * 637, "F2"),

					new Beat(StartTime + gap * 640, "J"), new Beat(StartTime + gap * 640, "J2"),
					new Beat(StartTime + gap * 644, "K"), new Beat(StartTime + gap * 644, "K2"),
					new Beat(StartTime + gap * 648, "J"), new Beat(StartTime + gap * 648, "J2"),
					new Beat(StartTime + gap * 652, "K"), new Beat(StartTime + gap * 652, "K2"),
					new Beat(StartTime + gap * 656, "J"), new Beat(StartTime + gap * 656, "J2"),
					new Beat(StartTime + gap * 660, "K"), new Beat(StartTime + gap * 660, "K2"),
					new Beat(StartTime + gap * 664, "J"), new Beat(StartTime + gap * 664, "J2"),
					new Beat(StartTime + gap * 668, "K"), new Beat(StartTime + gap * 668, "K2"),

					new Beat(StartTime + gap * 677, "D"), new Beat(StartTime + gap * 677, "K"),
					new Beat(StartTime + gap * 677, "D2"), new Beat(StartTime + gap * 677, "K2"),
					new Beat(StartTime + gap * 684, "D"), new Beat(StartTime + gap * 684, "K"),
					new Beat(StartTime + gap * 684, "D2"), new Beat(StartTime + gap * 684, "K2"),
					new Beat(StartTime + gap * 691, "D"), new Beat(StartTime + gap * 691, "K"),
					new Beat(StartTime + gap * 691, "D2"), new Beat(StartTime + gap * 691, "K2"),
					new Beat(StartTime + gap * 698, "D"), new Beat(StartTime + gap * 698, "K"),
					new Beat(StartTime + gap * 698, "D2"), new Beat(StartTime + gap * 698, "K2"),
					new Beat(StartTime + gap * 705, "D"), new Beat(StartTime + gap * 705, "K"),
					new Beat(StartTime + gap * 705, "D2"), new Beat(StartTime + gap * 705, "K2"),
					new Beat(StartTime + gap * 712, "D"), new Beat(StartTime + gap * 712, "K"),
					new Beat(StartTime + gap * 712, "D2"), new Beat(StartTime + gap * 712, "K2"),
					new Beat(StartTime + gap * 719, "D"), new Beat(StartTime + gap * 719, "K"),
					new Beat(StartTime + gap * 719, "D2"), new Beat(StartTime + gap * 719, "K2"),

					new Beat(StartTime + gap * 726, "F"), new Beat(StartTime + gap * 726, "F2"),
					new Beat(StartTime + gap * 730, "J"), new Beat(StartTime + gap * 730, "J2"),
					new Beat(StartTime + gap * 734, "F"), new Beat(StartTime + gap * 734, "F2"),
					new Beat(StartTime + gap * 738, "J"), new Beat(StartTime + gap * 738, "J2"),
					new Beat(StartTime + gap * 742, "F"), new Beat(StartTime + gap * 742, "F2"),
					new Beat(StartTime + gap * 746, "J"), new Beat(StartTime + gap * 746, "J2"),
					new Beat(StartTime + gap * 750, "F"), new Beat(StartTime + gap * 750, "F2"),
					new Beat(StartTime + gap * 754, "J"), new Beat(StartTime + gap * 754, "J2"),

					new Beat(StartTime + gap * 758, "D"), new Beat(StartTime + gap * 758, "D2"),
					new Beat(StartTime + gap * 762, "K"), new Beat(StartTime + gap * 762, "K2"),
					new Beat(StartTime + gap * 766, "D"), new Beat(StartTime + gap * 766, "D2"),
					new Beat(StartTime + gap * 770, "K"), new Beat(StartTime + gap * 770, "K2"),
					new Beat(StartTime + gap * 774, "D"), new Beat(StartTime + gap * 774, "D2"),
					new Beat(StartTime + gap * 778, "K"), new Beat(StartTime + gap * 778, "K2"),
					new Beat(StartTime + gap * 782, "D"), new Beat(StartTime + gap * 782, "D2"),
					new Beat(StartTime + gap * 786, "K"), new Beat(StartTime + gap * 786, "K2"),

					new Beat(StartTime + gap * 793, "D"), new Beat(StartTime + gap * 793, "F"),
					new Beat(StartTime + gap * 793, "D2"), new Beat(StartTime + gap * 793, "F2"),
					new Beat(StartTime + gap * 800, "J"), new Beat(StartTime + gap * 800, "K"),
					new Beat(StartTime + gap * 800, "J2"), new Beat(StartTime + gap * 800, "K2"),
					new Beat(StartTime + gap * 807, "D"), new Beat(StartTime + gap * 807, "F"),
					new Beat(StartTime + gap * 807, "D2"), new Beat(StartTime + gap * 807, "F2"),
					new Beat(StartTime + gap * 814, "J"), new Beat(StartTime + gap * 814, "K"),
					new Beat(StartTime + gap * 814, "J2"), new Beat(StartTime + gap * 814, "K2"),
					new Beat(StartTime + gap * 821, "D"), new Beat(StartTime + gap * 821, "F"),
					new Beat(StartTime + gap * 821, "D2"), new Beat(StartTime + gap * 821, "F2"),
					new Beat(StartTime + gap * 828, "J"), new Beat(StartTime + gap * 828, "K"),
					new Beat(StartTime + gap * 828, "J2"), new Beat(StartTime + gap * 828, "K2"),
					new Beat(StartTime + gap * 835, "D"), new Beat(StartTime + gap * 835, "F"),
					new Beat(StartTime + gap * 835, "D2"), new Beat(StartTime + gap * 835, "F2"),

					new Beat(StartTime + gap * 842, "D"), new Beat(StartTime + gap * 842, "D2"),
					new Beat(StartTime + gap * 846, "J"), new Beat(StartTime + gap * 846, "J2"),
					new Beat(StartTime + gap * 850, "F"), new Beat(StartTime + gap * 850, "F2"),
					new Beat(StartTime + gap * 854, "K"), new Beat(StartTime + gap * 854, "K2"),
					new Beat(StartTime + gap * 858, "D"), new Beat(StartTime + gap * 858, "D2"),
					new Beat(StartTime + gap * 862, "J"), new Beat(StartTime + gap * 862, "J2"),
					new Beat(StartTime + gap * 866, "F"), new Beat(StartTime + gap * 866, "F2"),
					new Beat(StartTime + gap * 870, "K"), new Beat(StartTime + gap * 870, "K2"),

					new Beat(StartTime + gap * 874, "D"), new Beat(StartTime + gap * 874, "D2"),
					new Beat(StartTime + gap * 876, "J"), new Beat(StartTime + gap * 876, "J2"),
					new Beat(StartTime + gap * 880, "F"), new Beat(StartTime + gap * 880, "F2"),
					new Beat(StartTime + gap * 884, "K"), new Beat(StartTime + gap * 884, "K2"),
					new Beat(StartTime + gap * 888, "D"), new Beat(StartTime + gap * 888, "D2"),
					new Beat(StartTime + gap * 892, "J"), new Beat(StartTime + gap * 892, "J2"),
					new Beat(StartTime + gap * 896, "F"), new Beat(StartTime + gap * 896, "F2"),
					new Beat(StartTime + gap * 900, "K"), new Beat(StartTime + gap * 900, "K2"),

					new Beat(StartTime + gap * 907, "D"), new Beat(StartTime + gap * 907, "F"),
					new Beat(StartTime + gap * 907, "D2"), new Beat(StartTime + gap * 907, "F2"),
					new Beat(StartTime + gap * 912, "F"), new Beat(StartTime + gap * 912, "J"),
					new Beat(StartTime + gap * 912, "F2"), new Beat(StartTime + gap * 912, "J2"),
					new Beat(StartTime + gap * 917, "J"), new Beat(StartTime + gap * 917, "K"),
					new Beat(StartTime + gap * 917, "J2"), new Beat(StartTime + gap * 917, "K2"),
					new Beat(StartTime + gap * 922, "D"), new Beat(StartTime + gap * 922, "F"),
					new Beat(StartTime + gap * 922, "D2"), new Beat(StartTime + gap * 922, "F2"),
					new Beat(StartTime + gap * 925, "J"), new Beat(StartTime + gap * 925, "K"),
					new Beat(StartTime + gap * 925, "J2"), new Beat(StartTime + gap * 925, "K2"),
					new Beat(StartTime + gap * 927, "D"), new Beat(StartTime + gap * 927, "D2"),
					new Beat(StartTime + gap * 929, "F"), new Beat(StartTime + gap * 929, "F2"),
					new Beat(StartTime + gap * 931, "J"), new Beat(StartTime + gap * 931, "J2"),
					new Beat(StartTime + gap * 933, "K"), new Beat(StartTime + gap * 933, "K2"),

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
			int startTime = 4650 - Main.REACH_TIME * 1000;
			int gap = 140;
			beats = new Beat[] { new Beat(startTime + gap * 1, "D"), new Beat(startTime + gap * 1, "K"),
					new Beat(startTime + gap * 2, "J"),
					// new Beat(startTime + gap *9,"D"),//+1
					new Beat(startTime + gap * 2, "F"), // +3

					new Beat(startTime + gap * 26, "D"),
					// new Beat(startTime + gap *46,"D"),
					new Beat(startTime + gap * 32, "K"), new Beat(startTime + gap * 38, "F"),
					new Beat(startTime + gap * 44, "J"),

					new Beat(startTime + gap * 48, "D"), new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 59, "D"), new Beat(startTime + gap * 60, "F"),
					new Beat(startTime + gap * 61, "J"), new Beat(startTime + gap * 62, "K"),
					// new Beat(startTime + gap *57,"D"),//+1
					new Beat(startTime + gap * 72, "K"), new Beat(startTime + gap * 73, "J"),
					new Beat(startTime + gap * 74, "F"), // +1

					new Beat(startTime + gap * 83, "J"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 96, "K"), new Beat(startTime + gap * 97, "J"),
					new Beat(startTime + gap * 98, "K"),

					new Beat(startTime + gap * 99, "J"),

					new Beat(startTime + gap * 100, "F"), new Beat(startTime + gap * 108, "D"),

					new Beat(startTime + gap * 109, "F"), new Beat(startTime + gap * 110, "J"),
					new Beat(startTime + gap * 111, "K"),
					// new Beat(startTime + gap * 109, "F"),
					// new Beat(startTime + gap *94,"K"),
					// new Beat(startTime + gap *95,"J"),
					new Beat(startTime + gap * 117, "K"), new Beat(startTime + gap * 117, "D"),

					new Beat(startTime + gap * 124, "F"), new Beat(startTime + gap * 124, "J"),
					new Beat(startTime + gap * 149, "K"), new Beat(startTime + gap * 156, "K"),

					new Beat(startTime + gap * 158, "F"), new Beat(startTime + gap * 159, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 161, "D"), new Beat(startTime + gap * 166, "D"),
					new Beat(startTime + gap * 169, "F"), new Beat(startTime + gap * 171, "J"),
					new Beat(startTime + gap * 172, "K"),

					// new Beat(startTime + gap *109,"K"),
					new Beat(startTime + gap * 183, "K"), new Beat(startTime + gap * 184, "D"),

					new Beat(startTime + gap * 195, "J"), new Beat(startTime + gap * 196, "F"),
					new Beat(startTime + gap * 197, "J"), new Beat(startTime + gap * 198, "F"),
					new Beat(startTime + gap * 199, "K"), new Beat(startTime + gap * 210, "K"),
					new Beat(startTime + gap * 213, "J"), new Beat(startTime + gap * 216, "F"),
					new Beat(startTime + gap * 219, "D"), new Beat(startTime + gap * 222, "F"),
					new Beat(startTime + gap * 222, "J"),
					// 반복
					new Beat(startTime + gap * 244, "D"), new Beat(startTime + gap * 246, "F"),
					new Beat(startTime + gap * 255, "D"), new Beat(startTime + gap * 256, "F"),
					new Beat(startTime + gap * 257, "J"), new Beat(startTime + gap * 258, "K"),

					new Beat(startTime + gap * 269, "K"), new Beat(startTime + gap * 270, "J"),
					new Beat(startTime + gap * 271, "F"),

					new Beat(startTime + gap * 280, "J"), new Beat(startTime + gap * 284, "D"),
					new Beat(startTime + gap * 293, "K"), new Beat(startTime + gap * 296, "F"),

					new Beat(startTime + gap * 303, "D"), new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 305, "J"), new Beat(startTime + gap * 306, "K"),

					new Beat(startTime + gap * 314, "K"), new Beat(startTime + gap * 314, "D"),

					new Beat(startTime + gap * 320, "F"), new Beat(startTime + gap * 320, "J"),
					new Beat(startTime + gap * 344, "K"), new Beat(startTime + gap * 351, "K"),

					new Beat(startTime + gap * 354, "F"), new Beat(startTime + gap * 355, "J"),
					// new Beat(startTime + gap *106,"S"),
					new Beat(startTime + gap * 357, "D"), new Beat(startTime + gap * 362, "D"),
					new Beat(startTime + gap * 365, "F"), new Beat(startTime + gap * 368, "J"),
					new Beat(startTime + gap * 369, "K"),

					new Beat(startTime + gap * 374, "K"), new Beat(startTime + gap * 381, "D"),

					new Beat(startTime + gap * 391, "J"),
					// new Beat(startTime + gap * 391, "F"),
					new Beat(startTime + gap * 395, "K"), new Beat(startTime + gap * 406, "K"),
					new Beat(startTime + gap * 410, "J"), new Beat(startTime + gap * 412, "F"),
					new Beat(startTime + gap * 414, "D"), new Beat(startTime + gap * 419, "F"),
					new Beat(startTime + gap * 419, "J")

			};
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName(), titleName, beats[i].getItemType());
				note.start();
				if (beats[i].getNoteName().contentEquals("D") || beats[i].getNoteName().contentEquals("F")
						|| beats[i].getNoteName().contentEquals("J") || beats[i].getNoteName().contentEquals("K"))
					noteList.add(note);
				else
					noteList2.add(note);
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
		String judge;
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judge = note.judge();
				judgeEvent(judge);
				ChatMsg cm = new ChatMsg(UserName, "700");
				cm.setJudge(judge);
				cm.setOtherScore(scorePoint);
				cm.setnoteType(note.getNoteType());
				SendObject(cm);

				break;
			}
		}
	}

	public void judgeEvent(String judge) {

		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			scorePoint -= 10;
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();

		} else if (judge.equals("Late")) {

			scorePoint += 10;
			judgeImage = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();

		} else if (judge.equals("Good")) {
			scorePoint += 40;

			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();

		} else if (judge.equals("Great")) {
			scorePoint += 60;
			judgeImage = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();

		} else if (judge.equals("Perfect")) {
			scorePoint += 100;
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();

		} else if (judge.equals("Early")) {
			scorePoint += 10;
			judgeImage = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();

		} else if (judge.equals("money")) {
			scorePoint += 250;
			judgeImage = new ImageIcon(Main.class.getResource("../images/item_bell.png")).getImage();

		} else if (judge.equals("apple")) {
			appleCount += 1;
			scorePoint += 50;
			judgeImage = new ImageIcon(Main.class.getResource("../images/item_apple.png")).getImage();

		} else if (judge.equals("saida")) {
			saidaCount += 1;
			scorePoint += 50;
			judgeImage = new ImageIcon(Main.class.getResource("../images/item_saida.png")).getImage();
			if (saidaCount == 3) {
				qq = 0;
				// judge = "perfect";
				Timer timer1 = new Timer();
				TimerTask timertask1 = new TimerTask() {

					@Override
					public void run() {
						qq = 1;
						saidaCount = 0;

					}

				};
				timer1.schedule(timertask1, 5000);
			}

		} else if (judge.equals("bee")) {
			scorePoint *= 2;
			scorePoint += 50;
			judgeImage = new ImageIcon(Main.class.getResource("../images/item_bee.png")).getImage();

		}
	}

	public void gameCode(ChatMsg cm) {
		String msg = null;
		
		switch (cm.getCode()) {

		case "700":
			otherScorePoint = cm.getOtherScore();
			System.out.println("Received : " + cm.getJudge());
			if (!cm.getJudge().equals("None")) {
				blueFlareImage2 = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
			}
			switch (cm.getJudge()) {
			case "Perfect":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
				break;
			case "Great":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();
				break;
			case "Good":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
				break;
			case "Early":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();
				break;
			case "Late":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();
				break;
			case "Miss":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
				break;
			case "money":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/item_bell.png")).getImage();
				break;
			case "apple":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/item_apple.png")).getImage();
				break;
			case "saida":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/item_saida.png")).getImage();
				break;
			case "bee":
				judgeImage2 = new ImageIcon(Main.class.getResource("../images/item_bee.png")).getImage();
				break;

			}
			switch(cm.getnoteType()) {
			case "D" :
				noteRouteDImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
				keyPadDImage1 = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
				Timer timer1 = new Timer();
				TimerTask timertask1 = new TimerTask() {

					@Override
					public void run() {
						noteRouteDImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
						keyPadDImage1 = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
					}

				};
				timer1.schedule(timertask1, 100);
				break;
			case "F" :
				noteRouteFImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
				keyPadFImage1 = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
				Timer timer2 = new Timer();
				TimerTask timertask2 = new TimerTask() {

					@Override
					public void run() {
						noteRouteFImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
						keyPadFImage1 = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
					}

				};
				timer2.schedule(timertask2, 100);
				break;
			case "J" :
				noteRouteJImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
				keyPadJImage1 = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
				Timer timer3 = new Timer();
				TimerTask timertask3 = new TimerTask() {

					@Override
					public void run() {
						noteRouteJImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
						keyPadJImage1 = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
					}

				};
				timer3.schedule(timertask3, 100);
				break;
			case "K" :
				noteRouteKImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
				keyPadKImage1 = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
				Timer timer4 = new Timer();
				TimerTask timertask4 = new TimerTask() {

					@Override
					public void run() {
						noteRouteKImageU1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
						keyPadKImage1 = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
					}

				};
				timer4.schedule(timertask4, 100);
				break;
			}
			
			

			break;
		}

	}

	// 커밋을 위한 거짓 주석
	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {

			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("메세지 송신 에러!!\n");

		}
	}

}