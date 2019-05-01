import java.util.*;
import java.io.*;

/*
 * Results class for recommendations
 *
 * @author Courtney Dixon
 * @author Benjamin Blackwell
 * @version Spring 2019
 */
public class Results
{
    private int numLines;
    private int numUsers;
    private int numMovies;
    private int numUsersCourt;
    private int numMoviesCourt;
    private int numUsersBen;
    private List<Integer> users = new ArrayList<>();    //Ben-to count users
    private List<Integer> movies;   //based on Ben's userCount- gets movie count
    private Scanner dataFile;
    private int[][] rawData;        //holds the data from the file
    private int[][] bees;           //the 'b' for each pair of movies
    private float[][] freqs;        //number of times a pair of movies has been seen
    private LinkedList<Integer>[] data;
    //private Map<Integer, Map<Integer, Float>> data;

    /*
     * Constructor
     */
    public Results(String f)
    {
        movies = new ArrayList<>();  
        readFile(f);
              
        numUsers = users.size();
        numMovies = movies.size();
        System.out.println("The call to size says this many users: " + numUsers); 
        System.out.println("The call to size says this many movies: " + numMovies); 
        System.out.println("Ben's count user method say this many users: " + numUsersBen);
        System.out.println("Courtney's count moveis method say this many movie: " + numMoviesCourt);

        //countUsers();       //call to Courtney's original user count method
        //printData();
        //printUserInfo();
        //sortItOut();
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
            countMovies(movie);             //calling Court's user counting method
            user = dataFile.nextInt();
            userCount(user);                //calling Ben's user counting method
            rating = dataFile.nextInt();
            while (dataFile.hasNext())
            {
                rawData[i][j] = user;
                rawData[i][j+1] = movie;
                rawData[i][j+2] = rating;
                movie = dataFile.nextInt();
                countMovies(movie);
                user = dataFile.nextInt();
                userCount(user);
                rating = dataFile.nextInt();
                i++;
            }
            //System.out.println(numUsersBen);
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
     * userCount
     * Benjamin's user counting method
     */
    public void userCount(int userID){
        if(!(users.contains(userID))){
            numUsersBen+=1;
            //users.addFirst(userID);
            users.add(userID);
        }
    }
  
    /*
     * countMovies
     * Courtney's movie counting method
     * based on Ben's user counting method
     */
    public void countMovies(int movieID)
    {
        if(!(movies.contains(movieID)))
        {
            //movies.addFirst(movieID);
            movies.add(movieID);
            numMoviesCourt+=1;
        }
    }
  
    /*
     *
     */
    public void buildFreqs()
    {
        freqs = new float[numMovies][numMovies];
        data = new LinkedList[numUsers];
        LinkedList<Integer> trash = users;
        int tempUser;
        int tempMovie;
        for (int i = 0; i < numLines; i++)
        {
             //for(int j = 0; j < numUsers; j++)
             //{
                 tempUser = trash.getFirst();
                 if(rawData[i][1] == tempUser) 
                 {
                     data[tempUser].add(data[i][0]);
                 }
                     
                   
                     //rawData[i][j+1] = movie;
                 //rawData[i][j+2] = rating;

        }

    }
    
    
    /*
     * sortItOut
     */
    /*public void sortItOut()
    {
        data = new LinkedList[numUsers];
        data[i].add[j];
        data[j].add[i];
        freqs[i][j]++;
        freqs[j][i]++;
    }*/
    
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





