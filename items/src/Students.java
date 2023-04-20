public class Students {

    protected final int studentID;

    protected String name;

    protected Class[] classes;

    public Students(int studentID, String name, Class[] classes) {
        this.studentID = studentID;
        this.name = name;
        this.classes = classes;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }
}
