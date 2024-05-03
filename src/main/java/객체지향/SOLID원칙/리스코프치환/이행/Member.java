package main.java.객체지향.SOLID원칙.리스코프치환.이행;

public abstract class Member implements TournamentJoiner, TournamentOrganizer {
    private final String name;

    public Member(String name) {
        this.name = name;
    }
}
