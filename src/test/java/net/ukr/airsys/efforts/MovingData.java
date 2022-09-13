import java.nio.file.*;
import java.io.*;
import java.util.stream.Stream;

public class MovingData {

	private static final String RESOURCES_DIR = "src/test/resources/";

	public static void main(String[] args) {
		Path source = Paths.get("unit-test-data.csv"); // существует 
		Path target = Paths.get("temp-data.csv"); // не существует
		try(Stream<String> stream = Files.lines(source);) {
			PrintWriter pw = 
				new PrintWriter(Files.newBufferedWriter(target));
    		stream.skip(1L).limit(1).forEach(pw::println);
    		pw.flush();
    		pw.close();
		} catch (IOException ex) { 
			ex.printStackTrace(); 
		}
	}
}