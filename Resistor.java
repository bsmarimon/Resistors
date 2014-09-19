import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Resistor {
	
	private ArrayList<String> bands;
	private int numberOfBands;
	private String multiplier; 
	private HashMap<String, Integer> colorsToValues;
	private HashMap<Integer, String> valuesToColors;
	private HashMap<String, Integer> colorsToMultipliers;
	private HashMap<Integer, String> multipliersToColors;
	
	// CONSTRUCTORS
	
	public Resistor(String[] args) {
		bands = new ArrayList<String>();
		colorsToMultipliers = new HashMap<String, Integer>();
		for (String band: args) {
			bands.add(band.toLowerCase());
		}
		numberOfBands = bands.size();
		this.constructColorValueMaps();
		this.constructMultiplierMaps();
	}	
	
	public Resistor(String resistance) {
		multiplier = "1";
		bands = new ArrayList<String>();
		String[] vals = resistance.split("");
		System.out.println(Arrays.toString(vals));
		for (int i = 1; i < vals.length; i++) {
			if (!vals[i].equals("0")) {
				bands.add(vals[i]);
			} else {
				multiplier += "0"; 
			}
		}
		if (resistance.contains(".")) {
			multiplier = "-100";
		}
		System.out.println(multiplier);
		Integer check = Integer.parseInt(resistance);
		if (check < 10) {
			multiplier = "-10";
		}
		bands.add(multiplier);
		this.constructColorValueMaps();
		this.constructMultiplierMaps();
	}

	protected String findBands() {
		String rtnString = "";
		for (int i = 0; i < bands.size() - 1; i++) {
			Integer val = Integer.parseInt(bands.get(i));
			rtnString += valuesToColors.get(val) + " ";
		}
		int bandInt = Integer.parseInt(bands.get(bands.size() - 1));
		rtnString += multipliersToColors.get(bandInt) + " orange";
		return rtnString;
	}
	
	public String findResistance() {
		String tolerance = this.getTolerance();
		Integer resistance = findResistanceHelper();
		String rtnString = resistance.toString() + " Ohms with a tolerance of " + tolerance + "%";
		return rtnString;
	}
	
	private int findResistanceHelper () {
		String base = this.getNthColorValue(0) + this.getNthColorValue(1);
		Integer multiplier = this.getMultiplier();
		int resistance;
		if (multiplier < 0) {
			resistance = Integer.parseInt(base) / Math.abs(multiplier);
		} else {
			resistance = Integer.parseInt(base) * multiplier;
		}	 
		return resistance;
	}
	
	protected String getNthColorValue(int n) {
		return (colorsToValues.get(getNthBand(n))).toString();
	}
	
	protected Integer getMultiplier () {
		return colorsToMultipliers.get(getNthBand(2));
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
	}

	private void constructMultiplierMaps() {;
		colorsToMultipliers = new HashMap<String, Integer>();
		multipliersToColors = new HashMap<Integer, String>();
	
		colorsToMultipliers.put("silver", -100);
		colorsToMultipliers.put("gold", -10);
		colorsToMultipliers.put("black", 1);
		colorsToMultipliers.put("brown", 10);
		colorsToMultipliers.put("red", 100);
		colorsToMultipliers.put("orange", 1000);
		colorsToMultipliers.put("yellow", 10000);
		colorsToMultipliers.put("green", 100000);
		colorsToMultipliers.put("blue", 1000000);
		
		multipliersToColors.put(-100, "silver");
		multipliersToColors.put(-10, "gold");
		multipliersToColors.put(1, "black");
		multipliersToColors.put(10, "brown");
		multipliersToColors.put(100, "red");
		multipliersToColors.put(1000, "orange");
		multipliersToColors.put(10000, "yellow");
		multipliersToColors.put(100000, "green");
		multipliersToColors.put(1000000, "blue");
	}
}
