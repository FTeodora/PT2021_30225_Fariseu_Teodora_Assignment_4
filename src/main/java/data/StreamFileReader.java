package data;
import business.items.BaseProduct;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa care citeste din fisiere .csv
 */
public class StreamFileReader {

    private StreamFileReader singleInstance=new StreamFileReader();

    /**
     * Citirea din fisier
     * @param filePath calea fisierului
     * @return produsele citite, distincte dupa nume
     * @throws IOException in cas de esec
     */
    public static Set<BaseProduct> readFile(String filePath) throws IOException {
    Set<BaseProduct> result;
    Stream<String> stream = Files.lines(Paths.get(filePath));
    result=stream.skip(1).map((line)-> BaseProduct.parse(line)).collect(Collectors.toSet());
    return result;
    }
}
