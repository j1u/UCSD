public class finalstuff {
    public static void main(String[] args) {

    }

    public String[] method(String s){
        String s1 = "";
        String s2 = "";

        for (int i = 0; i < s.length(); i++){
            if (i % 2 == 0){
                s1+=Character.toString(s.charAt(i));
            }else{
                s2+=Character.toString(s.charAt(i));
            }
        }
        return new String[]{s1, s2};
    }
}
