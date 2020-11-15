package rhythmgame;

//�ϳ��� � ���� ������ ��� Ŭ����
public class Track {
	private String startImage; // ���� ���� â ǥ�� �̹���
	private String gameImage; // �ش� ���� �������� �� ǥ�� �̹���
	private String startMusic; // ���� ���� â ����
	private String gameMusic; // �ش� ���� �������� �� ���
	private String titleName; //�� ����
	

	public String getStartImage() {
		return startImage;
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
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitieName(String titleName) {
		this.titleName=titleName;
	}
	public Track(String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
		super();
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

}
