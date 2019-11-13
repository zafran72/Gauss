/*
    Program Penyelesaian Persamaan Linier Simultan Metode Eliminasi Gauss
    Kelompok 2
*/
import java.util.Scanner;

public class gauss {
    //instance variable
    private int n;
    private double A[][] = new double[100][101];
    private double x[] = new double[100];
    static Scanner user = new Scanner(System.in);

    //method constructor
    //tze
    public gauss() {
        n = 0;
    }
    public static void main(String[] args) {
        gauss matriks = new gauss();

        matriks.setOrdo();
        matriks.setMatriks();
        matriks.cekNol();
        matriks.doOperasi();
        matriks.doSubstitusi();
        matriks.getX();
    }

    //method untuk input ukuran ordo matriks
    public void setOrdo() {
        System.out.print("Masukkan ukuran ordo matriks(n) : ");
        n = user.nextInt();
    }

    //method untuk memasukkan nilai matriks
    public void setMatriks() {
        System.out.println("Masukkan nilai matriks : ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n+1; j++) {
                A[i][j] = user.nextDouble();
            }
        }
    }

    //method untuk menampilkan tampilan matriks
    public void getMatriks() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n+1; j++) {
                System.out.print(String.format("%.2f", A[i][j])+ " ");
            }
            System.out.println();
        }
    }

    //method untuk mengecek satu utama nya nol atau tidak
    public void cekNol() {
        for (int i = 0; i < n; i++) {
            if (A[i][i]==0) {
                for (int j = 0; j < n; j++) {
                    if (j==i) {
                        continue;
                    } else if (A[j][i]!=0) {
                        tukarBaris(j,i);
                    }
                }
            }
        }
    }

    //method untuk menukar baris
    public void tukarBaris(int b1, int b2) {
        double tmp;
        for (int i = 0; i < n+1; i++) {
            tmp = A[b1][i];
            A[b1][i] = A[b2][i];
            A[b2][i] = tmp;
        }
    }

    //method untuk melakukan operasi
    public void doOperasi() {
        for (int i = 0; i < n; i++) {
            double p=A[i][i];
            for (int j = 0; j < n+1; j++) {
                A[i][j] /= p;
            }
            getMatriks();

            for (int j = i+1; j < n; j++) {
                double c = A[j][i];
                for (int k = 0; k < n+1; k++) {
                    A[j][k] = A[j][k]-c*A[i][k];
                }
                getMatriks();
            }
        }
    }

    //metode untuk melakukan substitusi
    public void doSubstitusi() {
        int awal = 0;
        for (int i = n-1; i >= 0; i--) {
            x[i] = A[n-1-awal][n];
            awal++;
            int akhir = n-1;
            for (int j = 0; j < n-i-1; j++) {
                x[i] -= A[n-awal][n-1-j]*x[akhir--];
            }
        }
    }

    //method untuk menampilkan semua nilai x
    public void getX() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.println("X" +(i+1)+ " = " +String.format("%.2f", x[i]) );
        }
    }
}