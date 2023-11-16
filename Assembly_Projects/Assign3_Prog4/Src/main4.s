	.syntax unified
	.text
	.global main
main:	ldr	 	r0,=Ary8	//Loads the memory address for Ary8 into r0
		ldr	 	r1,=Ary32	//Loads the memory address for Ary32 into r1
		ldr  	r2,=Diff	//Loads the memory address for Diff into r2
		ldr  	r3,=Sum		//Loads the memory address for Sum into r3
		mov	 	r4,#0		//Index for auto-indexing Ary8
		mov  	r5,#0		//Index for auto-indexing Ary32
loop:	ldrsb 	r6,[r0,r4]	//Loads the first value from Ary8 into r6
		ldr  	r7,[r1,r5]	//Loads the first value from Ary32 into r7
		sub  	r8,r6,r7	//Subtracts the first value of Ary8 and Ary32 and stores it in r8
		add  	r9,r6,r7	//Adds the first value of Ary8 and Ary32 and stores it in r9
		str	 	r8,[r2,r5]	//Stores difference of first values in first slot of Diff
		str	 	r9,[r3,r5]	//Stores sum of first values in first slot of Sum
		add	 	r4,#1		//Adds 1 to iterate to next value of Ary8
		add  	r5,#4		//Adds 4 to r5 to iterate next value of Ary32, Diff, and Sum
		cmp  	r4,#4		//Compares r4 to 3
		blt  	loop		//Repeat loop if compare is false
		b	 	.


		.data
Ary8: 	.byte 0xCC, 12, -3, 0xB
Ary32:	.word 10, -5, 0xFFFFFFF4, 0x77777777
Diff:	.word 0, 0, 0, 0
Sum:	.word 0, 0, 0, 0
		.end
