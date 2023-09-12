public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();

        for (int i = 0; i < 100; i++) {
            bank.addAccount(new Account(String.valueOf(i), 0L));
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    try {
                        bank.getAccounts().values().forEach( account -> account.deposit(50_000L));
                        bank.getAccounts().values().forEach( account -> account.deposit(50_000L));
                        bank.transfer(String.valueOf(j), String.valueOf(j + 1), 51_000);
                        bank.transfer(String.valueOf(j + 1), String.valueOf(j), 2_000L);
                        bank.transfer(String.valueOf(j+ 15), String.valueOf(j + 8), 52_000);
                        bank.getAccounts().values().forEach( account -> account.deposit(5_000L));
                        bank.getAccounts().values().forEach( account -> account.withdraw(10_000L));
                        System.out.println(bank.getSumAllAccounts());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }


    }
}
