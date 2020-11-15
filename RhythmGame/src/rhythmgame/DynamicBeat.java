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

	//�̹��� ����
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
	
	private boolean isMainScreen = false; //main�Լ��� true
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	
	//���� ���õǴ� �ſ� �ʿ��� ������
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0; //���� ���õ� Ʈ���� ��ȣ
	
	public static Game game; //���α׷� ��ü���� ����ϴ� ���� 
	
	public DynamicBeat() {
		
		trackList.add(new Track("StarmanTitleImage.png","StarmanGameImage.jpg","StarmanStartImage.jpg","Starman.mp3","Starman.mp3","Starman"));
		trackList.add(new Track("OverwTitleImage.png","OverwGameImage.jpg","OverwStartImage.jpg","overworld.mp3","overworld.mp3","Over World"));
		trackList.add(new Track("UnderwTitleImage.png","DarkGameImage.jpg","DarkStartImage.jpg","Underworld.mp3","Underworld.mp3","Under World"));

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
		
		//�������ڸ��� ����
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

		// '�����ϱ�' ��ư ��ġ ����
		startButton.setBounds(40, 350, 400, 100);
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
				enterMain(); // �ڵ� �ϴܿ� ����
				
			}
		});
		add(startButton);

		/*
		// '�����ϱ�' ��ư ��ġ ����
		quitButton.setBounds(40, 500, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			// ��ư�� ���콺 �ø��� �̹��� �ٲ�
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); // �ѹ��� ����
				buttonEnteredMusic.start();
			}

			// ���� �ٽ� ���� �̹�����
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
		add(quitButton);
		*/
		
		//upButton
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 64, 64);
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
		
		
		//downButton 
		rightButton.setVisible(false); //ó������ �Ⱥ��̰�
		rightButton.setBounds(1080, 310, 64, 64);
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
		
		//���� �����ϱ� ��ư���� ���
		easyButton.setVisible(false); //ó������ �Ⱥ��̰�
		easyButton.setBounds(500, 600, 250, 67);
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
				//���� ���� �̺�Ʈ
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
		
		//���ӵ��߿� ����ȭ������ ���ư� �� ����
		backButton.setVisible(false); //ó������ �Ⱥ��̰�
		backButton.setBounds(20, 50, 64, 64);
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
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // ���ϴ� �̹����� draw��
		if(isMainScreen) { 
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen) {//���� ��Ʈ�� class�� ����
			game.screenDraw(g); 
		}
		paintComponents(g); // �׻� �����Ǿ��ִ� �̹����� paintComponent��
		try {
			Thread.sleep(5);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		this.repaint(); // �ٽ� paint �Լ� �ҷ����� --> �� �������� ��� �ݺ��ؼ� �׷���
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close(); //���� ����ǰ� �ִ� ���� ����
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0)
			nowSelected = trackList.size() -1; //���� �����ʿ� �ְ��� ����
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
		
		setFocusable(true); //���� �����ӿ� Ű���� ��Ŀ��
	}
	
	public void backMain() {
		//isMainScreen = true;
		//leftButton.setVisible(true); //���� �ڵ�
		//rightButton.setVisible(true); //���� �ڵ�
		//easyButton.setVisible(true); //���� �ڵ�
		startButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg"))
				.getImage(); 
		backButton.setVisible(false);
		//selectTrack(nowSelected);
		selectedMusic.close();
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		isGameScreen = false;
		game.close(); //���� �ڵ�
	}
	
	public void enterMain() {
		// ���� ���� �̺�Ʈ
		
		startButton.setVisible(false); //��ư �����
		//quitButton.setVisible(false);
		
	
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		isMainScreen = true;
		leftButton.setVisible(true); //��/����ư�� ���̱�
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		
		backButton.setVisible(false);
		introMusic.close(); 
		selectTrack(0);
		
	}

}
