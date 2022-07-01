import java.io.IOException;
import java.util.Scanner;
public class Sistema {
    public static void main(String[] args)
    {
        menu();
    }

    public static void menu()
    {
        TabelaCandidatos tabelaCandidatos = new TabelaCandidatos();
        TabelaEleitores tabelaEleitores = new TabelaEleitores();
        Votacao votacao = new Votacao(tabelaEleitores, tabelaCandidatos);
        GeradorResultados geradorResultados = new GeradorResultados();
        boolean exit = false;

        do {
            opcoesMenu();
            int key = scannerInt();
            switch (key) {
                case 1:
                    votacao.menu();
                    break;

                case 2:
                    votacao.encerrarVotacao();
                    break;

                case 3:
                    tabelaEleitores.listarEleitores();
                    break;

                case 4:
                    tabelaEleitores.ordenarEleitoresAlfabetica();
                    tabelaEleitores.listarEleitores();
                    break;

                case 5:
                    geradorResultados.exibirResultados(votacao);
                    break;

                case 6:
                    try {
                        geradorResultados.gerarArquivoResultado(votacao);
                    } catch (IOException e) {
                        System.out.println("Erro ao criar seu arquivo de resultados");
                        System.out.println(e);
                    }

                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    exit = true;
                    break;
            }
        } while (!exit);


    }

    private static void opcoesMenu() {
        System.out.println("1-Iniciar votacao");
        System.out.println("2-Encerrar votacao");
        System.out.println("3-Listar eleitores em tela");
        System.out.println("4-Listar eleitores em tela (ordem alfabetica)");
        System.out.println("5-Imprimir resultados na tela");
        System.out.println("6-Gerar arquivo com resultados");
        System.out.println("0-Sair");
        System.out.print("Escolha sua opcao: ");
    }

    public static int scannerInt() {
        Scanner in = new Scanner(System.in);
        int retornoDeInt = in.nextInt();
        return retornoDeInt;
    }

    public static String scannerString() {
        Scanner in = new Scanner(System.in);
        String retornoDeString = in.nextLine();
        return retornoDeString;
    }
}
