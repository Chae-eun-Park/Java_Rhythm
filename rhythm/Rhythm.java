package rhythm;

//JComponent.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "sPress");
//JComponent.getActionMap().put("sPress",  javax.swing.Action);
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rhythm extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exit = new ImageIcon(Main.class.getResource("../images/exit.jpg"));
	private ImageIcon exit2 = new ImageIcon(Main.class.getResource("../images/exit2.jpg"));
	private ImageIcon startBasic = new ImageIcon(Main.class.getResource("../images/startbasic.png"));
	private ImageIcon startEntered = new ImageIcon(Main.class.getResource("../images/startenter.png"));
	private ImageIcon quitBasic = new ImageIcon(Main.class.getResource("../images/quitbasic.png"));
	private ImageIcon leftBasic = new ImageIcon(Main.class.getResource("../images/leftBasic.png"));
	private ImageIcon rightBasic = new ImageIcon(Main.class.getResource("../images/rightBasic.png"));
	private ImageIcon leftEntered = new ImageIcon(Main.class.getResource("../images/leftEnter.png"));
	private ImageIcon rightEntered = new ImageIcon(Main.class.getResource("../images/rightEnter.png"));
	private ImageIcon quitEntered = new ImageIcon(Main.class.getResource("../images/quitenter.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easybuttonenter.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easybuttonbasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardbuttonenter.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardbuttonbasic.png"));
	private ImageIcon backButtonBasic = new ImageIcon(Main.class.getResource("../images/leftbasic.png"));
	// backButton�� �׳� leftbutton�̹����� ����
	// ImageIcon�� getImage�� ����, Image�� getImage�� �ʿ��ϴ�,.
	private Image background = new ImageIcon(Main.class.getResource("../images/title.jpg")).getImage();

	private JLabel menubar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.jpg")));

	private JButton exitButton = new JButton(exit);
	private JButton startButton = new JButton(startBasic);
	private JButton quitButton = new JButton(quitBasic);
	private JButton leftButton = new JButton(leftBasic);
	private JButton rightButton = new JButton(rightBasic);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasic);

	private int mouseX, mouseY;

	private boolean isMainScreen = false; // ó���� ����ȭ��ƴ϶� false ->���۹�ư ������ �� ��ȭ������ ����
	private boolean isGameScreen = false; // ����ȭ���̹���

	ArrayList<Track> trackList = new ArrayList<Track>(); // ������ ���� ���� ���� ����Ʈ

	private Music selectedMusic; // �뷡���ÿ� ���� ��������
	private Image selectedImage; // �ٲ�Ƿ� ó������ �������� �ʿ� ����
	private Music intromusic = new Music("titlemusic.mp3", true); // true -intromusic���ѽ���
	private int nowSelected = 0; // 0 - ù��° ���� ��ȣ, �ε�����

	public static Game game; // ������ ����� �� ���ÿ� ������ ����Ǹ� �ȵǹǷ� public static

	public Rhythm() {		
		trackList.add(new Track("Game Set! Start.png", "Game Set! Background.png", "EK-07 - Game Set!.mp3", "EK-07 - Game Set!"));
		trackList.add(new Track("Now Start.png", "Now Background.png", "EK-07 - Now.mp3", "EK-07 - Now"));
		trackList.add(new Track("Wild Flower Start.png", "Wild Flower Background.png", "Wild Flower - Joakim Karud.mp3", "Wild Flower - Joakim Karud"));

		setUndecorated(true);// ó�����۽�
		setTitle("Rhythm_PCE"); // â������
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // ũ�⼳��
		setResizable(false); // ������ ��������� �Ұ���
		setLocationRelativeTo(null); // ����� ����â �߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ������ �����
		setVisible(true); // ���� ���̰� ��
		setBackground(new Color(0, 0, 0, 0)); // �Ͼ��
		setLayout(null);

		addKeyListener(new KeyListener()); // Ű ������ �̺�Ʈ Ŭ����
		// ��� Ű�� ������ �� �̺�Ʈó�� ����
		intromusic.start();

		// ������ ������� �־��ּ���~ ����� ����Ʈ ���� ����(���ʷ� 0�� 1�� �ε����� �Ǵ°���)

		menubar.setBounds(0, 0, 1280, 30); // ��ġ�� ũ��
		// ���콺�� X, Y�� �ޱ�
		menubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		/* �����ư �̺�Ʈ */
		exitButton.setBounds(1250, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exit);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
				Music mexit = new Music("Bbok.mp3", false);
				mexit.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exit2);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/*
				 * ���� �����Ҷ��� �Ҹ��� �ְ������ ���� Thread�� ����Ѵ�. Music mexit2 = new Music("�����̸�.mp3",
				 * false); mexit2.start(); try{ Thread.sleep(1000);//1�� �ִٰ� ���� Ʋ�� �����ϱ�����
				 * }catch(Exception e){ ex.printStackTrace(); }
				 * 
				 */
				System.exit(0);
			}
		});

		add(exitButton);

		/* ���۸޴�(��ư-�����ϱ�) */
		startButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		startButton.setContentAreaFilled(false); // ��ư ä��� ����
		startButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���

		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startEntered);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startBasic);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���ӽ����̺�Ʈ
				intromusic.close();
				enterMain();
			}
		});
		add(startButton);

		/* ����޴� */
		// �Ʒ� �� ���� ����ȭ�� ���� ��µ�..
		quitButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		quitButton.setContentAreaFilled(false); // ��ư ä��� ����
		quitButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���

		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitEntered);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitBasic);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���������̺�Ʈ

				System.exit(0);
			}
		});

		add(quitButton);

		// �� ���� ���ʹ�ư
		leftButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		leftButton.setContentAreaFilled(false); // ��ư ä��� ����
		leftButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftEntered);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftBasic);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("button.mp3", false);
				buttonEnteredMusic.start();
				selectedLeft();
			}
		});

		add(leftButton);

		// �� ���� �����ʹ�ư
		rightButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		rightButton.setContentAreaFilled(false); // ��ư ä��� ����
		rightButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightEntered);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightBasic);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("button.mp3", false);
				buttonEnteredMusic.start();
				// �����ʹ�ư�̺�Ʈ
				selectedRight();
			}
		});

		add(rightButton);

		// backbutton
		backButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		backButton.setContentAreaFilled(false); // ��ư ä��� ����
		backButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(leftEntered);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(leftBasic);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("button.mp3", false);
				buttonEnteredMusic.start();
				// ���� ȭ�鿡�� ����ȭ������ ���ư��� �̺�Ʈ
				backMain();
			}
		});

		add(backButton);

		// easy button
		easyButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		easyButton.setContentAreaFilled(false); // ��ư ä��� ����
		easyButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("button.mp3", false);
				buttonEnteredMusic.start();
				// ���̵� ���� �̺�Ʈ
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);

		// hard button
		hardButton.setBorderPainted(false); // ��ư�� �ܰ��� ���ֱ�
		hardButton.setContentAreaFilled(false); // ��ư ä��� ����
		hardButton.setFocusPainted(false); // ���õ����� �׵θ� ���þ���
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���콺 �ö��� �� �հ������
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// �ȿö����� �⺻����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("button.mp3", false);
				buttonEnteredMusic.start();
				// ���̵� ����� �̺�Ʈ
				gameStart(nowSelected, "hard");
			}
		});
		add(hardButton);

		// �޴�â �ű��
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menubar);
	}

	// ȭ�鿡 �������(�������)
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics(); // �׷��� ��ü ����
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// ��ÿ� ���� ���ٲ��
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
		}
		if (isGameScreen) {
			game.screenDraw(g); // GameŬ������ �̵�
		}
		// main frame�� add�� �߰�����Ұ� �׷������� ���� paintComponents��
		paintComponents(g); // JLabel�������� �����Ǳ� ������ paintComponents�� �̿�����
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	// Ʈ�� ������ ���� �Լ� - Track�� ����
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getGameMusic(), true);
		selectedMusic.start();
	}

	/* ����ȭ�鿡�� ���ʼ��� */
	public void selectedLeft() {
		// �� �ʱⰪ�� 0 �ε� �̰Ϳ��� �������� ���� �� ���� ���� ���;� �ϱ� ������ -1
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		// ���� ��찡 �ƴ϶�� �ܼ��� 1���ָ� �ȴ�
		else
			nowSelected--;
		selectTrack(nowSelected); // �ε��� ����� �ش��ϴ� Ʈ������
	}

	public void selectedRight() {
		// �� �ʱⰪ�� 0 �ε� �̰Ϳ��� �������� ���� �� ���� ���� ���;� �ϱ� ������ -1
		if (nowSelected == trackList.size() - 1) // trackList�� ũ�⿡�� -1 �Ѵ� �°� �� �ڷ� ���ٴ� ��
			nowSelected = 0;
		// ���� ��찡 �ƴ϶�� �ܼ��� 1�����ָ� �ȴ�
		else
			nowSelected++;
		selectTrack(nowSelected); // �ε��� ����� �ش��ϴ� Ʈ��
	}

	// real���ӽ��۽� ȭ��
	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) { // ������ �̹� �������̶��
			selectedMusic.close(); // ���� ����Ǵ� ������ �ݾ��ش�
			isMainScreen = false; // ����ȭ�� ���ְ� ����ȭ������
			isGameScreen = true; // ���� ���� ȭ��
			leftButton.setVisible(false);
			rightButton.setVisible(false);
			easyButton.setVisible(false);
			hardButton.setVisible(false);
			// ���ӽ���� ȭ��
			background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
					.getImage();
			backButton.setVisible(true);
			game = new Game(trackList.get(nowSelected).getTitlename(),difficulty,trackList.get(nowSelected).getGameMusic());
			game.start(); //run�Լ� �����		
			setFocusable(true); //���������ӿ� ����, Ű���� ���� ���ֱ� ����
			//requestFocus();
		}
	}

	// ����ȭ�鿡�� backButton��������
	public void backMain() {
		isGameScreen = false;
		isMainScreen = true; // ����ȭ�� ����
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		// ���ӽ���� ȭ��
		background = new ImageIcon(Main.class.getResource("../images/mainbackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); // ���� ������ ���� �������
		game.close(); //���ӽ���Ǵ� ���ǰ�, �����ϴ��� ����
	}

	// ����ȭ�� �� �� ����ϰ� ����
	public void enterMain() {
		background = new ImageIcon(Main.class.getResource("../images/mainbackground.jpg")).getImage();
		startButton.setVisible(false); // ���۹�ư ������ �ʰ�
		isMainScreen = true; // ��ȭ������
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		intromusic.close();
		selectTrack(0); // ó�� ���۽ÿ� �� ó�� �� ������ ����
		// TrackŬ���� ���� �Ʒ��κ� �ʿ����
		// Music selectedMusic = new Music("EK-07 - Game Set!.mp3", true); //���� �ٲ��� �ʴ�
		// �̻� ���ѹݺ�
		// selectedMusic.start();
	}
}
