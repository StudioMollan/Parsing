package parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadingCSV {

	public static String COMMA_DELIMITER = ",";
	public static List<List<String>> records = new ArrayList<>();

	public static void main(String[] args) {
			// adding data from the file "sample.csv" to the ArrayList record
		try (Scanner scanner = new Scanner(new File("sample.csv"));) {

			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		namesContainingChar('A'); // case sensitive, calls method for counting names containing"a"
		System.out.println();
		numberOfAndroids(); // calls method to count number of android users
		System.out.println();
		TimeStampDups(); // calls method to display duplicate persons with same time stamp.
	}

	private static void TimeStampDups() {
		List<String> nameList = new ArrayList<>();		//new ArrayList from column 2 of "records"
		List<String> dupsTempList = new ArrayList<>();	//new ArrayList from column 0 of "records"
		for (List<String> b : records) {
			String tempDup = b.get(0);			//adds all strings from column 0 of "records" into dupsTempList
			dupsTempList.add(tempDup);
		}
		dupsTempList.remove(0);					// removes unwanted strings in ArrayList
		dupsTempList.remove(23);
		dupsTempList.remove(22);
		dupsTempList.remove(21);
		dupsTempList.remove(20);
		dupsTempList.remove(19);
		for (List<String> b : records) {
			String name = b.get(2);				//adds all strings from column 2 of "records" into nameList
			nameList.add(name);
		}
		nameList.remove(0);						// removes unwanted strings in ArrayList
		nameList.remove(23);
		nameList.remove(22);
		nameList.remove(21);
		nameList.remove(20);
		nameList.remove(19);
		for (int i = 0; i < dupsTempList.size(); i++) {			//iterates ArrayList "dupsTempList" searching for duplicates
			for (int j = i + 1; j < dupsTempList.size(); j++) {
				List<String> tempList = new ArrayList<>();			// New ArrayList for storing duplicates
				if (dupsTempList.get(i).contains(dupsTempList.get(j))) {	//loop to display duplicates				
					tempList.add(dupsTempList.get(i));				
					int index = dupsTempList.indexOf(dupsTempList.get(i));	//position of first duplicate
					int lastIndex = dupsTempList.lastIndexOf(dupsTempList.get(i));//position of last duplicate
					System.out.println(nameList.get(index)+" and "+nameList.get(lastIndex)+" have equal time stamps. "); //prints duplicates
					System.out.println();
				}					
			}
		}
	}

	private static void numberOfAndroids() {
		List<String> androidList = new ArrayList<>(); //makes a new list of persons on android
		for (List<String> b : records) {
			String android = b.get(5);
			if (android.equals("Yes I do")) {	//adds string to ArrayList "androidList" if column 5 equals "yes i do"
				androidList.add(android);
			}
		} //prints the size of ArrayList "androidList" displaying the number of persons on android.
		System.out.println("Number of people on 'android': " + androidList.size());
	}

			//method to construct a 2d arrayList
	private static List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}
			// method for finding names containing the char A
	private static void namesContainingChar(char c) { 
		List<String> fullName = new ArrayList<>();	// makes a new ArrayList from column 1 and 2 in ArrayList "records"
		for (List<String> b : records) {
			String name1A = b.get(1);
			String name2A = b.get(2);
			fullName.add(name1A + name2A);
		}
		fullName.remove(0);//removes title row from new list
//		System.out.println(fullName); 
		List<String> containsA = new ArrayList<>(); // Makes a new arrayList with strings from ArrayList "fullName" containing "A".
		for (String b : fullName) {
			if (b.indexOf(c) >= 0) { 
				containsA.add(b);
			}
		}	//prints the size of ArrayList "containsA" displaying the number of persons with "A" in their name.
		System.out.println("Persons with name containing the letter (upper case)'A': " + containsA.size());
	}
}
