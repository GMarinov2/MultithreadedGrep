package bg.uni.sofia.fmi.grep;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;


public class FileCollector {
	
	//I thought about using threads in the collectFiles method
	//but I use Depth First Search so it could actually make
	//the directory traversal real slow
	
	public static void collectFiles(Path directory, Set<Path> collectedFiles) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) { 
			for (Path entry : stream) { 
				if (Files.isRegularFile(entry)) { 
					collectedFiles.add(entry);
				}else if ((!entry.endsWith("..")) && (!entry.endsWith("."))) {
					collectFiles(entry, collectedFiles);
				}
			}
		} catch (IOException x) { 
			x.printStackTrace();
		}
	}
}