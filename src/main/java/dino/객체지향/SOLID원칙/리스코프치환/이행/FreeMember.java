package dino.객체지향.SOLID원칙.리스코프치환.이행;

public class FreeMember implements TournamentJoiner {
    private final String name;

    public FreeMember(String name) {
        this.name = name;
    }

    @Override
    public void joinTournament() {
        System.out.println("Classic member joins tournament ...");
    }
}
