package rhythm;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED* Main.REACH_TIME); // y��ǥ�� 580�̶� 1��(1000_)�� ������ �� ��Ȯ�� -120�� �����ϴ°�
																		// �̿�(������Ʈ)
	private String noteType;
	private boolean proceeded = true; //���� ��Ʈ�� �������ΰ�?
	public String getNoteType() {
		return noteType;
	}
	public boolean isProceeded() { //������ ����
		return proceeded;
	}
	public void close() { //������ ��Ʈ ���̻� �̵�x
		proceeded = false;
		
	}
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
		if(y > 620) { //��û �Ʒ��� �������ٸ�(��Ʈ ������ miss�߱�)
			System.out.println("Miss");
			close();
		}
	}

	// ������ ��Ʈ ����߸���
	@Override
	public void run() {
		try {
			while (true) {
				drop(); // 1�ʿ� 700�ȼ���ŭ �Ʒ���
				if(proceeded) { //��Ʈ ������ ���鼭 ������ ��
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt(); //�����尡 ���ߵ��� interrupt
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//�����Լ� - Game
	public void judge() {
		if(y >= 613) {
			System.out.println("Late");
			close();
		}
		else if(y >= 600) {
			System.out.println("Good");
			close();
		}
		else if(y >= 587) {
			System.out.println("Great");
			close();
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
		}
		else if(y >= 565) {
			System.out.println("Great");
			close();
		}
		else if(y >= 550) {
			System.out.println("Good");
			close();
		}
		else if(y >= 535) {
			System.out.println("Early");
			close();
		}
	}
}