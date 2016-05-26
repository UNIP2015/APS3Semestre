package br.com.unip.aps.order.method;

public class Selection {
	//Metodo de ordenacao tipo Selection sort
	public int [] selection(int[] vet) {
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
}
