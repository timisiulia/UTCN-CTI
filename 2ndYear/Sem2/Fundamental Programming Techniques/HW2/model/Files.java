package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
    Scanner scan;
    public  ArrayList<String> readFromFile(File file) throws IllegalArgumentException
    {
        ArrayList<String> fileResult = new ArrayList<>();
        try
        {
            try{
                scan = new Scanner(file);
            }
            catch(Exception exceptie) {
                throw new IllegalArgumentException("Nu exista fisierul! ");
            }
            while(scan.hasNextLine())
            {
                String nextLine = scan.nextLine();
                String[] splittedLine = nextLine.split(",");

                for(String arr : splittedLine)
                {
                    if(arr.matches("[1-9]{1}[0-9]*"))
                    {
                        fileResult.add(arr);
                    }
                  }
            }

            scan.close();
        }
        catch(IllegalArgumentException exceptie)
        {
            throw exceptie;
        }

        return fileResult;
    }
}
