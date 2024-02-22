package 객체지향.SOLID원칙.리스코프치환.미이행;

public abstract class Member {
    private final String name;

    public Member(String name){
        this.name = name;
    }

    public abstract void joinTournament();
    public abstract void organizeTournament();
}
