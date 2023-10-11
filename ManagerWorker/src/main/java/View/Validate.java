
package View;

import Model.Worker;
import java.util.ArrayList;
import java.util.Scanner;


public class Validate {
     public int checkInputIntLimit(int min, int max) {
         Scanner sc = new Scanner(System.in);
         while(true) {
             try {
                 int result = Integer.parseInt(sc.nextLine().trim());
                 if(result < min || result > max) {
                     throw new NumberFormatException();
                 }
                 return result;
             } catch (NumberFormatException e) {
                 System.err.println("Please input number in rage [" + min + ", " + max + "]");
                 System.out.print("Enter again: ");
             }
         }
     }
     
     public String checkInputString() {
         Scanner sc = new Scanner(System.in);
         while(true) {
             String result = sc.nextLine().trim();
            if(result.isEmpty()) {
                 System.err.println("Not empty");
                 System.out.print("Enter again: ");
            } else {
                 return result;
            }
         }
     }
     
     public boolean checkIdExist(ArrayList<Worker> lw, String id) {
         for (Worker worker : lw) {
             if(id.equalsIgnoreCase(worker.getId())) {
                 return true;
             }
         }
         return false;
    }
     
     public int checkInputSalary() {
         Scanner sc = new Scanner(System.in);
         while(true) {
             try {
                 int result = Integer.parseInt(sc.nextLine().trim());
                 if(result < 0) {
                     throw new NumberFormatException();
                 } 
                 return result;
             } catch (NumberFormatException e) {
                 System.err.println("Salary must be greater than 0");
                 System.out.print("Enter again: ");
             }
         }
     }
     
     
     public boolean checkWorkerExist(ArrayList<Worker> lw, String id, String name, int age, int salary, String workLocation) {
         for (Worker worker : lw) {
             if(id.equalsIgnoreCase(worker.getId()) && name.equalsIgnoreCase(worker.getName()) && age == worker.getAge() && salary == worker.getSalary()) {
                 return false;
                 
             }
         }
         return true;
     }
       
}
