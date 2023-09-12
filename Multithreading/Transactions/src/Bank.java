import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts = new HashMap<>();

    public boolean isFraud() throws InterruptedException {
        Thread.sleep(1000);
        return Math.random() < 0.05;
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account number");
        }

        List<Account> accountsToLock = Arrays.asList(fromAccount, toAccount);
        accountsToLock.sort(Account.AccountComparator);

        accountsToLock.get(0).lock.lock();
        accountsToLock.get(1).lock.lock();

        try {
            if (amount > 50000 && isFraud()) {
                System.out.println("Transaction and accounts is blocked");
                return;
            }

            if (fromAccount.getMoney() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        } finally {
            fromAccount.lock.unlock();
            toAccount.lock.unlock();
        }
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        try {
            sum = accounts
                    .values()
                    .stream()
                    .mapToLong(Account::getMoney)
                    .sum();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sum;
    }

    public void addAccount(Account account) {
        account.lock.lock();
        try {
            accounts.put(
                    account.getAccNumber(),
                    account
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            account.lock.unlock();
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
