import java.util.ArrayList;


public class UserInterface {
	
	private final static ArrayList<String> list = new ArrayList<String>();

	public UserInterface() {
		
		DataHandler dataHand = new DataHandler();
		CommandHandler commHand = new CommandHandler();
		
	}

	public static void main(String[] args) {

		// list = dataHand.getList("today");
		UserIntSwing swi = new UserIntSwing();
		swi.execute();
	}

}
