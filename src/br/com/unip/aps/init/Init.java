package br.com.unip.aps.init;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.ui.RefineryUtilities;

import br.com.unip.aps.chart.Chart;
import br.com.unip.aps.csv.CSVReader;
import br.com.unip.aps.graph.Graph;
import br.com.unip.aps.order.Order;
import br.com.unip.aps.random.Generate;

public class Init {
	public static JFileChooser jf;
	public static int size = 100000;
	private static int[] originalVetor; 

	public static void main(String[] args) throws IOException {

		boolean isOut = false;
		Generate nv = new Generate();
		Order o = new Order();
		int[] r = null;
		double[] time = null;
		originalVetor = new int[size];
		//Cria um diretório para a exportação de arquivos dentro da pasta raiz do projeto
		String current = System.getProperty("user.dir") +"/files";
		new File(current).mkdir(); 
		
		while(!isOut){
			//Menu
			int option = Integer.parseInt(JOptionPane.showInputDialog(
					"1 - Definir a quantidade de valores (Atual: " + size+")" 
					+ "\n2 - Importar CSV "
					+ "\n3 - Gerar valores aleatorios com repetição"
					+ "\n4 - Gerar valores aleatórios sem repetição"
					+ "\n5 - Ordernar (Necessário valores carregados)"
					+ "\n6 - Exportar arquivo** e gerar gráfico (Necessário ordenação)"
					+ "\n9 - Para sair"
					+ "\n\n**Limite para exportação excel é de 32.000 por limitação do próprio formato[Excel 2003]"));
			
			//Opções do Menu e suas respectivas chamadas
			switch (option) {
			case 1: 
				String value = JOptionPane.showInputDialog("Digite a quantidade de valores desejado: ");
				size = Integer.parseInt(value);	
				originalVetor = new int[size];
				break;
			case 2:
				doCSVAction();
				break;
			case 3:
				time = null;
				r = nv.generateRandomNumbersNoVerify(size);
				originalVetor = r.clone(); 
				System.out.print("\nNúmeros Gerados: \n");
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 4:
				time = null;
				r = nv.generateRandomNumbers(size);
				originalVetor = r.clone();
				System.out.print("\nNúmeros Gerados: \n");
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 5:
				if(r != null){
					time = o.returnOrder(r);
				}else {
					JOptionPane.showMessageDialog(null, "Vetor não carregado");
				}
				break;
			case 6:
				try {
					if(time != null){
						Graph chart = new Graph("Gráfico de tempo por algoritmo de ordenação", "Tempo de ordenação para "+size+" registros", time);
						chart.pack( );        
						RefineryUtilities.centerFrameOnScreen(chart);        
						chart.setVisible( true ); 
	
						//Monto as labels para o excel
						String[] colunas = {"Original","Insertion Sort","Selection Sort","Merge Sort"};
					
						//Crio uma tabela com o numero de items no vetor ordenado, e 3 colunas
						long[][] linhas = new long[size][4]; 

						//Monto as linhas
						for(long linha = 0; linha < size; linha++){
							System.out.println(originalVetor[(int) linha]);
							linhas[(int) linha][0] = originalVetor[(int) linha];
							linhas[(int) linha][1] = o.insertSort[(int) linha];
							linhas[(int) linha][2] = o.selectionSort[(int) linha];
							linhas[(int) linha][3] = o.mergeSort[(int) linha];
						}
					
						Chart grafico = new Chart("files/Relatorio.xls", colunas, linhas, "files/Graph.jpg");
						grafico.export();
						
						JOptionPane.showMessageDialog(null, "Arquivos exportados para '../files'");
					}
					else{
						JOptionPane.showMessageDialog(null, "A ordenação ainda não foi efetuada!");
					}
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
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
}