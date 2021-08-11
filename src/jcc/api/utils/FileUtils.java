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
	/**
	 * Recursively fetches all files in a given directory
	 * @param dir a File specifying the directory
	 * @return a List<File> with all the files contained in the given directory
	 */
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