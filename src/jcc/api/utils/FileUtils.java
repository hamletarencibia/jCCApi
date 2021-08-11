package jcc.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 * The class have methods to handle files
 * @author Hamlet Arencibia Casanova
 *
 */
public class FileUtils {
	/**
	 * Copies a file to the given destination
	 * @param fileToCopy file to be copied
	 * @param destinationPath destination of the copied file
	 * @throws IOException if an I/O error occurs
	 */
	@Deprecated()
	public static void copy(File fileToCopy,File destinationPath) throws IOException{
		new File(destinationPath.getParent()).mkdirs();
		FileInputStream originalFile=new FileInputStream(fileToCopy);
		FileOutputStream destinationFile=new FileOutputStream(destinationPath);
		int counter=originalFile.available();
		for(int i=0;i<counter;i++){
			destinationFile.write(originalFile.read());
		}
		originalFile.close();
		destinationFile.close();
	}
	
	/**
	 * Returns the extension of the given file
	 * @param file file to get extension
	 * @return a {@link String} object with the extension name
	 */
	public static String getExtension(File file) {
		String extension = null;
		if(file.isFile()) {
			String[] nameArray=file.getName().split("\\.");
			extension=nameArray[nameArray.length-1];
		}
		return extension;
	}
	
	public static List<File> fetchAllFiles(File dir){
		List<File> files = new LinkedList<File>();
		if(dir.isDirectory()) {
			File[] dirFiles = dir.listFiles();
			for(File file : dirFiles) {
				if(file.isDirectory())
					files.addAll(fetchAllFiles(file));
				else {
					files.add(file);
				}
			}
		}
		else
			files.add(dir);
		return files;
	}
}