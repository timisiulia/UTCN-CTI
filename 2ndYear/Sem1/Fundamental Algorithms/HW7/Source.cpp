#include <stdlib.h>
#include <stdio.h>
#include "Profiler.h"


#define nr_teste 10
#define maxsize 10000
#define stepsize 100
Profiler p("Arbori_binari");

typedef struct nodbinar//arbore binar 
{
	int key;// cheia;
	int s;//size 
	struct nodbinar* stanga;
	struct nodbinar* dreapta;
	//memoram pentru fiecare nod ce se afla in stanga si in dreapta lui 
}nodbinar;

nodbinar* new_Node(int k, int s)
{
	//cream un nod 
	nodbinar* nod = (nodbinar*)malloc(sizeof(nodbinar));
	nod->key = k;
	nod->s=s;
	nod->stanga = NULL;
	nod->dreapta = NULL;
	return nod;


}

nodbinar* buildtree(int arr[], int st, int dr,Operation c)
{
	c.count();
	if (st > dr)
		return NULL;
	else
	{
		int mij = (st + dr) / 2;//alegem radacina
		nodbinar* rad = new_Node(arr[mij], dr - st + 1);
		c.count();
		//construiesc arborele cu jumatatea din tablou de dinainte de mijloc
		rad->stanga = buildtree(arr, st, mij - 1,c);
		c.count();
		//construiesc arborele cu jumatatea din tablou de dupa  mijloc
		rad->dreapta = buildtree(arr, mij+1, dr,c);
		return rad;
	}
}
nodbinar* Os_select(nodbinar* n, int i,Operation o)
{
	int rank = 0;
	o.count(2);
	//in caz ca nu  e null
	if (n != NULL && n->stanga != NULL)
	{
		rank = n->stanga->s;//se da size-ul pe stanga
	}
	rank = rank + 1;
	o.count();
	if (i == rank)
	{
		//daca am gasit nodul, il returnam
		return n;
	}
	o.count();
	if (i < rank)//se merge pe stanga
	{
		o.count();
		if (n->stanga != NULL)
			return Os_select(n->stanga, i ,o);
	}

	else 
	{
		o.count();
		if (i > rank)//se merge pe dreapta
		{ 
			o.count();
			if (n->dreapta != NULL)
				return Os_select(n->dreapta, i-rank,o);
		}
	}

}

nodbinar* Os_delete(nodbinar* n, int i, Operation d)
{
	int rank = 0;
	d.count(2);
	//daca e diferit de null
	if (n != NULL &&  n->stanga != NULL)
	{
		rank = n->stanga->s;
	}
	d.count();
	rank = rank + 1;
	if (i == rank)
	{
		d.count(2);
		//ne aflam in cazul in care nodul ce trebuie sters nu are copii
		if (n->stanga == NULL && n->dreapta == NULL)
		{
			return NULL;
		}

		//cazul in care avem doar un copil
		else
		{
			d.count();
			if (n->stanga == NULL)
			{
				//avem copil doar in partea dreapta
				return n->dreapta;//nodul sters va fi inlocuit cu copilul drept
			}
			else
			{
				d.count();
				if (n->dreapta == NULL)
				{
					//avem copil doar in partea stanga
					return n->stanga;//nodul sters va fi inlocuit cu copilul stang
				}

				//cazul in care avem 2 copii
			    //cautam succesorul
				else
				{
					nodbinar* succ = NULL;
						succ=Os_select(n->dreapta, 1, d);//primul element din tabloul de noduri din care construiesc
					n->key = succ->key;//mutam cheia succesorului la nodul n 
					n->dreapta = Os_delete(n->dreapta, 1,d);//se apeleaza stergerea pe succesor
					n->s--;//scade size-ul
				}
			}


		}
	}
		
	d.count();
	if (i < rank)
	{
		d.count();
		if (n->stanga != NULL)//se merge pe stanga arborelui
		{
			n->s--;
			n->stanga = Os_delete(n->stanga, i,d);
		}
	}
	else
	{
		d.count(1);
		if (i > rank)//se merge pe dreapta arborelui
		{
			d.count();
			if (n->dreapta != NULL)
			{
				n->s--;
				n->dreapta = Os_delete(n->dreapta, i - rank, d);
			}
		}
	}
	return n; //returnam radacina daca nu se schimba
	
}



void afis(int k, int s, int nivel)
{
	for (int i = 0; i < nivel; i++)
		printf(" \t ");
	printf("%d [%d]\n", k ,s);
}
void pretty_nodbinar(nodbinar* rad, int nivel)
{
	if (rad != NULL)
	{
		//daca inca nu a ajuns la final
		pretty_nodbinar(rad->dreapta, nivel + 1);
		afis(rad->key,rad->s, nivel);//afiseaza
		pretty_nodbinar(rad->stanga, nivel + 1); 
		


	}
}
void masuratori()
{

	for (int j = 0; j < nr_teste; j++)
	{
		for (int n = stepsize; n <= maxsize; n += stepsize)
		{
			Operation cautare = p.createOperation("Cautare", n);
			Operation stergere = p.createOperation("Stergere", n);
			Operation ConstruireArbore = p.createOperation("ConstruireArbore", n);

			int arr[maxsize];
			FillRandomArray(arr, n, 10, 50000, true, ASCENDING);//tablou cu n elem din care facem arbore
			nodbinar* tree = buildtree(arr, 0, n - 1, ConstruireArbore);
			for (int i = 1; i <= n; i++)
			{
				int index = rand() % tree->s;//index-ul pe care il caut
				
				Os_select(tree, index, cautare);

				tree=Os_delete(tree, index, stergere);
			}

		}
	}
	p.divideValues("Cautare", nr_teste);
	p.divideValues("Stergere", nr_teste);
	p.divideValues("ConstruireArbore", nr_teste);
	p.createGroup("STATISTICI DE ORDINE", "Cautare", "Stergere", "ConstruireArbore");
	p.showReport();
}
void demo()
{
	Operation nou = p.createOperation("Nou", 0);
	int n = 11;
	printf("Dimensiunea sirului e:%d\n",n);
	int arr[] = { 1,8,9,13,18,21,24,26,31,35,47};
	nodbinar* tree = buildtree(arr, 0, 10,nou);
	pretty_nodbinar(tree,0);

	nodbinar* nnn = NULL;
	nnn = Os_select(tree, 11,nou);
	printf(" \n Cea mai mare cheie este %d", nnn->key);
	int index = n;//
	for (int i = 2; i <n; i++)
	{
		printf("\n\n\n");
		tree = Os_delete(tree, rand() % index,nou);
		pretty_nodbinar(tree, 0);
		index--;

	}
	
}
int main()
{
	demo();
	//masuratori();
}
