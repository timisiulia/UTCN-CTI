#include <stdlib.h>
#include <stdio.h>

#include <iostream>

typedef struct nodmulticai //nod de arbore multicai
{
	int key; // cheia
	int nrcopii;// dam numarul de copii
	struct nodmulticai* copii[10] = { NULL };//lungimea vectorului
	//pentru fiecare nod, memoram o lista cu adresele copiilor
}nodmulticai;
typedef struct nodbinar//arbore binar 
{
	int key;// cheia;
	struct nodbinar* stanga;
	struct nodbinar* dreapta;
	//memoram pentru fiecare nod ce se afla in stanga si in dreapta lui 
}nodbinar;
nodmulticai* newNode(int k) 
{
	//cream un  nou nod pentru arborele multicai
	nodmulticai* nod = (nodmulticai*)malloc(sizeof(nodmulticai));
	nod->key = k;//ii dam cheia 
	nod->nrcopii = 0;
	return nod;
	
}
nodbinar* new_Node(int k)
{
	//cream un nod nou pentru reprezentarea binara
	nodbinar* nod = (nodbinar*)malloc(sizeof(nodbinar));
	nod->key = k;
	nod->stanga = NULL;
	nod->dreapta = NULL;
	return nod;


}
//transformare	R1 in R2
nodmulticai* transformare(int arr[], int n)
{
	nodmulticai* rad=NULL;//initializam radacina cu null
	nodmulticai* vect[10];//declaram un sir de adrese pentru fiecare nod
	for  (int i = 0;  i < n; i++)
	{
		vect[i] = newNode(i);//alocam pentru fiecare element din vector cate un nod de tip multicai
	}
	for (int j = 0; j < n; j++)
	{
		if (arr[j] != -1)//daca nu e radacina
		{ 
			//creaza o legatura intre parintele nodului curent si nodul de pe pozitia j
			vect[arr[j]]->copii[vect[arr[j]]->nrcopii] = vect[j];
			//crestem nr de copii
			vect[arr[j]]->nrcopii = vect[arr[j]]->nrcopii + 1;

		}
		else
		{
			rad = vect[j];//atribuim radacinii o adresa
		}
	}
	return rad;
}
nodbinar* transformare2(nodmulticai*rad)
{
	nodbinar* nou = new_Node(rad->key);//cream un nod nou
	if (rad->nrcopii == 0)
	{
		nou->stanga = NULL;//daca nu mai are copii punem pe partea stanga null

	}
	else
	{
		nou->stanga = transformare2(rad->copii[0]);//daca are copii, ceam legaura intre nodul curent si primul copil
		nodbinar* nod = (nodbinar*)malloc(sizeof(nodbinar));//alocam memorie pentru un nod
		nod = nou->stanga;//mergem spre urmatorul copil
		//cat timp mai sunt copii
		for (int i = 1; i < rad->nrcopii; i++)
		{
			nod->dreapta = transformare2(rad->copii[i]);//cream legatura intre frati
			nod = nod->dreapta;//facem legatura dintre subarbore si arbore
		}
	}
	return nou;
}

void afis(int k, int nivel)
{
	for (int i = 0; i < nivel; i++)
		printf(" \t ");
	printf("%d\n", k);
}
void pretty_nodmulticai(nodmulticai* radacina, int nivel)
{
	if (radacina == NULL)
	{
		//daca am ajuns la final, iesim din recursivitate
		return;
	}
	else
	{
		//apelam functia pentru fiecare copil al nodului curent
		for (int i = 0; i < radacina->nrcopii; i++)
			pretty_nodmulticai(radacina->copii[i], nivel+1);
		afis(radacina->key, nivel);// cand revine din recursivitate, afisaza

		
	}
	

}
void pretty_vector_tati(int arr[], int v[], int n, int poz_rad, int nivel)
{

	if (v[poz_rad] != 0) 
	{
		//daca am vizitat nodul, iesim din recursivitate
		return;
	}
	else
	{

		v[poz_rad] = 1;//il marcam ca vizitat
		for (int i = 1; i <= n; i++)
			if (arr[i] == poz_rad)
				pretty_vector_tati(arr, v, n, i, nivel+1);//apelam functia pt fiecare copil al nodului curent
		afis(poz_rad, nivel);//cand revine din recursivitate, afiseaza

		
	}
}
void pretty_nodbinar(nodbinar*rad, int nivel)
{
	if (rad != NULL)
	{
		//daca inca nu a ajuns la final
		pretty_nodbinar(rad->dreapta, nivel);//nu creste nivelul, deoarece fratii sunt pe acelasi  nivel 
		pretty_nodbinar(rad->stanga, nivel + 1); //apeleaza pentru fiecare copil
		afis(rad->key, nivel);//afiseaza
		
		
	}
}

void demo()
{

	int arr[] = { 0, 2 , 7 , 5 , 2 , 7 , 7 ,-1 ,5 ,2 };//vector de tati
	int n = 10;//dimensiunea lui
	int viz[10] = {0};//vector de vizitati

	nodmulticai* radacina = transformare(arr, n);
	pretty_nodmulticai(radacina, 0);
	printf("\n");
	pretty_vector_tati(arr, viz, n, 7, 0);
	printf("\n");
	nodbinar* rad = transformare2(radacina);
	pretty_nodbinar(rad, 0);


}
int main()
{
	demo();
}