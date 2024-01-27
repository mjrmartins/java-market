import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    //variaveis globais
    public static Scanner input_text = new Scanner(System.in);
    public static Scanner input_number = new Scanner(System.in);
    public static DecimalFormat df2 = new DecimalFormat("0.00");
    public static String[] produto = new String[1000];
    public static double[] preco = new double[1000];
    public static int total = 0;
    public static final int TEMPO = 2500;
    public static String vendas = "";
    public static double total_vendas = 0.0;
    public static int codigo;

    public static void main(String[] args) {
        int opcao;

        // A funcao seguinte (_temp()) pode ser ativada como forma de reduzir o tempo de registo de produtos
        //_temp();
        do {
            exibirMenu();
            System.out.print("\nOpcao: ");
            opcao = input_number.nextInt();
            limpa();

            switch (opcao){
                case 1: registarProduto(); break;
                case 2: editar(); break;
                case 3: pesquisar(); break;
                case 4: listarTodos(); break;
                case 5: registarVenda(); break;
                case 6: listarVendas(); break;
                case 7: apagarProduto(); break;
                case 0: sair(); break;

                default:
                    System.out.println("--- Opcao invalida ---");
                    aguarde(TEMPO);
                    break;
            }
            limpa();

        }while(opcao != 0);

    }

    //funcoes
    public static void exibirMenu(){
        System.out.println("\n\t***** MERCADO *****\n");
        System.out.println("1 - Registar produto");
        System.out.println("2 - Editar produto");
        System.out.println("3 - Pesquisar produto por baliza de precos");
        System.out.println("4 - Listar todos os produtos");
        System.out.println("\n5 - Registar venda");
        System.out.println("6 - Listar todas as vendas");
        System.out.println("7 - Apagar registo de produto");
        System.out.println("\n0 - Sair");
    }

    public static void registarProduto(){
        String produto_temp;
        double preco_temp;

        System.out.println("\t>>> Registo de Produtos <<<\n");
        System.out.print("Nome do produto: ");
        produto_temp = input_text.nextLine();
        System.out.print("Valor do produto: ");
        preco_temp = input_number.nextDouble();

        for(int b = 0; b < produto.length; b++){
            if(produto_temp.equals(produto[b])){
                System.out.println("\nEste produto ja se encontra registado.");
                aguarde(1500);
                break;
            }
            else{
                aguarde(300);
                System.out.println("\nSUCESSO!");
                produto[total] = produto_temp;
                preco[total] = preco_temp;
                total++;
                break;
            }
        }
        enter();
    }

    public static void editar(){
        if(total > 0){
            System.out.println("\t>>> Editar Registo de Produtos <<<\n");
            System.out.println("--- Lista de Produtos ---");
            for(int j = 0; j < total; j++){
                System.out.println("(" + j + ") - {" + produto[j] + "} [" + df2.format(preco[j]) + " €]");
            }
            System.out.print("\nDigite o codigo do produto a ser editado: ");
            codigo = input_number.nextInt();

            if(codigo >= 0 && codigo < total){
                System.out.println("\n(" + codigo + ") - {" + produto[codigo] + "} [" + df2.format(preco[codigo]) + " €]\n");
                System.out.print("Indique o nome do novo produto: ");
                produto[codigo] = input_text.nextLine();
                System.out.print("Indique o valor do novo produto: ");
                preco[codigo] = input_number.nextDouble();
                System.out.println("\nSUCESSO!");
                enter();
            }
            else{
                System.out.println("Codigo invalido. Deve digitar um codigo entre 0 e " + (total-1));
                aguarde(TEMPO);
            }
        }
        else{
            System.out.println("Nao existem produtos registados");
            enter();
        }
    }

    public static void listarTodos(){
        if(total > 0){
            System.out.println("\t>>> Lista de Produtos <<<\n");
            for(int j = 0; j < total; j++){
                System.out.println("(" + j + ") - {" + produto[j] + "} [" + df2.format(preco[j]) + " €]");
            }
            enter();
        }

        else{
            System.out.println("Nao existem produtos registados");
            enter();
        }
    }

    public static void registarVenda(){
        int quantidade;
        double fatura;

        if(total > 0){
            System.out.println("\t>>> Registo de Vendas de Produtos <<<\n");
            System.out.println("--- Lista de Produtos ---");
            for(int j = 0; j < total; j++){
                System.out.println("(" + j + ") - {" + produto[j] + "} [" + df2.format(preco[j]) + " €]");
            }
            System.out.print("\nDigite o codigo do produto a ser vendido: ");
            codigo = input_number.nextInt();

            if(codigo >= 0 && codigo < total){
                System.out.println("\n(" + codigo + ") - {" + produto[codigo] + "} [" + df2.format(preco[codigo]) + " €]\n");
                System.out.print("Indique a quantidade a ser vendida deste produto: ");
                quantidade = input_number.nextInt();
                fatura = preco[codigo] * quantidade;
                System.out.println("{" + produto[codigo] + "} [" + df2.format(preco[codigo]) + " €] x " + quantidade + " = [" + df2.format(fatura) + " €]");
                System.out.println("\nSUCESSO!");
                vendas += "(" + produto[codigo] + ") {" + df2.format(preco[codigo]) + " €} x " + quantidade + " = [" + df2.format(fatura) + " €]\n";
                total_vendas += fatura;
                enter();
            }
            else{
                System.out.println("Codigo invalido. Deve digitar um codigo entre 0 e " + (total-1));
                aguarde(TEMPO);
            }
        }

        else{
            System.out.println("Nao existem produtos registados");
            enter();
        }
    }

    public static void pesquisar(){
        double minimo, maximo;
        int cont = 0;

        if(total > 0){
            System.out.println("\t>>> Pesquisa de Produtos (por baliza de precos) <<<\n");
            System.out.print("Indique o valor minimo: ");
            minimo = input_number.nextDouble();
            System.out.print("Indique o valor maximo: ");
            maximo = input_number.nextDouble();
            limpa();
            System.out.println("\t>>> Pesquisa de Produtos (por baliza de precos) <<<\n");

            if(minimo < 0){
                System.out.println("Deve indicar um valor minimo igual ou superior a zero (0).");
            }
            else{
                for(int d = 0; d < total; d++){
                    if(preco[d] >= minimo && preco[d] <= maximo){
                        System.out.println("(" + d + ") - {" + produto[d] + "} [" + df2.format(preco[d]) + " €]");
                    }
                    else{
                        cont++;
                    }
                }
                if(cont == total){
                    System.out.println("Nada a apresentar. Tente outra baliza de precos.");
                }
            }
            enter();
        }

        else{
            System.out.println("Nao existem produtos registados");
            enter();
        }
    }

    public static void listarVendas(){
        System.out.println("\t>>> Lista de Vendas <<<\n");
        System.out.println(vendas);
        System.out.println("\nSaldo total das vendas: [" + df2.format(total_vendas) + " €]");
        enter();
    }

    public static void apagarProduto(){
        char resposta;

        System.out.println("\t>>> Apagar Produto <<<\n");
        if(total > 0){
            System.out.println("--- Lista de Produtos ---");
            for(int j = 0; j < total; j++){
                System.out.println("(" + j + ") - {" + produto[j] + "} [" + df2.format(preco[j]) + " €]");
            }
            System.out.print("\nDigite o codigo do produto a ser apagado: ");
            codigo = input_number.nextInt();

            if(codigo >= 0 && codigo < total){
                System.out.println("(" + codigo + ") - {" + produto[codigo] + "} [" + df2.format(preco[codigo]) + " €]");
                System.out.print("Deseja apagar este produto? s (sim) ou n (nao) ");
                resposta = input_text.next().charAt(0);
                if(resposta == 's'){
                    aguarde(1000);
                    System.out.println("SUCESSO! O registo foi apagado.");
                    for(int m = codigo; m < total-1; m++){
                        produto[m] = produto[m+1];
                        preco[m] = preco[m+1];
                    }
                    produto[total-1] = "";
                    preco[total-1] = 0;
                    total--;

                    aguarde(TEMPO);
                }

                else{
                    aguarde(1000);
                    System.out.print("A regressar ao menu");
                    for(int x = 0; x < 3; x++){
                        System.out.print(".");
                        aguarde(350);
                    }
                    aguarde(500);
                }
            }

            else{
                System.out.println("Codigo invalido. Deve digitar um codigo entre 0 e " + (total-1));
                aguarde(TEMPO);
            }
        }

        else{
            System.out.println("Nao existem produtos registados");
            enter();
        }
    }

    public static void sair(){
        System.out.print("A sair");
        for(int i = 0; i < 3; i++){
            System.out.print(".");
            aguarde(200);
        }
    }

    public static void limpa(){
        for(int i=0; i<25; i++){
            System.out.println();
        }
    }

    public static void aguarde(int ms){
        try{
            Thread.sleep(ms);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void enter(){
        System.out.println("\nPressione <ENTER> para continuar...");
        input_text.nextLine();
    }

    public static void _temp(){
        produto[0] = "pao";
        preco[0] = 3.2;
        total++;

        produto[1] = "banana";
        preco[1] = 5.2;
        total++;

        produto[2] = "queijo";
        preco[2] = 8.0;
        total++;

        produto[3] = "azeitonas";
        preco[3] = 6.1;
        total++;
    }
}