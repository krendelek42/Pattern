import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {
    private static FileService INSTANCE;
    private FileService () {

    }
    public static FileService getInstance() {
        if (INSTANCE == null) {
            synchronized (FileService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FileService();
                }
            }
        }
        return INSTANCE;
    }

    public void WriteCVS(ArrayList<Account> account) throws IOException {
        // write information about account in CVS file
        String file = "fileAccount.cvs";
        BufferedWriter writter = new BufferedWriter(new FileWriter(file));

        for (Account accounts : account) {
            writter.write(String.valueOf(accounts));
        }
        writter.close();
    }

    public ArrayList<Account> ReadCVS() throws IOException {
        // read information about account from CVS file
        ArrayList<Account> accounts = new ArrayList<>();
        String line; // добавить ошибки
        Scanner scanner;
        int i = 0;

        String file = "fileAccount.cvs";
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            Account account = new Account();
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                String data = scanner.next();
                if (i == 0) {account.setFIO(data);}
                else if (i == 1) {account.setDateBirth(data);}
                else if (i == 2) {account.setEmail(data);}
                else if (i == 3) {account.setPassword(data);}
                else if (i == 4) {account.setBlocked();}
                else {
                    System.out.println("-----");
                }
                i++;

            }
            accounts.add(account);
            i=0;


        }
        return accounts;
    }


}
