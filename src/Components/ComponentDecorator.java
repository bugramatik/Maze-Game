package Components;

import Actors.AbstractActor;

public class ComponentDecorator implements IRealTimeComponent {

    private AbstractActor wrappee;

    public ComponentDecorator(AbstractActor wrappee) {
        this.wrappee = wrappee;
    }

    public AbstractActor getWrappee() {
        return wrappee;
    }

    @Override
    public void update(float deltaT) {

    }


}
