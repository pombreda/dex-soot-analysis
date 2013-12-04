import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class KTAILEvents {

	public String logPath = "./logs/behavior.log";
	ArrayList<LogEvent> logEventList = new ArrayList<LogEvent>();
	ArrayList<ArrayList<String>> stringss = new ArrayList<ArrayList<String>>();
	
	
	public static void main(String[] args)
	{
		KTAILEvents kte = new KTAILEvents();
		kte.algorithm();
	}
	
	public void algorithm()
	{
		readInput();
		parseLogEvents();
//		printActivityStringss();
		buildAutomata();
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
				LogEvent se = new LogEvent(type, activityFullName);
				logEventList.add(se);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(sEventList.size());
		
	}
	
	public void parseLogEvents()
	{
		boolean findOther = true;
		LogEvent preLogEvent = new LogEvent();
		ArrayList<String> currentStrings = new ArrayList<String>();
		Iterator<LogEvent> leiterator = logEventList.iterator();
		while(leiterator.hasNext())
		{
			LogEvent le = leiterator.next();
			if(findOther)
			{
				if(le.getType().equals("R")) continue;
				else
				{
					findOther = false;
					preLogEvent = le;
				}
			}
			else
			{
				if(le.getType().equals("R"))
				{
					if(preLogEvent.getType().equals("P"))
					{
						if (currentStrings.isEmpty()) {
							currentStrings.add(preLogEvent.activityFullName);
						}
						currentStrings.add(le.getActivityFullName());
						preLogEvent = le;
					}
					else
					{
						findOther = true;
						preLogEvent = new LogEvent();
						stringss.add(currentStrings);
						currentStrings = new ArrayList<String>();
					}
				}
				else
				{
					if(preLogEvent.getType().equals("P"))
					{
						findOther = true;
						preLogEvent = new LogEvent();
						if(currentStrings.isEmpty()) continue;
						stringss.add(currentStrings);
						currentStrings = new ArrayList<String>();
					}
					else
					{
						if(le.getActivityFullName().equals(preLogEvent.getActivityFullName()))
						{
							preLogEvent = le;
						}
						else
						{
							findOther = true;
							preLogEvent = new LogEvent();
							stringss.add(currentStrings);
							currentStrings = new ArrayList<String>();
						}
					}
				}
			}
			
		}
		
		if(!currentStrings.isEmpty()) stringss.add(currentStrings);
		
	}
	
	public void printActivityStringss()
	{
		Iterator<ArrayList<String>> stringsIt = stringss.iterator();
		while(stringsIt.hasNext())
		{
			ArrayList<String> currentStrings = stringsIt.next();
			Iterator<String> csIt = currentStrings.iterator();
			while(csIt.hasNext())
			{
				String cs = csIt.next();
				System.out.println(cs);
			}
		}
	}
	
	public void buildAutomata()
	{
		
	}
	
	
	public class LogEvent
	{
		public String type = new String();
		public String activityFullName = new String();
		
		public LogEvent()
		{
			
		}
		
		public LogEvent(String type, String activityFullName)
		{
			this.type = type;
			this.activityFullName = activityFullName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getActivityFullName() {
			return activityFullName;
		}

		public void setActivityFullName(String activityFullName) {
			this.activityFullName = activityFullName;
		}
		
		
		
	}
	
	public class activityEvent
	{
		public String activityFullName = new String();
	}
	
	
	
}


