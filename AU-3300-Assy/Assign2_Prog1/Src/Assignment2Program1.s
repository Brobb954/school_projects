/* Assembly language setup example for STM32F3348-Discovery
   Simple program to be built and debugged.
     R/O memory at 0x08000000 (code area)
     R/W memory at 0x20000000 (data area)
*/

// Code section - to begin following startup code
		.section .text.main
		.global  main
main:	mov  r0,#100 		//set r0 = 100
		movt r0,#3	 		//set top of r0 (upper 16 bits) = 3
		mov  r1,#20 	 	//set r1 = -20 and set flags
		add  r2,r0,r1	 	//r2 = r0+r1
		ldr  r4,=CC1 		//address of CC1 to r4 from literal pool
		ldr  r5,[r4]	 	//load value of CC using pointer in r4
		ldr	 r6,=CC2		//address of CC2 to r6 from literal pool
		ldr  r7,[r6]	 	//load value of CC2 to r7
		sub  r2,r2,r7	 	//r2 = r2 - r1
		str  r2,[r6,#4]	 	//store r2 at VarA (4 bytes after CC2), CC2 ptr in r6
Here:	b    Here		   	//effectively halts the program

//Place a 32-bit constant in code memory area address CC1
CC1:	.word 5

//literal pool will be placed here by the assembler
//address of CC1 (literal =CC1)
//address of CC2 (literal =CC2)


// Data section - to begin at 0x20000000
// Initial data values copied from flash to RAM by startup program
		.data
CC2:	.word -3		//allocate 4 bytes for 32-bit word, initialize to -3
VarA:	.word 5  		//allocate 4 bytes for 32-bit word, initialize to 5
Ary1:	.word 6,7,-8	//allocate 3 32-bit words, iniitalized to 6,7,-8
Ary2:	.byte 3,-4,5	//allocate 3 8-bit bytes, iniitalized to 3,-4,5
	    .end	    	//end of assembly language source file
