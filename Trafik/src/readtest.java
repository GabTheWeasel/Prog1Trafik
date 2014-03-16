import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class readtest {

	public static void main(String[] args) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		// TODO Auto-generated method stub
		try {
			Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("settings.txt"), "UTF-8"));
			while(scan.hasNextLine()){
				scan.nextLine();
				s.add(scan.nextInt());
				scan.nextLine();
				scan.nextLine();
			}
			scan.close();
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
