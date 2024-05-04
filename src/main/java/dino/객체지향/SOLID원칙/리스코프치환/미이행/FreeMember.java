package dino.객체지향.SOLID원칙.리스코프치환.미이행;

public class FreeMember extends Member{
    public FreeMember(String name) {
        super(name);
    }

    @Override
    public void joinTournament() {
        System.out.println("Classic member joins tournament ...");
    }

    //리스코프 치환 원칙에 맞지 않은 메서드
    @Override
    public void organizeTournament() {
        System.out.println("A free member cannot organize tournament ...");
    }
}
