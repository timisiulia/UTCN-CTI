#include<stdio.h>
#include"Profiler.h"
#define Dimensiune_Max 10000
#define suta 100
#define NR_TESTE 10

Profiler p("Metode_sortare");

void bubbleSort(int arr[], int n)
{
    Operation Comparatii = p.createOperation("comparatii_bubble", n);
    Operation Atribuiri = p.createOperation("atributii_bubble", n);
    int i, j, temp;
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < n - i - 1; j++)
        {
            Comparatii.count();

            if (arr[j] > arr[j + 1])
            {
                // swap the elements

                Atribuiri.count(3);
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
void SelectionSort(int a[], int n) {
    Operation Comparatii = p.createOperation("comparatii_selection", n);
    Operation Atribuiri = p.createOperation("atributii_selection", n);
    int i = 0, min;
    while (i < n) {

        min = i;
        for (int j = i + 1; j < n; j++) {
            Comparatii.count();
            if (a[min] > a[j]) min = j;
        }
            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
                Atribuiri.count(3);
            }
            i++;
        }
    }

        void testare()
        {
            int vector1[] = { 4,9,6,7,2,1,5 };
            int n = sizeof(vector1) / sizeof(vector1[0]);
            int vector2[] = { 4,9,6,7,2,1,5 };
          

            printf("Sirul nesortat: \n");
            for (int i = 0; i < n; i++)
                printf("%d ", vector1[i]);
            printf("\n");


            bubbleSort(vector1, n);
            SelectionSort(vector2, n);

            printf("BubbleSort: \n");
            for (int i = 0; i < n; i++)
                printf("%d ", vector1[i]);
            printf("\n");

            
            printf("SelectionSort: \n");
            for (int i = 0; i < n; i++)
                printf("%d ", vector2[i]);
            printf("\n");

        }
        void copiere(int a[], int b[], int n) {
            for (int i = 0; i < n; i++) {
                b[i] = a[i];
            }
        }
        void perf(int order)
        {
            int v1[Dimensiune_Max], v3[Dimensiune_Max];
            int n;
            int min1 = 0, min2 = 0;
            for (n = suta ; n <= Dimensiune_Max; n += suta)
            {
                for (int test = 0; test < NR_TESTE; test++) {
                    FillRandomArray(v1, n, 10, 50000, false, order);
               
                    copiere(v1, v3, n);
                    bubbleSort(v1, n);
                    SelectionSort(v3, n);
                }
            }
            p.divideValues("atributii_bubble",NR_TESTE);
            p.divideValues("comparatii_bubble", NR_TESTE);
            p.addSeries("bubbleSort", "atributii_bubble", "comparatii_bubble");
        
            p.divideValues("atributii_selection", NR_TESTE);
            p.divideValues("comparatii_selection", NR_TESTE);
            p.addSeries("selectionSort", "atributii_selection", "comparatii_selection");

            p.createGroup("Atribuiri ", "atributii_bubble", "atributii_selection");
            p.createGroup("Comparatii", "comparatii_bubble",  "comparatii_selection");
            p.createGroup("Total", "bubbleSort", "selectionSort");
            
        }
       void testare_toate() {
            perf(UNSORTED);
            p.reset("Cel_mai_bun_caz");
            perf(ASCENDING);
            p.reset("Cel mai rau caz");
            perf(DESCENDING);
            p.showReport();
        }
  
    int main() {
        testare();
        //testare_toate();
        return 0;
    }