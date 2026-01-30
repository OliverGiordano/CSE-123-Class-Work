import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {
    private String name;
    private int size;
    private Commit head;
    
    public Repository(String name){
        if(name == null){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getRepoHead(){
        if(head == null){
            return null;
        }
        return head.id;
    }

    public int getRepoSize(){
        return size;
    }

    public String toString(){
       return name + " - Current head: " + head.toString(); 
    }

    public boolean contains(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        Commit tmp = head;
        while(tmp != null){
            if(tmp.id == targetId){
                return true;
            }
            tmp = tmp.past;
        }
        return false;
    }
        
    public String getHistory(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        int i = 0;
        Commit tmp = head;
        String output = "";
        while(i < n && tmp != null){
            output += tmp.toString() + "\n";
            tmp = tmp.past;
        } 
        return output;
    }

    public String Commit(String message){
        if(message == null){
            throw new IllegalArgumentException();
        }
        head = new Commit(message, head);
        size++;
        return head.id;
    }

    public boolean drop(String targetId){
        if(targetId == null){
            throw new IllegalArgumentException();
        }
        if(head.id == targetId){
            head = head.past;
            size--;
            return true;
        }
        if(head == null){
            return false;
        }
        Commit tmp = head;
        while(tmp.past != null){
            if(tmp.past.id == targetId){
               tmp.past = tmp.past.past; 
               size--;
               return true;
            }
        }
        return false;
    }

    public void syncronize(Repository other){
        if(other == null){
            throw new IllegalArgumentException();
        }
        if(head == null){
            head = other.head;
            return;
        }
        Commit tmp = head;
        while(other.head != null && tmp.timeStamp < other.head.timeStamp){
            Commit otherTmp = other.head;
            other.drop(other.head.id);
            otherTmp.past = head.past;
            head = otherTmp;
        }
        tmp = head;
        while(tmp.past != null && other.head != null){
            if(tmp.past.timeStamp < other.head.timeStamp){
                Commit otherTmp = other.head;
                other.drop(other.head.id);
                otherTmp.past = head.past;
                tmp = otherTmp;
            }
            tmp = tmp.past;
        }
        while(other.head != null){
            Commit otherTmp = other.head;
            other.drop(other.head.id);
            tmp.past = otherTmp;
            tmp = tmp.past;
        }

    }


    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public static class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit. Should be non-null.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
