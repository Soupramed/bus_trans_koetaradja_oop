import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class TestBus {
   public TestBus() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      Bus var2 = new Bus();
      welcome();

      while(true) {
         showMainMenu();
         int var3 = inputInt(var1, "Pilih (1-5): ");
         switch (var3) {
            case 1:
               handleNaik(var1, var2);
               break;
            case 2:
               handleTurun(var1, var2);
               break;
            case 3:
               render3DPanel(var2);
               pause(var1);
               break;
            case 4:
               simulateDemo(var2);
               System.out.println("\n[Simulasi selesai: tekan ENTER untuk lihat dashboard]");
               pause(var1);
               render3DPanel(var2);
               pause(var1);
               break;
            case 5:
               System.out.println("Terima kasih. Sampai jumpa!");
               var1.close();
               return;
            default:
               System.out.println("❌ Pilihan tidak dikenal. Masukkan 1-5.");
         }
      }
   }

   private static int inputInt(Scanner var0, String var1) {
      while(true) {
         System.out.print(var1);

         try {
            return var0.nextInt();
         } catch (InputMismatchException var3) {
            System.out.println("❌ ERROR: Input harus berupa angka! Coba lagi.");
            var0.nextLine();
         }
      }
   }

   private static boolean inputYesNo(Scanner var0, String var1) {
      while(true) {
         System.out.print(var1);
         String var2 = var0.next().trim().toLowerCase();
         if (var2.equals("y")) {
            return true;
         }

         if (var2.equals("n")) {
            return false;
         }

         System.out.println("❌ ERROR: Masukkan hanya 'y' atau 'n'!");
      }
   }

   private static void welcome() {
      System.out.println();
      System.out.println("  ░█▀▄░█░█░█▀▀░▀█▀░█▀█░█▀▄   BUS TRANS KOETARADJA");
      System.out.println("  ░█▀▄░█░█░█▀▀░░█░░█░█░█░█   KOTA BANDA ACEH");
      System.out.println("  ░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀░");
      System.out.println();
   }

   private static void showMainMenu() {
      System.out.println("==== MENU UTAMA ====");
      System.out.println("1. Naikkan Penumpang");
      System.out.println("2. Turunkan Penumpang (by ID)");
      System.out.println("3. Lihat Dashboard ");
      System.out.println("4. Simulasi Otomatis (Demo isi cepat)");
      System.out.println("5. Keluar");
   }

   private static void pause(Scanner var0) {
      System.out.println("\nTekan ENTER untuk kembali...");

      try {
         System.in.read();
      } catch (Exception var2) {
      }

   }

   private static void handleNaik(Scanner var0, Bus var1) {
      int var2 = inputInt(var0, "Masukkan ID (angka): ");
      if (var1.idExists(var2)) {
         System.out.println("✖ ID sudah ada di dalam bus. Gunakan ID lain.");
      } else {
         int var3 = inputInt(var0, "Umur: ");
         boolean var4 = inputYesNo(var0, "Hamil? (y/n): ");
         Penumpang var5 = new Penumpang(var2, var3, var4);
         if (var1.naikkanPenumpang(var5)) {
            System.out.println("✔ Penumpang berhasil naik. Sisa saldo: Rp " + var5.getSaldo());
         } else if (var5.getSaldo() < 2000) {
            System.out.println("✖ Saldo tidak cukup.");
         } else {
            System.out.println("✖ Bus penuh atau tidak bisa naik.");
         }

      }
   }
   private static void handleTurun(Scanner var0, Bus var1) {
      int var2 = inputInt(var0, "Masukkan ID penumpang yang turun: ");
      if (var1.turunkanPenumpang(var2)) {
         System.out.println("✔ Penumpang ID " + var2 + " berhasil diturunkan.");
      } else {
         System.out.println("✖ Penumpang tidak ditemukan.");
      }

   }

   private static void simulateDemo(Bus var0) {
      Penumpang var1 = new Penumpang(101, 65, false);
      Penumpang var2 = new Penumpang(102, 30, false);
      Penumpang var3 = new Penumpang(103, 8, false);
      Penumpang var4 = new Penumpang(104, 27, true);
      Penumpang var5 = new Penumpang(105, 45, false);
      var0.naikkanPenumpang(var1);
      var0.naikkanPenumpang(var2);
      var0.naikkanPenumpang(var3);
      var0.naikkanPenumpang(var4);
      var0.naikkanPenumpang(var5);

      for(int var6 = 200; var6 < 205; ++var6) {
         var0.naikkanPenumpang(new Penumpang(var6, 20 + var6 % 5, false));
      }

      System.out.println("\n[Simulasi] 8 penumpang otomatis dimasukkan untuk demo.");
   }

   private static void render3DPanel(Bus var0) {
      String var1 = "╔══════════════════════════════════════════════════════════════╗";
      String var2 = "╠══════════════════════════════════════════════════════════════╣";
      String var3 = "╚══════════════════════════════════════════════════════════════╝";
      System.out.println();
      System.out.println(var1);
      System.out.println("║" + centerText("░█▀▄░█░█░█▀▀  BUS TRANS KOETARADJA  ░▀░▀░▀▀▀░▀▀▀", 62) + "║");
      System.out.println(var2);
      System.out.println("║ " + padRight("STATUS BUS", 62) + "║");
      System.out.println("║ " + padRight("──────────────────────────────────────────────────────", 62) + "║");
      System.out.println("║ " + padRight("Kursi Prioritas (4):", 62) + "║");
      PrintStream var10000;
      String var10001;
      Iterator var4;
      Penumpang var5;
      if (var0.getJumlahPenumpangPrioritas() == 0) {
         System.out.println("║ " + padRight("  <Kosong>", 62) + "║");
      } else {
         var4 = var0.getPenumpangPrioritas().iterator();

         while(var4.hasNext()) {
            var5 = (Penumpang)var4.next();
            var10000 = System.out;
            var10001 = "  ► " + shortDesc(var5);
            var10000.println("║ " + padRight(var10001, 62) + "║");
         }
      }

      System.out.println("║ " + padRight("", 62) + "║");
      System.out.println("║ " + padRight("Kursi Biasa (16):", 62) + "║");
      if (var0.getJumlahPenumpangBiasa() == 0) {
         System.out.println("║ " + padRight("  <Kosong>", 62) + "║");
      } else {
         int var7 = 0;
         Iterator var8 = var0.getPenumpangBiasa().iterator();

         while(var8.hasNext()) {
            Penumpang var6 = (Penumpang)var8.next();
            var10000 = System.out;
            var10001 = "  ► " + shortDesc(var6);
            var10000.println("║ " + padRight(var10001, 62) + "║");
            ++var7;
            if (var7 >= 8) {
               break;
            }
         }
