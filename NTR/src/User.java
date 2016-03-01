import java.util.PriorityQueue;


public class User {
	private boolean near;
	private AccessPoint accessPoint;
	private PriorityQueue<Paquet> buffer;
	
	public User(AccessPoint accessPoint, boolean near) {
		this.accessPoint = accessPoint;
		this.near = near;
		buffer = new PriorityQueue<Paquet>();
	}
	
	public void createPacket() {
		Paquet packet = new Paquet(this);
		buffer.add(packet);
	}
	
	public Paquet getCurrentPacket() {
		return buffer.peek();
	}
	
	public void packetTerminated(int time) {
		getCurrentPacket().setEndSend(time);
		buffer.remove();
	}
}