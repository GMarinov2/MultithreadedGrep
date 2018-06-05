package bg.uni.sofia.fmi.grep;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private Path path;
	private Set<Path> collectedFiles;
	private String search;
	private int maxThreads;
	
	public Set<Path> getCollectedFiles() {
		return this.collectedFiles;
	}
	
	public Path getPath() {
		return this.path; 
	}
	
	public String getSearch() {
		return this.search;
	}
	
	public int getMaxThreads() {
		return this.maxThreads;
	}
	
	public Main(String path, String search, int maxThreads) {
		this.path = Paths.get(path);
		this.collectedFiles = new HashSet<>();
		this.search = search;
		this.maxThreads = maxThreads > 0 ? maxThreads : 1;
	}
	
	public void collectFiles() {
		FileCollector.collectFiles(path, collectedFiles);
	}
	
	public void filterFiles() {
		ExecutorService threadPool = Executors.newFixedThreadPool(this.maxThreads);
		for (Path path : collectedFiles) {
			threadPool.execute(new FileFilter(this.path, path ,this.search));
		}
		threadPool.shutdown();
	}
}