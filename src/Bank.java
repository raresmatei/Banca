import java.util.ArrayList;

class Bank{
    private ArrayList<Client> clients;

    public Bank(){
        this.clients = new ArrayList<Client>();
    }

    public ArrayList<Client> getClients(){
        return this.clients;
    }

    public String toString(){
        StringBuilder str;
        str = new StringBuilder("Clients: \n");
        for(Client client: clients){
            str.append(client.toString());
            str.append('\n');
        }

        return str.toString();
    }

    public Client getClientByCNP(String CNP){
        for(Client c : clients){
            if(c.getCNP().equals(CNP)){
                return c;
            }
        }
        return null;
    }
}