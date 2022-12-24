public class Account {
    private String FIO;
    private String dateBirth;
    private String email;
    private String password;
    private boolean blocked = true;
    private int count = 0;


    public Account(String FIO, String dateBirth, String email, String password) {
        this.FIO = FIO;
        this.dateBirth = dateBirth;
        this.email = email;
        this.password = password;
        this.blocked = false;
        this.count = 0;

    }
    @Override
    public String toString() {
        return "ФИО: " + this.FIO + "Дата рождения: " + this.dateBirth + "Почта: " + this.email;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setDateBirth(String dateBirth){
        this.dateBirth = dateBirth;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBlocked(){
        this.blocked = !this.blocked;
    }
    public String getEmail() {
        return email;
    }
    public String getFIO() {
        return FIO;
    }
    public String getPassword() {
        return password;
    }
    public String getDateBirth() {
        return dateBirth;
    }
    public Boolean getBlocked() {
        return blocked;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public void count_enter() { this.count++; }
    public void login(Integer zero) { this.count = zero; }

}