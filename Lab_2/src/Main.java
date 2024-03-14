public class Main {
    public static void main(String[] args){
        Example();
    }
    private static void Example(){
        ParseFile solve = new ParseFile();
        try{
            solve.FillSymbol();
            solve.ReadFile();
            solve.WriteFile();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
