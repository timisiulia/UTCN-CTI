#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "a2_helper.h"
#include <fcntl.h>
#include <sys/stat.h>
#include <semaphore.h>
#include <pthread.h>
#define nr_threads 4
#define nr_threads7 36
#define nr_threads4 4

typedef struct
{
	int id;
	pthread_mutex_t* lock;
	pthread_cond_t* cond;
} th_struct;

pthread_mutex_t ok1 = PTHREAD_MUTEX_INITIALIZER, ok2 = PTHREAD_MUTEX_INITIALIZER;

void* apel_creare(void* args)
{
	th_struct* numeStruct = (th_struct*)args;

	if (numeStruct->id == 2)
		pthread_mutex_lock(&ok1);

	info(BEGIN, 3, numeStruct->id);

	if (numeStruct->id == 4)
		pthread_mutex_unlock(&ok1);

	if (numeStruct->id == 4)
		pthread_mutex_lock(&ok2);

	info(END, 3, numeStruct->id);

	if (numeStruct->id == 2)
		pthread_mutex_unlock(&ok2);

	return NULL;
}

void creare()
{
	int i;
	th_struct params[nr_threads + 1];
	pthread_t tids[nr_threads + 1];
	pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
	pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

	pthread_mutex_lock(&ok1);
	pthread_mutex_lock(&ok2);

	for (i = 1; i <= nr_threads; i++)
	{
		params[i].id = i;
		params[i].lock = &lock;
		params[i].cond = &cond;

		pthread_create(&tids[i], NULL, apel_creare, &params[i]);

	}
	for (i = 1; i <= nr_threads; i++)
	{
		pthread_join(tids[i], NULL);
	}

	pthread_mutex_destroy(&ok1);
	pthread_mutex_destroy(&ok2);

	pthread_cond_destroy(&cond);
	pthread_mutex_destroy(&lock);
}

void* apel_creare7(void* args)
{
	th_struct* numeStruct = (th_struct*)args;

	info(BEGIN, 7, numeStruct->id);
	info(END, 7, numeStruct->id);

	return NULL;
}

void creare7()
{
	int i;
	th_struct params7[nr_threads7 + 1];
	pthread_t tids7[nr_threads7 + 1];
	pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
	pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

	pthread_mutex_lock(&ok1);
	pthread_mutex_lock(&ok2);

	for (i = 1; i <= nr_threads7; i++)
	{
		params7[i].id = i;
		params7[i].lock = &lock;
		params7[i].cond = &cond;

		pthread_create(&tids7[i], NULL, apel_creare7, &params7[i]);

	}
	for (i = 1; i <= nr_threads7; i++)
	{
		pthread_join(tids7[i], NULL);
	}

	pthread_mutex_destroy(&ok1);
	pthread_mutex_destroy(&ok2);

	pthread_cond_destroy(&cond);
	pthread_mutex_destroy(&lock);
}


void* apel_creare4(void* args)
{
	th_struct* numeStruct = (th_struct*)args;

	info(BEGIN, 4, numeStruct->id);
	info(END, 4, numeStruct->id);

	return NULL;
}

void creare4()
{
	int i;
	th_struct params4[nr_threads4 + 1];
	pthread_t tids4[nr_threads4 + 1];
	pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
	pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

	pthread_mutex_lock(&ok1);
	pthread_mutex_lock(&ok2);

	for (i = 1; i <= nr_threads4; i++)
	{
		params4[i].id = i;
		params4[i].lock = &lock;
		params4[i].cond = &cond;

		pthread_create(&tids4[i], NULL, apel_creare4, &params4[i]);

	}
	for (i = 1; i <= nr_threads4; i++)
	{
		pthread_join(tids4[i], NULL);
	}

	pthread_mutex_destroy(&ok1);
	pthread_mutex_destroy(&ok2);

	pthread_cond_destroy(&cond);
	pthread_mutex_destroy(&lock);
}



int main()
{
	init();

	info(BEGIN, 1, 0);
	pid_t pid2 = -1;
	pid_t pid3 = -1;
	pid_t pid4 = -1;
	pid_t pid5 = -1;
	pid_t pid6 = -1;
	pid_t pid7 = -1;
	pid_t pid8 = -1;
	pid2 = fork();
	if (pid2 == -1)
	{
		return -1;
	}
	else if (pid2 == 0)
	{
		info(BEGIN, 2, 0);
		pid7 = fork();
		if (pid7 == -1)
		{
			return -1;
		}
		else if (pid7 == 0)
		{
			info(BEGIN, 7, 0);

			creare7();

			info(END, 7, 0);
		}
		else
		{
			waitpid(pid7, NULL, 0);
			info(END, 2, 0);
		}
	}
	else
	{
		pid3 = fork();
		if (pid3 == -1)
		{
			return-1;
		}
		else if (pid3 == 0)
		{
			info(BEGIN, 3, 0);

			creare();

			pid6 = fork();
			if (pid6 == -1)
			{
				return -1;
			}
			else if (pid6 == 0)
			{
				info(BEGIN, 6, 0);
				info(END, 6, 0);
			}
			else
			{
				waitpid(pid6, NULL, 0);
				info(END, 3, 0);

			}
		}
		else
		{
			pid4 = fork();
			if (pid4 == -1)
			{
				return-1;
			}
			else if (pid4 == 0)
			{
				info(BEGIN, 4, 0);

				creare4();

				pid8 = fork();
				if (pid8 == -1)
				{
					return -1;
				}
				else if (pid8 == 0)
				{
					info(BEGIN, 8, 0);
					info(END, 8, 0);
				}
				else
				{
					waitpid(pid8, NULL, 0);
					info(END, 4, 0);

				}
			}
			else
			{
				pid5 = fork();
				if (pid5 == -1)
				{
					return-1;
				}
				else if (pid5 == 0)
				{
					info(BEGIN, 5, 0);
					info(END, 5, 0);
				}
				else
				{
					waitpid(pid2, NULL, 0);
					waitpid(pid3, NULL, 0);
					waitpid(pid4, NULL, 0);
					waitpid(pid5, NULL, 0);
					info(END, 1, 0);
				}
			}
		}
	}
	return 0;
}