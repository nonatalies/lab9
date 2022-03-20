package numberone;

public class validator implements globals{

    private static void validateBrackets(String str){
        int openNumber = 0;
        int closeNumber = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '(')
                openNumber++;
            if(str.charAt(i) == ')')
                closeNumber++;
        }

        if (openNumber != closeNumber) {
            throw new IllegalArgumentException("Ошибка. Некорректное выражение");
        }
    }

    public static boolean oneOfOperation(char operator){
        for(String operation: operations){
            if(operator == operation.charAt(0)) {
                return true;
            }
        }
        return false;
    }

    private static void validateMoreThanOneOperationInARow(String str) {
        for(int i = 0; i < str.length() - 1; i++){
            if(oneOfOperation(str.charAt(i)) && oneOfOperation(str.charAt(i+1))){
                throw new IllegalArgumentException("Ошибка. Некорректное выражение");
            }
        }
    }
    
    public static void validate(String str) {
        validateBrackets(str);
        validateMoreThanOneOperationInARow(str);
    }
}
