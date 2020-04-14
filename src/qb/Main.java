package qb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static class Map {
		int timestamp;
		double latitude;
		double longitude;
		double altitude;
		String start;
		String destination;
		int takeoff;
		
		double x;
		double y;
		double z;
	}
	
	public static class Flight {

		String start;
		String destination;
		int count = 1;
		int takeoff;
		Set<Integer> set = new HashSet<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public static void order(ArrayList<Flight> flightList) {

		Collections.sort(flightList, new Comparator() {

			public int compare(Object o1, Object o2) {

				String x1 = ((Flight) o1).start;
				String x2 = ((Flight) o2).start;
				int sComp = x1.compareTo(x2);

				if (sComp != 0) {
					return sComp;
				}

				String x3 = ((Flight) o1).destination;
				String x4 = ((Flight) o2).destination;
				return x3.compareTo(x4);
			}
		});
	}

	public static void main(String[] args) throws IOException {

		File file = new File("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level3/level3_5.in");
		Scanner reader = new Scanner(file);

		ArrayList<Map> mapList = new ArrayList<Map>();
		ArrayList<Flight> flightList = new ArrayList<Flight>();

		int N = Integer.parseInt(reader.nextLine());

		for (int i = 0; i < N; i++) {
			
			String[] splitter = reader.nextLine().trim().split(",");
			int z = 0;
			
			// Level 1 code
			Map map = new Map();
			// map.timestamp = Integer.parseInt(splitter[z++]);
			map.latitude = Double.parseDouble(splitter[z++]);
			map.longitude = Double.parseDouble(splitter[z++]);
			map.altitude = Double.parseDouble(splitter[z++]);
			
			/*
			 * map.start = splitter[z++]; map.destination = splitter[z++]; map.takeoff =
			 * Integer.parseInt(splitter[z++]);
			 */
			
			// Level 2 code
			/*
			 * boolean found = false; for (int j = 0; j < flightList.size(); j++) { if
			 * (map.start.equals(flightList.get(j).start) &&
			 * map.destination.equals(flightList.get(j).destination)) {
			 * 
			 * flightList.get(j).set.add(map.takeoff); found = true; } }
			 * 
			 * if (!found) { Flight flight = new Flight();
			 * 
			 * flight.start = map.start; flight.destination = map.destination;
			 * flight.takeoff = map.takeoff; flight.set.add(map.takeoff);
			 * flightList.add(flight); }
			 */
			
			// Level 3 code
			double cosLat = Math.cos(map.latitude * Math.PI / 180.0);
			double sinLat = Math.sin(map.latitude * Math.PI / 180.0);
			double cosLon = Math.cos(map.longitude * Math.PI / 180.0);
			double sinLon = Math.sin(map.longitude * Math.PI / 180.0);
			double rad = 6371 * 1000;
			double f = 1.0 / 298.257224;
			double C = 1.0 / Math.sqrt(cosLat * cosLat + (1 - f) * (1 - f) * sinLat * sinLat);
			double S = (1.0 - f) * (1.0 - f) * C;
			double h = map.altitude;
			map.x = (rad * 1 + h) * cosLat * cosLon;
			map.y = (rad * 1 + h) * cosLat * sinLon;
			map.z = (rad * 1 + h) * sinLat;	
			
			mapList.add(map);
		}
		
		reader.close();
		
		// Level 1 code
		
		/*
		 * int maxTimestamp = Integer.MIN_VALUE; float maxLatitude = Float.MIN_VALUE;
		 * float maxLongitude = Float.MIN_VALUE; float maxAltitude = Float.MIN_VALUE;
		 * 
		 * int minTimestamp = 0; float minLatitude = 0; float minLongitude = 0; float
		 * minAltitude = 0;
		 * 
		 * for (int i = 0; i < mapList.size(); i++) {
		 * 
		 * if (maxTimestamp < mapList.get(i).timestamp) { maxTimestamp =
		 * mapList.get(i).timestamp; } if (maxLatitude < mapList.get(i).latitude) {
		 * maxLatitude = mapList.get(i).latitude; } if (maxLongitude <
		 * mapList.get(i).longitude) { maxLongitude = mapList.get(i).longitude; } if
		 * (maxAltitude < mapList.get(i).altitude) { maxAltitude =
		 * mapList.get(i).altitude; }
		 * 
		 * }
		 * 
		 * minTimestamp = maxTimestamp; minLatitude = maxLatitude; minLongitude =
		 * maxLongitude; minAltitude = maxAltitude;
		 * 
		 * for (int i = 0; i < mapList.size(); i++) {
		 * 
		 * if (minTimestamp > mapList.get(i).timestamp) { minTimestamp =
		 * mapList.get(i).timestamp; } if (minLatitude > mapList.get(i).latitude) {
		 * minLatitude = mapList.get(i).latitude; } if (minLongitude >
		 * mapList.get(i).longitude) { minLongitude = mapList.get(i).longitude; } if
		 * (minAltitude > mapList.get(i).altitude) { minAltitude =
		 * mapList.get(i).altitude; }
		 * 
		 * }
		 * 
		 * String fileContent = (minTimestamp + " " + maxTimestamp + "\n"); fileContent
		 * += minLatitude + " " + maxLatitude + "\n"; fileContent += minLongitude + " "
		 * + maxLongitude + "\n"; fileContent += maxAltitude;
		 */
		
		/* order(flightList); */
		
		String fileContent = "";
		
		/*
		 * for (int i = 0; i < flightList.size(); i++) { fileContent +=
		 * flightList.get(i).start + " " + flightList.get(i).destination + " " +
		 * flightList.get(i).set.size() + "\n"; }
		 */
		
		for (int i = 0; i < mapList.size(); i++) {
			fileContent += mapList.get(i).x + " " + mapList.get(i).y + " " + mapList.get(i).z + "\n";
		}
		
		
		System.out.println(fileContent);

		FileWriter fileWriter = new FileWriter("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level3/level3_exx.out");
		fileWriter.write(fileContent);
		fileWriter.close();


	}

}
