package BigTalk.Visitor;

public class Success extends Action{
    private final String type = "成功";

    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人" + type + "时，背后多半有一个伟大的女人。");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人" + type + "时，背后多半有一个不成功的男人。");
    }
}
