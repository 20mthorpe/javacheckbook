public class Account {
    private int _id;
    private static int _nextId = 0;
    private String _name;
    private float _balance;
    //protected static int _id_count;
    Account(String name, float balance) {
        _id = _nextId;
        _nextId++;
        _name = name;
        _balance = balance;
    }
    Account(int id, String name, float balance)
    {
        _id = id;
        _name = name;
        _balance = balance;
    }
    public void display() {
        System.out.println("Name: " + _name);
        System.out.println("Account Id: " + _id);
        System.out.println("Current Balance: $" + _balance);
    }
    public void deposit(float deposit) {
        _balance += deposit;
    }
    public void withdraw(float withdraw) {
        _balance -= withdraw;
    }
    public int get_id() {
        return _id;
    }
    public String get_name(){
        return _name;
    }
    public float get_balance(){
        return _balance;
    }
}
