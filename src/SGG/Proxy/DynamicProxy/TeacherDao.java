package SGG.Proxy.DynamicProxy;

public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中……");
    }
}
