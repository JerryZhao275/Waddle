public class Class {
    protected int classCode;
    protected String className;
    protected Quiz[] quizzes;

    public Class(int classCode, String className, Quiz[] quizzes) {
        this.classCode = classCode;
        this.className = className;
        this.quizzes = quizzes;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Quiz[] getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Quiz[] quizzes) {
        this.quizzes = quizzes;
    }

    public void enrol(Students student) {
    }
}
