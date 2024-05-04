package dino.디자인패턴.어댑터패턴;

public class V110PowerSocket {
    private V110 plug;

    public V110PowerSocket(V110 plug) {
        this.plug = plug;
    }

    public void charge(){
        this.plug.charge();
    }
}
