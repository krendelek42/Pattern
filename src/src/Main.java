import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, AccountAlreadyExistsException, BlockedAccountException, WrongDataException {

        FileAccountManager dateAccount = new FileAccountManager();
        Account user1 = new Account("Докукина_К_А", "13.11.2002", "dudukina777@gmail.com", "123456");
        Account user2 = new Account("Куркин_А_С", "15.06.2002", "pupkin@gmail.com", "654321");

        try {
            dateAccount.register(user1);
            dateAccount.register(user2);
            dateAccount.register(user1);

        } catch (AccountAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (int i = 0; i < 5; i++) {
                try {
                    dateAccount.login("dudukina777@gmail.com", "ffff");

                } catch (WrongDataException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (BlockedAccountException e) {
            System.out.println(e.getMessage());
        }

        try {
            dateAccount.login("dudukina777@gmail.com", "123456");
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            dateAccount.login("pupkin@gmail.com", "0000");

        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }


        try {
            dateAccount.login("pupkin@gmail.com", "654321");
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }


        for (int i = 0; i < 3; i++) {
            try {
                dateAccount.login("pupkin@gmail.com", "0000");

            } catch (WrongDataException e) {
                System.out.println(e.getMessage());
            }

        }

        try {
            dateAccount.login("pupkin@gmail.com", "654321");
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            dateAccount.removeAccount("dudukina777@gmail.com", "12345");
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            dateAccount.removeAccount("vfrdre@gmail.com", "0000");

        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            dateAccount.removeAccount("pupkin@gmail.com", "654321");
        } catch (WrongDataException e) {
            System.out.println(e.getMessage());
        }


    }
}