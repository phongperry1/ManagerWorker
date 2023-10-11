
package Controller;

import Model.History;
import Model.Worker;
import View.Validate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Manager {
    Validate vl = new Validate();
    
    public int menu() {
        System.out.println("1. Add worker.");
        System.out.println("2. Increase salary for worker.");
        System.out.println("3. Decrease for worker");
        System.out.println("4. Show adjusted salary worker information");
        System.out.println("5. Exit");
        int choice = vl.checkInputIntLimit(1, 5);
        return choice;
    }
    
    
    public void addWorker(ArrayList<Worker> lw) {
        System.out.print("Enter code: ");
        String id = vl.checkInputString();
        
        System.out.print("Enter name: ");
        String name = vl.checkInputString();
        System.out.print("Enter age: ");
        int age = vl.checkInputIntLimit(18, 50);
        System.out.print("Enter salary: ");
        int salary = vl.checkInputSalary();
        System.out.print("Enter work location: ");
        String workLocation = vl.checkInputString();
        if(!vl.checkWorkerExist(lw, id, name, age, salary, workLocation)) {
            System.err.println("Duplicate");
        } else {
            lw.add(new Worker(id, name, age, salary, workLocation));
            System.out.println("Add successful");
        }
    }
    
    public void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, int status) {
        if(lw.isEmpty()) {
            System.err.println("List is empty");
            return;
        } 
        System.out.print("Enter code: ");
        String id = vl.checkInputString();
        Worker wk = getWorkerByCode(lw, id);
        if(wk == null) {
            System.err.println("Not exist worker.");
        } else {
            int salaryCurrent = wk.getSalary();
            int salaryUpdate;
            if(status == 1) {
                System.out.print("Enter salary: ");
                while(true) {
                    salaryUpdate = vl.checkInputSalary();
                    if(salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("UP", getCurrentDate(), wk.getId(), wk.getName(), wk.getAge(), salaryUpdate, wk.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");
                while(true) {
                    salaryUpdate = vl.checkInputSalary();
                    if(salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("DOWN", getCurrentDate(), wk.getId(), wk.getName(), wk.getAge(), salaryUpdate, wk.getWorkLocation()));
            }
            wk.setSalary(salaryUpdate);
            System.out.println("Update Successful");
        }
    }
    
    
    public void printListHistory(ArrayList<History> lh) {
        if(lh.isEmpty()) {
            System.err.println("List is empty.");
            return;
            
        }
        System.out.printf("%-10s%-20s%-10s%-15s%-15s%-25s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        Collections.sort(lh);
        
        for (History history : lh) {
            printHistory(history);
        }
    }
    
    public Worker getWorkerByCode(ArrayList<Worker> lw, String id) {
        for (Worker worker : lw) {
            if(id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }
    
    public String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    
    public void printHistory(History history) {
        System.out.printf("%-10s%-20s%-10d%-15d%-15s%-25s\n", history.getId(), history.getName(), history.getAge(), history.getSalary(), history.getStatus(), history.getDate());
    }
}
