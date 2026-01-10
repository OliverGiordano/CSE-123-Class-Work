import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws FileNotFoundException{
        /*File obj = new File("./books/The Great Gatsby.txt");
        try (Scanner sc = new Scanner(obj)){
            Book test = new Book("The greater gatsby", new ArrayList<>(Arrays.asList("F. scotty", "zelda")), sc);
            System.out.println(test.getTitle());
            System.out.println(test.getArtists());
            System.out.println(test.getNumRatings());
            System.out.println(test.getAverageRating());
            test.addRating(4);
            test.addRating(3);
            test.addRating(3);
            System.out.println(test.getNumRatings());
            System.out.println(test.getAverageRating());
            System.out.println(test.toString());
            for(String str : test.getContent()){
                System.out.print(str + " ");
            }
        } catch (FileNotFoundException e){
            System.out.println("looks like rain");
        }*/

        Map<String, Set<Media>> map = SearchClient.createIndex(SearchClient.loadBooks());
        for(String key : map.keySet()){
            //System.out.print(key + " ");
            //System.out.println(key + " : " + map.get(key));
            if(key.equals("scott")){
                System.out.println(key + " : " + map.get(key));
            }
        }
    }
}
