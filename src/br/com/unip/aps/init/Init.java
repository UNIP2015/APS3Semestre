package br.com.unip.aps.init;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.unip.aps.csv.CSVReader;
import br.com.unip.aps.order.Order;
import br.com.unip.aps.random.Generate;

public class Init {
	public static JFileChooser jf;
	public static int size = 100000;
	
	//Metodo de chamada para importação do CSV
	protected static int[] doCSVAction(){
		jf = new JFileChooser();
		int[] numeros = new int[0];
		
		FileFilter filter = new FileNameExtensionFilter("CSV", "csv");
		jf.setFileFilter(filter);
		int retorno = jf.showOpenDialog(null);
		if(retorno == JFileChooser.APPROVE_OPTION){
			String splitSeparator = JOptionPane.showInputDialog("Qual o separador do CSV? ");
			File fileSelected = jf.getSelectedFile();
			CSVReader csvReader = new CSVReader(fileSelected.getAbsolutePath(),splitSeparator);
			numeros = csvReader.convert();
			
		}
		
		return numeros;
	}
	
	public static void main(String[] args) {

		boolean isOut = false;
		Generate nv = new Generate();
		int[] r = null;
		
		while(!isOut){
			//Menu
			int option = Integer.parseInt(JOptionPane.showInputDialog(
					"1 - Definir a quantidade de valores (Atual: " + size+")" 
					+ "\n2 - Importar CSV "
					+ "\n3 - Gerar valores aleatorios com repetição"
					+ "\n4 - Gerar valores aleatórios sem repetição"
					+ "\n5 - Ordernar"
					+ "\n6 - Exportar Arquivo"
					+ "\n9 - Para sair" ));
			
			//Opções do Menu e suas respectivas chamadas
			switch (option) {
			case 1: 
				String value = JOptionPane.showInputDialog("Digite a quantidade de valores desejado: ");
				size = Integer.parseInt(value);	
				break;
			case 2:
				doCSVAction();
				break;
			case 3:
				r = nv.generateRandomNumbersNoVerify(size);
				System.out.print("\nNúmeros Gerados: \n");
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 4:
				r = nv.generateRandomNumbers(size);
				System.out.print("\nNúmeros Gerados: \n");
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 5:
				if(r != null){
					Order o = new Order();
					o.returnOrder(r);
				}else {
					JOptionPane.showMessageDialog(null, "Vetor não carregado");
				}
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Em construção");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Até mais!");
				isOut = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opçao invalida!");
				break;
			}
		}
	}
}