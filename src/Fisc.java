import java.util.ArrayList;

//Fisc is a singleton
class Fisc{
    private static final Fisc instance = new Fisc();
    private ArrayList<String> monitorizedClients;

    private Fisc(){
        this.monitorizedClients = new ArrayList<String>();
    }

    public static Fisc getInstance(){
        return instance;
    }

    public void enableObservers(Client client){
        new FiscRonObserver(client);
        new FiscEuroObserver(client);
    }

    public void startMonitorize(String CNP, Bank bank){
        try {
            Client monitorizedClient = bank.getClientByCNP(CNP);
            if(monitorizedClient == null){
                throw new NullPointerException();
            }

            if (monitorizedClient.getObservers().size() == 0) {
                enableObservers(monitorizedClient);
                monitorizedClients.add(monitorizedClient.getCNP());
                System.out.println("Client " + monitorizedClient.getCNP() + " successfully monitorized");
            } else if (monitorizedClient.getObservers().size() > 0) {
                System.out.println("Client " + monitorizedClient.getCNP() + " already monitorized");
            }
        }
        catch (NullPointerException nullPointerException){
            System.out.println("Client " + CNP + " does not exist");
        }
    }

    public void stopMonitorize(String CNP, Bank bank){
        try {
            Client monitorizedClient = bank.getClientByCNP(CNP);
            if (monitorizedClient == null) {
                throw new NullPointerException();
            }

            if (monitorizedClient.getObservers().size() > 0) {
                monitorizedClient.detach();
                monitorizedClients.remove(monitorizedClient.getCNP());
                System.out.println("Client " + monitorizedClient.getCNP() + " successfully unmonitorized");
            } else if (monitorizedClient.getObservers().size() == 0) {
                System.out.println("Client " + monitorizedClient.getCNP() + " already unmonitorized");
            }
        }
        catch (NullPointerException nullPointerException){
            System.out.println("Client " + CNP + " does not exist");
        }
    }

    public ArrayList<String> getMonitorizedClients(){
        return this.monitorizedClients;
    }

}
