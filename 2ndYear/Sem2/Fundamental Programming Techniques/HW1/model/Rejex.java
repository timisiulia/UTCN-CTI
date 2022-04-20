package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rejex {

    private List<Monomial> polinom = new ArrayList<>();

    public Polynomial createPolynomial(String userInput) {

        String memFormat = "([+-]?[\\d\\.]*)([a-zA-Z]?)\\^?(\\d*)";
        Polynomial rezultat = new Polynomial();
        int putere = 0;
        double coeficient = 0;

        Pattern p = Pattern.compile(memFormat);
        Matcher m = p.matcher(userInput);

        while (!m.hitEnd()) {

            m.find();
            putere = Integer.valueOf(m.group(3));
            coeficient = Double.valueOf(m.group(1));

            Monomial b = new Monomial(putere, coeficient);
            polinom.add(b);
        }
        rezultat.setPolinom(polinom);
        return rezultat;
    }

}