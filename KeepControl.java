import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RAMIRO
 */
public class KeepControl {
    
    PreparedStatement pstm;
    ResultSet rs;
    
    // Salvar Registro
    
    public void Salvar(KeepBean kb){
        ConexaoBD con = new ConexaoBD();
        try{
            String sql = "insert into KEEPS (servico, tiposervico, email, usuario, senha) values (?,?,?,?,?)";
            pstm = con.conecta().prepareStatement(sql);
            pstm.setString(1, kb.getServico());
            pstm.setString(2, kb.getTiposervico());
            pstm.setString(3, kb.getEmail());
            pstm.setString(4, kb.getUsuario());
            pstm.setString(5, kb.getSenha());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso!");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não Foi Possível Gravar o Registro!");
        }finally{
            con.desconecta();
        }
    }
    
    // Alterar Registro
    
    public void Alterar(KeepBean kb){
        ConexaoBD con = new ConexaoBD();
        try{
            String sql = "update KEEPS set tiposervico = ?, email = ?, usuario = ?, senha = ? where servico = ?";
            pstm = con.conecta().prepareStatement(sql);
            pstm.setString(1, kb.getTiposervico());
            pstm.setString(2, kb.getEmail());
            pstm.setString(3, kb.getUsuario());
            pstm.setString(4, kb.getSenha());
            pstm.setString(5, kb.getServico());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não Foi Possível Alterar o Registro!");
        }finally{
            con.desconecta();
        }
    }
    
    // Ecluir Registro
    
    public void Excluir(KeepBean kb){
        ConexaoBD con = new ConexaoBD();
        String sql = "delete from KEEPS where servico = ?";
        String nome = "Deseja deletar o serviço " + kb.getServico() + "?";
        int opcao = JOptionPane.showConfirmDialog(null, nome, "Deletar Serviço", JOptionPane.YES_NO_OPTION);
        try{
            pstm = con.conecta().prepareStatement(sql);
            pstm.setString(1, kb.getServico());
            if (opcao == JOptionPane.YES_OPTION) {
                //pstm.executeUpdate();
                int excluiu = pstm.executeUpdate();
                if (excluiu == 1) {
                    JOptionPane.showMessageDialog(null, "Registro Excluído com Sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Registro Não Foi Excluído!");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não Foi Possível Excluir o Registro!");
        }finally{
            con.desconecta();
        }
    }
}