import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TabelaEleitores {
    private final String CAMINHO_ARQUIVO = "arquivos/eleitores.txt";
    private Eleitor[] eleitores;
    private File arquivo;
    private int quantidadeEleitores = 0;


    public TabelaEleitores()
    {
        this.setArquivo();
        try{
            this.setQuantidadeEleitores();
            this.setEleitores();
            this.populaEleitores();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro, entre em contato com o suporte");
            System.out.println(e);
        }

    }
    private void setArquivo()
    {
        this.arquivo = new File(this.CAMINHO_ARQUIVO);
    }


    public void setQuantidadeEleitores() throws FileNotFoundException
    {
        Scanner leitor = new Scanner(this.arquivo);
        while(leitor.hasNextLine()) {
            leitor.nextLine();
            this.quantidadeEleitores++;
        }
        this.quantidadeEleitores--;
        leitor.close();
    }

    public int getQuantidadeEleitores()
    {
        return this.quantidadeEleitores;
    }

    private void setEleitores()
    {
        this.eleitores = new Eleitor[this.quantidadeEleitores];
    }

    private void populaEleitores() throws FileNotFoundException
    {
        Scanner leitor = new Scanner(this.arquivo);
        leitor.nextLine();
        while(leitor.hasNextLine())
        {
            String[] colunas = leitor.nextLine().split(",");

            int codigo = Integer.parseInt(colunas[0]);
            String nome = colunas[1];
            boolean apto = false;
            if(colunas[2].equals("Apto")) {
                apto = true;
            }
            for(int i = 0; i < this.eleitores.length; i++) {
                if(this.eleitores[i] == null) {
                    Eleitor eleitor = new Eleitor(codigo, nome, apto);
                    this.eleitores[i] = eleitor;
                    break;
                }
            }
        }
        leitor.close();
    }
    public Eleitor[] getEleitores()
    {
        return this.eleitores;
    }
    public void listarEleitores()
    {
        System.out.println("Codigo, Nome, Situacao");
        for(int i = 0; i < this.eleitores.length; i++) {
            System.out.printf(" %d,", this.eleitores[i].getCodigo());
            System.out.printf(" %s,",this.eleitores[i].getNome());
            String apto = (this.eleitores[i].isApto()) ? "Apto" : "Nao Apto";
            System.out.printf(" %s\n", apto);
        }
    }
    public void ordenarEleitoresAlfabetica()
    {
        int contador = this.eleitores.length -1;
        for(int j = 0; j < contador; j++) {
            for (int i = 0; i < contador - j; i++) {
                char primeiraLetraEleitor1 = this.eleitores[i].getNome().charAt(0);
                char primeiraLetraEleitor2 = this.eleitores[i + 1].getNome().charAt(0);
                char segundaLetraEleitor1 = this.eleitores[i].getNome().charAt(1);
                char segundaLetraEleitor2 = this.eleitores[i + 1].getNome().charAt(1);
                char terceiraLetraEleitor1 = this.eleitores[i].getNome().charAt(2);
                char terceiraLetraEleitor2 = this.eleitores[i + 1].getNome().charAt(2);
                if (primeiraLetraEleitor1 > primeiraLetraEleitor2) {
                    this.trocaOrdemEleitor(i);
                } else if (primeiraLetraEleitor1 == primeiraLetraEleitor2) {
                    if (segundaLetraEleitor1 > segundaLetraEleitor2) {
                        this.trocaOrdemEleitor(i);
                    } else if (segundaLetraEleitor1 == segundaLetraEleitor2) {
                        if (terceiraLetraEleitor1 > terceiraLetraEleitor2) {
                            this.trocaOrdemEleitor(i);
                        }
                    }
                }
            }
        }
    }

    private void trocaOrdemEleitor(int i) {
        Eleitor aux = this.eleitores[i];
        this.eleitores[i] = this.eleitores[i + 1];
        this.eleitores[i + 1] = aux;
    }

}
