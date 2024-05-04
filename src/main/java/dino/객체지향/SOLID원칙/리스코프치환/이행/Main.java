package dino.객체지향.SOLID원칙.리스코프치환.이행;


import java.util.List;

public class Main {
    public static void main(String[] args){
        List<TournamentJoiner> members = List.of(
                new PremiumMember("Jack Hores"),
                new VipMember("Tom Johns"),
                new FreeMember("Martin Vilop")
        );

        for(TournamentJoiner member : members){
            member.joinTournament();
        }

        List<TournamentOrganizer> members2 = List.of(
                new PremiumMember("Jack Hores"),
                new VipMember("Tom Johns")
        );

        for(TournamentOrganizer member : members2){
            member.organizeTournament();
        }
    }
}
