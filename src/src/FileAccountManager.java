import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FileAccountManager implements AccountManager {
    protected ArrayList<Account> accounts;
    public FileService file = FileService.getInstance();

    public void register(Account account) throws IOException {
        accounts = file.ReadCVS();
        int number = 0;

        for (int i = 0; i < accounts.size(); i++) {
            if (Objects.equals(accounts.get(i).getEmail(), account.getEmail())) {
                number ++;
            }
        }

        if (number == 0) {
            accounts.add(account);
            file.WriteCVS(accounts);
            System.out.println("Зарегистрирован аккаунт с email:" + account.getEmail());
        } // ошибку что акк уже есть

    }

    public Account login(String email, String password) throws IOException {
        accounts = file.ReadCVS();
        int number = 0;
        int new_i = 0;

        for (int i=0; i < accounts.size(); i++) {
            if (accounts.get(i).getEmail() == email) {
                number ++;
                new_i = i;
            }
        }

        if (number == 0) {
            // ошибка что неверно введены данные: почта или пароль
        } else {

            if (accounts.get(new_i).getPassword() == password && !accounts.get(new_i).getBlocked()) {
                // добавить попытки входа
                System.out.println("Вы вошли в аккаунт");
                file.WriteCVS(accounts);
                return accounts.get(new_i);
            } else if (accounts.get(new_i).getPassword() == password) {
                accounts.get(new_i).setBlocked();
                file.WriteCVS(accounts);
                System.out.println("Аккаунт разблокирован. Вы вошли в аккаунт");
                return accounts.get(new_i);
            } // добавить счетчик
        }
        return accounts.get(new_i);
    }

    public void removeAccount(String email, String password) throws IOException {

        accounts = file.ReadCVS();
        int number = 0;
        int new_i = 0;

        for (int i=0; i < accounts.size(); i++) {
            if (accounts.get(i).getEmail() == email) {
                number ++;
                new_i = i;
            }
        }

        if (number == 0) {
            //
        } else {
            if (accounts.get(new_i).getPassword() == password) {
                accounts.remove(new_i);
                file.WriteCVS(accounts);
                System.out.println("Аккаунт удален");
            } //
        }


    }
}
