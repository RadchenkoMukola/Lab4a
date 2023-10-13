import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Data {
    public ArrayList<Account> accounts;
    private ReadWriteLock readWriteLock;

    public Data() {
        accounts = startAccountsInit();
        readWriteLock = new ReentrantReadWriteLock();
    }

    private ArrayList<Account> startAccountsInit() {
        ArrayList<Account> acc = new ArrayList<>();
        acc.add(new Account("Ivan", "Ivanovich", "Ivanov", "+380000000000"));
        acc.add(new Account("Petro", "Petrovich", "Petrov", "+38000000001"));
        acc.add(new Account("Serhyi", "Serhyivich", "Olegov", "+38000000221"));
        acc.add(new Account("Darina", "Getman", "Vasilivna", "+380638336333"));
        acc.add(new Account("Mykola", "Poplavski", "Davidov", "+380444175444"));
        acc.add(new Account("Oleg", "Gonta", "Semenov", "+309501456"));
        return acc;
    }

    public void lockRead() {
        readWriteLock.readLock().lock();
    }

    public void lockWrite() {
        readWriteLock.writeLock().lock();
    }

    public void unlockRead() {
        readWriteLock.readLock().unlock();
    }

    public void unlockWrite() {
        readWriteLock.writeLock().unlock();
    }

    public void print() {
        System.out.println("Accounts:");
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

}