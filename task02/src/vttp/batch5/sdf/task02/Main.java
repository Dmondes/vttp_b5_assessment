package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {

		// System.out.printf("hello, world\n");
		 String boardName = args[0];
        File file = new File(boardName);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        if (!file.exists()) {
            System.out.println("File doesn't exist in directory");
        } else {

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null ){
				System.out.println(line);
			}
		}
	}
}
