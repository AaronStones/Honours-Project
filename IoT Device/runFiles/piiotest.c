

/*
 ============================================================================
 Name        : piiotest.c
 Author      : CMP408 - Aaron Stones 1600964
 Version     : 0.1
 Copyright   : See Abertay copyright notice
 Description : Test App for PIIO Drivers
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h> 
#include <time.h> 
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <string.h>
#include"piio.h"
#include "readings.c"

gpio_pin apin;
lkm_data lkmdata;
#define BUFFER_SIZE 1024

        int printRandoms(int lower, int upper, int count){ 
        int i; 
                for (i = 0; i < count; i++) { 
                        int num = (rand() % 
                        (upper - lower + 1)) + lower; 
                        return num; 

                }
        } 
  

        int getRandHR(int fd){
                int lower = 60, upper = 100, count = 1; 
                int val = printRandoms(lower, upper, count);
                return val;     
        }

        int getRandSYS(int fd){
                int count = 1; 
                return printRandoms(100, 140, count);     
        }

        int getRandDYS(int fd){
                int count = 1; 
                return printRandoms(20, 80, count);     
        }

        int getRandTemp(int fd){
                int count = 1; 
                return printRandoms(36, 40, count);     
        }
        int getRandShake(int fd){
                int count = 1; 
                return printRandoms(50, 100, count);     
        }

        int main(int argc, char *argv[]) {
        srand(time(0)); 

        int fd, ret;
        char *msg = "Message passed by ioctl\n";

        fd = open("/dev/dev/dAaronStones", O_RDWR);
        if (fd < 0) {
                printf("Can't open device file: %s\n", DEVICE_NAME);
                exit(1);
        }


        if (argc > 1) {
                if (!strncmp(argv[1], "getRandHR",8)) {
                        printf("%d",getRandHR(fd));

                }

                if (!strncmp(argv[1], "getRandSYS", 9)) {
                        printf("%d",getRandSYS(fd));
                }
                if (!strncmp(argv[1], "getRandDYS", 9)) {
                        printf("%d",getRandDYS(fd));
                }
                if (!strncmp(argv[1], "getRandTemp", 9)) {
                        printf("%d",getRandTemp(fd));
                }
                if (!strncmp(argv[1], "getRandShake", 9)) {
                        printf("%d",getRandShake(fd));
                }             
        }


        close(fd);
        return 0;
}

