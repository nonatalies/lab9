package numberone;

public class normalizer {

    private static String removeRedundantBrackets(String str) {
        while(true){
            if(str.charAt(0) == '(' && str.charAt(1) == '('
                && str.charAt(str.length()-1) == ')' && str.charAt(str.length()-2) == ')') {
                str = str.substring(1, str.length()-1);
            } else {
                break;
            }
        }
        return str;
    }

    public static String normalize(String str) {
        String str1 = removeRedundantBrackets(str);

        return str1;
    }

}
