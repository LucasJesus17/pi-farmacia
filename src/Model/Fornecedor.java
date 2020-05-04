/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author kinha
 */
public class Fornecedor {

    public String fornecedor;
    public String CPF;
    public int telefone;
    public String item;
    public Double valorTotal;
    public int qtd;
    public Date dataEntrega;
    
    


     public String getifornecedor(){
        return item;
    
    }
    
    public void setfornecedor(String fornecedor){
        this.fornecedor = fornecedor;
    
    }
    public String getCPF(){
        return CPF;

    
    }
    
    public void setCPF(String CPF){
        this.CPF = CPF;
 
    }
    
    public int gettelefone(){
        return telefone;
    
    }
    
    public void settelefone(String CPF){
        this.CPF = CPF;
    
    }
    
    
    public Date getdataEntrega() {
        return dataEntrega;
    }

    public void setdataEntrega(Date dataEntrega) {
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
     private String cpf;
    private static final String Formato = "###.###.###-##";
    
    public Fornecedor(String C) {
        this.cpf = this.Format(C,false);
    }
    
    
    public boolean isCPF(){
        
        if (this.cpf.equals("00000000000") || 
            this.cpf.equals("11111111111") || 
            this.cpf.equals("22222222222") || 
            this.cpf.equals("33333333333") ||
            this.cpf.equals("44444444444") ||
            this.cpf.equals("55555555555") ||
            this.cpf.equals("66666666666") ||
            this.cpf.equals("77777777777") ||
            this.cpf.equals("88888888888") ||
            this.cpf.equals("99999999999") ||
            this.cpf.length() != 11)
            return(false);
        
        char dig10, dig11; 
        int sm, i, r, num, peso; 

        try { 
            // Calculo do primeiro Digito Verificador 
            sm = 0; 
            peso = 10; 
            for (i=0; i<9; i++) { 
                num = (int)(this.cpf.charAt(i) - 48); 
                sm = sm + (num * peso); 
                peso = peso - 1;
            } 
            r = 11 - (sm % 11); 
            if ((r == 10) || (r == 11)) 
                dig10 = '0'; 
            else 
                dig10 = (char)(r + 48); 

            // Calculo do segundo Digito Verificador 
            sm = 0; 
            peso = 11; 
            for(i=0; i<10; i++) { 
                num = (int)(this.cpf.charAt(i) - 48);
                sm = sm + (num * peso); 
                peso = peso - 1;
            } 
            r = 11 - (sm % 11); 
            if ((r == 10) || (r == 11)) 
                dig11 = '0'; 
            else 
                dig11 = (char)(r + 48); 

            if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10))) 
                return(true); 
            else return(false);
        } catch(Exception e) { 
            return(false); 
        } 
    }

    public String getCPF(boolean Mascara) {
        return Format(this.cpf,Mascara);
    }

    private String Format(String C, boolean Mascara){
        if(Mascara){
            return(C.substring(0, 3) + "." + C.substring(3, 6) + "." +
            C.substring(6, 9) + "-" + C.substring(9, 11));
        }else{
            C = C.replace(".","");
            C = C.replace("-","");
            return C;
        }
}
}