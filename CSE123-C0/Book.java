// Oliver Giordano 
// 01/09/2026
// CSE 123 
// C0: Search Engine
// TA: Ishita Mundra

import java.util.*;

// A method the implement the media interface, it takes in a book title, author, and a Scanner
// that has a book or text loaded into it, it then provides array version of the text and 
// allows the book to sotre ratings.
class Book implements Media, Comparable<Book>{  
    private String title;
    private List<String> authors;
    private Scanner content;
    private double rating; // every time a rating is added the rating will add to this score
    private int numberOfRatings; // ever time a rating is added this will increment by 1
    private ArrayList<String> scannerContent = new ArrayList<String>();
    
    // Behavior:
    //   - This method takes initialilzation arguments and loads them into private class feilds
    // Parameters:
    //   - title: the title of the book as a String value 
    //   - authors: the authors of the book as a List of Strings
    //   - content: A Scanner object loaded with text
    // Returns:
    //   - This is a constructor nothing is returned
    // Exceptions:
    //   - None
    public Book(String title, List<String> authors, Scanner content){
       this.title = title;
       this.authors = authors;
       this.content = content;
    }
    
    // Behavior:
    //   - Returns the title of the book as input when initalizing the class
    // Parameters:
    //   - None 
    // Returns:
    //   - The the title of the book as a String
    // Exceptions:
    //   - None
    public String getTitle(){ // return the title of the media
        return title;
    }

    // Behavior:
    //   - Returns the authors of the book as input when initializing the class
    // Parameters:
    //   - None 
    // Returns:
    //   - The the authors as a list of Strings
    // Exceptions:
    //   - None
    public List<String> getArtists(){
        return new ArrayList<String>(authors);
    }

    // Behavior:
    //   - Takes in a score value and adds it to the total rating, it also increase the 
    //     number of reviews
    // Parameters:
    //   - The score that you want to increase the rating by
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public void addRating(int score){ 
        numberOfRatings++;
        rating += score; 
    }

    // Behavior:
    //   - Returns the total number of ratings that have been input 
    // Parameters:
    //   - None
    // Returns:
    //   - The total number of ratings that have been input
    // Exceptions:
    //   - None
    public int getNumRatings(){ 
        return numberOfRatings;
    }

    // Behavior:
    //   - Calculates the average score of the book 
    // Parameters:
    //   - None
    // Returns:
    //   - the average value score as a double value
    // Exceptions:
    //   - None
    public double getAverageRating(){
        if(numberOfRatings == 0){
            return 0.0;
        }
        return ((double)rating/numberOfRatings);
    }

    // Behavior: 
    //   - Takes the scanner object with book contents as passed in on initialization and add them
    //     to an array
    // Parameters:
    //   - None
    // Returns:
    //   - an array containing all the words in the scanner object split by spaces
    // Exceptions:
    //   - None
    public List<String> getContent(){
        if(scannerContent.isEmpty()){}
            while(content.hasNext()){ 
                scannerContent.add(content.next());
            }
        return new ArrayList<String>(scannerContent);
    }

    // Behavior:
    //   - Returns a string value of the object book object
    // Parameters:
    //   - None
    // Returns:
    //   - Returns a String in the format:
    //     "<title> by [<authors>]: <average rating> [<number of reviews>]"
    //   - if there are no ratings, the rturn format will look like:
    //     "<title> by [<authors>]"
    // Exceptions:
    //   - None
    public String toString(){
        String outputString = ""; 
        outputString += title + " by [";
        for(int i = 0; i < authors.size(); i++){
            outputString += authors.get(i);
            if(i < authors.size() - 1){ 
                outputString += ", ";
            }
        }
        outputString += "]"; 
        if(numberOfRatings != 0){
            outputString += ": ";
            double score = (double)rating/numberOfRatings; 
            outputString += String.format("%.2f", score) + " (" + numberOfRatings + " ratings)";
        }
        return outputString;
    }

    // Behavior:
    //   - Compares two book objects 
    // Parameters:
    //   - The book object that you want to compare too
    // Returns:
    //   - Returns a postive interger based if the book your comparing two has a lower average
    //     rating and returns a negitive interger if the book has a lower average rating
    //   - if the rounded difference between the ratings is 0 than, it compares the authors and
    //     returns the a positive interger if author of the book object passed in has a name that 
    //     comes alphabeticly second, if the author of the book object that is passed in has a name
    //     that comes alphabeticly first, a negitive interger is returned. if the authors are the
    //     same, than it returns a positive number if the title of the book passed in is shorter, 
    //     a negitive nnumber if the title of the book passed in is longer, and 0 if the titles are
    //     the same length
    // Exceptions:
    //   - None
    public int compareTo(Book book){ 
        if(Math.round(book.getAverageRating() - this.getAverageRating()) != 0){
            return (int)Math.round(book.getAverageRating() - this.getAverageRating());
        }
        if(!(authors.get(0).compareTo(book.getArtists().get(0)) == 0)){
            return authors.get(0).compareTo(book.getArtists().get(0));
        }
        if(getTitle().length() > book.getTitle().length()){
            return 1;
        } else if(getTitle().length() < book.getTitle().length()){
            return -1;
        } else {
            return 0;
        }
    }

}


