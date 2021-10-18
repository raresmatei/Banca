abstract class FiscObserver{
    public Client client;

    public FiscObserver(Client client){
        this.client = client;
        this.client.attach(this);
    }

    public abstract void update();
}

class FiscRonObserver extends FiscObserver{

    public FiscRonObserver(Client client) {
        super(client);
    }

    public void update(){
        System.out.println("RON account for client " + client.getCNP());
        System.out.println("Balance: " + client.getSoldContRon());
    }
}

class FiscEuroObserver extends FiscObserver{

    public FiscEuroObserver(Client client){
        super(client);
    }

    public void update(){
        System.out.println("EURO account for client " + client.getCNP());
        System.out.println("Balance: " + client.getSoldContEuro() );
    }
}