import java.io.IOException;

public class parent {

	public parent() {
		for (int i = 0; i < 2; i++) {
			try { 
				Process.exec(child.class);
			}  catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
