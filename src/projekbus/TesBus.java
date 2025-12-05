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
