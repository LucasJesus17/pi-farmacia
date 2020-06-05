/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.GerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kinha
 */
public class Fornecedor {

    public int id;
    public String nome;
    public String CPF;
    public int telefone;
    public String item;
    public Double valorTotal;
    public int qtd;
    public Date dataEntrega;

    private Fornecedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getnome(){
        return item;
    
    }
    
    public void setnome(String nome){
        this.nome = nome;
    
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
    
    public void settelefone(int telefone){
        this.telefone = telefone;
    
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
    public void setqtd(int qtd){
    this.qtd = qtd;
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
  
   public static boolean excluir(Fornecedor p) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
            instrucaoSQL.setInt(1, p.getId());
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if (linhasAfetadas > 0)
                retorno = true;
            else
                retorno = false;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            GerenciadorConexao.fecharConexao(conexao, instrucaoSQL);
        }
        return retorno;
    }
    
    public static boolean salvar(Fornecedor p) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("INSERT INTO fornecedor (fornecedor, CPF, telefone, item, valorTotal, qtd, dataEntrega) VALUES (?, ?, ?, ?, ?, ?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, p.getnome());
            instrucaoSQL.setString(2, p.getCPF());
            instrucaoSQL.setInt(3, p.gettelefone());
            instrucaoSQL.setString(4, p.getitem());
            instrucaoSQL.setDouble(5, p.getvalorTotal());
            instrucaoSQL.setInt(6, p.getqtd());
            instrucaoSQL.setDate(7, (java.sql.Date) p.getdataEntrega());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if (linhasAfetadas > 0){                
                retorno = true;
                
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                if (generatedKeys.next()) {
                    p.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o ID do fornecedor.");
                }
            } else retorno = false;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            GerenciadorConexao.fecharConexao(conexao, instrucaoSQL);
        }
        return retorno;
    }
    
    public static boolean atualizar(Fornecedor p) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE fornecedor SET fornecedor = ?, CPF = ?, telefone = ?, item = ?, valorTotal = ?, qtd = ? WHERE id = ?");
            instrucaoSQL.setString(1, p.getnome());
            instrucaoSQL.setString(2, p.getCPF());
            instrucaoSQL.setInt(3, p.gettelefone());
            instrucaoSQL.setString(4, p.getitem());
            instrucaoSQL.setDouble(5, p.getvalorTotal());
            instrucaoSQL.setInt(6, p.getqtd());
            instrucaoSQL.setDate(7, (java.sql.Date) p.getdataEntrega());
            instrucaoSQL.setInt(8, p.getId());
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if (linhasAfetadas > 0)
                retorno = true;
            else
                retorno = false;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            GerenciadorConexao.fecharConexao(conexao, instrucaoSQL);
        }
        return retorno;
    }
    
    public static ArrayList<Fornecedor> consultarFornecedor(String nome) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        boolean filtroNome = nome != null && !nome.isEmpty();
        
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            String query = "SELECT * FROM fornecedor WHERE 1 = 1";
            
            if(filtroNome) query += " AND fornecedor like ?";
            
            instrucaoSQL = conexao.prepareStatement(query);
            if(filtroNome) instrucaoSQL.setString(1, nome + "%");

            rs = instrucaoSQL.executeQuery();
            
            //Percorrer o resultSet
            while(rs.next())
            {
                Fornecedor p = new Fornecedor();
                p.setId(rs.getInt("id"));
                p.setnome(rs.getString("nome"));
                p.setCPF(rs.getString("CPF"));
                p.settelefone(rs.getInt("telefone"));
                p.setitem(rs.getString("item"));
                p.setvalorTotal(rs.getDouble("valorTotal"));
                p.setqtd(rs.getInt("qtd"));
                p.setdataEntrega(rs.getDate("dataEntrega"));
                
                listaFornecedores.add(p);
            }
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            listaFornecedores = null;
        } finally{
            GerenciadorConexao.fecharConexao(conexao, instrucaoSQL);
        }
        
        return listaFornecedores;
    }
}
