import java.util.*;
import java.io.*;

/*
 * Results class for recommendations
 *
 * @author Courtney Dixon
 * @version Spring 2019
 */
public class Results
{
    private int numLines;
    private int numUsers;
    private Scanner dataFile;
    private int[][] rawData;
    private int[][] bees;
    private float[][] freqs;
    private LinkedList<Integer>[] data;
    //private Map<Integer, Map<Integer, Float>> data;

    /*
     * Constructor
     */
    public Results(String f)
    {
        readFile(f);
        countUsers();
        //printData();
        //printUserInfo();
        sortItOut();
    }

    /*
     * readFile
     * reads the raw data file into a 2-D array
     * @param fn file to be read
     */
    public void readFile(String fn)
    {
        int user;
        int movie;
        int rating;
        int i = 0;
        int j = 0;

        try
        {
            dataFile = new Scanner(new FileReader(fn));
            numLines = dataFile.nextInt();
            //System.out.println(numLines);
            rawData = new int[numLines][3];
            movie = dataFile.nextInt();
            user = dataFile.nextInt();
            //movie = dataFile.nextInt();
            rating = dataFile.nextInt();
            while (dataFile.hasNext())
            {
                rawData[i][j] = user;
                rawData[i][j+1] = movie;
                rawData[i][j+2] = rating;
                movie = dataFile.nextInt();
                user = dataFile.nextInt();
                //movie = dataFile.nextInt();
                rating = dataFile.nextInt();
                i++;
            }
            dataFile.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        //System.out.println("Data read with no problemos!");
    }
    
    /*
     * sortItOut
     */
    public void sortItOut()
    {
        data = new LinkedList[numUsers];
        data[i].add[j];
        data[j].add[i];
        freqs[i][j]++;
        freqs[j][i]++;
    }
    
    /*
     * countUsers
     */
    public void countUsers()
    {
        
        numUsers++;
    }
    
    /*
     * printData
     * prints the raw data file after being read into the raw data array
     */
    public void printData()
    {
        for (int i = 0; i < numLines; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(rawData[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    
    /*
     * printUserInfo
     * prints the movie ids that a single user is associated with
     */
    public void printUserInfo()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which user would you like a list for? ");
        int uNum = keyboard.nextInt();
        for(int i = 0; i < numLines; i++)
        {
            if(rawData[i][0] == uNum)
            {
                System.out.println(rawData[i][1]);
            }
        }
    }

    /*
     * Main method
     * program runs from command line
     */
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("usage: java Results <filename>");
            System.exit(0);
        }
        String filename = args[0];
        Results r = new Results(filename);
    }
}





