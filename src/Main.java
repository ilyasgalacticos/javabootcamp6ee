public class Main {
    public static void main(String[] args) {
        int a = 10;
        if(a>5){
            System.out.println("Hello");
        }else{
            System.out.println("Hi");
        }

        int b = 10;
        System.out.println((a>5?"Hello":"Hi"));
    }
}
