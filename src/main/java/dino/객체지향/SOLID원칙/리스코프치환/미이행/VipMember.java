package dino.객체지향.SOLID원칙.리스코프치환.미이행;

public class VipMember extends Member{
    public VipMember(String name) {
        super(name);
    }

    @Override
    public void joinTournament() {
        System.out.println("Vip member joins tournament ...");
    }

    @Override
    public void organizeTournament() {
        System.out.println("Vip member organize tournament ...");
    }
}
