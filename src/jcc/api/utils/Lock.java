package jcc.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Lock {
	private static File f;
	private static FileChannel channel;
	private static FileLock lock;
	
	@SuppressWarnings("resource")
	public Lock(String lockFile) {
		try {
			f = new File(lockFile);
			if(f.exists())
				f.delete();
			channel = new RandomAccessFile(f, "rw").getChannel();
			lock = channel.tryLock();
			if(lock == null) {
				channel.close();
				throw new RuntimeException("Two instance cannot run at a time");
			}
			ShutdownHook shutdownHook = new ShutdownHook();
			Runtime.getRuntime().addShutdownHook(shutdownHook);
		}
		catch(IOException e) {
			throw new RuntimeException("Could not start process.",e);
		}
	}
	
	public static void unlockFile() {
		try {
			if(lock != null) {
				lock.release();
				channel.close();
				f.delete();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static class ShutdownHook extends Thread {
		public void run() {
			unlockFile();
		}
	}
}
