package rhythm;

/*���� ���� -��/����ư Ŭ�� - ����Ǿ�� �ϴ� �͵�*/
public class Track {
	// private String titleImage; // ���� �κ� �̹���. �� ��+���̸� �ϳ�����
	private String startImage; // ���� ���� â ǥ�� �̹���
	private String gameImage; // �ش� �� ����� ǥ�� �̹���
	private String gameMusic; // �ش� ���� �������� �� ����
	private String titlename;
	// private String startMusic; // ���� ���� â ����(���̶���Ʈ 30������ ���� ����������)

	// ���콺 ��Ŭ��-source-Generate getters and setters - �ڵ�����
	public String getStartImage() {
		return startImage;
	}
	// Track�̶�� Ŭ���� �ȿ��� ���ο� ������ ����� �� ��
	// �ѹ��� ���� ������ �ʱ�ȭ���ִ� �޼ҵ� (������)
	// source - select generate fields

	public Track(String startImage, String gameImage, String gameMusic, String titlename) {
		super();
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.gameMusic = gameMusic;
		this.titlename = titlename;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
}
