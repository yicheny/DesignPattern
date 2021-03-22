package BigTalk.Visitor;

import java.util.ArrayList;

public class ObjectStructure {
    private final ArrayList<Person> elements = new ArrayList<>();

    public void attach(Person element){
        elements.add(element);
    }

    public void detach(Person element){
        elements.remove(element);
    }

    public void display(Action visitor){
        elements.forEach((e)->{
            e.accept(visitor);
        });
    }
}
