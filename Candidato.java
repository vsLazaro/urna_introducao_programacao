public class Candidato {
    private int codigo;
    private String nome;
    private String partido;
    private int votos = 0;

    public Candidato(int codigo, String nome, String partido)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.partido = partido;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getPartido() {
        return this.partido;
    }

    public String toString() {
        return "Candidato{" +
                "codigo=" + this.codigo +
                ", nome='" + this.nome + '\'' +
                ", partido='" + this.partido + '\'' +
                '}';
    }

    public void adicionarVoto()
    {
        this.votos++;
    }
    public int getVotos()
    {
        return this.votos;
    }
}
