import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {
    public static int currentTime = 0;
    public static Semaphore cortandoCabelo = new Semaphore(1);

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tempo de espera do usuário
        System.out.print("Digite o tempo de espera: ");
        int timeOffWaiting = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        // Solicitar o nome do arquivo do usuário
        System.out.print("Digite o nome do arquivo: ");
        String file = scanner.nextLine();

        // Solicitar a quantidade de barbeiros do usuário
        System.out.print("Digite a quantidade de barbeiros ");
        String quantidadeDeBarbeiros = scanner.nextLine();
        //NÃO CONTER ESPAÇOS!!!!
       
        List<List<Integer>> fila = lerArquivo(file);

        Thread recrutaZero = new Thread(new RecrutaZero(cortandoCabelo));
        Thread recrutaZero2 = new Thread(new RecrutaZero(cortandoCabelo));
        Thread recrutaZero3 = new Thread(new RecrutaZero(cortandoCabelo));

        Thread sargentoTainha = new Thread(new SargentoTainha(fila, timeOffWaiting));
        Thread tenenteEscovinha = new Thread(new TenenteEscovinha());

        // Executar threads de acordo com a quantidade de barbeiros selecionados
        if (quantidadeDeBarbeiros.equals("1")) {
            recrutaZero.start();
        }
        if (quantidadeDeBarbeiros.equals("2")) {
            recrutaZero.start();
            recrutaZero2.start();
        }
        if (quantidadeDeBarbeiros.equals("3")) {
            recrutaZero.start();
            recrutaZero2.start();
            recrutaZero3.start();
        }

        tenenteEscovinha.start();
        sargentoTainha.start();

        try {
            if (quantidadeDeBarbeiros.equals("1")) {
                recrutaZero.join();
            }
            if (quantidadeDeBarbeiros.equals("2")) {
                recrutaZero.join();
                recrutaZero2.join();
            }
            if (quantidadeDeBarbeiros.equals("3")) {
                recrutaZero.join();
                recrutaZero2.join();
                recrutaZero3.join();
            }
            sargentoTainha.join();
            tenenteEscovinha.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void addCurrentTime(int time) {
        currentTime += time;
    }

    private static List<List<Integer>> lerArquivo(String arquivo) throws IOException {
        List<List<Integer>> entrada = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" ");
                List<Integer> numeros = new ArrayList<>();
                for (String parte : partes) {
                    try {
                        numeros.add(Integer.parseInt(parte));
                    } catch (NumberFormatException e) {
                        System.err.println("Não foi possível converter a string para inteiro: " + parte);
                    }
                }
                entrada.add(numeros);
            }
        }
        return entrada;
    }
}
