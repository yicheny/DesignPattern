package BigTalk.Visitor;

public class Failing extends Action{
    private final String type = "失败";

    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人" + type + "时，闷头喝酒，谁也不用劝");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人" + type + "时，眼泪汪汪，谁也劝不了");
    }
}
