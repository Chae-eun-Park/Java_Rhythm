package rhythm.copy;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED* Main.REACH_TIME); // y��ǥ�� 580�̶� 1��(1000_)�� ������ �� ��Ȯ�� -120�� �����ϴ°�
																		// �̿�(������Ʈ)
	private String noteType;

	public Note(String noteType) { // y��ǥ�� ��Ʈ���ǵ�� ��Ʈ�� ���½ð��� ���� �޶����� ������ ����
		switch(noteType) {
		case "S": x = 228; break;
		case "D": x = 332; break;
		case "F": x = 436; break;
		case "Space": x = 540; break;
		case "J": x = 744; break;
		case "K": x = 848; break;
		case "L": x = 952; break;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null); // �����̽� ���� ���α��� 2��(+100)
		}

		else {
			g.drawImage(noteBasicImage, x, y, null);
			
		}
	}

	// ��Ʈ�� �������� �Լ�
	public void drop() {
		y += Main.NOTE_SPEED; // �Ʒ������� 7��ŭ
	}

	// ������ ��Ʈ ����߸���
	@Override
	public void run() {
		try {
			while (true) {
				drop(); // 1�ʿ� 700�ȼ���ŭ �Ʒ���
				Thread.sleep(Main.SLEEP_TIME);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}