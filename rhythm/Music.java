package rhythm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player; //�ٿ���� ���̺귯�� jlayer1.0.1 _Cdrive

public class Music extends Thread{
	private Player player;  //���̺귯�������� ���
	private boolean isLoop; //�ѹ�or���� ��� ����
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	//���� ����, ���ѹݺ�����
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; //isLoop�ʱ�ȭ
			file = new File(Main.class.getResource("../music/" + name).toURI()); //toURI�� �ش������� ��ġ
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//���� ����ð�üũ
	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition(); //�ð��м�
	}
	//���� ����Ǵ��� �׻� ������ �� �ֵ��� ��
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //thread�������·�
	}
	//thread��ӹ����� ���� ������ ����� �� run
	@Override
	public void run() {
		try {
			do {
				player.play(); //�뷡����
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //isLoop�� true��� ����
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
