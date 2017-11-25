package backstageTest;

import java.util.Vector;


public class DemoTest {
	public static void main(String[] args) {
		//List<Integer> ls=new ArrayList<>();
		Vector<Integer> ls=new Vector<Integer>();
		Thread thread=new Thread() {
			public void run() {
				for(int i=0;i<(1<<20);i++)
					ls.add(i);
			}
		};
		
	}
}
