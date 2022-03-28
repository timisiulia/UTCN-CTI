#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
int afisaredirector(char *path)
{
        DIR *dir = NULL;
        struct dirent *entry = NULL;
        dir = opendir(path);
        if (dir == NULL)
        {
                perror("ERROR\ninvalid directory path");
                return -1;
        }
        else
        {
                printf("SUCCESS\n");
        }
        while ((entry = readdir(dir)) != NULL)
        {
                if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
                        printf("%s/%s\n", path, entry->d_name);
        }
        closedir(dir);
        return 0;
}
void afisarerecursiva(char *path, int k)
{
        DIR *dir = NULL;
        struct dirent *entry = NULL;
        char fullPath[512];
        struct stat statbuf;
        dir = opendir(path);
        if (dir == NULL)
        {
                perror("ERROR\n invalid directory path");
                return;
        }
        else
        {
                if (k == 0)
                {
                        printf("SUCCESS\n");
                        k = 1;
                }
                while ((entry = readdir(dir)) != NULL)
                {
                        if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
                        {
                                snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
                                if (lstat(fullPath, &statbuf) == 0)
                                {
                                        printf("%s\n", fullPath);
                                        if (S_ISDIR(statbuf.st_mode))
                                        {
                                                afisarerecursiva(fullPath, k);
                                        }
                                }

                        }
                }
        }
        closedir(dir);
}


void afisare_dimensiune(char *path, int size)
{
        DIR *dir = NULL;
        struct dirent *entry = NULL;
        dir = opendir(path);
        struct stat statbuf;
        char fullPath[512];

        if (dir == NULL)
        {
                perror("ERROR\ninvalid directory path");
                return;
        }
        else
        {
                printf("SUCCESS\n");
        }
        while((entry = readdir(dir)) != NULL){
                snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
                if(lstat(fullPath, &statbuf) == 0){
                        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
                                if(S_ISREG(statbuf.st_mode) && statbuf.st_size > size){
                                        printf("%s\n", fullPath);
                                }
                        }
                }
        }
        closedir(dir);
}

void afisare_dimensiune_recursiv(char *path, int k, int size)
{
        DIR *dir=NULL;
        struct dirent *entry=NULL;
        char fullPath[512];
        struct stat statbuf;
        dir = opendir(path);
        if (dir == NULL)
        {
                perror("ERROR\n invalid directory path");
                return;
        }
        else
        {
                if (k == 0)
                {
                        printf("SUCCESS\n");
                        k = 1;
                }
                while ((entry = readdir(dir)) != NULL)
                {
                        snprintf(fullPath, 512, "%s/%s", path, entry->d_name);


                        if (lstat(fullPath, &statbuf) == 0)
                        {
                                if ((strcmp(entry->d_name, ".") != 0) && (strcmp(entry->d_name, "..") != 0)){

                                        if (S_ISDIR(statbuf.st_mode))
                                        {
                                                //printf("%s\n", fullPath);
                                                afisare_dimensiune_recursiv(fullPath,k,size);
                                        }
                                        else
                                        {
                                                if(statbuf.st_size > size)
                                                {
                                                        printf("%s\n",fullPath);
                                                }
                                        }
                                }
                        }


                }
        }

        closedir(dir);
}
void permisiuni(char *path)
{
        DIR *dir = NULL;
        struct dirent *entry = NULL;
        dir = opendir(path);
        struct stat statbuf;
        char fullPath[512];

        if (dir == NULL)
        {
                perror("ERROR\ninvalid directory path");
                return;
        }
        else
        {
                printf("SUCCESS\n");
        }
        while((entry = readdir(dir)) != NULL){
                snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
                if(lstat(fullPath, &statbuf) == 0){
                        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
                                if(statbuf.st_mode & 0100){
                                        printf("%s\n", fullPath);
                                }
                        }
                }
        }
        closedir(dir);
}

