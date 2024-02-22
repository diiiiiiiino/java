package 객체지향.SOLID원칙.리스코프치환.이행;

public class PremiumMember extends Member {
    public PremiumMember(String name) {
        super(name);
    }

    @Override
    public void joinTournament() {
        System.out.println("Premium member joins tournament ...");
    }

    @Override
    public void organizeTournament() {
        System.out.println("Premium member organize tournament ...");
    }
}
