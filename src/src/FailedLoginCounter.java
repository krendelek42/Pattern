public class FailedLoginCounter {

    private static FailedLoginCounter instance;
    private FailedLoginCounter() {
    }
    public static FailedLoginCounter getInstance() {
        if (instance == null) {
            instance = new FailedLoginCounter();
        }
        return instance;
    }

    public void logint_false(Account account) { account.count_enter(); }

    public void login_true(Account account) { account.login(0); }
}