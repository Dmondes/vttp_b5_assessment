package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static final String[] DAY = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	public static void main(String[] args) throws IOException {

		// System.out.printf("hello, world\n");
		String csvName = "day.csv";
		File file = new File(csvName);
		// System.out.println(file.getAbsolutePath());
		// System.out.println(file.getName());
		if (!file.exists()) {
			System.out.println("File doesn't exist in directory");
		} else {
			ArrayList<BikeEntry> bikes = new ArrayList<>();
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			int count = 0;
			HashMap<String, Integer> mostCyclists = new HashMap<>();
			br.readLine(); // Skip first row
			while ((line = br.readLine()) != null) {
				count++;
				BikeEntry bike = splitString(line);
				bikes.add(bike);
				String day = Integer.toString(bike.getSeason()) + "," +
						Integer.toString(bike.getMonth()) + ","
						+ Integer.toString(bike.getWeekday()) + "," + Boolean.toString(bike.isHoliday());
				// String day = Utilities.toSeason(bike.getSeason())
				// + Utilities.toMonth(bike.getMonth())
				// + weekday(bike.getWeekday());
				int totalCycle = bike.getCasual() + bike.getRegistered();
				if (mostCyclists.get(day) == null) {
					mostCyclists.put(day, totalCycle);
				} else if (mostCyclists.get(day) != null) {
					int dayCount = mostCyclists.get(day);
					dayCount += totalCycle;
					mostCyclists.replace(day, dayCount);
				}
				// if (count == 10){
				// break;
				// }
			}

			LinkedHashMap<String, Integer> sortedBikes = sortHashMapByValues(mostCyclists);
			List<String> topFive = new ArrayList<String>(sortedBikes.keySet());
			Collections.reverse(topFive);
			for (int i = 0; i < 5; i++) {
				String[] parts = topFive.get(i).split(",");
				int total = sortedBikes.get(topFive.get(i));
				String season = Utilities.toSeason(Integer.valueOf(parts[0]));
				String month = Utilities.toMonth(Integer.valueOf(parts[1]));
				String day = Utilities.toWeekday(Integer.valueOf(parts[2]));
				Boolean isHoliday = Boolean.valueOf(parts[3]);
				String holiday = "";
				if (isHoliday) {
					holiday = "a holiday";
				} else {
					holiday = "not a holiday";
				}
				int weather = 0;
				for (BikeEntry bike : bikes) {
					String newline = Integer.toString(bike.getSeason()) + "," +
							Integer.toString(bike.getMonth()) + ","
							+ Integer.toString(bike.getWeekday()) + "," + Boolean.toString(bike.isHoliday());
					if (newline.equalsIgnoreCase(topFive.get(i))) {
						weather = bike.getWeather();
						break;
					}
				}
				String weatherSit = "";
				if (weather == 1) {
					weatherSit = "Clear, Few clouds, Partly cloudy, Partly cloudy";
				} else if (weather == 2) {
					weatherSit = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
				} else if (weather == 3) {
					weatherSit = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
				} else if (weather == 4) {
					weatherSit = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
				}
				if (i == 0) {
					System.out.printf(
							"The highest recorded number of cyclists was in %s, on a %s in the month of %s.\n", season,
							day, month);
				} else if (i == 1) {
					System.out.printf(
							"The second highest recorded number of cyclists was in %s, on a %s in the month of %s.\n",
							season, day, month);
				} else if (i == 2) {
					System.out.printf(
							"The third highest recorded number of cyclists was in %s, on a %s in the month of %s.\n",
							season, day, month);
				} else if (i == 3) {
					System.out.printf(
							"The fourth highest recorded number of cyclists was in %s, on a %s in the month of %s.\n",
							season, day, month);
				} else if (i == 4) {
					System.out.printf(
							"The fifth highest recorded number of cyclists was in %s, on a %s in the month of %s.\n",
							season, day, month);
				}
				System.out.printf("There were a total of %d cyclists.", total);
				System.out.printf("The weather was %s.\n", weatherSit);
				System.out.printf("The day was %s.\n", holiday);
				System.out.println();
			}

			// for (BikeEntry bike : bikes){
			// System.out.println("Season: " + bike.getSeason() + " Month: " +
			// bike.getMonth() +" Isholiday: " + Boolean.toString(bike.isHoliday()) + "
			// Weekday: " + bike.getWeekday() + " Weather: " + bike.getWeather() + "
			// Temperature: " + bike.getTemperature() + " Humidity: "+ bike.getHumidity() +
			// " Windspeed" + bike.getWindspeed() + "Casual: " + bike.getCasual() + "
			// Registered: "+ bike.getRegistered());
			// }
			br.close();
		}
	}

	public static BikeEntry splitString(String line) {
		String[] parts = line.split(",");
		BikeEntry newBike = new BikeEntry();
		int season = Integer.valueOf(parts[0]);
		int month = Integer.valueOf(parts[1]);
		boolean holiday = Boolean.valueOf(parts[2]);
		int weekday = Integer.valueOf(parts[3]);
		int weather = Integer.valueOf(parts[4]);
		float temperature = Float.valueOf(parts[5]);
		float humidity = Float.valueOf(parts[6]);
		float windspeed = Float.valueOf(parts[7]);
		int casual = Integer.valueOf(parts[8]);
		int registered = Integer.valueOf(parts[9]);

		newBike.setSeason(season); // 0
		newBike.setMonth(month); // 1
		newBike.setHoliday(holiday); // 2
		newBike.setWeekday(weekday); // 3
		newBike.setWeather(weather); // 4
		newBike.setTemperature(temperature); // 5
		newBike.setHumidity(humidity); // 6
		newBike.setWindspeed(windspeed); // 7
		newBike.setCasual(casual); // 8
		newBike.setRegistered(registered); // 10
		return newBike;

	}
	//sort hashmap in ascending order
	public static LinkedHashMap<String, Integer> sortHashMapByValues( 
			HashMap<String, Integer> passedMap) {
		List<String> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Integer> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Integer val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Integer comp1 = passedMap.get(key);
				Integer comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	public static String weekday(int weekday) {
		switch (weekday) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return DAY[weekday];
			default:
				return "incorrect day";
		}
	}
}
