import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Resistor {
	
	private LinkedList<String> bands;
	private int numberOfBands;
	private HashMap<String, Integer> colorsToValues;
	private HashMap<Integer, String> valuesToColors;
	private HashMap<String, Integer> resistorMultipliers;
	
	public Resistor(String[] args) {
		bands = new LinkedList<String>();
		resistorMultipliers = new HashMap<String, Integer>();
		for (String band: args) {
			bands.add(band.toLowerCase());
		}
		numberOfBands = bands.size();
		this.constructColorValueMaps();
		this.constructMultipliers();
	}	

	protected String getNthColorValue(int n) {
		return (colorsToValues.get(getNthBand(n))).toString();
	}
	
	protected Integer getMultiplier () {
		return resistorMultipliers.get(getNthBand(2));
	}

	protected String getTolerance() {
		String tolerance = this.getNthBand(numberOfBands - 1);
		if (tolerance.equals("silver")) {
			return "10";
		}
		else if (tolerance.equals("gold")) {
			return "5";
		}
		else if (tolerance.equals("brown")) {
			return "1";
		}
		else if (tolerance.equals("red")) {
			return "2";
		} else {
			return "0";		
		}
	}
	
	// private method declarations 
	
	private int getNumberOfBands() {
		return numberOfBands;
	}
		
	private String getNthBand(int n) {
		return bands.get(n);
	}
	
	private void constructColorValueMaps() {
		colorsToValues = new HashMap<String, Integer>();
		valuesToColors = new HashMap<Integer, String>();
		ArrayList<String> myColors = new ArrayList<String>();
		myColors.add("black");
		myColors.add("brown");
		myColors.add("red");
		myColors.add("orange");
		myColors.add("yellow");
		myColors.add("green");
		myColors.add("blue");
		myColors.add("violet");
		myColors.add("grey");
		myColors.add("white");
		for (int i = 0; i < myColors.size(); i++) {
			colorsToValues.put(myColors.get(i), i);
			valuesToColors.put(i, myColors.get(i));
		}
		System.out.println(colorsToValues.toString());
		System.out.println(valuesToColors.toString());
	}

	private void constructMultipliers() {;
		resistorMultipliers.put("silver", -100);
		resistorMultipliers.put("gold", -10);
		resistorMultipliers.put("black", 1);
		resistorMultipliers.put("brown", 10);
		resistorMultipliers.put("red", 100);
		resistorMultipliers.put("orange", 1000);
		resistorMultipliers.put("yellow", 10000);
		resistorMultipliers.put("green", 100000);
		resistorMultipliers.put("blue", 1000000);
	}
}
