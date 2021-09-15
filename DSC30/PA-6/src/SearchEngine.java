/*
 * Name: James Lu
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author James Lu
 * @since  5/10/2021
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                populateMovieStudio(movieTree, cast, movie);
                populateMovieStudio(studioTree, studios, movie);
                populateRating(ratingTree, cast, rating);
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Populates the given trees with values (for movies and studios)
     *
     * @param tree tree to be populated
     * @param keys keys of the tree
     * @param value values to be stored
     */
    private static void populateMovieStudio(BSTree<String> tree, String[] keys, String value){
        for (String key : keys) {
            key = key.toLowerCase();
            if (!tree.findKey(key)) {
                tree.insert(key);
            }
            if (!tree.findDataList(key).contains(value)){
                tree.insertData(key, value.toLowerCase());
            }
        }
    }

    /**
     * Populates the given trees with values (for ratings)
     *
     * @param tree tree to be populated
     * @param keys keys of the tree
     * @param value values to be stored
     */
    private static void populateRating(BSTree<String> tree, String[] keys, String value){
        for (String key : keys) {
            key = key.toLowerCase();
            if (!tree.findKey(key)) {
                tree.insert(key);
            }
            if (!tree.findDataList(key).contains(value)){
                tree.insertData(key, value);
            }
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        LinkedList<String> inter = new LinkedList<>();

        try {
            inter = new LinkedList<>(searchTree.findDataList(keys[0]));
            if (keys.length > 1) {
                for (int i = 1; i < keys.length; i++) {
                    inter.retainAll(searchTree.findDataList(keys[i]));
                }
            }
            print(query, inter);
        }catch (IllegalArgumentException e){
            print(query, inter);
        }

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        if (keys.length > 1){
            LinkedList<String> total = new LinkedList<>();
            LinkedList<String> ind = new LinkedList<>();
            for (String key : keys) {
                try {
                    ind = new LinkedList<>(searchTree.findDataList(key));
                    ind.removeAll(total);
                    ind.removeAll(inter);
                    if (ind.isEmpty()){ continue; }
                    print(key, ind);
                    total.addAll(searchTree.findDataList(key));
                }catch (IllegalArgumentException e){
                    print(key, ind);
                }
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        final int ARG_START = 2;
        final int ACTORS_MOVIES = 0;
        final int STUDIOS_MOVIES = 1;
        final int MOVIES_RATINGS = 2;


        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        StringBuilder sb = new StringBuilder();
        for (int i = ARG_START; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }
        String key = sb.substring(0, sb.length() - 1);

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);

        // choose the right tree to query
        switch (searchKind){
            case ACTORS_MOVIES:
                searchMyQuery(movieTree, key);
                break;
            case STUDIOS_MOVIES:
                searchMyQuery(studioTree, key);
                break;
            case MOVIES_RATINGS:
                searchMyQuery(ratingTree, key);
                break;
        }
    }
}
