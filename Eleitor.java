public class Eleitor {
    private int codigo;
    private String nome;
    private boolean apto;

    public Eleitor(int codigo, String nome, boolean apto)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.apto = apto;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isApto() {
        return this.apto;
    }

    public String toString() {
        return "Eleitor{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", apto=" + apto +
                '}';
    }
}
