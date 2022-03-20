import java.util.Scanner;

import numberone.*;


public class lab9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Ввод: ");
            String userInput = in.nextLine();
            if(userInput.equals("quit")){
                in.close();
                break;
            }
            try {
                String result = calculator.main(userInput);
                System.out.println("Вывод: " + result);
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
    }
}