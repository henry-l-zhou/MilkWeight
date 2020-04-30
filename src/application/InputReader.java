package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * This class checks for valid inputs in the file
 * 
 * @author ateam85
 *
 */
public class InputReader {
	private File file;
	private List<MilkWeight> mwList;

	/**
	 * parses a line of input code and returns a new milkweight object
	 * @param farmId
	 * @param dateInput
	 * @param weightInput
	 * @return MW object
	 * @throws FormatException
	 */
	public static MilkWeight parseLine(String farmId, String dateInput, String weightInput) throws FormatException {
		int year, month, date, weight;
		if (farmId == null || dateInput == null || weightInput == null) {
			throw new FormatException("Null Input invalid");
		}

		// date parsing
		String[] dateSplit = dateInput.split("-");
		try {
			year = Integer.parseInt(dateSplit[0]);
			month = Integer.parseInt(dateSplit[1]);
			date = Integer.parseInt(dateSplit[2]);
		} catch (NumberFormatException e) {
			throw new FormatException("Date input invalid");
		}

		try {
			weight = Integer.parseInt(weightInput);
		} catch (NumberFormatException e) {
			throw new FormatException("Weight input invalid");
		}

		return new MilkWeight(year, month, date, farmId, weight);
	}

	/**
	 * Takes in a file and parses it
	 * @param file
	 * @throws FormatException
	 */
	public InputReader(File file) throws FormatException {
		this.file = file;
		mwList = new LinkedList<MilkWeight>();
		parseFile();
	}

	/**
	 * parses a file and throws format exception if the input is wrong
	 * @throws FormatException
	 */
	private void parseFile() throws FormatException {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			sc.nextLine();
			int lineNum = 2;
			while (sc.hasNextLine()) {
				int year = 0;
				int month = 0;
				int date = 0;
				String farmId = "";
				int weight = 0;
				String mwString = sc.nextLine();
				String[] split = mwString.split(",");
				// split[0] should be date, split[1] should be farmID, split[2] should be weight
				if (split.length != 3)
					throw new FormatException("File has invalid input at line " + lineNum);

				// date parsing
				String[] dateSplit = split[0].split("-");
				try {
					year = Integer.parseInt(dateSplit[0]);
					month = Integer.parseInt(dateSplit[1]);
					date = Integer.parseInt(dateSplit[2]);
				} catch (NumberFormatException e) {
					throw new FormatException("Date input invalid at line " + lineNum);
				}

				farmId = split[1];

				try {
					weight = Integer.parseInt(split[2]);
				} catch (NumberFormatException e) {
					throw new FormatException("Weight input invalid at line " + lineNum);
				}
				mwList.add(new MilkWeight(year, month, date, farmId, weight));
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null)
				sc.close();
		}
	}

	public List<MilkWeight> getList() {
		return mwList;
	}
}
