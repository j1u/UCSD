/*
 * Name: James Lu
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Processes a text file and records the number of documents that contain a given word
 *
 * @author James Lu
 * @since 5/24/21
 */
public class DocumentFrequency {

    private ArrayList<String> docs;
    private ArrayList<HashTable> tables;
    private int numDocs;

    /**
     * Document frequency constructor that reads in a text file and initializes
     * the hash tables
     *
     * @param path path to file
     * @throws IOException if the file is not found
     */
    public DocumentFrequency(String path) throws IOException {
        this.numDocs = 0;
        this.docs = new ArrayList<>();
        this.tables = new ArrayList<>();
        // open and read file
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String d = scanner.nextLine().trim();
            //temp hash table
            HashTable tmp = new HashTable();
            //insert words from doc into hashtable
            for (String w : d.split(" ")) {
                w = w.toLowerCase();
                tmp.insert(w);
            }
            //add doc to doc list and hashtables to tables list
            this.docs.add(d);
            this.tables.add(tmp);
            this.numDocs++;
        }
        scanner.close();
    }

    /**
     * returns the number of docs in the file
     *
     * @return number of docs in the file
     */
    public int numDocuments() {
        return this.numDocs;
    }

    /**
     * Queries a word and finds how many documents it occurs in
     *
     * @param word word to be queried
     * @return number of occurrences
     */
    public int query(String word) {
        word = word.toLowerCase();
        int count = 0;
        for (HashTable ht : this.tables) {
            if (ht.lookup(word)){
                count++;
            }
        }
        return count;
    }
}