void permisiuni_recursiv(char *path,int k)
{
        DIR *dir = NULL;
        struct dirent *entry = NULL;
        dir = opendir(path);
        struct stat statbuf;
        char fullPath[512];

        if (dir == NULL)
        {
                perror("ERROR\ninvalid directory path");
                return;
        }

        if (k == 0)
        {
                printf("SUCCESS\n");
                k = 1;
        }

        while((entry = readdir(dir)) != NULL){
                snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
                if(lstat(fullPath, &statbuf) == 0){
                        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
                                if(statbuf.st_mode & 0100){
                                        printf("%s\n", fullPath);
                                        if(S_ISDIR(statbuf.st_mode))
                                                permisiuni_recursiv(fullPath,k);
                                }
                        }
                }
        }
        closedir(dir);
}
void parsare(char *path)
{
        int file_directory=-1;
        file_directory=open(path, O_RDONLY);

        if (file_directory==-1)
        {
                perror("ERROR \n invalid directory path");
                return;
        }
        char magic[3] = {0, 0, 0};
        int header_size=0;
        int version=0;
        int no_of_sections=0;
        int sect_type=0;

        read(file_directory,magic,2);
        read(file_directory,&header_size,2);
        if(strcmp(magic,"XI") !=0)
        {
                printf("ERROR \n wrong magic");
                return;
        }
        read(file_directory, &version,4);
        if (version<123 || version>202)
        {
                printf("ERROR \n wrong version");
                return;
        }
        read(file_directory,&no_of_sections,1);
        if(no_of_sections<8 || no_of_sections>14)
        {
                printf("ERROR \n wrong sect_nr");
                return;
        }

        char name[18];
        int offset=0;
        int size=0;
        for (int i = 0; i < no_of_sections; i++) {
                read (file_directory, name, 18);
                read (file_directory, &sect_type, 1);
                read (file_directory, &offset, 4);
                read (file_directory, &size, 4);

                if (sect_type != 90 && sect_type != 22 && sect_type != 32) {
                        printf("ERROR\nwrong sect_types");
                        return;
                }
        }
        lseek(file_directory, 0, SEEK_SET);
        lseek(file_directory, 9, SEEK_CUR);
        printf("SUCCESS\n");
        printf("version=%d\n", version);
        printf("nr_sections=%d\n", no_of_sections);

        for (int i = 0; i < no_of_sections; i++)
        {
                read (file_directory, &name, 18);
                name[18]='\0';
                read (file_directory, &sect_type, 1);
                read (file_directory, &offset, 4);
                read (file_directory, &size, 4);
                printf("section%d: %s %d %d\n", i + 1, name, sect_type, size);
        }
}
int verificare_parsare(char* path)
{
        int file_directory=-1;
        file_directory=open(path, O_RDONLY);
        if (file_directory==-1)
        {
                perror("ERROR \n invalid directory path");
                return -1;
        }
        char magic[3] = {0, 0, 0};
        int header_size=0;
        int version=0;
        int no_of_sections=0;
        int sect_type=0;

        read(file_directory,magic,2);
        read(file_directory,&header_size,2);
        if(strcmp(magic,"XI") !=0)
        {
                return -1;
        }

        read(file_directory, &version,4);
        if (version<123 || version>202)
        {
                return -1;
        }

        read(file_directory,&no_of_sections,1);
        if(no_of_sections<8 || no_of_sections>14)
        {
                return -1;
        }
        char name[18];
        int offset=0;
        int size=0;
        for (int i = 0; i < no_of_sections; i++) {
                read (file_directory, name, 18);
                read (file_directory, &sect_type, 1);
                read (file_directory, &offset, 4);
                read (file_directory, &size, 4);

                if (sect_type != 92 && sect_type != 22 && sect_type != 32)
                {
                        return -1;
                }
        }
        return 0;
}

int extract_line(char *path, int section, int line) {
        int file_directory=-1;
        file_directory=open(path, O_RDONLY);
        if (file_directory==-1)
        {
                perror("ERROR\ninvalid file\n");
                return -1;
        }
        char magic[3] = {0, 0, 0};
        int header_size=0;
        int version=0;
        int no_of_sections=0;
        int sect_type=0;

        read(file_directory,magic,2);
        read(file_directory,&header_size,2);
        if(strcmp(magic,"XI") !=0)
        {
                perror("ERROR\ninvalid file\n");
                return -1;
        }

        read(file_directory, &version,4);
        if (version<123 || version>202)
        {
                perror("ERROR\ninvalid file\n");
                return -1;
        }

        read(file_directory,&no_of_sections,1);
        if(no_of_sections<8 || no_of_sections>14)
        {
                perror("ERROR\ninvalid file\n");
                return -1;
        }
        char name[18];
        int offset=0;
        int size=0;
        //printf("%s %d %d %d\n", magic, header_size, version, no_of_sections);
        for (int i = 0; i < no_of_sections; i++) {
                read (file_directory, name, 18);
                read (file_directory, &sect_type, 1);
                read (file_directory, &offset, 4);
                read (file_directory, &size, 4);
                //printf("%s %d %d %d\n", name, sect_type, offset, size);
                if (sect_type != 90 && sect_type != 22 && sect_type != 32)
                {
                        perror("ERROR\ninvalid file\n");
                        return -1;
                }
                if(i + 1 == section)
                {
                        lseek(file_directory, offset, SEEK_SET);
                        int line_count = 0;
                        int last_newline = 0;
                        char *buffer = malloc(size);
                        int gasit = 0;
                        int j;
                        read(file_directory, buffer, size);
                        for(j = 1; j < size; j++)
                        {
                                if(buffer[j - 1] == 0x0D && buffer[j] == 0x0A)
                                {
                                        line_count ++;
                                        if(line_count == line)
                                        {
                                                gasit = 1;
                                                break;
                                        }
                                        else
                                        {
                                                last_newline = j + 1;
                                        }
                                }
                        }
                        if(gasit == 1 || line == 1)
                        {

                                printf("SUCCESS\n");
                                for(int k = last_newline; k < j; k++) {
                                        printf("%c", buffer[k]);
                                }
                                free(buffer);
                                return 0;
                        }
                        else
                        {
                                free(buffer);
                                printf("ERROR\ninvalid line\n");
                                return -1;
                        }

                }
        }
        printf("ERROR\ninvalid section\n");
        return -1;
}

