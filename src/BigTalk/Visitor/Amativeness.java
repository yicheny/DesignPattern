package BigTalk.Visitor;

public class Amativeness extends Action{
    private final String type = "恋爱";

    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人" + type + "时，不懂也要装懂。");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人" + type + "时，懂也要装作不懂");
    }
}
