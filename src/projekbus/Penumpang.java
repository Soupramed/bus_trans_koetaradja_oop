/**
 * Penumpang.java
 * Representasi satu penumpang
 */
public class Penumpang {
    private int id;
    private int umur;
    private boolean hamil;
    private int saldo;

    public Penumpang(int id, int umur, boolean hamil) {
        this.id = id;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = 10000; // saldo awal
    }

    public int getID() { return id; }
    public int getUmur() { return umur; }
    public boolean getHamil() { return hamil; }
    public int getSaldo() { return saldo; }

    public void tambahSaldo(int s) { saldo += s; }
    public void kurangiSaldo(int ongkos) { saldo -= ongkos; }

    public boolean isPrioritas() {
        return (umur > 60) || (umur < 10) || hamil;
    }

    @Override
    public String toString() {
        String status = isPrioritas() ? "PRIORITAS" : "Biasa";
        String h = hamil ? "Ya" : "Tidak";
        return String.format("ID:%03d | Umur:%2d | Hamil:%-3s | %6s | Saldo: Rp %d",
                             id, umur, h, status, saldo);
    }
}
