package meli.socialMeli.util;

public class Calculator {
    private int count;


    public Calculator(int count){
        this.count=count;
    }

    public void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
