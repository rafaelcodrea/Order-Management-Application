package model;

public class Client {
    private int id;
    private String name;
    private int age;
    private String adress;

    public Client(int id, String name, int age, String adress) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.adress = adress;
    }

    public Client(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Client with ID: ");
        sb.append(getId());
        sb.append(" and name: ");
        sb.append(getName());
        return String.valueOf(sb);
    }
}
