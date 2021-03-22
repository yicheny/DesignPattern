package SGG.Template;

//豆浆
public abstract class SoyaMilk {
    public final void make(){
        select();
        addCondiments();
        soak();
        beat();
    }

    private void select(){
        System.out.println("选择黄豆");
    }

    //添加配料
    protected abstract void addCondiments();

    private void soak(){
        System.out.println("将黄豆和配料进行浸泡");
    }

    private void beat(){
        System.out.println("将黄豆和配料放到豆浆机打碎");
    }
}
