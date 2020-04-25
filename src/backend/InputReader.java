package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
	private File file;
	private List<MilkWeight> mwList;
	
	public InputReader(File file) {
		this.file = file;
		mwList = new LinkedList<MilkWeight>();
		parseFile();
	}
	
	private void parseFile() {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(sc != null) sc.close();
		}
		
	}
}
