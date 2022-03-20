package numberone;

import java.util.ArrayList;

public class calculator implements globals {

    private static boolean hasOperator(String str) {
        for(String operation: operations){
            if(str.indexOf(operation) > 0)
                return true;
        }
        return false;
    }

    private static fraction parsefraction(String str) {
        int a = str.indexOf("/");
        if(a == -1){
            throw new IllegalArgumentException("Ошибка. Некорректное выражение");
        }
        try {
            int first = Integer.parseInt(str.substring(0, a));
            int second = Integer.parseInt(str.substring(a + 1));
            return new fraction(first, second);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка. Некорректное выражение");
        }
    }

    

    private static ArrayList<String> calculateAndRemoveFromArray(ArrayList<String> stringArrayList, int priority) {
        int i = 1;
        String[] operationsToCheck;
        if(priority == 1){
            operationsToCheck = new String[] {operations[0], operations[1]};
        } else {
            operationsToCheck = new String[] {operations[2], operations[3]};
        }
        try{
            while(true){
                if(stringArrayList.get(i).equals(operationsToCheck[0])  || stringArrayList.get(i).equals(operationsToCheck[1])) {
                    fraction a = parsefraction(stringArrayList.get(i-1));
                    fraction b = parsefraction(stringArrayList.get(i+1));
                    fraction c = null;
                    if(priority == 1){
                        if(stringArrayList.get(i).equals(operationsToCheck[0])){
                            c = a.multi(b);
                        } else {
                            c = a.div(b);
                        }
                    } else {
                        if(stringArrayList.get(i).equals(operationsToCheck[0])){
                            c = a.sum(b);
                        } else {
                            c = a.sub(b);
                        }
                    }
                    stringArrayList.set(i-1, c.toString());
                    stringArrayList.remove(i+1);
                    stringArrayList.remove(i);
                } else {
                    i = i + 2;
                }
            }
        } catch (IndexOutOfBoundsException e){
            return stringArrayList;
        }
    }

    private static String calculateInPriority(ArrayList<String> stringArrayList) {
        ArrayList<String> stringArrayList1 = calculateAndRemoveFromArray(stringArrayList, 1);
        ArrayList<String> stringArrayList2 = calculateAndRemoveFromArray(stringArrayList1, 2);
        return stringArrayList2.get(0);
    }

    private static ArrayList<String> moveToArrayList(String str) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        int o = 0;
        for(int i = 1; i < str.length() - 1; i++) {
            if(validator.oneOfOperation(str.charAt(i)) && str.charAt(i-1) != '/') {
                stringArrayList.add(str.substring(o, i));
                stringArrayList.add(String.valueOf(str.charAt(i)));
                o = i+1;
            }
        }
        stringArrayList.add(str.substring(o));
        return stringArrayList;
    }

    public static String calculateInString(String str) {
        ArrayList<String> stringArrayList = moveToArrayList(str);
        return calculateInPriority(stringArrayList);
    }

    private static String parseBrackets(String str) {
        int indexOfOpenBracket = -1;
        int indexOfCloseBracket = -1;
        for (int i = 0; i < str.length() - 1; i++)
        {
            if((str.charAt(i) == '(') && (str.charAt(i+1) != '(')){
                indexOfOpenBracket = i;
                break;
            } 
        }
        if(indexOfOpenBracket == -1){
            return calculateInString(str);
        }
        indexOfCloseBracket = str.indexOf(")");

        if(indexOfCloseBracket < indexOfOpenBracket){
            throw new IllegalArgumentException("Ошибка. Некорректное выражение");
        }

        String inBrackets = calculateInString(str.substring(indexOfOpenBracket + 1, indexOfCloseBracket));

        return str.substring(0, indexOfOpenBracket) + inBrackets+str.substring(indexOfCloseBracket + 1);
    }

    public static fraction calculate(String str){
        while(hasOperator(str)){
            str = parseBrackets(str);
        }
        return parsefraction(str);
    }

    public static String main(String userInput) {
        userInput = userInput.replaceAll(" ", "");
        validator.validate(userInput);
        String normalizedInput = normalizer.normalize(userInput);

        fraction result = calculate(normalizedInput);
        return result.optimization();
    }
    
}
