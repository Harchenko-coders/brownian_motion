import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter Number of cells: ");
        int numberOfCells = in.nextInt();

        System.out.print("Enter Number of atoms: ");
        int numberOfAtoms = in.nextInt();

        System.out.print("Enter probability: ");
        double probability = in.nextDouble();

        in.close();

        Cristal cristal = new Cristal(numberOfCells, numberOfAtoms, probability);
        cristal.startSimulation(false);
    }
}




