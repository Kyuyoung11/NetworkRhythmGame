package rhythmgame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	//이미지 변수
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));

	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/quitButtonEntered.png"));
	
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/backButtonEntered.png"));
	
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg"))
			.getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);


	private int mouseX, mouseY;
	
	private boolean isMainScreen = false; //main함수면 true
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	
	//곡이 선택되는 거에 필요한 변수들
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; //현재 선택된 트랙의 번호
	
	public static Game game; //프로그램 전체에서 사용하는 변수 
	
	public DynamicBeat() {
		
		trackList.add(new Track("StarmanTitleImage.png","StarmanGameImage.jpg","StarmanStartImage.jpg","Starman.mp3","Starman.mp3","Starman"));
		trackList.add(new Track("OverwTitleImage.png","OverwGameImage.jpg","OverwStartImage.jpg","overworld.mp3","overworld.mp3","Over World"));
		trackList.add(new Track("UnderwTitleImage.png","DarkGameImage.jpg","DarkStartImage.jpg","Underworld.mp3","Underworld.mp3","Under World"));

		setUndecorated(true);
		setTitle("네트워크 리듬게임");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 임의로 크기 변경 불가능
		setLocationRelativeTo(null); // 가운데에 화면 뜨기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // 배경 하얀색
		setLayout(null);
		
		addKeyListener(new KeyListener()); 
		
		//시작하자마자 음악
		introMusic.start();
		
		
		// 'x'버튼 위치 조정 (메뉴바의 오른쪽 상단)
		exitButton.setBounds(1230, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 누르면 프로그램 종료
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		// '시작하기' 버튼 위치 조정
		startButton.setBounds(40, 350, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}

			// 누르면 곡 선택 화면으로 감 
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain(); // 코드 하단에 있음
				
			}
		});
		add(startButton);

		/*
		// '종료하기' 버튼 위치 조정
		quitButton.setBounds(40, 500, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 누르면 프로그램 종료
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		*/
		
		//upButton
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 왼쪽 버튼 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);
		
		
		//downButton 
		rightButton.setVisible(false); //처음에는 안보이게
		rightButton.setBounds(1080, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 오른쪽 버튼 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);
		
		//게임 시작하기 버튼으로 사용
		easyButton.setVisible(false); //처음에는 안보이게
		easyButton.setBounds(500, 600, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 오른쪽 버튼 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				//게임 시작 이벤트
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
		
		//게임도중에 메인화면으로 돌아갈 수 있음
		backButton.setVisible(false); //처음에는 안보이게
		backButton.setBounds(20, 50, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 오른쪽 버튼 이벤트
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
				
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			// 메뉴바 누르고 드래그 가능해짐
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});

		add(menuBar);

		
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // 변하는 이미지는 draw로
		if(isMainScreen) { 
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen) {//게임 컨트롤 class로 가기
			game.screenDraw(g); 
		}
		paintComponents(g); // 항상 고정되어있는 이미지는 paintComponent로
		try {
			Thread.sleep(5);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		this.repaint(); // 다시 paint 함수 불러오기 --> 매 페이지를 계속 반복해서 그려줌
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close(); //현재 재생되고 있는 음악 멈춤
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0)
			nowSelected = trackList.size() -1; //가장 오른쪽에 있곡이 선택
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() -1)
			nowSelected = 0; //
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"+ trackList.get(nowSelected).getGameImage()))
				.getImage(); 
		backButton.setVisible(true);
		isGameScreen = true;
		
		game = new Game(trackList.get(nowSelected).getTitleName(),trackList.get(nowSelected).getGameMusic());
		game.start();
		
		setFocusable(true); //메인 프레임에 키보드 포커스
	}
	
	public void backMain() {
		//isMainScreen = true;
		//leftButton.setVisible(true); //원래 코드
		//rightButton.setVisible(true); //원래 코드
		//easyButton.setVisible(true); //원래 코드
		startButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg"))
				.getImage(); 
		backButton.setVisible(false);
		//selectTrack(nowSelected);
		selectedMusic.close();
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		isGameScreen = false;
		game.close(); //원래 코드
	}
	
	public void enterMain() {
		// 게임 시작 이벤트
		
		startButton.setVisible(false); //버튼 숨기기
		//quitButton.setVisible(false);
		
	
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		isMainScreen = true;
		leftButton.setVisible(true); //왼/오버튼은 보이기
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		
		backButton.setVisible(false);
		introMusic.close(); 
		selectTrack(0);
		
	}

}
