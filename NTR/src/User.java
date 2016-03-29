import java.util.LinkedList;
import java.util.Deque;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;



public class User {
	private int id;
	private boolean near;
	private AccessPoint accessPoint;
	private List<Packet> packet_send;
	private Deque<Packet> buffer;
	private int debitMoy;
	private int debitCurrent;
	
	public User(int id, AccessPoint accessPoint, boolean near) {
		this.id = id;
		this.accessPoint = accessPoint;
		this.near = near;
		this.packet_send = new ArrayList<Packet>();;
		buffer = new LinkedList<Packet>();
		if(near) {
			debitMoy = 6;
		}
		else {
			debitMoy = 3;
		}
	}

	public boolean isNear() {
		return near;
	}

	public void createPacket() {
		if(this.randomBoolean()){
			Packet packet = new Packet(this, accessPoint.getTime());
			buffer.add(packet);
		}
	}

	public void checkPacket() {
		Packet packet = this.getCurrentPacket();
		if(packet != null) {
			int bitsLeft = packet.getNbBitsLeft() - debitCurrent;
			if(bitsLeft <= 0){
				packet.setNbBitsLeft(0);
				this.packetTerminated();
			}
			else {
				packet.setNbBitsLeft(bitsLeft);
			}
		}
	}

	public Packet getCurrentPacket() {
		return buffer.peek();
	}
	
 	public List<Packet> getPacket_send() {
		return this.packet_send;
	}

	public void packetTerminated() {
		getCurrentPacket().setEndSend(accessPoint.getTime());
		this.packet_send.add(getCurrentPacket());
		buffer.removeFirst();
	}

	public void removePacket(){
		this.packet_send.remove(getCurrentPacket());
	}

	public void calculateDebit() {
		debitCurrent = (int)(2*(Math.random()*((debitMoy*2)/2+1)));
		//System.out.println("debitCurrent pour utilisateur " + near + " = " + debitCurrent);
	}

	public int getDebitCurrent(){
		return this.debitCurrent;
	}

	public int getSNR() {
		return this.debitCurrent;
	}

	public int getId() {
		return this.id;
	}

	public void interferenceDetected() {
		debitCurrent = debitCurrent / 2;
	}

	private boolean randomBoolean() {
		return (Math.random() < 0.25);
	}
}
