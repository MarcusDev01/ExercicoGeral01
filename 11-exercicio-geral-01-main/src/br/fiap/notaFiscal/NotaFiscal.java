package br.fiap.notaFiscal;

import br.fiap.cliente.Cliente;
import br.fiap.item.ItemProduto;


import java.util.ArrayList;
import java.util.List;

public class NotaFiscal {
    private List <ItemProduto>lista;
    private Cliente cliente;
    private boolean status;//true indica nota em aberto,compra em aberto
                                //false indica nota fechada, compra finalizada


    public NotaFiscal(Cliente cliente){
        this.lista=new ArrayList<>();
        this.cliente=cliente;
        this.status=true;
    }


    public void adicionarItem(ItemProduto item){
        lista.add(item);
    }

    public double calculaTotal(){
        double totalItens = 0;
       for(ItemProduto item : lista){
           totalItens+=item.calculaTotal();
       }

       return totalItens;
    }
    public void removerProduto(){

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
