
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kinha
 */
public class Pedido {
    public int id;
    public Date dataEntrega;
    public String item;
    public Double valorTotal;
    public int qtd;
    
    public void id(){
    id++;
    }
 
    public Date getdataEntrega() {
        return dataEntrega;
    }

    public void setDataAtualizacao(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    public String getitem(){
        return item;
    
    }
    
    public void setitem(String item){
        this.item = item;
    
    }
    public double getvalorTotal(){
        return valorTotal;
    
    }    
    public void setvalorTotal(Double valorTotal){
        this.valorTotal = valorTotal;
    
    }    
    
    public int getqtd(){
        return qtd;
    
    }    
    public void setqtd(int qtd){
        this.qtd = qtd;
    
    }
}
