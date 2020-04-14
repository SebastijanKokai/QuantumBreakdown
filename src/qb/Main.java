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

		ArrayList<LLA> lla = new ArrayList<LLA>();

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
	
	public static class LLA {
		double latitude;
		double longitude;
		double altitude;
		int timestampOffset;

		public LLA(double latitude, double longitude, double altitude, int timestampOffset) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.altitude = altitude;
			this.timestampOffset = timestampOffset;
		}
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

		File file = new File("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level4/level4_example.in");
		Scanner reader = new Scanner(file);

		ArrayList<Map> mapList = new ArrayList<Map>();
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		ArrayList<LLA> list = new ArrayList<LLA>();

		int N = Integer.parseInt(reader.nextLine());

		for (int i = 0; i < N; i++) {

			String[] splitter = reader.nextLine().split(" ");
			int z = 0;

			// Level 4
			String flightID = splitter[z++];
			int timestamp = Integer.parseInt(splitter[z]);
			File fileFlights = new File(
					"/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level4/usedFlights/" + flightID + ".csv");
			Scanner readerFlights = new Scanner(fileFlights);

			Map map = new Map();

			map.start = readerFlights.next();
			map.destination = readerFlights.next();
			map.takeoff = Integer.parseInt(readerFlights.next());

			int n = Integer.parseInt(readerFlights.next());

			for (int j = 0; j < n; j++) {
				String[] split = readerFlights.next().split(",");
				int k = 0;
				int timestampOffset = Integer.parseInt(split[k++]);
				double lat = Double.parseDouble(split[k++]);
				double longi = Double.parseDouble(split[k++]);
				double alt = Double.parseDouble(split[k++]);

				map.lla.add(new LLA(lat, longi, alt, timestampOffset));
			}

			for (int k = 0; k < n; k++) {
				int ts = map.lla.get(k).timestampOffset + map.takeoff;

				if (ts == timestamp) {
					list.add(new LLA(map.lla.get(k).latitude, map.lla.get(k).longitude, map.lla.get(k).altitude,
							map.lla.get(k).timestampOffset));
					break;
				} else if (ts > timestamp) {

					double longii = map.lla.get(k - 1).longitude
							+ (timestamp - (map.lla.get(k - 1).timestampOffset + map.takeoff))
									* (map.lla.get(k).longitude - map.lla.get(k - 1).longitude)
									/ (ts - (map.lla.get(k - 1).timestampOffset + map.takeoff));

					double latti = map.lla.get(k - 1).latitude
							+ (timestamp - (map.lla.get(k - 1).timestampOffset + map.takeoff))
									* (map.lla.get(k).latitude - map.lla.get(k - 1).latitude)
									/ (ts - (map.lla.get(k - 1).timestampOffset + map.takeoff));

					double alti = map.lla.get(k - 1).altitude
							+ (timestamp - (map.lla.get(k - 1).timestampOffset + map.takeoff))
									* (map.lla.get(k).altitude - map.lla.get(k - 1).altitude)
									/ (ts - (map.lla.get(k - 1).timestampOffset + map.takeoff));

					list.add(new LLA(latti, longii, alti, map.lla.get(k).timestampOffset));
					break;
				}

			}

			readerFlights.close();
		}
		
		reader.close();
		
		
		String fileContent = "";
		
		
		for (int i = 0; i < list.size(); i++) {
			fileContent += list.get(i).latitude + " " + list.get(i).longitude + " " + list.get(i).altitude + "\n";
		}
		
		
		System.out.println(fileContent);

		FileWriter fileWriter = new FileWriter("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level4/level4_exx.out");
		fileWriter.write(fileContent);
		fileWriter.close();


	}

}
