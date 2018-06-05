package bg.uni.sofia.fmi.grep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;

public class FileFilter implements Runnable {
	
	private Path rootDir;
	private Path path;
	private String search;
	
	public Path getRootDir() {
		return this.rootDir;
	}
	
	public Path getPath() {
		return this.path;
	}
	
	public String getSearch() {
		return this.search;
	}
	
	public FileFilter(Path rootDir, Path path, String search) {
		this.rootDir = rootDir;
		this.path = path;
		this.search = search;
	}
	
	@Override
	public void run() {
		this.filterFile();
	}
	
	
	public void filterFile() {
		try (BufferedReader r = new BufferedReader(new FileReader(path.toString()))) {
			String line;
			int lineCount = 1;
			while ((line = r.readLine()) != null) {
			    if (line.contains(this.search)) {
					String temp = this.getFileData(path, lineCount, line);
					System.out.println(temp);
				}
				++lineCount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileData(Path path, int lineCount, String line) {
		String result = getRelativePath(path);
		String temp = result.concat(":" + lineCount + ":" + line);
		return temp;
		
	}
	
	private String getRelativePath(Path path) {
		Path pathBase = this.rootDir.toAbsolutePath();
		Path pathRelative = pathBase.relativize(path);
		return pathRelative.toString();
	}
	
}