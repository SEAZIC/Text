package musicPanckmain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import FpsSetting.FpsManager;
import musicPanckmain.*;
public class GameEngin implements Runnable{Thread myThread;
LinkedList<FrameWork> frame;
FpsManager fpsM;
LinkedList<MessagePack> messageManager;
String[] FrameName;
HashMap<String,FrameWork> FrameMap;

public GameEngin() {
	MessagePack MP = new MessagePack();
	fpsM = new FpsManager(120, 0);

	frame = new LinkedList<FrameWork>();
//	mainFrame MF = new mainFrame();
//
//	frame.add(MF);
	//frame.add(new MMDreadFrame());
	//frame.add(new matEditor());
//MF.setmessage(MP);
	FrameMap  =new HashMap<String,FrameWork>();
	FrameName = new String[frame.size()];

	for(int cnt = 0; cnt < frame.size();cnt++){
		FrameName[cnt] = frame.get(cnt).getFrameName();
		FrameMap.put(frame.get(cnt).getFrameName(), frame.get(cnt));
		System.out.println(frame.get(cnt).getFrameName());
	}
	MP.setMeta_A("FrameNameSet");
	MP.setPresent(FrameName);

	

	messageManager  =new LinkedList<MessagePack>();

}
@Override
public void run() {
	// TODO Auto-generated method stub
	while(true){
		try {
			for(int cnt = 0 ; cnt < frame.size(); cnt++){
				frame.get(cnt).run();

				MessagePack MP;

				MP = frame.get(cnt).getmessage();
				if(MP != null){
					System.out.println("MP_create");
					System.out.println(MP.getMeta_A());
					if(messageManager.indexOf(MP) < 0){
						messageManager.add(MP);
						System.out.println("Message_add");
					}
					frame.get(cnt).removeMessage();
				}
			}
			for(int cnt = 0;cnt < messageManager.size();cnt++){
				String S = messageManager.get(cnt).getMeta_A();
				if(FrameMap.containsKey(S)){
					FrameMap.get(S).setmessage(messageManager.get(cnt));
				}
			}
			messageManager.removeAll(messageManager);
			TimeUnit.NANOSECONDS.sleep(fpsM.fpsClc());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (fpsM.getovertimeflag()){

			Thread.yield();
		}
	}
}

public void createFrame(Class F,String S){
	for(FrameWork fff: frame){
		if(fff.getFrameName()!=null)
		if(fff.getFrameName().equalsIgnoreCase(S)){
			return;
		}
	}
	System.out.println("frame作ろう");
	FrameWork df;
	try {
		df = (FrameWork) F.newInstance();
		df.setFrameName(S);
		frame.add(df);
		FrameName = new String[frame.size()];
		FrameMap.put(df.getFrameName(), df);
		for(int cnt = 0; cnt < frame.size();cnt++){
			FrameName[cnt] = frame.get(cnt).getFrameName();
			
			System.out.println(frame.get(cnt).getFrameName());
		}
	} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException  | SecurityException e) {
		e.printStackTrace();
	}
}
public void createFrame(FrameWork F){
	frame.add(F);
}

}
