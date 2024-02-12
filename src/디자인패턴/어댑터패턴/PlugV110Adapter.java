package 디자인패턴.어댑터패턴;

public class PlugV110Adapter implements V110{
    private V220 plug;

    public PlugV110Adapter(V220 plug) {
        this.plug = plug;
    }

    @Override
    public void charge() {
        this.plug.charge();
    }
}
