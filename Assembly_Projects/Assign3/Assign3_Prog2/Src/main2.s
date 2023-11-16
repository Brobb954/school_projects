	.syntax unified
	.text
	.global main
main:	ldr r0,=Leia	 //Adds the memory address of Leia to r0
		ldr r1,[r0]		 //Adds value of Leia to r1
		ldr r2,[r0, #4]	 //Adds value of Han to r2
		ldr r3,[r0, #8]	 //Adds the value of Luke to r3
		sub r4,r1,r2	 //Subracts Han from Leia
		sub r5,r3,#123	 //Subracts 123 from Luke
		add r6,r4,r5	 //Adds the results of Han, Leia, 123 and Luke
		str r6,[r0, #12] //Stores the sum of r0 and r2 in the memory address of r6
		b	.




// Data section starts at 0x20000000
	.data
Leia:	.word	400		//Allocates one 32-bit word, initialized to 400
Han:	.word	550		//Allocates one 32-bit word, initialized to 550
Luke:	.word	200		//Allocates one 32-bit word, initialized to 200
Yoda:	.word	0		//Allocates one 32-bit word, initialized to 0
		.end			//End of source file
