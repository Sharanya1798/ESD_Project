import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.lang.Math;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static double sqrtFunction(double number)
    {
        logger.info("Verifying squareRoot Function for "+number);
        return Math.sqrt(number);
    }
    public static double factorialFunction(double number)
    {
        logger.info("Verifying factorial Function for "+number);

        if(number < 0)
            return Double.NaN;
        double fact = 1, i;
        for(i=1; i<=number; i++){
            fact = fact*i;
        }
        return fact;
    }
    public static double naturalLogFunction(double number)
    {
        logger.info("Verifying natural logarithm Function for "+number);
        return Math.log(number);
    }
    public static double powerFunction(double number, double power)
    {
        logger.info("Verifying power Function for "+number+"and power"+power);

        if(number == 0 && power == 0)
            return Double.NaN;
        return Math.pow(number, power);
    }
    public static void main(String[] args) {
        while(true)
        {
            System.out.println("\n\n--------------Select your choice---------------\n1. Square Root\n2. Factorial function\n3. Natural logarithm(ln(x))\n4. Power function\n5. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if(choice == 5)
            {
                System.out.println("exit done");
                return;
            }
            System.out.println("Enter any number");
            double number = scanner.nextDouble();
            switch (choice) {
                case 1:
                    System.out.println("Square root of given number is "+sqrtFunction(number));
                    break;
                case 2:
                    System.out.println("Factorial of given number is "+factorialFunction(number));
                    break;
                case 3:
                    System.out.println("Natural logarithm of the given number is "+naturalLogFunction(number));
                    break;
                case 4:
                    System.out.println("Enter the power");
                    double power = scanner.nextDouble();
                    System.out.println("The result obtained is "+powerFunction(number, power));
                    break;
                default: System.out.println("Invalid choice made");
            }
        }
    }
}
