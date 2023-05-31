package arvore;

public class main {

	public static void main(String[] args) {
		NoArvore<Integer> n13 = new NoArvore<Integer>(13);		
		NoArvore<Integer> n14 = new NoArvore<Integer>(14);		
		
		NoArvore<Integer> n11 = new NoArvore<Integer>(11);
		n11.inserirFilho(n13);
		n11.inserirFilho(n14);
		NoArvore<Integer> n12 = new NoArvore<Integer>(12);
		

		
		NoArvore<Integer> n9 = new NoArvore<Integer>(9);
		n9.inserirFilho(n11);
		n9.inserirFilho(n12);
		NoArvore<Integer> n10 = new NoArvore<Integer>(10);
		NoArvore<Integer> n4 = new NoArvore<Integer>(4);
		n4.inserirFilho(n10);
		n4.inserirFilho(n9);
		NoArvore<Integer> n8 = new NoArvore<Integer>(8);
		NoArvore<Integer> n3 = new NoArvore<Integer>(3);
		n3.inserirFilho(n8);
		NoArvore<Integer> n5 = new NoArvore<Integer>(5);
		NoArvore<Integer> n6 = new NoArvore<Integer>(6);
		NoArvore<Integer> n7 = new NoArvore<Integer>(7);
		NoArvore<Integer> n2 = new NoArvore<Integer>(2);
		n2.inserirFilho(n7);
		n2.inserirFilho(n6);
		n2.inserirFilho(n5);
		NoArvore<Integer> n1 = new NoArvore<Integer>(1);
		n1.inserirFilho(n4);
		n1.inserirFilho(n3);
		n1.inserirFilho(n2);
		Arvore<Integer> arvore = new Arvore<>();
		arvore.setRaiz(n1);
		
		System.out.println("arvore: " + arvore);
		System.out.println("altura da arvore: " + arvore.getAltura());		
		System.out.println("nivel do no: " + arvore.getNivel(n11.getInfo()));		
		System.out.println("nivel do no: " + arvore.getNivel(n13.getInfo()));	
		System.out.println("nivel do no: " + arvore.getNivel(n9.getInfo()));	
		System.out.println("nivel do no: " + arvore.getNivel(n1.getInfo()));
		System.out.println("balanceada: " + arvore.isBalanceada());
	}

}
