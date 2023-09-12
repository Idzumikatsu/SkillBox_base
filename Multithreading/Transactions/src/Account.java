import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private long money;
    private String accNumber;
    protected Lock lock;

    public Account(String accNumber, long money) {
        this.money = money;
        this.accNumber = accNumber;
        this.lock = new ReentrantLock();
    }

    public long getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void deposit(long amount) {
        lock.lock();
        try {
            money += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(long amount) {
        lock.lock();
        try {
            money -= amount;
        } finally {
            lock.unlock();
        }
    }

    public static Comparator<Account> AccountComparator = Comparator.comparing(Account::getAccNumber);
}
