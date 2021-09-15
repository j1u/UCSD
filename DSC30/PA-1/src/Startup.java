/**
 * Name: James Lu
 */

/**
 * Class description
 *
 * @author James Lu
 * @since 4-2-2021
 */
public class Startup {
    public static void main(String[] args) {

    }

    /**
     * Determines if every even index in a string array has even length and if every odd index in
     * the array has an odd length.
     * @param arr String array to be checked
     * @return Whether or not it follows the described format
     */
    public static boolean[] arrEvenOdd(String[] arr) {
        //init new boolean array for each check
        boolean[] evenOdd = new boolean[arr.length];

        //loop through string array
        for (int i = 0; i < arr.length; i++) {
            //check if length is even or odd
            if (i % 2 == 0) {
                evenOdd[i] = arr[i].length() % 2 == 0;
            } else {
                evenOdd[i] = arr[i].length() % 2 == 1;
            }
        }
        return evenOdd;
    }

    /**
     * Removes digits from a string
     * @param input String to be manipulated
     * @return New string with digits removed
     */
    public static String removeDigits(String input) {
        //remove all digits in string with regex pattern
        return input.replaceAll("\\d", "");
    }

    /**
     * Finds mass of a compound by multiplying the element (1-26) by the number of elements (1-9)
     * @param compound String representing a compound
     * @return The total mass of the compound
     */
    public static int compoundMass(String compound) {
        //Declare conversion constants and substring index
        final int LETTERCONVERSION = 64;
        final int NUMBERCONVERSION = 48;
        final int SUBSTRING = 2;

        //base case
        if (compound.length() < 1) {
            return 0;
        }
        //convert character to its elemental number using ascii conversion
        int conversion = (int) compound.toUpperCase().charAt(0) - LETTERCONVERSION;

        //convert number of element to its correct number using ascii conversion
        int num = compound.charAt(1) - NUMBERCONVERSION;

        //recursive case removing first two characters from the string
        return (conversion * num) + compoundMass(compound.substring(SUBSTRING));
    }

    /**
     * Swaps uppercase letters with lowercase letters and vice versa
     * @param input String to be manipulated
     * @return New string with cases swapped
     */
    public static String swapCase(String input) {
        //Declare upper / lower ascii bounds and conversion constants
        final int LOWERCASELOWERBOUND = 96;
        final int LOWERCASEUPPERBOUND = 123;
        final int UPPERCASELOWERBOUND = 64;
        final int UPPERCASEUPPERBOUND = 91;
        final int CONVERSION = 32;

        //String builder to create new string
        StringBuilder newString = new StringBuilder();

        //loop through input string
        for (int i = 0; i < input.length(); i++) {
            //ascii number of the character
            int ascii = (int) input.charAt(i);
            //determine if the character is a uppercase or lowercase letter and converts it then
            //appends to string builder
            if (ascii > LOWERCASELOWERBOUND && ascii < LOWERCASEUPPERBOUND) {
                newString.append((char) (ascii - CONVERSION));
            } else if (ascii > UPPERCASELOWERBOUND && ascii < UPPERCASEUPPERBOUND) {
                newString.append((char) (ascii + CONVERSION));
            } else {
                newString.append(input.charAt(i));
            }
        }
        return newString.toString();
    }

    /**
     * Binarizes a matrix based on the midrange
     * @param matrix The matrix to be binarized
     * @return New integer matrix that is binarized
     */
    public static int[][] binarizeMatrixByMidrange(double[][] matrix) {
        //init min, max, and avg
        double min = 999999999;
        double max = -999999999;
        double midrange = 0;
        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        //loop through matrix
        for (double[] inner : matrix) {
            //loop through values
            for (double value : inner) {
                //if value lt min assign new min
                if (value < min) {
                    min = value;
                }
                //if value gt max assign new max
                if (value > max) {
                    max = value;
                }
            }
        }

        //assign midrange with min / max
        midrange = (min + max) / 2;

        //loop through outer array
        for (int i = 0; i < matrix.length; i++) {
            //loop through inner array
            for (int j = 0; j < matrix[i].length; j++) {
                //if value is lt midrange set newMatrix to 0 else set equal to 1
                if (matrix[i][j] < midrange) {
                    newMatrix[i][j] = 0;
                } else {
                    newMatrix[i][j] = 1;
                }
            }
        }
        return newMatrix;
    }

    /**
     * Checks if set1 is a subset of set2
     * @param set1 The subset to be checked
     * @param set2 The set to be checked against
     * @return Whether or not set1 is a subset of set2
     */
    public static boolean subsetChecker(int[] set1, int[] set2) {
        //loop through set1
        for (int i : set1) {
            //loop through set2
            for (int j = 0; j < set2.length; j++) {
                //if the element in set1 is found in set2 break inner loop
                if (i == set2[j]) {
                    break;
                }
                //if the element is never found in set2 return false
                if ((j == set2.length - 1) && (i != set2[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Counts number of rows whose average is less than the total average
     * @param data The data to be analyzed
     * @return The number of rows whose average is < total average
     */
    public static int countSmallRows(double[][] data) {
        //init local variables
        int count = 0;
        int numItems = 0;
        double totalSum = 0;
        double totalAvg = 0;

        //loop through outer array
        for (double[] inner : data) {
            //loop through inner array
            for (double value : inner) {
                totalSum += value;
                numItems += 1;
            }
        }

        //find total average
        totalAvg = totalSum / numItems;

        //loop through outer array
        for (double[] inner : data) {
            int numItemsRow = 0;
            int rowSum = 0;
            int rowAvg = 0;

            //loop through inner array
            for (double value : inner) {
                rowSum += value;
                numItemsRow += 1;
            }

            //calculate row average
            rowAvg = rowSum / numItemsRow;

            //if row avg lt total avg increment count
            if (rowAvg < totalAvg) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Determines if a sequence of numbers are in the same row or column in a number pad
     * @param num Sequence of numbers to be analyzed
     * @return Whether or not all numbers in the sequence are in the same row or column
     */
    public static boolean numpadSRC(int num) {
        //init string builder and valid rows and cols
        StringBuilder sb = new StringBuilder();
        String[] validRowsCols = {"0", "123", "456", "789", "0147", "0258", "0369"};

        //generate new string wout duplicates
        for (char c : Integer.toString(num).toCharArray()){
            if (sb.indexOf(Character.toString(c)) == -1){
                sb.append(c);
            }
        }

        String noDupes = sb.toString();

        if (noDupes.length() == 1){
            return true;
        }else if (noDupes.length() > 2){
            String firstNum = Character.toString(noDupes.charAt(0));
            String secondNum = Character.toString(noDupes.charAt(1));

            //loops through valid rows and cols
            for (int i = 0; i < validRowsCols.length; i++){
                //if the first and second num are in a valid row or col then check rest of noDupes
                if (validRowsCols[i].contains(firstNum) && validRowsCols[i].contains(secondNum)){
                    //loop through no dupes string if any are not found in this row or col
                    //then return false
                    for (char c : noDupes.toCharArray()){
                        if (validRowsCols[i].indexOf(c) == -1){
                            return false;
                        }
                    }
                    return true;
                //if the first and second num are never found together in the valid rows and cols
                //then return false
                }else if (i == validRowsCols.length - 1){
                    return false;
                }
            }
        }
        return true;
    }
}