int verificare_find(char *path)
{
        int file_directory=-1;
        file_directory=open(path, O_RDONLY);

        if (file_directory==-1)
        {
                return -1;
        }
        char magic[3] = {0, 0, 0};
        int header_size=0;
        int version=0;
        int no_of_sections=0;
        int sect_type=0;

        read(file_directory,magic,2);
        read(file_directory,&header_size,2);
        if(strcmp(magic,"XI") !=0)
        {
                return -1;
        }
        read(file_directory, &version,4);
        if (version<123 || version>202)
        {
                return -1;
        }
        read(file_directory,&no_of_sections,1);
        if(no_of_sections<8 || no_of_sections>14)
        {
                return -1;
        }
        char name[18];
        int offset=0;
        int size=0;
        for (int i = 0; i < no_of_sections; i++) {
                read (file_directory, name, 18);
                read (file_directory, &sect_type, 1);
                read (file_directory, &offset, 4);
                read (file_directory, &size, 4);

                if (sect_type != 90 && sect_type != 22 && sect_type != 32)
                {
                        return -1;
                }
                if(size > 1488)
                        return -1;
        }
        //free(path);
        return 0;
}

void find(char*path, int k)
{

        DIR *dir=NULL ;
        struct dirent *entry =NULL;
        //dir = opendir(path);
        struct stat statbuf;
        char fullPath[512];
        dir = opendir(path);

        if (dir == NULL)
        {
                perror("ERROR\ninvalid directory path");
                //free(path);
                return ;
        }
        else
        {
                if (k==-1){
                        printf("SUCCESS\n");
                        k=1;
                }
        }
        while ((entry = readdir(dir)) != NULL)
        {
                if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
                {
                        snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
                        if (lstat(fullPath, &statbuf) == 0 )
                        {
                                if (S_ISREG(statbuf.st_mode)){

                                        if(verificare_find(fullPath)==0){
                                                printf("%s\n", fullPath);
                                        }

                                }
                                if(S_ISDIR(statbuf.st_mode)) {
                                        find(fullPath, k);
                                }
                        }
                }

        }
        //free(entry);
        closedir(dir);
        //free(path);

}

int main(int argc, char **args)
{
        int list = 0;
        int parse = 0;
        int recursive = 0;
        int extract = 0;
        int has_perm_execute = 0;
        int size = 0;
        int findall=0;
        int line = 0;
        int section = 0;
        char *path;

        for (int i = 1; i < argc; ++i) {
                if (strcmp(args[i], "variant") == 0) {
                        printf("77359\n");
                        return 0;
                } else if (strcmp(args[i], "list") == 0)
                        list = 1;
                else if (strcmp(args[i], "parse") == 0)
                        parse = 1;
                else if (strcmp(args[i], "recursive") == 0)
                        recursive = 1;
                else if (strcmp(args[i], "has_perm_execute") == 0)
                        has_perm_execute = 1;
                else if (strstr(args[i], "path=") == args[i])
                        path = args[i] +5;
                else if (strstr(args[i], "size_greater=") == args[i]) {
                        if (sscanf(args[i] + 13, "%d", &size) != 1)
                                return -1;
                }
                else if (strstr(args[i], "line=") == args[i]) {
                        if (sscanf(args[i] + 5, "%d", &line) != 1)
                                return -1;
                }
                else if (strstr(args[i], "section=") == args[i]) {
                        if (sscanf(args[i] + 8, "%d", &section) != 1)
                                return -1;
                }
                else if(strcmp(args[i],"findall")==0){
                        findall=1;
                }
                else if(strcmp(args[i],"extract")==0){
                        extract = 1;
                }
                else{
                        //return -1;
                }

        }


        if(extract) {
                extract_line(path, section, line);
        }

        else if(list==1 && recursive==0 && size==0 && parse==0 &&has_perm_execute==0 && findall==0)
        {
                afisaredirector(path);
        }
        else if(parse)
        {
                parsare(path);
        }

        else if(recursive==1 && list==1 && size==0 && parse==0 &&has_perm_execute==0 && findall==0)
        {
                afisarerecursiva(path,0);
        }

        else if(size && list==1 && recursive==0 && parse==0 &&has_perm_execute==0 && findall==0 )
        {
                afisare_dimensiune(path,size);
        }
        else if(size && list==1 && recursive==1 && parse==0 &&has_perm_execute==0 && findall==0)
        {
                afisare_dimensiune_recursiv(path,0,size);
        }
        else if(size==0 && list==1 && recursive==0 && parse==0 &&has_perm_execute==1 && findall==0)
        {
                permisiuni(path);
        }
        else if(size==0 && list==1 && recursive==1 && parse==0 &&has_perm_execute==1 && findall==0 )
        {
                permisiuni_recursiv(path,0);
        }

        else if(findall==1 &&size==0 && list==0 && recursive==0 && parse==0 && has_perm_execute==0){
                printf("SUCCESS\n");
                find(path,0);
        }

        return 0;
}