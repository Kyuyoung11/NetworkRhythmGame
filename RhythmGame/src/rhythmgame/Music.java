package rhythmgame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player; //음악을 재생하기 위해 다운받은 파일에 속한거임
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; //초기화
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//현재 재생되고 있는 음악의 time
	public int getTime() {
		if (player == null) 
			return 0;
		return player.getPosition();
	}
	
	//언제든 게임 close 가능
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do { //isloop true 면 해당 곡 무한반복
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
