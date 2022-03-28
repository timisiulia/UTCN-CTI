#include <iostream>
#include "Profiler.h"

#define max_size 10000
#define step_size 100
#define nr_test 5
Profiler p("HEAP");



void maxheapify(int arr[], int n, int i)
{
	Operation opComp = p.createOperation("comp_bottom", n);
	Operation opAtrib = p.createOperation("atrib_bottom", n);
	int max = i;
	int st = 2 * i + 1;
	int dr = 2 * i + 2;
	opComp.count();
	if (dr<n && arr[dr]>arr[max])
		max = dr;
	opComp.count();
	if (st<n && arr[st]>arr[max])
		max = st;
	if (max != i)
	{
		opAtrib.count(3);
		int aux;
		aux = arr[i];
		arr[i] = arr[max];
		arr[max] = aux;
		maxheapify(arr, n, max);

	}

}
void buildheap(int arr[], int n)
{
	for (int i = n / 2 - 1; i >= 0; i--)
		maxheapify(arr, n, i);
}
//top down
int parinte(int n)
{
	return(n - 1) / 2;
}
void heapincreasekey(int arr[], int heapsize, int key, int n)
{
	Operation opComp = p.createOperation("comp_topdown", n);
	Operation opAtrib = p.createOperation("atrib_topdown", n);
	opAtrib.count();
	arr[heapsize] = key;
	while (heapsize > 0 && arr[parinte(heapsize)] < arr[heapsize])
	{
		opComp.count();
		opAtrib.count(3);
		int aux = arr[parinte(heapsize)];
		arr[parinte(heapsize)] = arr[heapsize];
		arr[heapsize] = aux;
		heapsize = parinte(heapsize);
	}
	if (heapsize > 0)
		opComp.count();
}
void maxinsert(int arr[], int* heapsize, int key, int n)
{
	Operation opComp = p.createOperation("comp_topdown", n);
	Operation opAtrib = p.createOperation("atrib_topdown", n);
	opAtrib.count(2);
	*heapsize = *heapsize + 1;
	arr[*heapsize - 1] = INT_MIN;
	heapincreasekey(arr, *heapsize - 1, key, n);
}
void buildheap2(int arr[], int n)
{
	Operation opComp = p.createOperation("comp_topdown", n);
	Operation opAtrib = p.createOperation("atrib_topdown", n);
	int i;
	opAtrib.count();
	int heapsize = 1;
	for (i = 1; i < n; i++)
		maxinsert(arr, &heapsize, arr[i], n);
}

void heapsort(int arr[], int n)
{
	buildheap2(arr, n);
	//buildheap(arr,n); 

	for (int i = n - 1; i > 0; i--)
	{
		
		int aux;
		aux = arr[0];
		arr[0] = arr[i];
		arr[i] = aux;
		maxheapify(arr, i, 0);
	}
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
	int n = sizeof(arr) / sizeof(arr[1]);
	heapsort(arr, n);
	printf("Sirul sortat e \n");
	afisare(arr, n);
}
void cazmediustatistic()
{
	int arr1[max_size], arr2[max_size];
	int n, i;
	for (n = step_size; n <= max_size; n += step_size)
		for (int test = 0; test < nr_test; test++)
		{
			FillRandomArray(arr1, n, 10, 50000, false, 0);
			for (int i = 0; i < n; i++)
				arr2[i] = arr1[i];
			buildheap(arr1, n);
			buildheap2(arr2, n);

		}
	p.divideValues("atrib_bottom", nr_test);
	p.divideValues("comp_bottom", nr_test);
	p.addSeries("operatii_bottom", "atrib_bottom", "comp_bottom");

	p.divideValues("atrib_topdown", nr_test);
	p.divideValues("comp_topdown", nr_test);
	p.addSeries("operatii_topdown", "atrib_topdown", "comp_topdown");

	p.createGroup("Total_atribuiri", "atrib_bottom", "atrib_topdown");
	p.createGroup("Total_comparatii", "comp_bottom", "comp_topdown");
	p.createGroup("Total_operatii", "operatii_bottom", "operatii_topdown");
	p.showReport();
}
int main()
{

	demo();
	//cazmediustatistic();
	return 0;
}
