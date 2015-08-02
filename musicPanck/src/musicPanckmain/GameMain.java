package musicPanckmain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameMain {
	public static void main(String[] args) {
		System.out.println("❶"+(int)'❶');
		GameEngin GE = new GameEngin();
		ExecutorService ES = Executors.newSingleThreadExecutor();
		GE.createFrame(new mainFrame());
		try{
		ES.submit(GE);
		}finally{
			ES.shutdown();
		//System.exit(0);
		}
	}
}
