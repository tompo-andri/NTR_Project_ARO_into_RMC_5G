
public class CalculThroughput implements Calculation{

	private World world; 
	private int time;
	private int nb_bytes;

	public CalculThroughput(World world){
		this.world = world;
		this.time = this.world.getTime();
	}

	public World getWorld(){
		return this.world;
	}

	public void setUser(World w){
		this.world = w;
	}

	public void execute(){
		
		for(int i = 0; i < world.getNbAccessPoints(); ++i){
			
			AccessPoint a = world.getAccessPoint(i);
            List<UR> ur = a.getUr();
            Iterator<UR> it = ur.Iterator();
            while(it.hasNaxt){
                 UR ur_current = it.next();
                 if(){
                    
                 }       
            }

		}
	
	}


	public void finalize() { }
}