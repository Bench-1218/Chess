package chess;
public class Chess {
    public static void main(String[] args) {
        A a = A.cpp;
        
    }
    static void out(A a){
        if(a == A.cpp) System.out.println(123);
    }
}

enum A{
    java, cpp, python;
}
