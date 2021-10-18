import java.util.ArrayList;

class Client{
    private String CNP;
    private Bank bank;
    private double soldContEuro;
    private double soldContRon;
    private ArrayList<FiscObserver> observers = new ArrayList<>();

    public Client(String CNP, Bank bank){
        this.CNP = CNP;
        this.bank = bank;
        if(!bank.getClients().contains(this)){
            bank.getClients().add(this);
        }
        else{
            System.out.println("You already have an open account");
        }
    }

    public void attach(FiscObserver fiscObserver){
        observers.add(fiscObserver);
    }

    public void detach(){
        observers.removeAll(observers);
    }

    /*public void createAccounts(Bank bank){
        //we make sure that the client does not already have an open account at this bank:
        if(!bank.getClients().contains(this)){
            bank.getClients().add(this);
        }
        else{
            System.out.println("You already have an open account");
        }
    }*/

    public void clearAccount(){
        if(this.soldContEuro == 0 && this.soldContRon == 0){
            bank.getClients().remove(this);
            System.out.println("Account successfully removed");
        }
        else{
            System.out.println("You still have money in your account");
        }
    }

    public void depositRon(double sum){
        if(this.getSoldContRon() + sum >= 1000){
            setSoldContRon(this.getSoldContRon() + sum);
        }
        else{
            System.out.println("The currency is invalid or the account new value is less than 1000");
        }
    }

    public void depositEuro(double sum){
        if(this.getSoldContEuro() + sum >= 1000){
            setSoldContEuro(this.getSoldContEuro() + sum);
        }
        else{
            System.out.println("The currency is invalid or the account new value is less than 1000");
        }
    }

    public void withdrawRon(double sum){
        if(this.getSoldContRon() - sum >= 1000 || this.getSoldContRon() - sum == 0){
            setSoldContRon(this.getSoldContRon() - sum);
        }
        else if (this.getSoldContRon() - sum < 0){
            System.out.println("You don't have enough money in your account");
        }
        else{
            System.out.println("The currency is invalid or the account new value is less than 1000");
        }
    }

    public void withdrawEuro(double sum){
        if(this.getSoldContEuro() - sum >= 1000 || this.getSoldContEuro() - sum == 0){
            setSoldContEuro(this.getSoldContEuro() - sum);
        }
        else if (this.getSoldContEuro() - sum < 0){
            System.out.println("You don't have enough money in your account");
        }
        else{
            System.out.println("The currency is invalid or the account new value is less than 1000");
        }
    }

    public void interogareCont(){
        System.out.println(this);
    }

    public void notifyAllObservers(){
        for (FiscObserver observer : observers) {
            observer.update();
        }
    }

    public boolean equals(Object o){
        if(o instanceof Client){
            return ((Client)o).CNP.equals(this.CNP);
        }
        else{
            return false;
        }
    }

    public String toString(){
        return this.CNP + " sold cont EURO: " + this.soldContEuro + " sold cont RON: " + this.soldContRon;
    }

    public String getCNP(){
        return this.CNP;
    }

    public double getSoldContEuro(){
        return this.soldContEuro;
    }

    private void setSoldContEuro(double soldContEuro){
        this.soldContEuro = soldContEuro;
        if(observers.size() > 0){
            notifyAllObservers();
        }
    }

    public double getSoldContRon(){
        return this.soldContRon;
    }

    private void setSoldContRon(double soldContRon){
        this.soldContRon = soldContRon;
        if(observers.size() > 0){
            notifyAllObservers();
        }
    }

    public ArrayList<FiscObserver> getObservers(){
        return this.observers;
    }
}