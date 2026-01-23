import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MarginMath {
    static int indexProduct = 0;
    static int indexIngredients = 0;
    static String Product[][] = new String[100][100];
    static float Price[][] = new float[100][100];
    static int bTenagaK[] = new int[100];
    static int biayaOverhead[] = new int[100];
    static MarginMath main = new MarginMath();
    static Scanner s = new Scanner(System.in);
    static int ProfitMargin = -1;
    static float hpp = 0;
    static float bep = 0;
    static float beq = 0;
    static float hargaJual = 0;
    static int jumlahBB;
    static int allPricePerProduct[][] = new int[100][100];
    static int allPrice = 0;

    public static void main(String[] args) throws Exception {
        boolean Running = true;
        char Option;
        do {
            main.Menu();
            Option = s.next().charAt(0);
            switch (Option) {
                case '1':
                    main.RegisterItem(indexProduct);
                    break;
                case '2':
                    main.ShowRegisteredItems();
                    break;
                case '3':
                    main.ReplaceItem();
                    break;
                case '4':
                    main.SetProfitMargin();
                    break;
                case '5':
                    main.Calculate();
                    break;
                case '6':
                    main.ClearData();
                    break;
                case '0':
                    Running = false;
                    main.SaveData();
                    System.out.println("");
                    System.out.println("Terima Kasih!");
                    System.out.println("");
                    break;
                default:
                    System.out.println("");
                    System.out.println("<> Invalid Option <>");
                    System.out.println("");
                    break;
            }
        } while (Running);
        s.close();
    }

    public void Menu() {
        System.out.println("------------------------------------------------");
        System.out.println("    <> Pilihan <>");
        System.out.println("|1| Daftarkan Item Baru, Bahan Baku dan Harganya");
        System.out.println("|2| Tampilkan Item yang Terdaftar");
        System.out.println("|3| Ganti Item");
        System.out.println("|4| Tentukan Margin Keuntungan");
        System.out.println("|5| Hitung HPP, BEP, BEQ, dan Harga Jual");
        System.out.println("|6| Hapus Semua Data");
        System.out.println("|0| Keluar");
        System.out.println("-------------------------------------------------");
        System.out.print("Masukkan Pilihan anda: ");
    }

    public void RegisterItem(int itemNumber) {
        boolean registerItem = true;
        boolean incorrect = true;
        boolean isNumber = false;

        System.out.print("Masukkan Produk yang ingin anda jual: ");
        Product[itemNumber][0] = s.next() + s.nextLine();
        System.out.println("-------------------------------------------------------------------");

        do {
            System.out.print("Masukkan Bahan Baku yang anda perlukan untuk membuat " + Product[itemNumber][0] + ": ");
            Product[itemNumber][indexIngredients + 1] = s.next() + s.nextLine();

            do {
                try {
                    isNumber = false;
                    System.out.print("Masukkan Harga dari " + Product[itemNumber][indexIngredients + 1] + " : Rp. ");
                    allPricePerProduct[itemNumber][indexIngredients + 1] = s.nextInt();
                    if (allPricePerProduct[itemNumber][indexIngredients + 1] < 0) {
                        isNumber = false;
                        System.out.println("");
                        System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                        System.out.println("");
                    } else {
                        isNumber = true;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("<> Input harus berupa angka <>");
                    System.out.println("");
                    s.nextLine();
                }
            } while (isNumber == false);

            do {
                try {
                    isNumber = false;
                    System.out.print(
                            "Untuk " + Product[itemNumber][indexIngredients + 1] + " seharga Rp. "
                                    + allPricePerProduct[itemNumber][indexIngredients + 1]
                                    + " dapat digunakan untuk berapa "
                                    + Product[itemNumber][0] + ": ");
                    jumlahBB = s.nextInt();
                    Price[itemNumber][indexIngredients + 1] = allPricePerProduct[itemNumber][indexIngredients + 1]
                            / jumlahBB;
                    isNumber = true;
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("<> Input harus berupa angka <>");
                    System.out.println("");
                    s.nextLine();
                }
            } while (isNumber == false);

            do {
                System.out.print("Apakah masih ada Bahan Baku yang perlu anda tambah (Y/N)? ");
                String response = s.next() + s.nextLine();
                indexIngredients += 1;
                if (response.equalsIgnoreCase("Y")) {
                    System.out.println("-------------------------------------------------------------------");
                    incorrect = false;
                    registerItem = true;
                } else if (response.equalsIgnoreCase("N")) {
                    System.out.println("--------------------------------------------------------------------");
                    indexProduct += 1;
                    indexIngredients = 0;
                    incorrect = false;
                    registerItem = false;

                    do {
                        try {
                            isNumber = false;
                            System.out.print("Masukkan biaya tenaga kerja yang anda perlukan (per produk): Rp. ");
                            bTenagaK[itemNumber] += s.nextInt();
                            if (bTenagaK[itemNumber] < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);

                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Masukkan Biaya Overhead anda (per produk): ");
                    do {
                        try {
                            isNumber = false;
                            System.out.print("Biaya Sewa: Rp. ");
                            int sewa = s.nextInt();
                            if (sewa < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                            biayaOverhead[itemNumber] += sewa;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);

                    do {
                        try {
                            isNumber = false;
                            System.out.print("Biaya Listrik: Rp. ");
                            int listrik = s.nextInt();
                            if (listrik < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                            biayaOverhead[itemNumber] += listrik;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);

                    do {
                        try {
                            isNumber = false;
                            System.out.print("Biaya Air: Rp. ");
                            int air = s.nextInt();
                            if (air < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                            biayaOverhead[itemNumber] += air;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);

                    do {
                        try {
                            isNumber = false;
                            System.out.print("Biaya Administrasi: Rp. ");
                            int admin = s.nextInt();
                            if (admin < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                            biayaOverhead[itemNumber] += admin;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);

                    do {
                        try {
                            isNumber = false;
                            System.out.print("Biaya Lain-lain: Rp. ");
                            int lain = s.nextInt();
                            if (lain < 0) {
                                isNumber = false;
                                System.out.println("");
                                System.out.println("<> Harga tidak bisa dibawah Rp. 0 <>");
                                System.out.println("");
                            } else {
                                isNumber = true;
                            }
                            biayaOverhead[itemNumber] += lain;
                        } catch (InputMismatchException e) {
                            System.out.println("");
                            System.out.println("<> Input harus berupa angka <>");
                            System.out.println("");
                            s.nextLine();
                        }
                    } while (isNumber == false);
                } else {
                    incorrect = true;
                    System.out.println("");
                    System.out.println("<> Input harus berupa Y / y / N / n <>");
                    System.out.println("");
                }
            } while (incorrect);
        } while (registerItem);
    }

    public void ShowRegisteredItems() {
        if (Product[0][0] != null) {
            System.out.println("");
            for (int index = 0; index < indexProduct; index++) {
                System.out.println("Produk nomor: " + (index + 1));
                for (int index2 = 0; index2 < Product[index].length; index2++) {
                    if (index2 == 0) {
                        System.out.println("Produk: " + Product[index][0]);
                    } else if (Product[index][index2] != null) {
                        System.out.print("Bahan Baku: " + Product[index][index2] + " ");
                        System.out.println("| Harga (per produk): Rp. " + (int) Price[index][index2]);
                    } else {
                        break;
                    }
                }
                System.out.println("");
            }
        } else {
            System.out.println("");
            System.out.println("<> Belum ada data <>");
            System.out.println("");
        }
    }

    public void ReplaceItem() {
        main.ShowRegisteredItems();
        boolean valid = false;
        int ItemNumber = 0;
        if (indexProduct != 0) {
            do {
                try {
                    System.out.print("Nomor Produk yang ingin anda ganti: ");
                    ItemNumber = s.nextInt() - 1;
                    if (ItemNumber == -1) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Nomor tidak bisa 0 <>");
                        System.out.println("");
                    } else if (ItemNumber < -1) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Nomor tidak bisa dibawah 0 <>");
                        System.out.println("");
                    } else if ((ItemNumber + 1) > indexProduct) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Anda hanya memiliki " + indexProduct + " produk <>");
                        System.out.println("");
                    }else {
                        valid = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("<> Input harus berupa angka <>");
                    System.out.println("");
                    s.nextLine();
                }
            } while (valid == false);

            for (int j = 0; j < Product[ItemNumber].length; j++) {
                Product[ItemNumber][j] = null;
                Price[ItemNumber][j] = 0;
                bTenagaK[ItemNumber] = 0;
                biayaOverhead[ItemNumber] = 0;
            }
            main.RegisterItem(ItemNumber);
            indexProduct -= 1;
        }
    }

    public void SetProfitMargin() {
        boolean isNumber = false;
        do {
            try {
                isNumber = false;
                System.out.println(" ");
                System.out.print("Masukkan persentase Margin Keuntungan (%): ");
                ProfitMargin = s.nextInt();
                if (ProfitMargin < 0) {
                    isNumber = false;
                    System.out.println("");
                    System.out.println("<> Persentase tidak bisa dibawah 0% <>");
                    System.out.println("");
                } else {
                    isNumber = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("<> Input harus berupa angka <>");
                System.out.println("");
                s.nextLine();
            }
        } while (isNumber == false);
    }

    public void Calculate() {
        boolean valid = false;
        int ItemNumb = 0;
        if (indexProduct == 0) {
            main.ShowRegisteredItems();
        } else {
            if (ProfitMargin == -1) {
                main.SetProfitMargin();
            }
            main.ShowRegisteredItems();
            do {
                try {
                    System.out.print("Masukkan nomor Produk yang ingin kamu hitung: ");
                    ItemNumb = s.nextInt() - 1;
                    if (ItemNumb == -1) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Nomor tidak bisa 0 <>");
                        System.out.println("");
                    } else if (ItemNumb < -1) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Nomor tidak bisa dibawah 0 <>");
                        System.out.println("");
                    } else if ((ItemNumb + 1) > indexProduct) {
                        valid = false;
                        System.out.println("");
                        System.out.println("<> Anda hanya memiliki " + indexProduct + " produk <>");
                        System.out.println("");
                    } else {
                        valid = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("");
                    System.out.println("<> Input harus berupa angka <>");
                    System.out.println("");
                    s.nextLine();
                }
            } while (valid == false);

            int totalhargabb = 0;
            for (int i = 1; i < Product[i].length; i++) {
                if (Product[ItemNumb][i] != null) {
                    totalhargabb += Price[ItemNumb][i];
                } else {
                    break;
                }
            }
            System.out.println("------------------------------------");
            System.out.println("Total Biaya Bahan Baku: Rp. " + totalhargabb);
            System.out.println("Total Biaya Tenaga Kerja: Rp. " + bTenagaK[ItemNumb]);
            System.out.println("Total Biaya Overhead: Rp. " + biayaOverhead[ItemNumb]);

            hpp = totalhargabb + bTenagaK[ItemNumb] + biayaOverhead[ItemNumb];

            hargaJual = (hpp * ((float) ProfitMargin / 100)) + hpp;

            for (int i = 1; i < Product[i].length; i++) {
                if (Product[ItemNumb][i] != null) {
                    allPrice += allPricePerProduct[ItemNumb][i];
                } else {
                    break;
                }
            }

            bep = allPrice + bTenagaK[ItemNumb] + biayaOverhead[ItemNumb];

            beq = bep / hargaJual;

            System.out.println("-------------------------------------------------");
            System.out.println("HPP: Rp. " + (int) Math.ceil(hpp));
            System.out.println("BEP: Rp. " + (int) bep);
            System.out.println("BEQ: " + (int) Math.ceil(beq) + " jumlah");
            System.out.println("Harga Jual per Produk: Rp. " + (int) Math.ceil(hargaJual));
        }
    }

    public void ClearData() {
        if (indexProduct != 0) {
            for (int index = 0; index < Product.length; index++) {
                for (int index2 = 0; index2 < Product[indexProduct].length; index2++) {
                    Product[index][index2] = null;
                    Price[index][index2] = 0;
                    bTenagaK[index] = 0;
                    biayaOverhead[index] = 0;
                }
            }
            indexProduct = 0;
            indexIngredients = 0;
            System.out.println("");
            System.out.println("<> Data Anda Telah Dihapus <>");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("<> Anda Belum Ada Data untuk Dihapus <>");
            System.out.println("");
        }
    }

    public void SaveData() {
        System.out.print("Apakah anda ingin menyimpan Data anda (Y/N)? ");
        String response = s.next() + s.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            System.out.print("Masukkan Username anda: ");
            String username = s.next() + s.nextLine();
            String Filename = username + ".txt";
            System.out.print("Masukkan Password anda: ");
            String Password = s.next() + s.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
                writer.write(Password);
                writer.newLine();

                writer.write("Product Array:");
                writer.newLine();
                for (int k = 0; k < Product.length; k++) {
                    for (int l = 0; l < Product[k].length; l++) {
                        if (Product[k][l] != null) {
                            writer.write(Product[k][l]);
                        }
                        if (l < Product[k].length - 1) {
                            writer.write(";");
                        }
                    }
                    writer.newLine();
                }

                writer.write("Price Array:");
                writer.newLine();
                for (int k = 0; k < Price.length; k++) {
                    for (int l = 0; l < Price[k].length; l++) {
                        writer.write(String.valueOf(Price[k][l]));
                        if (l < Price[k].length - 1) {
                            writer.write(";");
                        }
                    }
                    writer.newLine();
                }

                writer.write("bTenagaK Array:");
                writer.newLine();
                for (int i = 0; i < bTenagaK.length; i++) {
                    writer.write(String.valueOf(bTenagaK[i]));
                    if (i < bTenagaK.length - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine();

                writer.write("biayaOverhead Array:");
                writer.newLine();
                for (int i = 0; i < biayaOverhead.length; i++) {
                    writer.write(String.valueOf(biayaOverhead[i]));
                    if (i < biayaOverhead.length - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine();

                writer.write("indexProduct: " + indexProduct);
                writer.newLine();
                writer.write("indexIngredients: " + indexIngredients);
                writer.newLine();
                writer.write("ProfitMargin: " + ProfitMargin);
                writer.newLine();

                writer.write("allPricePerProduct Array:");
                writer.newLine();
                for (int k = 0; k < allPricePerProduct.length; k++) {
                    for (int l = 0; l < allPricePerProduct[k].length; l++) {
                        writer.write(String.valueOf(allPricePerProduct[k][l]));
                        if (l < allPricePerProduct[k].length - 1) {
                            writer.write(";");
                        }
                    }
                    writer.newLine();
                }

                System.out.println("");
                System.out.println("<> Data Anda Telah Disimpan <>");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    public boolean LoadData() {
        boolean berhasil = false;
        System.out.print("Masukkan Username anda: ");
        String username = s.next() + s.nextLine();
        String Filename = username + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String savedPassword = reader.readLine();
            System.out.print("Masukkan password anda: ");
            String enteredPassword = s.next() + s.nextLine();
            if (!enteredPassword.equals(savedPassword)) {
                System.out.println("");
                System.out.println("<> Password salah! <>");
                System.out.println("");
                return false;
            }

            System.out.println("");
            System.out.println("<> Memuat data... <>");
            System.out.println("");

            reader.readLine(); // Skip "Product Array:" line
            for (int i = 0; i < Product.length; i++) {
                String line = reader.readLine();
                if (line == null || line.isEmpty())
                    break;
                String[] values = line.split(";");
                System.arraycopy(values, 0, Product[i], 0, values.length);
            }

            reader.readLine(); // Skip "Price Array:" line
            for (int i = 0; i < Price.length; i++) {
                String line = reader.readLine();
                if (line == null || line.isEmpty())
                    break;
                String[] values = line.split(";");
                for (int j = 0; j < values.length; j++) {
                    Price[i][j] = Float.parseFloat(values[j]);
                }
            }

            reader.readLine(); // Skip "bTenagaK Array:" line
            String[] tenagaValues = reader.readLine().split(";");
            for (int i = 0; i < tenagaValues.length; i++) {
                bTenagaK[i] = Integer.parseInt(tenagaValues[i]);
            }

            reader.readLine(); // Skip "biayaOverhead Array:" line
            String[] overheadValues = reader.readLine().split(";");
            for (int i = 0; i < overheadValues.length; i++) {
                biayaOverhead[i] = Integer.parseInt(overheadValues[i]);
            }

            indexProduct = Integer.parseInt(reader.readLine().split(": ")[1]);
            indexIngredients = Integer.parseInt(reader.readLine().split(": ")[1]);
            ProfitMargin = Integer.parseInt(reader.readLine().split(": ")[1]);

            reader.readLine(); // Skip "allPricePerProduct Array:" line
            for (int i = 0; i < allPricePerProduct.length; i++) {
                String line = reader.readLine();
                if (line == null || line.isEmpty())
                    break;
                String[] values = line.split(";");
                for (int j = 0; j < values.length; j++) {
                    allPricePerProduct[i][j] = Integer.parseInt(values[j]);
                }
            }

            System.out.println("<> Data Anda Telah Dimuat <>");
            berhasil = true;
        } catch (IOException e) {
            System.out.println("");
            System.err.println("<> Username tidak ditemukan <>");
            System.out.println("");
        }
        return berhasil;
    }
}
