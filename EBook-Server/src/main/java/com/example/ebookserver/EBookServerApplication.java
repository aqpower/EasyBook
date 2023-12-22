package com.example.ebookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EBookServerApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(EBookServerApplication.class, args);
        //System.out.printf(MD5Util.encode("easybook123"));
        //System.out.printf(FileUntil.filterSensitivityWord("tmd你就是一个sb", '*'));
//        File file = new File("D:\\EBook\\EBook-Server\\src\\main\\resources\\static\\keywords.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        List<String> lines = new ArrayList<>();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            if (line.trim().split(" ").length > 1) {
//                lines.add(line);11
//            }
//        }
//        reader.close();
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//        for (String lineToWrite : lines) {
//            writer.write(lineToWrite + "\n");
//        }
//            writer.close();

        //System.out.printf(FileUntil.filterSensitivityWord("你好，这是我的好日子，审查了很多问题", '*'));
    }
}
