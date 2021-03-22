package BigTalk.Visitor;

public class Marriage extends Action{
    private final String type = "结婚";

    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人" + type + "时，感概道：恋爱游戏终结时，‘有期徒刑’遥无期");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人" + type + "时，欣慰曰：爱情长跑路漫漫，婚姻保险保平安");
    }
}
