import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class readtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("settings.txt"), "UTF-8"));
			scan.nextLine();
			scan.nextLine();
			while(scan.hasNextLine()){
				System.out.println(scan.nextInt());
			}
			scan.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
