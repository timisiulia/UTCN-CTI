package model;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    //lista monoame care alcatuiesc polinomul
    List<Monomial> polinom = new ArrayList<>();

    public Polynomial() {
    }

    //operatii
    //adunare
    public Polynomial adunare(Polynomial p) {
        Polynomial rezultat = new Polynomial();
        Monomial aux;
        //a si b reprezinta 2 monoame
        Monomial a;
        Monomial b;

        int sizeP = this.polinom.size();
        int sizeQ = p.polinom.size();

        int i = 0;
        int j = 0;

        //Se realizeaza instrctiunea cat timp exista monoame in polinoame
        while (i < sizeP && j < sizeQ) {
            a = this.polinom.get(i);
            b = p.polinom.get(j);
            //verificam daca gradul primului monom e mai mic decat gradul celui de al 2 lea
            if (a.getPutere() < b.getPutere()) {
                rezultat.polinom.add(b);
                j++;
            } else
                //verificam daca gradul primului monom e mare decat gradul celui de al 2 lea
                if (a.getPutere() > b.getPutere()) {
                    rezultat.polinom.add(a);
                    i++;
                }
                //daca monoamele au grade egale
                else {
                    aux = a.adunare(b);
                    rezultat.polinom.add(aux);
                    i++;
                    j++;
                }
        }
        // Daca primul polinom nu este parcurs in intregime
        while (i < sizeP) {
            a = p.polinom.get(i);
            rezultat.polinom.add(a);
            i++;
        }
        // Daca al 2 lea polinom nu este parcurs in intregime
        while (j < sizeQ) {
            b = p.polinom.get(j);
            rezultat.polinom.add(b);
            j++;
        }
        return rezultat;

    }

    //scadere
    public Polynomial scadere(Polynomial p) {
        Polynomial rezultat = new Polynomial();
        Monomial aux;
        //a si b reprezinta 2 monoame
        Monomial a;
        Monomial b;

        int sizeP = this.polinom.size();
        int sizeQ = p.polinom.size();

        int i = 0;
        int j = 0;

        while (i < sizeP && j < sizeQ) {
            a = this.polinom.get(i);
            b = p.polinom.get(j);
            //verificam daca gradul primului monom e mai mic decat gradul celui de al 2 lea
            if (a.getPutere() < b.getPutere()) {
                b.setCoeficient((-1.0) * b.getCoeficient());
                rezultat.polinom.add(b);
                j++;
            } else
                //verificam daca gradul primului monom e mare decat gradul celui de al 2 lea
                if (a.getPutere() > b.getPutere()) {
                    rezultat.polinom.add(a);
                    i++;
                }
                //daca monoamele au grade egale
                else {
                    aux = a.scadere(b);
                    rezultat.polinom.add(aux);
                    i++;
                    j++;
                }

        }
        while (i < sizeP) {
            a = p.polinom.get(i);
            rezultat.polinom.add(a);
            i++;
        }
        while (j < sizeQ) {
            b = p.polinom.get(j);
            b.setCoeficient((-1.0) * b.getCoeficient());
            rezultat.polinom.add(b);
            j++;
        }
        return rezultat;


    }

    //inmultire
    public Polynomial inmultire(Polynomial p) {
        Monomial aux;
        Polynomial a = new Polynomial();
        for (Monomial i : this.polinom) {
            Polynomial pol1 = new Polynomial();
            for (Monomial j : p.polinom) {
                aux = i.inmultire(j);
                pol1.polinom.add(aux);
            }
            a = a.adunare(pol1);
        }
        return a;
    }

    //derivare
    public Polynomial derivare() {
        Polynomial rezultat = new Polynomial();
        for (Monomial mem : this.polinom) {
            mem.setCoeficient(mem.getCoeficient() * mem.getPutere());
            mem.setPutere(mem.getPutere() - 1);
            rezultat.polinom.add(mem);
        }
        return rezultat;
    }

    //integrare
    public Polynomial integrare() {
        Polynomial rezultat = new Polynomial();
        for (Monomial mem : this.polinom) {
            Monomial aux = new Monomial();

            int puterei;
            double coeficienti;

            puterei = mem.getPutere();
            coeficienti = mem.getCoeficient();

            if (puterei >= 0) {
                puterei++;
                coeficienti = coeficienti / (double) puterei;
                aux.setCoeficient(coeficienti);
                aux.setPutere(puterei);
                rezultat.polinom.add(aux);
            }
        }
        return rezultat;
    }

    //Initializare polinom
    public void setPolinom(List<Monomial> p) {
        this.polinom = p;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Monomial monomial : polinom)
            s.append(monomial.toString());
        return s.toString();
    }
}
