public class BankProject{

    public static void main(String []args){
        Bank bank = new Bank();
        Client c1 = new Client("12345", bank);
        Client c2 = new Client("23456", bank);

        System.out.println(bank);

        c1.depositEuro(1000);
        c2.depositRon(2000);
        c1.depositRon(3000);
        c2.depositEuro(2000);
        c1.withdrawEuro(300);
        c2.withdrawRon(200);
        c1.withdrawRon(500);
        c2.withdrawEuro(500);

        System.out.println(bank);
        c1.interogareCont();
        c2.interogareCont();

        Fisc fisc = Fisc.getInstance();
        System.out.println("---");
        fisc.startMonitorize(c1.getCNP(), bank);
        fisc.startMonitorize(c1.getCNP(), bank);

        fisc.stopMonitorize(c1.getCNP(), bank);
        System.out.println("---");

        fisc.stopMonitorize(c1.getCNP(), bank);

        fisc.startMonitorize(c1.getCNP(), bank);
        fisc.startMonitorize(c2.getCNP(), bank);

        System.out.println(c1);
        System.out.println(c2);

        c1.depositEuro(1000);
        c1.depositRon(500);
        c2.depositEuro(2000);
        c2.withdrawEuro(500);

        System.out.println(bank);
    }
}
