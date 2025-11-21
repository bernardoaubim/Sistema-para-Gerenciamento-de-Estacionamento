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
    public static String vaga = "";

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

        estacionamento[0][1] = "C:08:15";
        estacionamento[1][2] = "V:12:34";
        estacionamento[2][4] = "M:15:03";
        estacionamento[3][1] = "C:21:00";

        // 4. Impressão da matriz com formatação.
        // for (int i = 0; i < corredores; i++) {
        // for (int j = 0; j < colunas; j++) {
        // System.out.printf("%s\t", estacionamento[i][j]);
        // }
        // System.out.println();
        // }
    }

    // devolve um array para futuramente poder usar ele em outras funcionalidades
    public static int[] converterVagaParaIndices(String vaga) {

        if (vaga == null || vaga.length() < 2) {
            return null;
        }

        vaga = (vaga.toUpperCase()).replace(" ", "");

        int letraVaga = vaga.charAt(0);
        int linhaLocal = letraVaga - 'A';

        try {
            String numeroVaga = vaga.substring(1);
            int colunaLocal = Integer.parseInt(numeroVaga) - 1;

            // verifica se esta dentro dos limites
            if (linhaLocal >= 0 && linhaLocal < corredores && colunaLocal >= 0 && colunaLocal < colunas) {
                return new int[] { linhaLocal, colunaLocal };
            } else {
                return null;
            }
        } catch (Exception e) {
            return null; // caso insira um fomato invalido tipo "AX"
        }

    }

    public static String ConverterIndicesParaVaga(int linha, int coluna) {
        char letra = (char) (linha + 'A');
        int numero = coluna + 1;
        return String.format("%c%d", letra, numero);
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
        String opcao = "";
        while (true) {
            if (opcao.equals("0")) {
                System.out.print("\033\143");
                break;
            }
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
            if (!estacionamento[linhaParaOcupar][colunaParaOcupar].equals(".")) {
                // talvez transforme isso em uma função para reutilizar em uma futura
                // funcionalidade
                String[] informacoes = estacionamento[linhaParaOcupar][colunaParaOcupar].split(":");
                String veiculo = "veiculo";
                if (informacoes[0].equals("C")) {
                    veiculo = "Carro";
                } else if (informacoes[0].equals("V")) {
                    veiculo = "Van";
                } else if (informacoes[0].equals("M")) {
                    veiculo = "Moto";
                }

                System.out.printf("A vaga %s está ocupada \r\n", vaga);
                System.out.printf("Tipo do veículo: %s \r\n", veiculo);
                System.out.printf("Vaga ocupada desde as %sh e %smin \r\n", informacoes[1], informacoes[2]);

            } else {
                System.err.println("A vaga está liberada");
            }
            System.out.println("Digite 0 para sair da tela ou qualquer outro valor para continuar a consulta");
            opcao = scanner.nextLine();
        }

    }

    public static void entrada(Scanner scanner) {
        char tipo = ' ';
        int horas = 0;
        int minutos = 0;
        Boolean validacao = false;
        while (validacao == false) {
            System.out.print("Informe o tipo de veículo (M/C/V): ");
            tipo = scanner.nextLine().toUpperCase().charAt(0);
            if (tipo == 'M' || tipo == 'C' || tipo == 'V') {
                validacao = true;
            } else {
                System.out.println("Tipo de veículo inválido.");
                validacao = false;
            }
        }

        while (validacao == true) {
            validacao = false;
            System.out.println("Infome a hora de entrada no formato hh:mm");
            String horario = scanner.nextLine();
            try {
                String[] horaMinuto = horario.split(":");
                horas = Integer.parseInt(horaMinuto[0]);
                minutos = Integer.parseInt(horaMinuto[1]);
                if (horas > 23 || horas < 0 || minutos > 59 || minutos < 0) {
                    System.out.println("Horário inválido");
                    validacao = true;
                }
            } catch (Exception e) {
                System.out.println("Formato inválido");
                validacao = true;
            }
        }
        int opcao = 0;
        System.out.println("+---------------------------------------+");
        System.out.println("| Selecione como deseja escolher a vaga |");
        System.out.println("+---------------------------------------+");
        System.out.println("| Opção 1 - Vaga Exata                  |");
        System.out.println("| Opção 2 - Por Corredor                |");
        System.out.println("| Opção 3 - Automático                  |");
        System.out.println("+---------------------------------------+");
        opcao = scanner.nextInt();
        scanner.nextLine();
        String informacoes = String.format("%c:%02d:%02d", tipo, horas, minutos);
        String vagaLocal = "";
        switch (opcao) {
            case 1:
                System.out.println("Informe a vaga desejada");
                String vaga1 = scanner.nextLine();
                int[] indices = converterVagaParaIndices(vaga1);
                while (indices == null) {
                    System.out.println("Vaga inválida");
                    System.out.println("Informe uma vaga válida ");
                    vaga1 = scanner.nextLine();
                    indices = converterVagaParaIndices(vaga1);
                }
                int linhaParaOcupar = indices[0];
                int colunaParaOcupar = indices[1];
                if (estacionamento[linhaParaOcupar][colunaParaOcupar].equals(".")) {
                    estacionamento[linhaParaOcupar][colunaParaOcupar] = informacoes;
                    vagaLocal = ConverterIndicesParaVaga(linhaParaOcupar, colunaParaOcupar);
                    System.out.printf("A entrada na vaga %s foi validada \r\n", vagaLocal);

                } else {
                    System.out.println("A vaga desejada já está ocupada");
                }
                break;

            case 2:
                System.out.println("Informe o corredor desejado");
                char corredor = scanner.nextLine().toUpperCase().charAt(0);
                int linha = corredor - 'A';
                for (int j = 0; j < colunas; j++) {
                    if (estacionamento[linha][j].equals(".")) {
                        estacionamento[linha][j] = informacoes;
                        vagaLocal = ConverterIndicesParaVaga(linha, j);
                        validacao = true;
                        System.out.printf("A entrada na vaga %s foi validada \r\n", vagaLocal);
                        break;
                    }
                }
                if (validacao == false) {
                    System.out.println("Não há nenhuma vaga livre");
                }
                break;

            case 3:
                validacao = false;
                procurarVaga: for (int i = 0; i < corredores; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if (estacionamento[i][j].equals(".")) {
                            estacionamento[i][j] = informacoes;
                            vagaLocal = ConverterIndicesParaVaga(i, j);
                            validacao = true;
                            System.out.printf("A entrada na vaga %s foi validada \r\n", vagaLocal);
                            break procurarVaga;

                        }
                    }
                }
                if (validacao == false) {
                    System.out.println("Não há nenhuma vaga livre");
                }
                break;

            default:
                break;
        }

    }

    public static void saida(Scanner scanner) {

    }

    public static void ocupação(Scanner scanner) {
        int opcao = 99;
        while (opcao != 0) {
            int quantidadeCarros = 0;
            int quantidadeMotos = 0;
            int quantidadeVans = 0;
            int vagasTotais = 0;
            int vagasOcupadas = 0;
            int vagasLivres = 0;
            String[][] mapaEstacionamento = new String[corredores][colunas];
            for (int i = 0; i < corredores; i++) {
                for (int j = 0; j < colunas; j++) {
                    vagasTotais++;
                    String vagaAtual = estacionamento[i][j];
                    if (vagaAtual.equals(".")) {
                        mapaEstacionamento[i][j] = ".";
                    } else {
                        vagasOcupadas++;

                        String[] informacao = vagaAtual.split(":");
                        String tipoVeiculo = informacao[0];

                        mapaEstacionamento[i][j] = tipoVeiculo;

                        if (tipoVeiculo.equals("C")) {
                            quantidadeCarros++;
                        } else if (tipoVeiculo.equals("M")) {
                            quantidadeMotos++;
                        } else {
                            quantidadeVans++;
                        }
                    }
                }
            }

            vagasLivres = vagasTotais - vagasOcupadas;

            double porcetagemOcupacaoMotos = 0;
            int barrasGraficoMoto = 0;
            double porcetagemOcupacaoCarros = 0;
            int barrasGraficoCarros = 0;
            double porcetagemOcupacaoVans = 0;
            int barrasGraficoVans = 0;

            if (vagasOcupadas > 0) {
                porcetagemOcupacaoMotos = (double) (quantidadeMotos * 100) / vagasOcupadas;
                barrasGraficoMoto = (int) porcetagemOcupacaoMotos / 5;

                porcetagemOcupacaoCarros = (double) (quantidadeCarros * 100) / vagasOcupadas;
                barrasGraficoCarros = (int) porcetagemOcupacaoCarros / 5;

                porcetagemOcupacaoVans = (double) (quantidadeVans * 100) / vagasOcupadas;
                barrasGraficoVans = (int) porcetagemOcupacaoVans / 5;
            }

            double porcetagemOcupacao = (double) (vagasOcupadas * 100) / vagasTotais;
            int barrasGraficosOcupados = (int) porcetagemOcupacao / 5;

            double porcetagemLivre = (double) (vagasLivres * 100) / vagasTotais;
            int barrasGraficosLivres = (int) porcetagemLivre / 5;

            System.out.printf(" \t");
            for (int n = 1; n <= colunas; n++) {
                System.out.printf("%02d\t", n);
            }
            System.out.println();

            for (int i = 0; i < corredores; i++) {
                char letra = (char) (i + 'A');
                System.out.printf("%c\t", letra);
                for (int j = 0; j < colunas; j++) {

                    System.out.printf("%s\t", mapaEstacionamento[i][j]);
                }
                System.out.println("\r\n");
            }

            imprimirLinhaGrafico("Moto", quantidadeMotos, porcetagemOcupacaoMotos, barrasGraficoMoto, vagasOcupadas);
            imprimirLinhaGrafico("Carro", quantidadeCarros, porcetagemOcupacaoCarros, barrasGraficoCarros,
                    vagasOcupadas);
            imprimirLinhaGrafico("Van", quantidadeVans, porcetagemOcupacaoVans, barrasGraficoVans, vagasOcupadas);

            System.out.println("------------------------------------------|");

            imprimirLinhaGrafico("Ocupadas", vagasOcupadas, porcetagemOcupacao, barrasGraficosOcupados, vagasTotais);
            imprimirLinhaGrafico("Livres", vagasLivres, porcetagemLivre, barrasGraficosLivres, vagasTotais);

            System.out.println("Digite 0 para sair da tela de ocupação");
            opcao = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("\033\143");
    }

    public static void imprimirLinhaGrafico(String veiculo, int quantidade, double porcentagem, int barras, int total) {

        String barra = "=".repeat(barras) + ".".repeat(20 - barras);

        System.out.printf(
                "%-8s: %2d - %2.1f%% |%s| (%2d vagas de %2d) \r\n",
                veiculo,
                quantidade,
                porcentagem,
                barra,
                quantidade,
                total);
    }

    public static void financeiro(Scanner scanner) {
System.out.println("\n---  Relatório Financeiro  ---");

        if (transacoes.isEmpty()) {
            System.out.println("Nenhum veículo saiu do estacionamento ainda.");
            System.out.println("\nPressione Enter para voltar ao menu...");
            scanner.nextLine();
            return;
        }

        double totalMoto = 0, totalCarro = 0, totalVan = 0;
        int countMoto = 0, countCarro = 0, countVan = 0;

        // Processa a lista de transações
        for (String t : transacoes) {
            String[] partes = t.split(":");
            
            if (partes.length == 2) {
                String tipo = partes[0];
                double valor = Double.parseDouble(partes[1]);

                switch (tipo) {
                    case "M":
                        countMoto++;
                        totalMoto += valor;
                        break;
                    case "C":
                        countCarro++;
                        totalCarro += valor;
                        break;
                    case "V":
                        countVan++;
                        totalVan += valor;
                        break;
                }
            }
        }

        double valorTotalGeral = totalMoto + totalCarro + totalVan;
        int quantTotalGeral = countMoto + countCarro + countVan;

        [cite_start]// Exibe a tabela 
        System.out.println("+-----------------------------------------+");
        System.out.printf("| %-10s | %-10s | %-12s |\n", "Veículo", "Quant.", "Valor (R$)");
        System.out.println("+-----------------------------------------+");
        System.out.printf("| %-10s | %-10d | R$ %-10.2f |\n", "Moto", countMoto, totalMoto);
        System.out.printf("| %-10s | %-10d | R$ %-10.2f |\n", "Carro", countCarro, totalCarro);
        System.out.printf("| %-10s | %-10d | R$ %-10.2f |\n", "Van", countVan, totalVan);
        System.out.println("+-----------------------------------------+");
        System.out.printf("| %-10s | %-10d | R$ %-10.2f |\n", "Total", quantTotalGeral, valorTotalGeral);
        System.out.println("+-----------------------------------------+");

        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    public static void salvarDados(Scanner scanner) {
     System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.print("O arquivo não existe, deseja criar ele? (sim/não)");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("sim") || resposta.equalsIgnoreCase("s")) {
                try {
                    arquivo.createNewFile();
                    System.out.print("Arquivo criado com sucesso!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }

            else {
        System.out.println("Operação cancelada.");
        return;
           }
        }

        
            try {
            FileWriter writer = new FileWriter( arquivo, true); 
            BufferedWriter buffer = new BufferedWriter(writer);
            

            for (int i = 0; i <corredores; i++) {
                for (int j = 0; j < colunas; j ++) {
                    if (!estacionamento[i][j].equals(".")) {
                        String vaga = ConverterIndicesParaVaga(i, j);
                        buffer.write(vaga + "=" + estacionamento[i][j]);
                        buffer.newLine();
                    }
                }
            }
            
            
        
        buffer.close(); // fecha o buffer (e salva tudo)
        System.out.println("Dados salvos com sucesso em " + nomeArquivo);

             } catch (IOException e) {
        System.out.println("Erro ao salvar os dados: " + e.getMessage());
    }
        }
        
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
