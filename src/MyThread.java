
public class MyThread extends Thread {
    private int task;
    private Data data;

    public MyThread(int task, Data data){
        this.task = task;
        this.data = data;
    }

    @Override
    public void run(){
        // Виконання відповідного завдання в залежності від значення task
        switch(task){
            case 1: findPhoneByName(); // Завдання пошуку телефону за ім'ям
                break;
            case 2: findNameByPhone(); // Завдання пошуку імені за номером телефону
                break;
            case 3: RWAccounts(); // Завдання зчитування/запису даних акаунтів
                break;
            default:
                System.out.println("There is no task number like this."); // Вивід повідомлення про відсутність відповідного завдання
        }
    }

    private void findPhoneByName() {
        String nameForFind = "Mykola";
        data.lockRead(); // Блокування читання даних
        System.out.println("Searching phone by name."); // Вивід повідомлення про пошук за ім'ям
        for(Account account: data.accounts){
            if(account.getName().equals(nameForFind))
                System.out.println("Founded by searching phone by name " + account.toString()); // Вивід знайденого акаунту
        }
        try {Thread.sleep(5000);} // Затримка на 5 секунд
        catch (InterruptedException e) {e.printStackTrace();} // Вивід інформації про виникнення виключення
        System.out.println("Searching phone by name is finished"); // Вивід повідомлення про завершення пошуку
        data.unlockRead(); // Розблокування читання даних
    }

    private void findNameByPhone() {
        String phoneNumberForFind = "+38000000001";
        data.lockRead(); // Блокування читання даних
        System.out.println("Searching name by phone."); // Вивід повідомлення про пошук за номером телефону
        for (Account account : data.accounts) {
            if (account.getPhoneNumber().equals(phoneNumberForFind))
                System.out.println("Founded in searching name by phone " + account.toString()); // Вивід знайденого акаунту
        }
        try {Thread.sleep(5000);} // Затримка на 5 секунд
        catch (InterruptedException e) {e.printStackTrace();} // Вивід інформації про виникнення виключення
        System.out.println("Searching name by phone is finished."); // Вивід повідомлення про завершення пошуку
        data.unlockRead(); // Розблокування читання даних
    }

    private void RWAccounts(){
        Account a1 = new Account("Bob", "Bobowich", "Bobov", "random");
        Account a2 = new Account("Ivan", "Semet", "Timurov", "+38000000001");

        data.lockWrite(); // Блокування запису даних
        System.out.println("Writing/Deleting account 1."); // Вивід повідомлення про запис/видалення облікового запису
        if(a1.getPhoneNumber().length() == 12) data.accounts.add(a1);
        else data.accounts.remove(a1);

        try {Thread.sleep(5000);} // Затримка на 5 секунд
        catch(InterruptedException e){e.printStackTrace();} // Вивід інформації про виникнення виключення
        System.out.println("Writing/Deleting account 1 is finished."); // Вивід повідомлення про завершення запису/видалення облікового запису
        data.unlockWrite(); // Розблокування запису даних

        try{Thread.sleep(1000);} // Затримка на 1 секунду
        catch (InterruptedException e){e.printStackTrace();} // Вивід інформації про виникнення виключення

        data.lockWrite(); // Блокування запису даних
        System.out.println("Writing/Deleting account 2."); // Вивід повідомлення про запис/видалення облікового запису
        if(a2.getPhoneNumber().length() == 12) data.accounts.add(a2);
        else data.accounts.remove(a2);

        try {Thread.sleep(5000);} // Затримка на 5 секунд
        catch(InterruptedException e){e.printStackTrace();} // Вивід інформації про виникнення виключення
        System.out.println("Writing/Deleting account 2 is finished."); // Вивід повідомлення про завершення запису/видалення облікового запису
        data.unlockWrite(); // Розблокування запису даних
    }
}