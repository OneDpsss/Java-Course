import java.awt.datatransfer.FlavorEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParseFile {
    private static final int ASCII_SYMBOL_A = 65;
    private static final int ASCII_SYMBOL_Z = 95;
    private static final int lower_case = 32;

    private static final HashMap<Character,Integer> SymbolCounter = new HashMap<>();

    public ParseFile(){

    }
    public void ReadFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла для считывания:");
        boolean flag = true;
        while(flag){
            try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(scanner.nextLine()))) {
                String Data;
                StringBuilder FileInputData = new StringBuilder();
                while ((Data = bufferedReader.readLine()) != null) {
                    FileInputData.append(Data.toString());
                }
                FillMap(FileInputData.toString());
                flag = false;
            } catch (IOException e) {
                throw new RuntimeException("Ошибка");
            }
        }
    }
    public void FillMap(String FileData){
        for (int i = 0;i < FileData.length(); i ++){
            char symb = FileData.charAt(i);
            if (SymbolCounter.containsKey(symb)){
                SymbolCounter.put(symb,SymbolCounter.get(symb) + 1);
            }
        }
    }

    public void FillSymbol(){
        for (int i = ASCII_SYMBOL_A;i < ASCII_SYMBOL_Z;i ++){
            SymbolCounter.put((char) i,0);
            SymbolCounter.put((char) (i + lower_case),0);
        }
    }

    public void WriteFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла для записи:");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(scanner.nextLine()))){
            for (Map.Entry<Character, Integer> entry : SymbolCounter.entrySet()) {
                bufferedWriter.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка");
        }
    }
}