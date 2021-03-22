package SGG.Visitor;

public class Success extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是成功");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人给的评价是成功");
    }
}
