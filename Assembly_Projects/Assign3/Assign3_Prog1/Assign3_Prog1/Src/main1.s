	.syntax unified
	.text
	.global main
main:	ldr r0,=Leia	//Adds the memory address of Leia to r0
		ldr r1,[r0]		//Adds value of Leia to r1
		ldr r2,=Han		//Adds the memory address of Han to r2
		ldr r3,[r2]		//Adds value of Han to r3
		ldr r4,=Luke	//Adds the memory address of Luke to r4
		ldr r5,[r4]		//Adds the value of Luke to r5
		ldr r6,=Yoda	//Adds the memory address of Yoda ti r6
		sub r7,r1,r3	//Subracts Han from Leia
		sub r8,r5,#123	//Subracts 123 from Luke
		add r7,r7,r8	//Adds the results of Han, Leia, 123 and Luke
		str r7,[r6]		//Stores the sum of r0 and r2 in the memory address of r6
		b	.




// Data section starts at 0x20000000
	.data
Leia:	.word	400		//Allocates one 32-bit word, initialized to 400
Han:	.word	550		//Allocates one 32-bit word, initialized to 550
Luke:	.word	200		//Allocates one 32-bit word, initialized to 200
Yoda:	.word	0		//Allocates one 32-bit word, initialized to 0
		.end			//End of source file
