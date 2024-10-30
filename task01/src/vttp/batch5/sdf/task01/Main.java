package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		// System.out.printf("hello, world\n");
		String csvName = "day.csv";
		File file = new File(csvName);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
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
				String day = Integer.toString(bike.getSeason()) + Integer.toString(bike.getMonth())
						+ Integer.toString(bike.getWeekday());
				if (mostCyclists.get(day) == null) {
					mostCyclists.put(day, 1);
				} else if (mostCyclists.get(day) > 0) {
					int dayCount = mostCyclists.get(day);
					dayCount += 1;
					mostCyclists.replace(day, dayCount);
				}
				// if (count == 10){
				// break;
				// }
			}

			int check = 0;
			// for (String item : mostCyclists.keySet()) {
			// 	System.out.println(item + ": " + mostCyclists.get(item));
			// 	check += mostCyclists.get(item);
			// }
			LinkedHashMap<String, Integer> sortedBikes = sortHashMapByValues(mostCyclists);
			for (String item : sortedBikes.keySet()) {
				System.out.println(item + ": " + sortedBikes.get(item));
				check += sortedBikes.get(item);
			}

			// for (BikeEntry bike : bikes){
			// System.out.println("Season: " + bike.getSeason() + " Month: " +
			// bike.getMonth() +" Isholiday: " + Boolean.toString(bike.isHoliday()) + "
			// Weekday: " + bike.getWeekday() + " Weather: " + bike.getWeather() + "
			// Temperature: " + bike.getTemperature() + " Humidity: "+ bike.getHumidity() +
			// " Windspeed" + bike.getWindspeed() + "Casual: " + bike.getCasual() + "
			// Registered: "+ bike.getRegistered());

			// }
			System.out.println("The total count is " + count + " Checksum: " + check);

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
	public static LinkedHashMap<String, Integer> sortHashMapByValues(
        HashMap<String, Integer> passedMap) {
    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
    List<Integer> mapValues = new ArrayList<>(passedMap.values());
    Collections.sort(mapValues);
    Collections.sort(mapKeys);

    LinkedHashMap<String, Integer> sortedMap =
        new LinkedHashMap<>();

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
}
