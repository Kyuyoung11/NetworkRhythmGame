package rhythmgame;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.util.Random;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/leaf.png")).getImage();
	private Image noteItemImage1 = new ImageIcon(Main.class.getResource("../images/apple2.png")).getImage();
	private Image noteItemImage2 = new ImageIcon(Main.class.getResource("../images/money.png")).getImage();
	private Image noteItemImage3 = new ImageIcon(Main.class.getResource("../images/saida2.png")).getImage();
	private Image noteItemImage4 = new ImageIcon(Main.class.getResource("../images/bee.png")).getImage();

	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private String titleName;
	private String itemType;

	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}
	
	public String getItemType() {
		return itemType;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType, String titleName, String itemType) {
		if (noteType.contentEquals("D")) {
			x = 90;
		} else if (noteType.contentEquals("F")) {
			x = 190;
		} else if (noteType.contentEquals("J")) {
			x = 285;
		} else if (noteType.contentEquals("K")) {
			x = 380;
		}
		else if (noteType.contentEquals("D2")) {
			x = 786;
		} else if (noteType.contentEquals("F2")) {
			x = 886;
		} else if (noteType.contentEquals("J2")) {
			x = 981;
		} else if (noteType.contentEquals("K2")) {
			x = 1076;
		}
		this.noteType = noteType;
		this.titleName = titleName;
		this.itemType = itemType;
	}

	public void screenDraw(Graphics2D g) {
		if (itemType.equals("basic"))
			g.drawImage(noteBasicImage, x + 10, y, null);
		// 완전체 - 애플
		else if (itemType.equals("item1"))
			g.drawImage(noteItemImage1, x + 18, y, null);
		// 점수추가 - 벨주머니
		else if (itemType.equals("item2"))
			g.drawImage(noteItemImage2, x + 27, y, null);
		else if (itemType.equals("item3"))
			g.drawImage(noteItemImage3, x + 18, y, null);
		else if (itemType.equals("item4"))
			g.drawImage(noteItemImage4, x + 15, y + 5, null);

	}

	public void drop() {
		if (titleName.equals("K.K._Idol"))
			y += (Main.NOTE_SPEED + 1);
		else if (titleName.equals("K.K._Western"))
			y += (Main.NOTE_SPEED + 1);
		else if (titleName.equals("K.K._House"))
			y += (Main.NOTE_SPEED);
		if (y > 620) { // 노트가 판정바를 벗어나는 지점
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) { // 배열에 저장된 노트를 떨어트려줄거임
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else { // 노트가 끝나면 스레드 정지
					interrupt();
					break;
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String judge() {
		 
		
			if (y >= 633) {
				System.out.println("Late");
				close();
				return "Late";
			} else if (y >= 593) {
				System.out.println("Good");
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Good";
			} else if (y >= 553) {
				System.out.println("Great");
				System.out.println(itemType);
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Great";
			} else if (y >= 513) {
				System.out.println("Perfect");
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Perfect";
			} else if (y >= 473) {
				System.out.println("Great");
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Great";
			} else if (y >= 433) {
				System.out.println("Good");
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Good";
			} else if (y >= 403) {
				System.out.println("Early");
				close();
				if (itemType.equals("item1"))
					return "apple";
				else if (itemType.equals("item2"))
					return "money";
				else if (itemType.equals("item3"))
					return "saida";
				else if (itemType.equals("item4"))
					return "bee";
				else if (Game.qq == 0) {
					close();
					return "Perfect";
				}
				return "Early";
			}
		
		return "None";
	}

	public int getY() {
		return y;
	}

}
