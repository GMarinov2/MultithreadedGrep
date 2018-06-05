import bg.uni.sofia.fmi.grep.Main;

public class MultithreadedGrep {
	public static void main(String[] args)
	{
	//	if(args.length != 3) {
	//		System.out.println("Invalid number of arguments...");
	//		return;
	//	}
		Main main = new Main("C:\\Users\\Marinov\\Documents\\TestDir", " z", 5);
	//	Main a = new Main(args[0],args[1],Integer.parseInt(args[2]));

		main.collectFiles();
		main.filterFiles();
	}

	private String[] parseArgs(String[] args) {
        String[] parsedArgs = null;
	    if (args.length >= 3) {
            parsedArgs = new String[3];
            parsedArgs[0] = args[0];
            for (int i = 1; i < args.length - 1 ;++i)  {
                parsedArgs[1].concat(args[i]);
            }
            parsedArgs[2] = args[args.length - 1];
        }
        return parsedArgs;
    }
}