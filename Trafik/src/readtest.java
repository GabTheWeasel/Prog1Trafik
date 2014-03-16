import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class readtest {

	public static void main(String[] args) {
		ArrayList<Double> s = new ArrayList<Double>();
		Scanner scan = new Scanner(System.in);
		s.add(scan.nextDouble());
		/*			Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("settings.txt"), "UTF-8"));
		while(scan.hasNextLine()){
			scan.nextLine();
			s.add(scan.nextInt());
			scan.nextLine();
			scan.nextLine();
		}*/
		
		scan.close();
		System.out.println(s);
	}

}
