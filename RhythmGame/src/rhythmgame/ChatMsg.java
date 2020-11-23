package rhythmgame;


// ChatMsg.java ä�� �޽��� ObjectStream ��.
import java.io.Serializable;
import javax.swing.ImageIcon;

class ChatMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code; // 100:�α���, 400:�α׾ƿ�, 200:ä�ø޽���, 300:Image
	private String data;
	public ImageIcon img;
	
	private int num;
	
	private int room1;
	private int room2;
	private int room3;
	
	public ChatMsg(String id, String code) {
		this.id = id;
		this.code = code;
	}
	

	public ChatMsg(String id, String code, String msg) {
		this.id = id;
		this.code = code;
		this.data = msg;
	}
	
	public int getRoom1() {
		return room1;
	}
	
	public int getRoom2() {
		return room2;
	}
	
	public int getRoom3() {
		return room3;
	}
	
	public void setRoom1(int room1) {
		this.room1 = room1;
	}
	
	public void setRoom2(int room2) {
		this.room2 = room2;
	}
	
	public void setRoom3(int room3) {
		this.room3 = room3;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
}