package rhythmgame;
// ChatMsg.java ä�� �޽��� ObjectStream ��.
public class ChatMsg {
    public String code; // 100:�α���, 400:�α׾ƿ�, 200:ä�ø޽���, 300:Image, 500: Mouse Event
    public String UserName;
    public String data;
    public byte[] imgbytes;

    public ChatMsg(String UserName, String code, String msg) {
        this.code = code;
        this.UserName = UserName;
        this.data = msg;
    }
}