
public class TimeTesting {

	public static void main(String[] args)
	{
		try {
			new Reminder(10);
			System.out.println("Task scheduled.");
		}
		
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
