import bg.uni.sofia.fmi.grep.Main;

public class MultithreadedGrep {
	public static void main(String[] args)
	{
		Main main = new Main("C:\\Users\\Marinov\\Documents\\TestDir", " z", 5);
		main.collectFiles();
		main.filterFiles();
	}
}
