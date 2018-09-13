package clone;

/**
 * Created by Administrator on 2018/8/27.
 */
public class Student implements Cloneable {
    String name;
    int age;
    Professor professor;

    public Student(String name, int age, Professor professor) {
        this.name = name;
        this.age = age;
        this.professor = professor;
    }

    @Override
    public Object clone(){
        Student o = null;

        try {
            o = (Student) super.clone();    //Object 中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        o.professor = (Professor) professor.clone();
        return o;
    }

    public static void main(String[] args) {
        Professor p = new Professor("wangwu",50);
        Student s1 = new Student("zhangwei",20, p);
        Student s2 = (Student) s1.clone();

        System.out.println("name:"+s2.professor.name+"------age:"+s2.professor.age);

        s2.professor.name = "liwu";
        s2.professor.age = 52;

        System.out.println("name:"+s2.professor.name+"------age:"+s2.professor.age);

        System.out.println("name:"+s1.professor.name+"------age:"+s1.professor.age);
    }
}
