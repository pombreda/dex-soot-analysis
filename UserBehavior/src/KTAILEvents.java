import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class KTAILEvents {

	public String logPath = "./logs/behavior.log";
	public static final String R = "R";
	public static final String P = "P";
	ArrayList<SEvent> sEventList = new ArrayList<SEvent>();
	
	
	
	public static void main(String[] args)
	{
		KTAILEvents kte = new KTAILEvents();
		kte.algorithm();
	}
	
	public void algorithm()
	{
		readInput();
	}
	
	public void readInput()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(logPath));
			String s = new String();
			while((s = br.readLine())!=null)
			{
				String type = new String();
				String activityFullName = new String();
				type = s.substring(1, 2);
				activityFullName = s.substring(3);
				SEvent se = new SEvent(type, activityFullName);
				sEventList.add(se);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(sEventList.size());
		
	}
	
	
	
	public class SEvent
	{
		public String type = new String();
		public String activityFullName = new String();
		
		public SEvent(String type, String activityFullName)
		{
			this.type = type;
			this.activityFullName = activityFullName;
		}
		
	}
}


