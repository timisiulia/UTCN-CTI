#include <stdlib.h>
#include "Profiler.h"
using namespace std;


#define n_max_size 10000
#define k_max_size 500
#define n_step_size 100
#define nr_test 5
#define k_step_size 10



Profiler p("interclasare");

typedef struct node //definire element pentru viitoarea lista
{
    int val;
    struct node* next;
} Node;

Node* newNode(int nr)// creeaza un element
{
    Node* n = (Node*)malloc(sizeof(Node));
    if (n != NULL)
    {
        n-> val = nr;
        n->next = NULL;
    }
    return n;
}

typedef struct list // definire structura lista
{
    Node* front;//primul element
    Node* rear;//ultimul element
} List;

List* lists[k_max_size];
int copy_arr[n_max_size];

List* newList()//creare lista noua 
{
    List* l = (List*)malloc(sizeof(List));
    if (l != NULL)
    {
        //se aloca memorie pentru lista, pentru capul de lista, si finalul ei
        l->front = NULL;
        l->rear = NULL;
    }
    return l;
}


void InsertNode(List* l, Node* n)
{
    if (l->front == NULL)
    {
        //daca lista e goala inseram un elemet, pe prima si ultima poz 
        l->front = n;
        l->rear = n;
    }
    else 
    {
        //daca exista elemente in lista, insereaza la final
        l->rear->next = n;
        l->rear = n;
    }
}

void FreeList(List* l)//elibereaza memoria dupa crearea listei
{
    Node* p = l->front;
    Node* aux;
    while (p != NULL)
    {
        aux = p;
        p = p->next;
        free(aux);
    }
    free(l);
}

void minheapify(Node* arr[], int n, int i, int size)
{
    Operation op = p.createOperation("interclasare", size);
    int min = i;
    int st = 2 * i ;
    int dr = 2 * i + 1;
    op.count();
    if (dr <= n && arr[dr] ->val < arr[min]->val)
        min = dr;
    op.count();
    if (st <=n && arr[st]->val < arr[min]->val)
        min = st;
    if (min != i)
    {
        op.count(3);
        Node* aux;
        aux = arr[i];
        arr[i] = arr[min];
        arr[min] = aux;
        minheapify(arr, n, min,size);

    }

}

void buildheap(Node* arr[], int n,int size)
{
    for (int i = n / 2 - 1; i >= 1; i--)
        minheapify(arr, n, i,size);
}

List* interlist(int n, int k, int size)
{
    Operation op = p.createOperation("interclasare", size);
    Node* aux;
    List* result = newList();//initializare lista
    Node* heap[k_max_size];
    for (int i = 0; i < k; ++i) 
        heap[i + 1] = lists[i]->front;

    buildheap(heap, k, size);
    while (k)// cat timp e >0
    {
        InsertNode(result, heap[1]);//insereaza in lista finala primul nod
        op.count();
        heap[1] = heap[1]->next;//trecem la pozitia urmatoare

        if (heap[1] == NULL) //verificam daca mai exista elemente in lista        
        {
            //ultima frunza o punem ca radacina
            aux = heap[1];
            heap[1] = heap[k];
            heap[k] = aux;
            k--;
        }
        minheapify(heap, k, 1, size);//iar se face ordoare
    }
    return result;
}
void test1(int k)//testare pentru k constant(5, 10, 100)
{
    for (int i = 0; i < k; ++i)// se initializeaza lista
        lists[i] = newList();
    
    for (int i = 1; i <= nr_test; ++i)
    {
        for (int n = n_step_size; n <= n_max_size; n += n_step_size)
        {
            for (int j = 0; j < k; ++j)//se creaza lista
            {
                FillRandomArray(copy_arr, n, 10, 50000, false, ASCENDING);
                for (int b = 0; b < n; ++b)
                    InsertNode(lists[j], newNode(copy_arr[b]));
            }
            List* result = interlist(n, k, n);// se face interclasarea pt un k 
            FreeList(result);
            for (int j = 0; j < k; ++j)
            {
                lists[j]->rear = lists[j]->front = NULL;//reinitializare lista
            }
        }
    }

}

void test2()//testare pentru k variabil de la 10 la 500
{
    for (int i = 0; i < k_max_size; ++i)
        lists[i] = newList();
    for (int i = 1; i <= nr_test; ++i)
    {
        for (int k = k_step_size; k <= k_max_size; k += k_step_size)
        {
            for (int j = 0; j < k; ++j)
            {
                FillRandomArray(copy_arr, n_max_size, 10, 50000, false, ASCENDING);
                for (int b = 0; b < n_max_size; ++b)
                    InsertNode(lists[j], newNode(copy_arr[b]));
            }
            List* result = interlist(n_max_size, k, k);
            FreeList(result);
            for (int j = 0; j < k; ++j)
            {
                lists[j]->front = lists[j]->rear = NULL;
            }
        }
    }
    p.divideValues("mergeklists", nr_test);
}

void PrintList(List* l)
{
    Node* p = l->front;
    while (p != NULL)
    {
        printf("%d ", p->val);
        p = p->next;
    }
    printf("\n");
}

void demo()
{
    for (int i = 0; i < 3; ++i)
        lists[i] = newList();
    int arr1[] = { 0 , 12, 30, 32, 100 };
    int arr2[] = { 1, 4, 5, 9, 23 };
    int arr3[] = { 15, 17, 19, 21, 25 };
    int n = sizeof(arr1) / sizeof(arr1[0]);
    for (int i = 0; i < n; ++i)
        InsertNode(lists[0], newNode(arr1[i]));
    for (int i = 0; i < n; ++i)
        InsertNode(lists[1], newNode(arr2[i]));
    for (int i = 0; i < n; ++i)
        InsertNode(lists[2], newNode(arr3[i]));

    int size = 3;
    int k = 3;

    List* result = interlist(n, k, 3);
    PrintList(result);
}


void test() {
    test1(5);
    p.reset("k=5");
    test1(10);
    p.reset("k=10");
    test1(100);
    p.reset("k=100");
    test2();
    p.showReport();


}
int main()
{
   //demo();
   test();
}

