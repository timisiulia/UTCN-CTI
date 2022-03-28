#include <iostream> 
#include <string>
#include <algorithm>
#include "Profiler.h"
using namespace std;

#define table_length 10007 //dimensiune maxima pentru tabela de dispersie
#define nr_tests 
Profiler p("Tabele");

//(k mod n)+i^2) mod n
int hashfunction(int key, int i, int n) 
{
	return ((key % n)  + i * i) % n;
}
//k=cheia pe care o inserez
int hashinsert(int arr[], int k, int n)
{
	int i = 0;
	while (i < n)
	{
		int j = hashfunction(k, i, n);//incepe inserarea
		if (arr[j] == 0)
		{
			arr[j] = k;//elementului ii se atribuie cheia
			return j;
		}
		else i++;//daca exista colizune vom creste i pentru a gasi o noua pozitie urm. element
	}
	return -1;// daca tabela de dispersie a fost plina

}
int hashsearch(int arr[], int k, int n, Operation& operatie)
{// functia de cautare a cheii
	int i = 0;
	int j = hashfunction(k, i, n);
	while (arr[j] != 0 && i < n)
	{
		operatie.count();
		j = hashfunction(k, i, n);
		if (arr[j] == k)
		{
			return j;
		}
		i++;
	}
	return -1;

}
void performanta1(int a, float b) {
	//performanta pentru 0.80

	int arr[table_length];
	int Heap[table_length] = { 0 };
	int maxAcceseG = 0;//gasite
	int maxAcceseNG = 0;//negasite
	int numereInserate[table_length];
	int pozDeAdaugat = 0;
	Operation gasite = p.createOperation("MediuGasite", 80);
	Operation negasite = p.createOperation("MediuNegasite", 80);

	for (int i = 0; i < 1; i++) 
	{

		FillRandomArray(arr, table_length, 5000, 50000, false, ASCENDING);
		for (int j = 0; j < a; j++)
		{
			if (hashinsert(Heap, arr[j], table_length) != -1)//daca e libra poz
			{
				numereInserate[pozDeAdaugat] = arr[j];
				pozDeAdaugat++;
			}
		}
		//Am inserat 8000 de numere care nu stim cu siguranta daca vor fi inserate toate


		//cautam in elem inserate
		//daca sunt elemente mai mari de 50000
		int cautari1 = gasite.get();
		int cautari2 = negasite.get();

		for (int l = 0; l < 1500; l++)
		{

			int elemDeCautat1 = numereInserate[rand() % pozDeAdaugat];
			hashsearch(Heap, elemDeCautat1, table_length, gasite);
			int accesCelula1 = gasite.get() - cautari1;
			cautari1 = gasite.get();
			maxAcceseG = max(maxAcceseG, accesCelula1);

			int elemDeCautat2 = (rand() % table_length + 50001);
			hashsearch(Heap, elemDeCautat2, table_length, negasite);
			int accesCelula2 = negasite.get() - cautari2;
			cautari2 = negasite.get();
			maxAcceseNG = max(maxAcceseNG, accesCelula2);
		}

		p.divideValues("MediuGasite", 1500);
		p.divideValues("MediuNegasite", 1500);

	}

	printf("Alfa | ef_mediu_g |  ef_max_g   |   ef_mediu_ng  |  ef_max_ng|\n\n");
	printf(" %2.f  |  %d       |     %d      |      %d          |      %d      |  \n\n",b,gasite.get(),maxAcceseG,negasite.get(),maxAcceseNG);
	printf("-------------------------------------------------------------------------\n");
}
void performance() {
	performanta1(8000, 0.80);
	performanta1(8500, 0.85);
	performanta1(9000, 0.90);
	performanta1(9500, 0.95);
	performanta1(9900, 0.99);
}
void demo() {
	int m = 30;
	int arr[51];
	int Hash[51] = { 0 };
	FillRandomArray(arr, 30, 10, 1000, true, ASCENDING);
	printf("Sirul initial e:\n");
	for (int i = 0; i < m; i++)
	{
	
		printf("%d ", arr[i]);
	}

	printf("\n");
	for (int i = 0; i < m - 2; i++) {
		if (hashinsert(Hash, arr[i], m - 2) == -1)
		{
			printf("Nu putem insera cheia %d\n", arr[i]);
		}

	}
	printf("Sirul dupa inserari e:\n");
	for (int i = 0; i < m; i++) 
	{
		printf("%d ", Hash[i]);
	}

}
int main() {
	demo();
	//performance();
}
