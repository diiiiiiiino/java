package 객체지향.SOLID원칙.리스코프치환.미이행;

import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Member> members = List.of(
                new PremiumMember("Jack Hores"),
                new VipMember("Tom Johns"),
                new FreeMember("Martin Vilop")
        );

        for(Member member : members){
            member.organizeTournament();
        }
    }
}
