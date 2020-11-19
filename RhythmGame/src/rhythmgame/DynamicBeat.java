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

	// �̹��� ����
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

	private JButton gameButton1 = new JButton("������...");
	private JButton gameButton2 = new JButton("������...");
	private JButton gameButton3 = new JButton("������...");

	private JButton enterButton1 = new JButton("���� ����");
	private JButton enterButton2 = new JButton("���� ����");
	private JButton enterButton3 = new JButton("���� ����");

	private int mouseX, mouseY;

	private boolean isMainScreen = false; // main�Լ��� true
	private boolean isGameScreen = false;
	
	//true -> ������ ��ư true & ���尡�� ��ư false
	//gameButton�� isGamingroom���� ���� enterButton�� !isGamingroom���� ���� ��
	private boolean isGamingroom1 = true;
	private boolean isGamingroom2 = false;
	private boolean isGamingroom3 = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	// ���� ���õǴ� �ſ� �ʿ��� ������
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; // ���� ���õ� Ʈ���� ��ȣ

	public static Game game; // ���α׷� ��ü���� ����ϴ� ����

	public DynamicBeat() {

		trackList.add(new Track("IdolGameImage.jpg", "mainBackground.jpg", "kk_idol.mp3", "kk_idol.mp3", "K.K._Idol"));
		trackList.add(new Track("HouseGameImage.jpg", "mainBackground.jpg", "nabi.mp3", "nabi.mp3", "K.K._House"));
		trackList.add(
				new Track("WesternGameImage.jpg", "mainBackground.jpg", "western.mp3", "western.mp3", "K.K._Western"));

		setUndecorated(true);
		setTitle("��Ʈ��ũ �������");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ���Ƿ� ũ�� ���� �Ұ���
		setLocationRelativeTo(null); // ����� ȭ�� �߱�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // ��� �Ͼ��
		setLayout(null);

		addKeyListener(new KeyListener());

		// �������ڸ��� ����
		introMusic.start();

		// 'x'��ư ��ġ ���� (�޴����� ������ ���)
		exitButton.setBounds(1230, 0, 32, 32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ������ ���α׷� ����
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

		// 1���� ǥ���� ��ư
		roomButton1.setBounds(430, 310, 50, 40);
		roomButton1.setBorderPainted(true);
		roomButton1.setFocusPainted(false);
		roomButton1.setContentAreaFilled(false);
		add(roomButton1);

		// 2���� ǥ���� ��ư
		roomButton2.setBounds(430, 430, 50, 40);
		roomButton2.setBorderPainted(true);
		roomButton2.setFocusPainted(false);
		roomButton2.setContentAreaFilled(false);
		add(roomButton2);

		// 3���� ǥ���� ��ư
		roomButton3.setBounds(430, 550, 50, 40);
		roomButton3.setBorderPainted(true);
		roomButton3.setFocusPainted(false);
		roomButton3.setContentAreaFilled(false);
		add(roomButton3);

		// 1���� ��� ǥ���� ��ư
		numButton1.setBounds(690, 310, 80, 40);
		numButton1.setBorderPainted(true);
		numButton1.setFocusPainted(false);
		numButton1.setContentAreaFilled(false);
		add(numButton1);

		// 2���� ��� ǥ���� ��ư
		numButton2.setBounds(690, 430, 80, 40);
		numButton2.setBorderPainted(true);
		numButton2.setFocusPainted(false);
		numButton2.setContentAreaFilled(false);
		add(numButton2);

		// 3���� ��� ǥ���� ��ư
		numButton3.setBounds(690, 550, 80, 40);
		numButton3.setBorderPainted(true);
		numButton3.setFocusPainted(false);
		numButton3.setContentAreaFilled(false);
		add(numButton3);

		// ������ ǥ�� (�� 1)
		gameButton1.setVisible(isGamingroom1);
		gameButton1.setBounds(530, 310, 100, 40);
		gameButton1.setBorderPainted(false);
		gameButton1.setFocusPainted(false);
		gameButton1.setContentAreaFilled(false);
		add(gameButton1);

		// ������ ǥ�� (�� 2)
		gameButton2.setVisible(isGamingroom2);
		gameButton2.setBounds(530, 430, 100, 40);
		gameButton2.setBorderPainted(false);
		gameButton2.setFocusPainted(false);
		gameButton2.setContentAreaFilled(false);
		add(gameButton2);

		// ������ ǥ�� (�� 3)
		gameButton3.setVisible(isGamingroom3);
		gameButton3.setBounds(530, 550, 100, 40);
		gameButton3.setBorderPainted(false);
		gameButton3.setFocusPainted(false);
		gameButton3.setContentAreaFilled(false);
		add(gameButton3);

		// ���� ���� ǥ�� (�� 1)
		enterButton1.setVisible(!isGamingroom1);
		enterButton1.setBounds(530, 310, 100, 40);
		enterButton1.setBorderPainted(false);
		enterButton1.setFocusPainted(false);
		enterButton1.setContentAreaFilled(false);
		add(enterButton1);

		// ���� ���� ǥ�� (�� 2)
		enterButton2.setVisible(!isGamingroom2);
		enterButton2.setBounds(530, 430, 100, 40);
		enterButton2.setBorderPainted(false);
		enterButton2.setFocusPainted(false);
		enterButton2.setContentAreaFilled(false);
		add(enterButton2);
		
		// ���� ���� ǥ�� (�� 3)
		enterButton3.setVisible(!isGamingroom3);
		enterButton3.setBounds(530, 550, 100, 40);
		enterButton3.setBorderPainted(false);
		enterButton3.setFocusPainted(false);
		enterButton3.setContentAreaFilled(false);
		add(enterButton3);
		

		// �� ��ư ��ġ ����
		startButton.setBounds(400, 280, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// ������ �� ���� ȭ������ ��
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterGame(); // �ڵ� �ϴܿ� ����

			}
		});
		add(startButton);

		// '�����ϱ�' ��ư ��ġ ����
		startButton1.setBounds(400, 400, 400, 100);
		startButton1.setBorderPainted(false);
		startButton1.setContentAreaFilled(false);
		startButton1.setFocusPainted(false);
		startButton1.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton1.setIcon(startButtonEnteredImage);
				startButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				startButton1.setIcon(startButtonBasicImage);
				startButton1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// ������ �������� ��
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterGame(); // �ڵ� �ϴܿ� ����

			}
		});
		add(startButton1);

		startButton2.setBounds(400, 520, 400, 100);
		startButton2.setBorderPainted(false);
		startButton2.setContentAreaFilled(false);
		startButton2.setFocusPainted(false);
		startButton2.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton2.setIcon(startButtonEnteredImage);
				startButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				startButton2.setIcon(startButtonBasicImage);
				startButton2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// ������ �� ���� ȭ������ ��
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterGame(); // �ڵ� �ϴܿ� ����

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
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���� ��ư �̺�Ʈ
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);

		// UpButton
		rightButton.setVisible(false); // ó������ �Ⱥ��̰�
		rightButton.setBounds(610, 270, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ������ ��ư �̺�Ʈ
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);

		// ���� �����ϱ� ��ư���� ���
		easyButton.setVisible(false); // ó������ �Ⱥ��̰�
		easyButton.setBounds(540, 163, 200, 100);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����

				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ������ ��ư �̺�Ʈ
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("startPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ���� ���� �̺�Ʈ
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);

		// ���ӵ��߿� ����ȭ������ ���ư� �� ����
		backButton.setVisible(false); // ó������ �Ⱥ��̰�
		backButton.setBounds(540, 70, 200, 100);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ������ ��ư �̺�Ʈ
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
			// �޴��� ������ �巡�� ��������
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
		g.drawImage(background, 0, 0, null); // ���ϴ� �̹����� draw��
		if (isMainScreen) {
			g.drawImage(selectedImage, 550, 345, null);
		}
		if (isGameScreen) {// ���� ��Ʈ�� class�� ����
			game.screenDraw(g);
		}
		paintComponents(g); // �׻� �����Ǿ��ִ� �̹����� paintComponent��
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // �ٽ� paint �Լ� �ҷ����� --> �� �������� ��� �ݺ��ؼ� �׷���
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close(); // ���� ����ǰ� �ִ� ���� ����
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);

		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // ���� �����ʿ� �ְ��� ����
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
		backButton.setVisible(true);// ���� ���� ������ ��ư
		isGameScreen = true;

		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());
		game.start();

		setFocusable(true); // ���� �����ӿ� Ű���� ��Ŀ��
	}

	public void backMain() {
		// isMainScreen = true;
		// leftButton.setVisible(true); //���� �ڵ�
		// rightButton.setVisible(true); //���� �ڵ�
		// easyButton.setVisible(true); //���� �ڵ�
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
		game.close(); // ���� �ڵ�
	}

	public void enterGame() {
		// ���� ���� �̺�Ʈ

		startButton.setVisible(false); // ��ư �����
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
		leftButton.setVisible(true); // ��/����ư�� ���̱�
		rightButton.setVisible(true);
		easyButton.setVisible(true); // ���ӽ��۹�ư

		selectTrack(0);

		backButton.setVisible(true);
		introMusic.close();

		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());

	}

}
