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
//Include eHealth library (it includes arduPi)
#include "eHealth.h"

gpio_pin apin;
lkm_data lkmdata;
#define BUFFER_SIZE 1024

 

        int getHR(int fd){
                return getBPM() // Returns the heart beats per minute.

        }

        int getSYS(int fd){
            getSystolicPressure(i)    // Returns the  value of the systolic pressure number i.
   
        }

        int getDYS(int fd){
            getDiastolicPressure(i)   // Returns the  value of the diastolic pressure number i.

        }

        int getTemp(int fd){
            int getTemperature() // Returns the corporal temperature.
   
        }
