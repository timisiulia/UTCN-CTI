package model;

public class Monomial {
    //iniatializam componentele unui monom
    private int putere;
    private double coeficient;

    public Monomial(int putere, double coeficient) {
        this.putere = putere;
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return putere;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public Monomial() {
    }

    //adunare
    public Monomial adunare(Monomial mem) {
        Monomial rezultat = new Monomial();
        rezultat.setPutere(this.getPutere());
        rezultat.setCoeficient(this.getCoeficient() + mem.getCoeficient());
        return rezultat;
    }

    //scadere
    public Monomial scadere(Monomial mem) {
        Monomial rezultat = new Monomial();
        rezultat.setPutere(this.getPutere());
        rezultat.setCoeficient(this.getCoeficient() - mem.getCoeficient());
        return rezultat;
    }

    //inmultire
    public Monomial inmultire(Monomial mem) {
        Monomial rezultat = new Monomial();
        rezultat.setPutere(this.getPutere() + mem.getPutere());
        rezultat.setCoeficient(this.getCoeficient() * mem.getCoeficient());
        return rezultat;
    }

    //derivare
    public Monomial derivare(Monomial mem) {
        Monomial rezultat = new Monomial(this.getPutere() - mem.getPutere(), this.getCoeficient() / mem.getCoeficient());
        return rezultat;

    }

    @Override
    public String toString() {
        if (coeficient >= 0)
            return "+" + coeficient + "x^" + (int) putere + " ";
        else
            return coeficient + "x^" + (int) putere + " ";
    }
}
