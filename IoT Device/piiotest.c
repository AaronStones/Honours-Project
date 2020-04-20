

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
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <string.h>
#include"piio.h"

/*
 * Functions for the ioctl calls
 *
 root@raspberrypi:/home/pi/LewisGoor_1600580_CW2# ./a.out writepin 3 1
User App
 WRITE:Requested pin:3 - val:1 - desc:WriteOpt
 Exit 0
 root@raspberrypi:/home/pi/LewisGoor_1600580_CW2# ./a.out readpin 3
 User App
 READ:Requested  pin:3 - val:1 - desc:LKMpin
 Exit 0
 root@raspberrypi:/home/pi/LewisGoor_1600580_CW2# ./a.out togglepin 3 0 2 90000
 TOGGLE:Requesting pin:3 - val:0 - desc:tgl
 TOGGLE:Requesting pin:3 - val:1 - desc:tgl
 */

gpio_pin apin;
lkm_data lkmdata;

void write_to_driver(int fd) {
        int ret;
        /* Write to kernel space - see dmesg command*/

        strcpy(lkmdata.data, "This is from user application");
        lkmdata.len = 32;
        lkmdata.type = 'w';
        ret = ioctl(fd, IOCTL_PIIO_WRITE, &lkmdata);

        if (ret < 0) {
                printf("Function failed:%d\n", ret);
                exit(-1);
        }

}

void read_from_drvier(int fd) {
        int ret;

        /*Read from kernel space - see dmesg command*/
        strcpy(lkmdata.data, "");
        ret = ioctl(fd, IOCTL_PIIO_READ, &lkmdata);

        if (ret < 0) {
                printf("Function failed:%d\n", ret);
                exit(-1);
        }

        printf("Message from driver: %s\n", lkmdata.data);
}


        int main(int argc, char *argv[]) {
        printf("IoT disseration project\n");
        int fd, ret;
        char *msg = "Message passed by ioctl\n";

        fd = open("/dev/dev/dAaronStones", O_RDWR);
        if (fd < 0) {
                printf("Can't open device file: %s\n", DEVICE_NAME);
                exit(1);
        }


        if (argc > 1) {
                if (!strncmp(argv[1], "readmsg", 8)) {
                        read_from_drvier(fd);

                }

                if (!strncmp(argv[1], "writemsg", 9)) {
                        write_to_driver(fd);
                }

                if (!strncmp(argv[1], "readpin", 8)) {

                        /*  Pass GPIO struct with IO control */
                        memset(&apin , 0, sizeof(apin));
                        strcpy(apin.desc, "Details");
                        apin.pin =  strtol (argv[2],NULL,10);
                        /* Pass 'apin' struct to 'fd' with IO control*/
                        ret = ioctl(fd, IOCTL_PIIO_GPIO_READ, &apin);
                        printf("READ:Requested  pin:%i - val:%i - desc:%s\n" , apin.pin , apin.value, apin.desc);
                }

                if (!strncmp(argv[1], "writepin", 9)) {
                        /*  Pass GPIO struct with IO control */
                        memset(&apin , 0, sizeof(apin));
                        strcpy(apin.desc, "WriteOpt");
                        apin.pin = strtol (argv[2],NULL,10);
                        apin.value = strtol (argv[3],NULL,10);
                        /* Pass 'apin' struct to 'fd' with IO control*/
                        printf("WRITE:Requested pin:%i - val:%i - desc:%s\n" , apin.pin , apin.value, apin.desc);
                        ret = ioctl(fd, IOCTL_PIIO_GPIO_WRITE, &apin);
                }

                if(!strncmp(argv[1], "togglepin", 7))
                {
                //pass GPIO structure with I/O controls
                memset(&apin, 0, sizeof(apin));
               strcpy(apin.desc, "tgl");
                apin.amount = strtol(argv[4], NULL, 10);
                apin.delay = strtol(argv[5], NULL, 10);
                apin.pin = strtol(argv[2], NULL, 10);
                apin.value = strtol(argv[3], NULL, 10);
                apin.delay = apin.delay / 1000000;
                int del = apin.delay;
 
                        for(int x = 0; x < apin.amount; x++)
                        {
                                //pass apin structure to fd with I/O controls
                                printf("TOGGLE:Requesting pin:%i - val:%i - desc:%s\n", apin.pin, apin.value, apin.desc);
                                ret = ioctl(fd, IOCTL_PIIO_GPIO_WRITE, &apin);
                                if (apin.value == 1){
                                        apin.value = 0;
                                }
                                else {
                                        apin.value = 1;
                                }
                                sleep(apin.delay);
                        }
                }
        }


        printf("Exit 0\n");

        close(fd);
        return 0;
}

