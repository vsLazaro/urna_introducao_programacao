import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class GeradorResultados {
    private String caminhoArquivoResultado = "resultados/resultado.txt";
    private final String CAMINHO_ARQUIVO_RESULTADO_NOVO = "resultados/resultado";
    public void exibirResultados(Votacao votacao)
    {
        if(!votacao.isVotacaoTerminou()) {
            System.out.println("Votacao ainda nao esta encerrada!");
            return;
        }
        System.out.format("%-10s %-26s %-29s %-10s %s \n","Codigo","Candidato","Partido","Votos","Votos Validos");
        for (int i = 0; i < votacao.getTabelaCandidatos().getCandidatos().length; i++) {
            int codigo = votacao.getTabelaCandidatos().getCandidatos()[i].getCodigo();
            String nome = votacao.getTabelaCandidatos().getCandidatos()[i].getNome();
            String partido = votacao.getTabelaCandidatos().getCandidatos()[i].getPartido();
            int votos = votacao.getTabelaCandidatos().getCandidatos()[i].getVotos();
            double votosEmPorcentagem = (votos*100) / votacao.totalVotosValidos();
            System.out.format("%-10d %-25s %-30s %-10d %.2f%% \n", codigo, nome, partido, votos, votosEmPorcentagem);
        }
        System.out.format("%43s %25d\n","NULOS", votacao.getVotosNulos());
        System.out.format("%49s %19d\n","Total Votos", votacao.totalVotos());
        System.out.format("%51s %17d\n","Votos Validos", votacao.totalVotosValidos());
    }

    public void gerarArquivoResultado(Votacao votacao) throws FileNotFoundException
    {
        if(!votacao.isVotacaoTerminou()) {
            System.out.println("Votacao ainda nao esta encerrada!");
            return;
        }
        try
        {
            File arquivo = new File(this.caminhoArquivoResultado);
            boolean criou = arquivo.createNewFile();
            if(!criou) {
                for (int i = 1; i <= 10; i++) {
                    arquivo = new File(this.CAMINHO_ARQUIVO_RESULTADO_NOVO + i +".txt");
                    if(arquivo.createNewFile()) {
                        this.caminhoArquivoResultado = this.CAMINHO_ARQUIVO_RESULTADO_NOVO + i +".txt";
                        criou = true;
                        break;
                    }
                }
            }
            if(!criou) {
                System.out.println("Limite de arquivos de votações já atingida");
                return;
            }
            FileWriter myWriter = new FileWriter(this.caminhoArquivoResultado);
            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss");
            String dataFormatada = data.format(formatacao);

            myWriter.write("Data/Hora: " + dataFormatada +"\n");
            for (int i = 0; i < votacao.getTabelaCandidatos().getCandidatos().length; i++) {
                int codigo = votacao.getTabelaCandidatos().getCandidatos()[i].getCodigo();
                String nome = votacao.getTabelaCandidatos().getCandidatos()[i].getNome();
                String partido = votacao.getTabelaCandidatos().getCandidatos()[i].getPartido();
                int votos = votacao.getTabelaCandidatos().getCandidatos()[i].getVotos();
                myWriter.write(codigo + "," + nome + "," + partido + "," + votos + "\n");
            }
            myWriter.write("000,NULO,NULO," + votacao.getVotosNulos());
            myWriter.close();
            System.out.println("Arquivo gerado com sucesso");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
