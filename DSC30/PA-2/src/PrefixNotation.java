/*
    Name: James Lu
 */

/**
 * Class prefix notation containing one method that evaluates a prefix expression
 * @author James Lu
 * @since  9 April 2021
 */
public class PrefixNotation {

    /**
     * Evaluates a given valid prefix expression
     * @param notation the valid prefix expression to evaluate
     * @return evaluated expression
     */
    public static int evaluate(String notation){
        //splits string using spaces as a delimiter
        String[] elements = notation.split(" ");
        //new intstack to track the expression
        IntStack st = new IntStack(elements.length);

        //starts loop from the end of the expression
        for (int i = elements.length-1; i >= 0; i--){
            //if current element is an operand then push it onto the stack
            if (elements[i].matches("\\d+")){
                st.push(Integer.parseInt(elements[i]));
            //if element is not a digit (must be a operator)
            }else {
                //if else statement that executes the operand and operator expression
                if (elements[i].equals("+")){
                    st.push(st.pop() + st.pop());
                }else if (elements[i].equals("-")){
                    st.push(st.pop() - st.pop());
                }else if (elements[i].equals("*")){
                    st.push(st.pop() * st.pop());
                }else if (elements[i].equals("/")){
                    st.push(st.pop() / st.pop());
                }
            }
        }
        //returns final expression stored in stack
        return st.pop();
    }
}
