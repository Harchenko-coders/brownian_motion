package java.com.harchenko.main;
import java.util.Scanner;
public class Main {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of cells");
        int number_of_cells = scanner.nextInt();

        System.out.println("Enter number of admixture");
        int number_of_admixture = scanner.nextInt();

        int[] cells = new int[number_of_cells];

        cells[0] = number_of_admixture;

        for(int i = 0; i < number_of_cells; i++){

        MyTread myTread = new MyTread();
        myTread.start();

        }
    }
}
class MyTread extends Thread {
    public void run() {

    }
}

class Runner implements Runnable {
    @Override
    public void run(){

    }
}