package propertyfile.java7.watcher.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;
import propertyfile.java7.watcher.PropertyFileReader;

public class AppTest extends TestCase {

	public AppTest(String testName) {
		super(testName);
	}

	public void testReloadablePropertyFileReader() {
		System.out.println("value of fname: "
				+ PropertyFileReader.getValue("fname"));

		String propFileLoc = System.getProperty("propFileLoc");
		try {
			if ((propFileLoc == null) || propFileLoc.trim().length() > 0) {
				propFileLoc = PropertyFileReader.class.getProtectionDomain()
						.getCodeSource().getLocation().getPath()
						+ "properties/test.properties";
			}

			File file = new File(propFileLoc);

			// adding new value in property file
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.append("country=IN");
			bw.flush();
			bw.close();

			Thread.sleep(10);
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}

		// reading newly added value from property file
		System.out.println("value of country: " + PropertyFileReader.getValue("country"));

	}
}
