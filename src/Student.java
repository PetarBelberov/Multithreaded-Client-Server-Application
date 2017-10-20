import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 7526472295622776147L;
    private String name;
    private int age;
    private int mark;
    private String email;

    public Student(String name, int age, int mark, String email) {
        this.name = name;
        this.age = age;
        this.mark = mark;
        this.email = email;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}