import java.util.Arrays;
import java.util.Scanner;

public class trabalho {
    public static double carroReferencia = 0;
    public static double carroAdicional = 0;

    public static double motoReferencia = 0;
    public static double motoAdicional = 0;

    public static double vanReferencia = 0;
    public static double vanAdicional = 0;

    public static int colunas = 0;
    public static int corredores = 0;

    public static int linha = 0;
    public static int coluna = 0;

    public static String statusVaga = ".";
    public static String[][] estacionamento;

    // teste
    public static String vaga = " b 3 ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        informacoes(scanner);
        gerenciarVagas();
        AbrirMenu(scanner);

        // testes

        // informacoes(scanner);
        // System.out.println("digite a vaga");
        // vaga = scanner.nextLine();
        // System.out.println(Arrays.toString(converterVagaParaIndices(vaga)));
        // ConverterIndicesParaVaga();

        scanner.close();

    }

    public static void informacoes(Scanner scanner) {
        System.out.println("Informe o valor de referência dos primeiros 30 minutos ");
        double valorReferencia = scanner.nextDouble();
        System.out.println("Informe o valor de cada 30 minutos adicionais ");
        double valorAdicional = scanner.nextDouble();
        System.out.println("Informe a quantidade de corredores (entre 5 e 15) ");
        corredores = scanner.nextInt();
        while (corredores > 15 || corredores < 5) {
            System.out.println("Quantidade de corredores inválida");
            System.out.println("Informe uma quantidade entre 5 e 15 ");
            corredores = scanner.nextInt();
        }

        System.out.println("Informe a quantidade de colunas (entre 5 e 20) ");
        colunas = scanner.nextInt();
        while (colunas > 20 || colunas < 5) {
            System.out.println("Quantidade de colunas inválida");
            System.out.println("Informe uma quantidade entre 5 e 20 ");
            colunas = scanner.nextInt();
        }

        carroReferencia = valorReferencia;
        carroAdicional = valorAdicional;

        motoReferencia = valorReferencia * 0.7;
        motoAdicional = valorAdicional * 0.7;

        vanReferencia = valorReferencia * 1.3;
        vanAdicional = valorAdicional * 1.3;
        scanner.nextLine();

    }

    public static void gerenciarVagas() {
        estacionamento = new String[corredores][colunas];
        for (int i = 0; i < corredores; i++) {
            for (int j = 0; j < colunas; j++) {
                estacionamento[i][j] = statusVaga;
            }
        }

        estacionamento[0][1] = "A2=C:08:15";
        estacionamento[1][2] = "B3=V:12:34";
        estacionamento[2][5] = "C6=M:15:03";
        estacionamento[3][1] = "D2=C:21:00";

        // 4. Impressão da matriz com formatação.
        for (int i = 0; i < corredores; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.printf("%s\t", estacionamento[i][j]);
            }
            System.out.println();
        }
    }

    // devolve um array para futuramente poder usar ele em outras funcionalidades
    public static int[] converterVagaParaIndices(String vaga) {

        if (vaga == null || vaga.length() < 2) {
            return null;
        }

        vaga = (vaga.toUpperCase()).replace(" ", "");

        int letraVaga = vaga.charAt(0);
        linha = letraVaga - 'A';

        try {
            String numeroVaga = vaga.substring(1);
            coluna = Integer.parseInt(numeroVaga) - 1;

            // verifica se esta dentro dos limites
            if (linha >= 0 && linha < corredores && coluna >= 0 && coluna < colunas) {
                return new int[] { linha, coluna };
            } else {
                return null;
            }
        } catch (Exception e) {
            return null; // caso insira um fomato invalido tipo "AX"
        }

    }

    public static void ConverterIndicesParaVaga() {
        char letra = (char) (linha + 'A');
        int numero = coluna + 1;
        vaga = String.format("%c%d", letra, numero);
        System.out.println(vaga);

    }

    public static void AbrirMenu(Scanner scanner) {
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("+---------------------------------+");
            System.out.println("|        Menu de opções           |");
            System.out.println("+---------------------------------+");
            System.out.println("| Opção 1 - Carregar dados        |");
            System.out.println("| Opção 2 - Consultar Vaga        |");
            System.out.println("| Opção 3 - Entrada               |");
            System.out.println("| Opção 4 - Saída                 |");
            System.out.println("| Opção 5 - Ocupação              |");
            System.out.println("| Opção 6 - Financeiro            |");
            System.out.println("| Opção 7 - Salvar Dados          |");
            System.out.println("| Opção 8 - Integrantes           |");
            System.out.println("| Opção 9 - Sair                  |");
            System.out.println("+---------------------------------+");
            System.out.print("Digite uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    carregarDados(scanner);
                    break;
                case 2:
                    consultarVaga(scanner, vaga);
                    break;
                case 3:
                    entrada(scanner);
                    break;
                case 4:
                    saida(scanner);
                    break;
                case 5:
                    ocupação(scanner);
                    break;
                case 6:
                    financeiro(scanner);
                    break;
                case 7:
                    salvarDados(scanner);
                    break;
                case 8:
                    integrantes(scanner);
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }

    public static void carregarDados(Scanner scanner) {

    }

    public static void consultarVaga(Scanner scanner, String vaga) {

        System.out.println("Informe a vaga desejada");
        vaga = scanner.nextLine();
        int[] indices = converterVagaParaIndices(vaga);
        while (indices == null) {
            System.out.println("Vaga inválida");
            System.out.println("Informe uma vaga válida ");
            vaga = scanner.nextLine();
            indices = converterVagaParaIndices(vaga);
        }
        
        int linhaParaOcupar = indices[0];
        int colunaParaOcupar = indices[1];
        if (estacionamento[linhaParaOcupar][colunaParaOcupar] != ".") {
            // talvez transforme isso em uma função para reutilizar em uma futura
            // funcionalidade
            String[] informacoes = estacionamento[linhaParaOcupar][colunaParaOcupar].split("[=:]");
            String veiculo = "veiculo";
            if (informacoes[1].equals("C")) {
                veiculo = "Carro";
            } else if (informacoes[1].equals("V")) {
                veiculo = "Van";
            } else if (informacoes[1].equals("M")) {
                veiculo = "Moto";
            }

            System.out.printf("A vaga %s está ocupada \r\n", informacoes[0]);
            System.out.printf("Tipo do veículo: %s \r\n", veiculo);
            System.out.printf("Vaga ocupada desde as %sh e %smin \r\n", informacoes[2], informacoes[3]);

        } else {
            System.err.println("A vaga está liberada");
        }

    }

    public static void entrada(Scanner scanner) {

    }

    public static void saida(Scanner scanner) {

    }

    public static void ocupação(Scanner scanner) {

    }

    public static void financeiro(Scanner scanner) {

    }

    public static void salvarDados(Scanner scanner) {

    }

    // === Integrantes ===
    public static void integrantes(Scanner scanner) {
        int opcao = 99;
        while (opcao != 0) {
            System.out.println("+---------------------------------+");
            System.out.println("|           Integrantes           |");
            System.out.println("+---------------------------------+");
            System.out.println("|        Bernardo Aubim           |");
            System.out.println("|        Kalibe dos Reis          |");
            System.out.println("|        Helam Bello              |");
            System.out.println("|        Leonardo Fernandes       |");
            System.out.println("+---------------------------------+");
            System.out.println("Digite 0 para sair da tela de integrantes.");
            opcao = scanner.nextInt();
            System.out.print("\033\143");

        }
    }

}
