import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TabelaCandidatos {
    private final String CAMINHO_ARQUIVO = "arquivos/candidatos.txt";
    private Candidato[] candidatos;
    private File arquivo;
    private int quantidadeCandidatos = 0;

    public TabelaCandidatos()
    {
        this.setArquivo();
        try {
            this.setQuantidadeCandidatos();
            this.setCandidatos();
            this.populaCandidatos();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro, entre em contato com o suporte");
            System.out.println(e);
        }
    }

    private void setArquivo()
    {
        this.arquivo = new File(this.CAMINHO_ARQUIVO);
    }

    private void setQuantidadeCandidatos() throws FileNotFoundException
    {
        Scanner leitor = new Scanner(this.arquivo);
        while(leitor.hasNextLine()) {
            leitor.nextLine();
            this.quantidadeCandidatos++;
        }
        this.quantidadeCandidatos--;
        leitor.close();
    }

    public int getQuantidadeCandidatos() {
        return this.quantidadeCandidatos;
    }

    private void setCandidatos()
    {
        this.candidatos = new Candidato[this.quantidadeCandidatos];
    }

    private void populaCandidatos() throws FileNotFoundException
    {
        Scanner leitor = new Scanner(this.arquivo);
        leitor.nextLine();
        while(leitor.hasNextLine())
        {
            String[] colunas = leitor.nextLine().split(",");

            int codigo = Integer.parseInt(colunas[0]);
            String nome = colunas[1];
            String partido = colunas[2];
            for(int i = 0; i < this.candidatos.length; i++) {
                if(this.candidatos[i] == null) {
                    Candidato candidato = new Candidato(codigo, nome, partido);
                    this.candidatos[i] = candidato;
                    break;
                }
            }
        }
        leitor.close();
    }

    public Candidato[] getCandidatos()
    {
        return this.candidatos;
    }

    public void mostrarCandidatos()
    {
        System.out.println("Candidatos:\n ----------------------------");
        System.out.println("Codigo, Nome");
        for(int i = 0; i< this.candidatos.length; i++) {
            System.out.printf("%d, %s \n", this.candidatos[i].getCodigo(), this.candidatos[i].getNome());
        }
    }
}
