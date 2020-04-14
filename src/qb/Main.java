package qb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static class Map {
		int timestamp;
		float latitude;
		float longitude;
		float altitude;
	}

	public static void main(String[] args) throws IOException {

		File file = new File("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level1/level1_1.in");
		Scanner reader = new Scanner(file);

		ArrayList<Map> mapList = new ArrayList<Map>();

		int N = Integer.parseInt(reader.nextLine());

		for (int i = 0; i < N; i++) {
			
			String[] splitter = reader.nextLine().trim().split(",");
			int z = 0;

			Map map = new Map();
			map.timestamp = Integer.parseInt(splitter[z++]);
			map.latitude = Float.parseFloat(splitter[z++]);
			map.longitude = Float.parseFloat(splitter[z++]);
			map.altitude = Float.parseFloat(splitter[z++]);
			
			mapList.add(map);
		}

		int maxTimestamp = Integer.MIN_VALUE;
		float maxLatitude = Float.MIN_VALUE;
		float maxLongitude = Float.MIN_VALUE;
		float maxAltitude = Float.MIN_VALUE;

		int minTimestamp = 0;
		float minLatitude = 0;
		float minLongitude = 0;
		float minAltitude = 0;

		for (int i = 0; i < mapList.size(); i++) {

			if (maxTimestamp < mapList.get(i).timestamp) {
				maxTimestamp = mapList.get(i).timestamp;
			}
			if (maxLatitude < mapList.get(i).latitude) {
				maxLatitude = mapList.get(i).latitude;
			}
			if (maxLongitude < mapList.get(i).longitude) {
				maxLongitude = mapList.get(i).longitude;
			}
			if (maxAltitude < mapList.get(i).altitude) {
				maxAltitude = mapList.get(i).altitude;
			}

		}

		minTimestamp = maxTimestamp;
		minLatitude = maxLatitude;
		minLongitude = maxLongitude;
		minAltitude = maxAltitude;

		for (int i = 0; i < mapList.size(); i++) {

			if (minTimestamp > mapList.get(i).timestamp) {
				minTimestamp = mapList.get(i).timestamp;
			}
			if (minLatitude > mapList.get(i).latitude) {
				minLatitude = mapList.get(i).latitude;
			}
			if (minLongitude > mapList.get(i).longitude) {
				minLongitude = mapList.get(i).longitude;
			}
			if (minAltitude > mapList.get(i).altitude) {
				minAltitude = mapList.get(i).altitude;
			}

		}

		String fileContent = (minTimestamp + " " + maxTimestamp + "\n");
		fileContent += minLatitude + " " + maxLatitude + "\n";
		fileContent += minLongitude + " " + maxLongitude + "\n";
		fileContent += maxAltitude;
		
		
		System.out.println(fileContent);

		FileWriter fileWriter = new FileWriter("/home/sebastijan/workspaces/eclipse-workspace/QuantumBreakdown/level1/level1_1.out");
		fileWriter.write(fileContent);
		fileWriter.close();


	}

}
