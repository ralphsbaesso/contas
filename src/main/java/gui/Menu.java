//package gui;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import javax.swing.JOptionPane;
//
//import dao.Idao;
//import dao.implementacao.DaoMantenedor;
//import dominio.Mantenedor;
//
//public class Menu {
//
//	public static void main(String[] args) {
//
//		Idao dao = new DaoMantenedor();
//		Mantenedor mantenedor = new Mantenedor();
//		List<Mantenedor> mantenedores;
//		Mantenedor manAux = new Mantenedor();
//		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		
//		
//		String menu = "1 - Cadastrar Mantenedor\n"
//					+ "2 - Alterar Mantenedor\n"
//					+ "3 - Excluir Mantenedor\n"
//					+ "4 - Listar Mantenedores\n"
//					+ "0 - Sair";
//		
//		String opcao = "a";
//		
//		while(!opcao.equals("0")){
//			
//			opcao = JOptionPane.showInputDialog(menu);
//			
//			switch(opcao){
//			case "1":
//				
//				mantenedor.setNome(JOptionPane.showInputDialog("Nome: "));
//				mantenedor.setCpf(JOptionPane.showInputDialog("cpf: "));
//				mantenedor.setRegistro(JOptionPane.showInputDialog("Registro: "));
//				mantenedor.setEmail(JOptionPane.showInputDialog("Email: "));
//				mantenedor.setDataCadastro(Calendar.getInstance());
//				
//				if(dao.salvar(mantenedor)){
//					JOptionPane.showMessageDialog(null, "Salvo com sucesso");
//				}else{
//					JOptionPane.showMessageDialog(null, "Erro de persist?ncia");
//				}
//				
//				break;
//			case "2":
//				
//				mantenedor.setCpf(JOptionPane.showInputDialog("Digite o cpf do mantenedor que deseja ALTERAR: "));
//				
//				mantenedores = dao.listar(mantenedor);
//				if(mantenedores.isEmpty()){
//					JOptionPane.showMessageDialog(null, "nenhum valor encontrado!");
//					continue;
//				}
//				
//				manAux = mantenedores.get(0);
//				
//				mantenedor.setNome(JOptionPane.showInputDialog("Nome atual: " + manAux.getNome()));
//				mantenedor.setRegistro(JOptionPane.showInputDialog("Registro atual: " + manAux.getRegistro()));
//				mantenedor.setEmail(JOptionPane.showInputDialog("Email atual: " + manAux.getEmail()));
//				mantenedor.setDataCadastro(Calendar.getInstance());
//				
//				if(dao.alterar(mantenedor)){
//					JOptionPane.showMessageDialog(null, "Alterado com sucesso");
//				}else{
//					JOptionPane.showMessageDialog(null, "Erro na altera??o");
//				}
//				
//				break;
//			case "3":
//				
//				mantenedor.setCpf(JOptionPane.showInputDialog("Digite o cpf do mantenedor que deseja EXCLUIR: "));
//				
//				mantenedores = dao.listar(mantenedor);
//				
//				if(mantenedores.isEmpty()){
//					JOptionPane.showMessageDialog(null, "nenhum valor encontrado!");
//					continue;
//				}
//				
//				manAux = mantenedores.get(0);
//				
//				int opcaoYesNo = JOptionPane.showConfirmDialog(null, "Deseja ecluir " + manAux.getNome() + " ?");
//				
//				if(opcaoYesNo != JOptionPane.YES_OPTION){
//					JOptionPane.showMessageDialog(null, "Opera??o cancelada");
//					continue;
//				}
//				
//				if(dao.excluir(mantenedor)){
//					JOptionPane.showMessageDialog(null, "Exclus?o com sucesso");
//				}else{
//					JOptionPane.showMessageDialog(null, "Erro na exclus?o");
//				}
//				
//				break;
//			case "4":
//				
//				mantenedor.setCpf(null);;
//				mantenedores = dao.listar(mantenedor);
//				
//				if(mantenedores.isEmpty()){	// vazio?
//					JOptionPane.showMessageDialog(null, "N?o foi encontrado nenhum dado");
//					return;
//				}
//				
//				String msg = "";
//				for(Mantenedor man2: mantenedores){
//					
//					msg += "ID: " + man2.getId()
//							+ " Nome: " + man2.getNome()
//							+ " - cpf: " + man2.getCpf()
//							+ " - Registro: " + man2.getRegistro()
//							+ " - email: " + man2.getEmail()
//							+ " - data de cadastro: " + formataData.format(man2.getDataCadastro().getTime()) + "\n\n";
//				}
//				
//				int qtde = mantenedores.size();
//				JOptionPane.showMessageDialog(null, 
//												"Quantidade de registro: " + qtde + "\n\n" + msg);
//				mantenedores.clear();
//				
//				break;
//				
//			case "0":
//				JOptionPane.showMessageDialog(null, "Finalizando Sistema!");
//				break;
//				
//			default:
//				JOptionPane.showMessageDialog(null, "Op??o invalida!");
//			}
//		}
//	}
//}
