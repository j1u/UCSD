/*
 * NAME: James Lu
 */

import java.io.*;
import java.util.*;

/**
 * Class that quantifies word frequency while accounting for trivial words that may appear
 * in every document
 */
public class TFIDF {

    //tracks total words in each document
    private ArrayList<Integer> totalWords;

    //map for each doc
    private ArrayList<HashMap<String, Integer>> maps;

    /**
     * TFIDF constructor that reads in a text file and populates the maps
     *
     * @param path path to file
     * @throws IOException If file cannot be found
     */
    public TFIDF(String path) throws IOException {
        this.totalWords = new ArrayList<>();
        this.maps = new ArrayList<>();

        File file = new File(path);
        Scanner sc = new Scanner(file);

        //loop through document
        while (sc.hasNextLine()){
            String line = sc.nextLine().toLowerCase();
            String[] words = line.split(" ");

            this.totalWords.add(words.length);
            this.maps.add(populateMaps(words));
        }

    }

    /**
     * Private method to help populate each individual hashmap
     *
     * @param words array of words formed from the document line
     * @return populated hashmap
     */
    private HashMap<String, Integer> populateMaps(String[] words){
        HashMap<String, Integer> tmp = new HashMap<>();

        for (String word : words) {
            if (!tmp.containsKey(word)){
                tmp.put(word, 1);
            }else {
                Integer old = tmp.get(word);
                tmp.replace(word, old,old + 1);
            }
        }
        return tmp;
    }

    /**
     * Returns the number of documents that have been read in
     *
     * @return number of documents
     */
    public int numDocuments() {
        return this.maps.size();
    }

    /**
     * Gets the term frequency for a given word in a given document
     *
     * @param word word to be queried
     * @param docID document to be searched
     * @return returns the freq of the word / total words in the document
     * @throws IllegalArgumentException if word == null or docID out of bounds
     */
    public double getTF(String word, int docID) {
        if (word == null || docID < 0 || docID > this.maps.size() - 1){
            throw new IllegalArgumentException();
        }
        word = word.toLowerCase();

        HashMap<String, Integer> doc = this.maps.get(docID);
        Integer freq = doc.get(word);
        if (freq == null){
            freq = 0;
        }
        return freq.doubleValue() / this.totalWords.get(docID).doubleValue();
    }

    /**
     * Gets the inverse document frequency for a given word
     *
     * @param word word to be searched
     * @return log10 of total # of docs / count of word
     * @throws IllegalArgumentException if word == null
     */
    public double getIDF(String word) {
        if (word == null){
            throw new IllegalArgumentException();
        }
        word = word.toLowerCase();
        double count = 0;
        for (HashMap<String, Integer> map : this.maps) {
            if (map.containsKey(word)){
                count++;
            }
        }
        if (count == 0){
            return Double.POSITIVE_INFINITY;
        }
        return Math.log10(this.numDocuments() / count);
    }

    /**
     * Given a word and docID, calculates the TFIDF statistic
     *
     * @param word word to be searched for
     * @param docID docID to be searched for
     * @return TFIDF statistic by multiplying TF * IDF
     */
    public double query(String word, int docID) {
        word = word.toLowerCase();

        double tf = this.getTF(word, docID);
        double idf = this.getIDF(word);

        return tf * idf;
    }
}
