import java.util.ArrayList;

/**
 * Bus.java
 * Menangani koleksi penumpang dan aturan naik/turun
 */
public class Bus {
    private ArrayList<Penumpang> penumpangBiasa = new ArrayList<>();
    private ArrayList<Penumpang> penumpangPrioritas = new ArrayList<>();
    private ArrayList<Penumpang> penumpangBerdiri = new ArrayList<>();

    public static final int ONGKOS_BUS = 2000;
    private int totalPendapatan = 0;

    // cek apakah ID sudah ada di bus
    public boolean idExists(int id) {
        for (Penumpang p : penumpangPrioritas) if (p.getID() == id) return true;
        for (Penumpang p : penumpangBiasa) if (p.getID() == id) return true;
        for (Penumpang p : penumpangBerdiri) if (p.getID() == id) return true;
        return false;
    }

    public boolean naikkanPenumpang(Penumpang p) {
        if (p.getSaldo() < ONGKOS_BUS) {
            return false; // saldo kurang
        }
        if (totalPenumpang() >= 40) {
            return false; // bus penuh
        }

        if (p.isPrioritas()) {
            if (penumpangPrioritas.size() < 4) penumpangPrioritas.add(p);
            else if (penumpangBiasa.size() < 16) penumpangBiasa.add(p);
            else penumpangBerdiri.add(p);
        } else {
            if (penumpangBiasa.size() < 16) penumpangBiasa.add(p);
            else penumpangBerdiri.add(p);
        }

