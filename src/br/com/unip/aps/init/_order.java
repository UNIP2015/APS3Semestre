
package br.com.unip.aps.init;
public class _order{

	public void _returnOrder(int[] vet){
		//salva tamanho do vetor na variavel fim
		int i, fim = vet.length;
		int[] retorno;
		
		//Mostra os algoritmos ordenados
		retorno = _insertion(vet);
		System.out.println("Ordenação por Insertion sort\n");
		for (i = 1; i < retorno.length; i++) {
			System.out.println(" "+retorno[i]);
		}
		
		System.out.println("\n");
		
		retorno = _selection(vet);
		System.out.println("Ordenação por Selection sort\n");
		for (i = 1; i < retorno.length; i++) {
			System.out.println(" "+retorno[i]);
		}
		
		System.out.println("\n");
		
		retorno = _merge(vet,1,fim);
		System.out.println("Ordenação por Merge sort\n");
		for (i = 1; i < retorno.length; i++) {
			System.out.println(" "+retorno[i]);
		}
	}
	
	//Metodo de ordenação tipo Insertion sort
	public int[] _insertion(int[] vet) {
		int i, j, aux;
		int[] insertion = vet;
		
		//para i = 1, enquanto i menor que tamanho de insertion, i++
		for (i = 1; i < insertion.length; i++) {
			
			//aux recebe valor de vet indice i
			aux = insertion[i];
			//j recebe valor de i
			j = i;
			//enquanto j maior que 0 e insertion indice j-1 maior que aux
			while ((j > 0) && (insertion[j - 1] > aux)) {
				//insertion indice j recebe insertion j-1
				insertion[j] = insertion[j - 1];
				//j recebe j-1
				j = j - 1;
			}
			//insertion indice j recebe aux
			insertion[j] = aux;
		}
		//retorna vetor
		return insertion;
	}
	
	//Metodo de ordenação tipo Selection sort
	public int [] _selection(int[] vet) {
	    int min, max, i, j;
	    int[] selection = vet;
	    
	    //para i = 0 enquanto i menor que tamanho de selection - 1, i++
        for (i = 0; i < selection.length - 1; i++) {
            // antes de comparar, considera-se menor o valor atual do loop
            min = selection[i];
            max = i;

            // compara com os outros valores do vetor
            for (j = i + 1; j < selection.length; j++){
                if (selection[j] < min){
                    min = selection[j];
                    max = j;
                }
            }

            // troca os valores menor e maior
            selection[max] = selection[i];
            selection[i] = min;
        }
        //retorna selection
        return selection;
	}
	
	//Metodo de ordenação tipo Merge sort
	public int[] _merge(int[] vet, int inicio, int fim) {
		int i, j, k;
		int[] merge = vet;
		
		//Se fim menor igual a inicio
		if(fim <= inicio) {
			//retorna null
			return null;
		}
		//salva na variavel meio o meio do vetor
		int meio = (inicio + fim) / 2;
		
		//chama a função do começo até a metade
		_merge(merge, inicio, meio);
		//chama a função do meio+1 até o fim
		_merge(merge, meio + 1, fim);
		
		//define um vetor A do tamanho meio - inicio +1
		int[] A = new int[meio - inicio + 1];
		//define um vetor B que vai de fim até o meio
		int[] B = new int[fim - meio];
		
		//para int igual a 0, enquanto i menor que meio - inicio, i++
		for(i = 0; i <= meio - inicio; i++) {
			//vetor A indice i recebe o vetor merge indice incio + i
			A[i] = merge[inicio + i];
		}
		
		//para int igual a 0, enquanto i menor igual a fim menos meio menos 1, i++
		for( i = 0; i <= fim - meio - 1; i++) {
			//vetor B indice i recebe o vetor merge indice meio + 1 + i
			B[i] = merge[meio + 1 + i];
		}
		
		//zera as variaveis de controle de indice
		i = 0;
		j = 0;
		
		//para k = inicio, k menor igual a fim, k++
		for(k = inicio; k <= fim; k++) {
			//se i menor que tamanho de A e j menor que tamanho de B
			if (i < A.length && j < B.length) {
				//se A indice i menor que B indice j
				if (A[i] < B[j]) {
					//merge indice k recebe A indice i incrementado após ser usado
					merge[k] = A[i];
					i++;
				} 
				//se não
				else {
					//merge indice k recebe B indice j incrementado após ser usado
					merge[k] = B[j];
					j++;
				}
				
			} 
			//se não se i menor que tamanho de A
			else if (i < A.length) {
				//merge indice k recebe A indice i incrementado após ser usado
				merge[k] = A[i];
				i++;
			} 
			//se não se j menor que tamanho de B
			else if (j < B.length) {
				//merge indice k recebe B indice j incrementado após ser usado
				merge[k] = B[j];
				j++;
			}
			
		}
		//retorna o vetor
		return merge;
		
	}
}
