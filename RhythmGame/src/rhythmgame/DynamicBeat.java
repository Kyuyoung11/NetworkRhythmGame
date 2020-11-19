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

	// 이미지 변수
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/bang.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/bang2.png"));



	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/oolBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/oolEnter.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/guriguriBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/guriguriEntered.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton startButton1 = new JButton(startButtonBasicImage);
	private JButton startButton2 = new JButton(startButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private JButton roomButton1 = new JButton("1");
	private JButton roomButton2 = new JButton("2");
	private JButton roomButton3 = new JButton("3");

	private JButton numButton1 = new JButton("2/2");
	private JButton numButton2 = new JButton("1/2");
	private JButton numButton3 = new JButton("0/2");

	private JButton gameButton1 = new JButton("게임중...");
	private JButton gameButton2 = new JButton("게임중...");
	private JButton gameButton3 = new JButton("게임중...");

	private JButton enterButton1 = new JButton("입장 가능");
	private JButton enterButton2 = new JButton("입장 가능");
	private JButton enterButton3 = new JButton("입장 가능");

	private int mouseX, mouseY;

	private boolean isMainScreen = false; // main함수면 true
	private boolean isGameScreen = false;
	
	//true -> 게임중 버튼 true & 입장가능 버튼 false
	//gameButton은 isGamingroom으로 쓰고 enterButton은 !isGamingroom으로 쓰면 됨
	private boolean isGamingroom1 = true;
	private boolean isGamingroom2 = false;
	private boolean isGamingroom3 = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	// 곡이 선택되는 거에 필요한 변수들
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; // 현재 선택된 트랙의 번호

	public static Game game; // 프로그램 전체에서 사용하는 변수

	public DynamicBeat() {

		trackList.add(new Track("IdolGameImage.jpg", "mainBackground.jpg", "kk_idol.mp3", "kk_idol.mp3", "K.K._Idol"));
		trackList.add(new Track("HouseGameImage.jpg", "mainBackground.jpg", "nabi.mp3", "nabi.mp3", "K.K._House"));
		trackList.add(
				new Track("WesternGameImage.jpg", "mainBackground.jpg", "western.mp3", "western.mp3", "K.K._Western"));

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

		// 시작하자마자 음악
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

		// 1번방 표시할 버튼
		roomButton1.setBounds(430, 310, 50, 40);
		roomButton1.setBorderPainted(true);
		roomButton1.setFocusPainted(false);
		roomButton1.setContentAreaFilled(false);
		add(roomButton1);

		// 2번방 표시할 버튼
		roomButton2.setBounds(430, 430, 50, 40);
		roomButton2.setBorderPainted(true);
		roomButton2.setFocusPainted(false);
		roomButton2.setContentAreaFilled(false);
		add(roomButton2);

		// 3번방 표시할 버튼
		roomButton3.setBounds(430, 550, 50, 40);
		roomButton3.setBorderPainted(true);
		roomButton3.setFocusPainted(false);
		roomButton3.setContentAreaFilled(false);
		add(roomButton3);

		// 1번방 명수 표시할 버튼
		numButton1.setBounds(690, 310, 80, 40);
		numButton1.setBorderPainted(true);
		numButton1.setFocusPainted(false);
		numButton1.setContentAreaFilled(false);
		add(numButton1);

		// 2번방 명수 표시할 버튼
		numButton2.setBounds(690, 430, 80, 40);
		numButton2.setBorderPainted(true);
		numButton2.setFocusPainted(false);
		numButton2.setContentAreaFilled(false);
		add(numButton2);

		// 3번방 명수 표시할 버튼
		numButton3.setBounds(690, 550, 80, 40);
		numButton3.setBorderPainted(true);
		numButton3.setFocusPainted(false);
		numButton3.setContentAreaFilled(false);
		add(numButton3);

		// 게임중 표시 (방 1)
		gameButton1.setVisible(isGamingroom1);
		gameButton1.setBounds(530, 310, 100, 40);
		gameButton1.setBorderPainted(false);
		gameButton1.setFocusPainted(false);
		gameButton1.setContentAreaFilled(false);
		add(gameButton1);

		// 게임중 표시 (방 2)
		gameButton2.setVisible(isGamingroom2);
		gameButton2.setBounds(530, 430, 100, 40);
		gameButton2.setBorderPainted(false);
		gameButton2.setFocusPainted(false);
		gameButton2.setContentAreaFilled(false);
		add(gameButton2);

		// 게임중 표시 (방 3)
		gameButton3.setVisible(isGamingroom3);
		gameButton3.setBounds(530, 550, 100, 40);
		gameButton3.setBorderPainted(false);
		gameButton3.setFocusPainted(false);
		gameButton3.setContentAreaFilled(false);
		add(gameButton3);

		// 입장 가능 표시 (방 1)
		enterButton1.setVisible(!isGamingroom1);
		enterButton1.setBounds(530, 310, 100, 40);
		enterButton1.setBorderPainted(false);
		enterButton1.setFocusPainted(false);
		enterButton1.setContentAreaFilled(false);
		add(enterButton1);

		// 입장 가능 표시 (방 2)
		enterButton2.setVisible(!isGamingroom2);
		enterButton2.setBounds(530, 430, 100, 40);
		enterButton2.setBorderPainted(false);
		enterButton2.setFocusPainted(false);
		enterButton2.setContentAreaFilled(false);
		add(enterButton2);
		
		// 입장 가능 표시 (방 3)
		enterButton3.setVisible(!isGamingroom3);
		enterButton3.setBounds(530, 550, 100, 40);
		enterButton3.setBorderPainted(false);
		enterButton3.setFocusPainted(false);
		enterButton3.setContentAreaFilled(false);
		add(enterButton3);
		

		// 방 버튼 위치 조정
		startButton.setBounds(400, 280, 400, 100);
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
				enterGame(); // 코드 하단에 있음

			}
		});
		add(startButton);

		// '시작하기' 버튼 위치 조정
		startButton1.setBounds(400, 400, 400, 100);
		startButton1.setBorderPainted(false);
		startButton1.setContentAreaFilled(false);
		startButton1.setFocusPainted(false);
		startButton1.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton1.setIcon(startButtonEnteredImage);
				startButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				startButton1.setIcon(startButtonBasicImage);
				startButton1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// 누르면 대기방으로 감
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterGame(); // 코드 하단에 있음

			}
		});
		add(startButton1);

		startButton2.setBounds(400, 520, 400, 100);
		startButton2.setBorderPainted(false);
		startButton2.setContentAreaFilled(false);
		startButton2.setFocusPainted(false);
		startButton2.addMouseListener(new MouseAdapter() {
			// 버튼에 마우스 올리면 이미지 바뀜
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton2.setIcon(startButtonEnteredImage);
				startButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // 한번만 실행
				buttonEnteredMusic.start();
			}

			// 떼면 다시 원래 이미지로
			@Override
			public void mouseExited(MouseEvent e) {
				startButton2.setIcon(startButtonBasicImage);
				startButton2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// 누르면 곡 선택 화면으로 감
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterGame(); // 코드 하단에 있음

			}
		});
		add(startButton2);

		// downButton
		leftButton.setVisible(false);
		leftButton.setBounds(610, 540, 64, 64);
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

		// UpButton
		rightButton.setVisible(false); // 처음에는 안보이게
		rightButton.setBounds(610, 270, 64, 64);
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

		// 게임 시작하기 버튼으로 사용
		easyButton.setVisible(false); // 처음에는 안보이게
		easyButton.setBounds(540, 163, 200, 100);
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
				// 게임 시작 이벤트
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);

		// 게임도중에 메인화면으로 돌아갈 수 있음
		backButton.setVisible(false); // 처음에는 안보이게
		backButton.setBounds(540, 70, 200, 100);
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
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // 변하는 이미지는 draw로
		if (isMainScreen) {
			g.drawImage(selectedImage, 550, 345, null);
		}
		if (isGameScreen) {// 게임 컨트롤 class로 가기
			game.screenDraw(g);
		}
		paintComponents(g); // 항상 고정되어있는 이미지는 paintComponent로
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // 다시 paint 함수 불러오기 --> 매 페이지를 계속 반복해서 그려줌
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close(); // 현재 재생되고 있는 음악 멈춤
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);

		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // 가장 오른쪽에 있곡이 선택
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0; //
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = true;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);// 게임 도중 나가기 버튼
		isGameScreen = true;

		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());
		game.start();

		setFocusable(true); // 메인 프레임에 키보드 포커스
	}

	public void backMain() {
		// isMainScreen = true;
		// leftButton.setVisible(true); //원래 코드
		// rightButton.setVisible(true); //원래 코드
		// easyButton.setVisible(true); //원래 코드
		startButton.setVisible(true);
		startButton1.setVisible(true);
		startButton2.setVisible(true);

		roomButton1.setVisible(true);
		roomButton2.setVisible(true);
		roomButton3.setVisible(true);

		numButton1.setVisible(true);
		numButton2.setVisible(true);
		numButton3.setVisible(true);

		
		gameButton1.setVisible(isGamingroom1);
		enterButton1.setVisible(!isGamingroom1);
		
		gameButton2.setVisible(isGamingroom2);
		enterButton2.setVisible(!isGamingroom2);
		
		gameButton3.setVisible(isGamingroom3);
		enterButton3.setVisible(!isGamingroom3);
		
		
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		backButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
		nowSelected = 0;
		selectedMusic.close();
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		isGameScreen = false;
		game.close(); // 원래 코드
	}

	public void enterGame() {
		// 게임 시작 이벤트

		startButton.setVisible(false); // 버튼 숨기기
		startButton1.setVisible(false);
		startButton2.setVisible(false);

		roomButton1.setVisible(false);
		roomButton2.setVisible(false);
		roomButton3.setVisible(false);

		numButton1.setVisible(false);
		numButton2.setVisible(false);
		numButton3.setVisible(false);

		gameButton1.setVisible(false);
		gameButton2.setVisible(false);
		gameButton3.setVisible(false);
		
		enterButton1.setVisible(false);
		enterButton2.setVisible(false);
		enterButton3.setVisible(false);
		

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true); // 왼/오버튼은 보이기
		rightButton.setVisible(true);
		easyButton.setVisible(true); // 게임시작버튼

		selectTrack(0);

		backButton.setVisible(true);
		introMusic.close();

		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());

	}

}
