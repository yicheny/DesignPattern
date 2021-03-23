package SGG.Proxy.StaticProxy;

public class TeacherDaoProxy implements ITeacherDao{
    private final ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target){
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("代理开始");
        this.target.teach();
        System.out.println("代理结束");
    }
}
