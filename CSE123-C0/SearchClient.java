// Oliver Giordano 
// 01/09/2026
// CSE 123 
// C0: Search Engine
// TA: Ishita Mundra

import java.io.*;
import java.util.*;

// This class allows users to quire and rate books within BOOK_DIRECTORY containing certain words
public class SearchClient {
    public static final String BOOK_DIRECTORY = "./books";
    private static final Random RAND = new Random();

    // Some class constants you can play around with to give random ratings to the uploaded books!
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;
    public static final int MIN_NUM_RATINGS = 1;
    public static final int MAX_NUM_RATINGS = 100;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        List<Media> media = new ArrayList<>(loadBooks());

        Map<String, Set<Media>> index = createIndex(media);

        System.out.println("Welcome to the CSE 123 Search Engine!");
        String command = "";
        while (!command.equalsIgnoreCase("quit")) {
            System.out.println("What would you like to do? [Search, Rate, Quit]");
            System.out.print("> ");
            command = console.nextLine();
            if (command.equalsIgnoreCase("search")) {
                searchQuery(console, index);
            } else if (command.equalsIgnoreCase("rate")) {
                addRating(console, media);
            } else if (!command.equalsIgnoreCase("quit")) {
                System.out.println("Invalid command, please try again.");
            }
        }
        System.out.println("See you next time!");
    }
    
    // Behavior:
    //   - Creates an inverted index, using words from an array of books as keys, and sorting
    //     all the titles of the books that those words appear into sets as the values
    // Parameters:
    //   - The list of books that will be intigrated into the index
    // Returns:
    //   - an inverted tree as a Map with Strings as the key and a Set of Media objects as the
    //     values
    // Exceptions:
    //   - None
    public static Map<String, Set<Media>> createIndex(List<Media> docs) {
        TreeMap<String, Set<Media>> invertedIndex = new TreeMap<>();
        for(Media doc : docs){
            for(String word : new HashSet<>(doc.getContent())){
                if(!invertedIndex.containsKey(word.toLowerCase())){
                    invertedIndex.put(word.toLowerCase(), new HashSet<Media>());
                }
                invertedIndex.get(word.toLowerCase()).add(doc);
            }
        }
        return invertedIndex;
    }

    // Behavior:
    //   - Searchs an inverted index for words that have been passed in as a string
    // Parameters:
    //   - An inverted tree of type Map using Strings as keys and a Set of Media objects, and
    //     a string that will be used to query the index
    // Returns:
    //   - a set of media objects that contained the words present in the query 
    // Exceptions:
    //   - None
    public static Set<Media> search(Map<String, Set<Media>> index, String query) {
        TreeSet<Media> mediaRecomendations = new TreeSet<Media>();
        String[] brokenQuery = query.split(" ");
        for(String word: brokenQuery){ 
            for(String key : index.keySet()){
                if(key.equals(word)){
                    for(Media media : index.get(key)){
                        mediaRecomendations.add(media);
                    }
                }
            }
        }
        return mediaRecomendations; // return the media recomendations
    }
    
    // Allows the user to search a specific query using the provided 'index' to find appropriate
    //  Media entries.
    //
    // Parameters:
    //   console - the Scanner to get user input from. Should be non-null
    //   index - an inverted index mapping terms to the Set of media containing those terms.
    //           Should be non-null
    public static void searchQuery(Scanner console, Map<String, Set<Media>> index) {
        System.out.println("Enter query:");
        System.out.print("> ");
        String query = console.nextLine();

        Set<Media> result = search(index, query);
        
        if (result.isEmpty()) {
            System.out.println("\tNo results!");
        } else {
            for (Media m : result) {
                System.out.println("\t" + m.toString());
            }
        }
    }

    // Allows the user to add a rating to one of the options wthin 'media'
    //
    // Parameters:
    //   console - the Scanner to get user input from. Should be non-null.
    //   media - list of all media options loaded into the search engine. Should be non-null.
    public static void addRating(Scanner console, List<Media> media) {
        for (int i = 0; i < media.size(); i++) {
            System.out.println("\t" + i + ": " + media.get(i).toString());
        }
        System.out.println("What would you like to rate (enter index)?");
        System.out.print("> ");
        int choice = Integer.parseInt(console.nextLine());
        if (choice < 0 || choice >= media.size()) {
            System.out.println("Invalid choice");
        } else {
            System.out.println("Rating [" + media.get(choice).getTitle() + "]");
            System.out.println("What rating would you give?");
            System.out.print("> ");
            int rating = Integer.parseInt(console.nextLine());
            media.get(choice).addRating(rating);
        }
    }

    // Loads all books from BOOK_DIRECTORY. Assumes that each book starts with two lines -
    //      "Title: " which is followed by the book's title
    //      "Author: " which is followed by the book's author
    // Exceptions:
    //   FileNotFoundException - if BOOK_DIRECTORY does not exist or is not a directory
    // Returns:
    //   A list of all book objects corresponding to the ones located in BOOK_DIRECTORY
    public static List<Media> loadBooks() throws FileNotFoundException {
        List<Media> ret = new ArrayList<>();
        
        File dir = new File(BOOK_DIRECTORY);
        for (File f : dir.listFiles()) {
            Scanner sc = new Scanner(f, "utf-8");
            String title = sc.nextLine().substring("Title: ".length());
            List<String> author = List.of(sc.nextLine().substring("Author: ".length()));

            Media book = new Book(title, author, sc);

            // Adds random ratings to 'book' based on the class constants. 
            // Feel free to comment this out.
            int minRating = RAND.nextInt(MAX_RATING - MIN_RATING + 1) + MIN_RATING;
            addRatings(minRating, 
                       Math.min(MAX_RATING,RAND.nextInt(MAX_RATING - minRating + 1) + minRating),
                       RAND.nextInt(MAX_NUM_RATINGS - MIN_NUM_RATINGS) + MIN_NUM_RATINGS, book);
            ret.add(book);
        }

        return ret;
    }

    // Adds ratings to the provided media numRatings amount of times. Each rating is a random int
    // between minRating and maxRating (inclusive).
    private static void addRatings(int minRating, int maxRating, int numRatings, Media media) {
        for (int i = 0; i < numRatings; i++) {
            media.addRating(RAND.nextInt(maxRating - minRating + 1) + minRating);
        }
    }
}

