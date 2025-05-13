package br.fiap.controle;
import br.fiap.cliente.Cliente;
import br.fiap.item.ItemProduto;
import br.fiap.notaFiscal.NotaFiscal;
import br.fiap.produto.Produto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

public class Controle {

    private static List<Produto>listaProduto=new ArrayList<>();
    private static List<Cliente>listaCliente=new ArrayList<>();
    private NotaFiscal nf;


    static {
        listaCliente.add(new Cliente(123,"Ana Maria"));
        listaCliente.add(new Cliente(456,"Jorge"));
        listaCliente.add(new Cliente(789,"Roberto Carlos"));

        listaProduto.add(new Produto(1,"camiseta",200,100));
        listaProduto.add(new Produto(2,"calça",2000,500));
        listaProduto.add(new Produto(1,"tenis",5000,100));
    }

    public  Controle(){
        Cliente cliente=pesquisar();
        nf=new NotaFiscal(cliente);
    }

    public void menu() {
        int opcao;
        while(true) {

            try{
                opcao = parseInt(showInputDialog(gerarMenu()));
                switch (opcao){
                    case 1:
                        adicionarItem();
                        break;
                    case 3:
                    finalizarCompra();
                    break;

                    case 4:
                        return;
                    default:
                        showMessageDialog(null,"opcap invalida");
                }

            }
            catch (NumberFormatException e){
                showMessageDialog(null, "a opçao deve ser um numero");
            }

        }
    }

    private void adicionarItem() {
        int quantidade;
        String produto=showInputDialog("qual o produto que sera comprado");
        for(Produto p:listaProduto){
            if(p.getNome().equalsIgnoreCase(produto)){
                quantidade=parseInt(showInputDialog("qual a quantidade q sera comprada"));
                if(p.getQuantidadeEmEstoque()>=quantidade){
                    nf.adicionarItem(new ItemProduto(p,quantidade));
                    p.debitarEstoque(quantidade);
                    return;
                }else{
                    showMessageDialog(null,"quantidade nao disponivel no estoque");
                }
            }
        }
    showMessageDialog(null,produto+"nao disponivel no estoque");


    }

    private Cliente pesquisar(){
        long cpf=parseLong(showInputDialog("Cpf do cliente"));
        for(Cliente cliente:listaCliente){
            if(cliente.getCpf()==cpf){
             return cliente;
            }
        }
        return null;

    }

    private String gerarMenu() {
        String aux = "SISTEMA DE COMPRAS ONLINE\n";
        aux += "1. Adicionar produto\n";
        aux += "2. Remover produto\n";
        aux += "3. Fechar compra\n";
        aux += "4. Finalizar compra\n";
        return aux;
    }
    private void finalizarCompra(){
        DecimalFormat df=new DecimalFormat("#,##0.00");
        showMessageDialog(null,"total da compra R$"+df.format(nf.calculaTotal()));
        nf.setStatus(true);
    }



}
