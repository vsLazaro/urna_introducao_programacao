public class Votacao {
    private int votosNulos = 0;
    private TabelaEleitores tabelaEleitores;
    private TabelaCandidatos tabelaCandidatos;
    private boolean votacaoTerminou = false;

    public Votacao(TabelaEleitores tabelaEleitores, TabelaCandidatos tabelaCandidatos)
    {
        this.tabelaCandidatos = tabelaCandidatos;
        this.tabelaEleitores = tabelaEleitores;
    }

    public void menu()
    {
        if (this.votacaoTerminou) {
            System.out.println("Votacao esta encerrada");
            return;
        }
        System.out.println("1 - Registrar novo voto");
        System.out.println("0 - Voltar");
        int key = Sistema.scannerInt();
        switch (key) {
            case 1:
                int codigoEleitor = this.identificarEleitor();
                boolean podeVotar = this.verificaEleitor(codigoEleitor);
                if(!podeVotar) {
                    this.mensagemErroEleitor();
                    this.menu();
                } else {
                    this.votar();
                    break;
                }
            case 0:
                break;
            default:
                break;

        }
    }

    private int identificarEleitor()
    {
        System.out.println("Digite seu código de eleitor:");
        return Sistema.scannerInt();
    }

    private boolean verificaEleitor(int codigo)
    {
        boolean achou = false;
        for(int i = 0; i < this.tabelaEleitores.getEleitores().length; i++) {
            if(this.tabelaEleitores.getEleitores()[i].getCodigo() == codigo && this.tabelaEleitores.getEleitores()[i].isApto()) {
                achou = true;
            }
        }
        return achou;
    }
    private void mensagemErroEleitor()
    {
        System.out.println("Código eleitor invalido ou nao esta apto a votar");
    }

    private void votar()
    {
        this.tabelaCandidatos.mostrarCandidatos();
        int codigoCandidato = this.identificarNumeroCandidato();
        boolean votou = this.validarVoto(codigoCandidato);
        if(votou) {
            System.out.println("Voto computado com sucesso");
        }
    }

    private int identificarNumeroCandidato()
    {
        System.out.println("Digite o numero do candidato: ");
        return Sistema.scannerInt();
    }

    private boolean validarVoto(int codigoCandidato)
    {
        boolean votou = false;
        for(int i = 0; i < this.tabelaCandidatos.getCandidatos().length; i++) {
            if (this.tabelaCandidatos.getCandidatos()[i].getCodigo() == codigoCandidato) {
                System.out.println("Candidato: " + this.tabelaCandidatos.getCandidatos()[i].getNome());
                boolean confirmou = this.confirmaVoto();
                if(!confirmou) {
                    this.votar();
                }
                this.computaVoto(i);
                votou = true;
            }
        }
        if(!votou) {
            System.out.println("Voce ira votar NULO");
            boolean confirmou = this.confirmaVoto();
            if (!confirmou){
                this.votar();
            }
            adicionarVotoNulo();
            votou = true;
        }
        return votou;

    }

    private boolean confirmaVoto()
    {
        System.out.println("Confirma o voto? (S/N)");
        String retorno = Sistema.scannerString();
        if(retorno.equals("S") || retorno.equals("s")) {
            return true;
        }
        return false;
    }

    private void computaVoto(int codigoCandidato)
    {
        this.tabelaCandidatos.getCandidatos()[codigoCandidato].adicionarVoto();
    }

    private void adicionarVotoNulo()
    {
        this.votosNulos++;
    }

    public void encerrarVotacao()
    {
        this.votacaoTerminou = true;
        System.out.println("Votacao encerrada!!");
    }

    public int totalVotos()
    {
        int soma = 0;
        for (int i = 0; i < this.tabelaCandidatos.getCandidatos().length; i++) {
            soma = soma + this.tabelaCandidatos.getCandidatos()[i].getVotos();
        }
        soma = soma + this.votosNulos;
        return soma;
    }

    public int totalVotosValidos()
    {
        int soma = 0;
        for (int i = 0; i < this.tabelaCandidatos.getCandidatos().length; i++) {
            soma = soma + this.tabelaCandidatos.getCandidatos()[i].getVotos();
        }
        return soma;
    }

    public TabelaCandidatos getTabelaCandidatos() {
        return this.tabelaCandidatos;
    }

    public boolean isVotacaoTerminou() {
        return this.votacaoTerminou;
    }

    public int getVotosNulos() {
        return this.votosNulos;
    }
}
