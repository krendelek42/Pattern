import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FileAccountManager implements AccountManager {
    protected ArrayList<Account> accounts;
    public FileService file = FileService.getInstance();
    public FailedLoginCounter counter = FailedLoginCounter.getInstance();

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

            if (accounts.get(new_i).getPassword() == password && !accounts.get(new_i).getBlocked() && accounts.get(new_i).getCount() <4) {

                counter.login_true(accounts.get(new_i));
                file.WriteCVS(accounts);
                System.out.println("Вы вошли в аккаунт");
                return accounts.get(new_i);

            } else if (accounts.get(new_i).getPassword() == password && accounts.get(new_i).getCount() >= 4) {
                accounts.get(new_i).setBlocked();
                counter.login_true(accounts.get(new_i));
                file.WriteCVS(accounts);
                System.out.println("Аккаунт разблокирован. Вы вошли в аккаунт");
                return accounts.get(new_i);
            } else if (accounts.get(new_i).getPassword() != password) {
                counter.logint_false(accounts.get(new_i));

                if (accounts.get(new_i).getCount() > 4) {
                    accounts.get(new_i).setBlocked();
                    file.WriteCVS(accounts);
                    // ошибка что акк заблок
                } else {
                    file.WriteCVS(accounts);
                    // ошибка неверного пароля
                }
            }
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
            // ошибка неверного пароля и почты
        } else {
            if (accounts.get(new_i).getPassword() == password) {
                accounts.remove(new_i);
                file.WriteCVS(accounts);
                System.out.println("Аккаунт удален");
            } // ошибка неверного пароля и почты
        }


    }
}
