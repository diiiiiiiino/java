package main.java.퍼즐러;

public class 멀티캐스트 {
    public static void main(String[] args) {
        System.out.println("(int)(char)(byte) -1 : " + (int)(char)(byte) -1);

        /**
         * 자바는 2의 보수 이진 연산을 사용하기 때문에
         * -1은 모든 32비트가 1이다.
         */
        int a = -1;

        /**
         * 축소 기본 타입 변환을 수행한다.
         * 하위 8비트를 제외한 나머지 모두를 잘라낸다.
         */
        byte b = (byte) a;
        System.out.println("b = (byte) a : " + b);

        /**
         * char는 부호가 없는 타입, byte는 부호가 있는 타입
         * 보통 수치 값을 보전하면서 한 정수 타입으로부터 보다 큰 타입으로 변환 가능
         * char 타입으로 음의 byte 타입 값을 나타내는 것은 불가능하다.
         * byte타입에서 char 타입으로의 변환은 확장 기본 타입 변환으로 간주되지 않고
         * 확장 축소 기본 타입변환으로 간주된다.
         * 이는 byte타입이 int 타입으로 (확장), 다시 그 int 타입이 char타입으로(축소) 바뀌는 변환이다.
         *
         * 변환되는 타입에 관계없이, 기본 값의 타입식이 부호가 있다면 부호 확장을,
         * char 타입이면 영의 확장이 행해진다.
         */
        char c = (char)b; // 부호 확장
        System.out.println("(char)b : " + Integer.toHexString(c));

        int i = c; //영의 확장
        System.out.println("i = c : " + i);

        /**
         * char 타입 변수 c로부터 보다 큰 타입으로 변환하고 부호 확장을 원치 않는다면
         * 명확하게 하기 위해 꼭 필요한 것은 아니지만 비트 마스크의 사용을 고려해라
         */
        int ii = c & 0xffff; //필요없긴하다
        System.out.println("ii = c & 0xffff" + ii);

        /**
         * 또는 변환의 동작을 기술하는 주석을 작성하자
         */
        ii = c; // 부호 확장이 이루어지지 않는다.
        System.out.println("ii = c" + ii);

        /**
         * 만약 char 타입 변수 c로부터 보다 큰 정수 타입으로 변환하고 부호 확장을 원한다면
         * char 타입을 char 타입과 같은 크기지만 부호가 있는 short 타입으로 캐스트하라.
         * 아래 코드의 미묘함을 고려한다면, 역시 주석을 작성하는 것이 좋다.
         */
        int iii = (short) c; //short 타입을 사용하지 않으면 65535, 사용하면 -1
        System.out.println("(short) c : " + Integer.toString((short) c));
        System.out.println("c : " + Integer.toString(c));

        /**
         * 만약 byte 타입 변수 b로부터 char 타입으로 변환하고 부호 확장을 원하지 않는다면,
         * 부호 확장을 막기 위해 비트 마스크를 사용해야 한다.
         */
        byte bb = -1;
        System.out.println("(char) (b & 0xff)) : " + Integer.toString((char) (bb & 0xff)));
        System.out.println("(char)b : " + Integer.toString((char)bb));
    }
}
