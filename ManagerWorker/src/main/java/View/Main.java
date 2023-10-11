
package View;

import Controller.Manager;
import Model.History;
import Model.Worker;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<Worker> lw = new ArrayList<>();
        ArrayList<History> lh = new ArrayList<>();
        Manager mn = new Manager();
        while(true) {
            int choice = mn.menu();
            switch (choice) {
                case 1:
                    mn.addWorker(lw);
                    break;
                case 2:
                    mn.changeSalary(lw, lh, 1);
                    break;
                case 3:
                    mn.changeSalary(lw, lh, 2);
                    break;
                case 4:
                    mn.printListHistory(lh);
                    break;
                case 5:
                    return;
            }
        }
                
    }
}
