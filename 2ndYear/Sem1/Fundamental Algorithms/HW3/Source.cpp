#include <iostream>
#include "Profiler.h"
using namespace std;


#define max_size 10000
#define step_size 100
#define nr_test 5
Profiler p("sort_worstcase");
//folosim num in locul lui n, pt ca n se modifica pe parcursul functiei
void maxheapify(int arr[], int n, int i, int num)
{
	Operation opComp = p.createOperation("Heapcomp", num);
	Operation opAtrib = p.createOperation("Heapatrib", num);
	int max = i;
	int st = 2 * i + 1;
	int dr = 2 * i + 2;
	opComp.count();
	if (dr < n && arr[dr] > arr[max]) {
		opAtrib.count();
		max = dr;
	}
	opComp.count();
	if (st < n && arr[st] > arr[max]) {
		opAtrib.count();
		max = st;
	}
	opComp.count();
	if (max != i)
	{
		opAtrib.count(3);
		int aux;
		aux = arr[i];
		arr[i] = arr[max];
		arr[max] = aux;
		maxheapify(arr, n, max, num);

	}

}
void buildheap(int arr[], int n, int num)
{
	for (int i = n / 2 - 1; i >= 0; i--)
		maxheapify(arr, n, i, num);
}
void heapsort(int arr[], int n, int num)
{
	Operation opComp = p.createOperation("Heapcomp", num);
	Operation opAtrib = p.createOperation("Heapatrib", num);
	buildheap(arr, n, num);

	for (int i = n ; i > 0; i--)
	{
		opAtrib.count(3);
		int aux;
		aux = arr[0];
		arr[0] = arr[i];
		arr[i] = aux;
		maxheapify(arr, i, 0, num);
	}
}

int part(int arr[], int st, int dr, int num)
{
	Operation opComp = p.createOperation("Quickcomp", num);
	Operation opAtrib = p.createOperation("Quickatrib", num);
	int k = arr[dr];//pivot
	int i = st - 1;//pozitia de plecare
	opAtrib.count(2);
	for (int j = st; j <= dr - 1; j++)
	{
		opComp.count(2);
		if (arr[j] < k)
		{//parcurge tablou, daca e mai mic se interschimba cu pozitia lui i 
			i++;
			opAtrib.count(3);
			int aux = arr[i];
			arr[i] = arr[j];
			arr[j] = aux;
		}
	}//pivotul e pe final
	opAtrib.count(3);
	//pivotul se aduce pe pozitia lui cea buna
	int aux2 = arr[i + 1];
	arr[i + 1] = arr[dr];
	arr[dr] = aux2;
	return i + 1;//returneaza o pozitie a viitorului pivot
}
void quicksort(int arr[], int st, int dr, int num)
{
	Operation opComp = p.createOperation("Quickcomp", num);
	Operation opAtrib = p.createOperation("Quickatrib", num);
	opComp.count();
	if (st < dr)
	{
		opAtrib.count();//k e poz pivotului
		int k = part(arr, st, dr, num);//se imparte in doua 
		quicksort(arr, st, k - 1, num);//tot imparte
		quicksort(arr, k + 1, dr, num);

	}
}
int RandPart(int arr[], int st, int dr, int num)// da pivot random
{
	int i = st + (rand() % (dr - st + 1));
	int aux = arr[i];
	arr[i] = arr[dr];
	arr[dr] = aux;
	return part(arr, st, dr, num);//reurneaza pivot
}
int quickselect(int arr[], int st, int dr, int i, int num)
{
	if (st == dr)
		return arr[st];
	int piv = RandPart(arr, st, dr, num);
	int k = piv - st + 1;//ia elementele de dupa pivot, pt ca dupa piv sunt elemente mai mari, iar inainte mai mici
	if (i == k) 
		return arr[piv];
	else if (i < k) 
		return quickselect(arr, st, piv - 1, i, num);//cauta in prima jumatate
	else 
		return  quickselect(arr, piv + 1, dr, i - k, num);//cauta in a 2 a jum, poz pleca de la pivot
}

void afisare(int arr[], int n)
{
	for (int i = 0; i < n; i++)
		printf("%d ", arr[i]);
	printf("\n");
}
void demo()
{
	int arr[] = { 3,5,7,9,6,1,4,2,8 };
	int n = sizeof(arr) / sizeof(arr[0]);
	int arr2[] = { 3,5,7,9,6,1,4,2,8 };
	int arr3[] = { 3,5,7,9,6,1,4,2,8 };
	printf("Sirul nesortat e \n");
	afisare(arr, n);
	quicksort(arr, 0, n - 1, n);
	heapsort(arr3, n - 1, n);
	int sel = quickselect(arr2, 0, n - 1, 3, n);
	printf("QuickSelect: %d\n", sel);
	afisare(arr2, n);
	printf("Sirul sortat de quicksort \n");
	afisare(arr, n);
	printf("Sirul sortat de heapsort \n");
	afisare(arr3, n);

}
void grafic()
{
	int arr1[max_size], arr2[max_size];
	int n, i;
	for (n = step_size; n <= max_size; n += step_size)
		for (int test = 0; test < nr_test; test++)
		{
			FillRandomArray(arr1, n, 10, 50000,false,2);
			for (int i = 0; i < n; i++)
				arr2[i] = arr1[i];
			quicksort(arr1, 0, n - 1, n);
			heapsort(arr2, n, n);

		}
	p.divideValues("Heapcomp", nr_test);
	p.divideValues("Heapatrib", nr_test);
	p.addSeries("operatii_Heap", "Heapcomp", "Heapatrib");

	p.divideValues("Quickcomp", nr_test);
	p.divideValues("Quickatrib", nr_test);
	p.addSeries("operatii_Quick", "Quickatrib", "Quickcomp");

	p.createGroup("Total_atribuiri", "Heapatrib", "Quickatrib");
	p.createGroup("Total_comparatii", "Heapcomp", "Quickcomp");
	p.createGroup("Total_operatii", "operatii_Heap", "operatii_Quick");
	p.showReport();
}
int main() {
	//demo();
	grafic();
	return 0;
}