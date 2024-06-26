	.syntax unified
	.text
	.global main
main:	ldr	 	r0,=Ary8	//Loads the memory address for Ary8 into r0
		ldr	 	r1,=Ary32	//Loads the memory address for Ary32 into r1
		ldrsb 	r2,[r0,#0]	//Load first value in Ary8 in r2
		ldr  	r3,[r1,#0]	//Load first value in Ary32 r3
		sub  	r2,r3		//Subtract the first value of each Array
		ldr  	r4,=Diff	//Loads the memory address for Diff into r4
		str  	r2,[r4,#0]	//Stores difference in first value at Diff memory address
		ldrsb 	r2,[r0,#1]	//Loads next value in Ary8
		ldr	 	r3,[r1,#4]	//Loads next value in Ary32
		sub  	r2,r3		//Subtract the second value of each Array
		str	 	r2,[r4,#4]	//Stores difference in second value at Diff memory address
		ldrsb 	r2,[r0,#2]	//Loads third value in Ary8
		ldr  	r3,[r1,#8]	//Loads third value in Ary32
		sub  	r2,r3		//Subtract the third value of each Array
		str	 	r2,[r4,#8]	//Stores difference in third value at Diff memory address
		ldrsb 	r2,[r0,#3]	//Loads fourth value in Ary8
		ldr  	r3,[r1,#12]	//Loads fourth value in Ary32
		sub  	r2,r3		//Subtract the third value of each Array
		str	 	r2,[r4,#12]	//Stores difference in fourth value at Diff memory address

		//Starts Addition portion of program

		ldrsb 	r2,[r0,#0]	//Load first value in Ary8 in r4
		ldr  	r3,[r1,#0]	//Load first value in Ary32 r5
		add  	r2,r3		//Add the first value of each Array
		ldr  	r4,=Sum		//Loads the memory address for Sum into r6
		str  	r2,[r4,#0]	//Stores sum in first value at Sum memory address
		ldrsb 	r2,[r0,#1]	//Loads next value in Ary8
		ldr	 	r3,[r1,#4]	//Loads next value in Ary32
		add  	r2,r3		//Add the second value of each Array
		str  	r2,[r4,#4]	//Stores sum in second value at Sum memory address
		ldrsb 	r2,[r0,#2]	//Loads third value in Ary8
		ldr  	r3,[r1,#8]	//Loads third value in Ary32
		add  	r2,r3		//Add the third value of each Array
		str  	r2,[r4,#8]	//Stores sum in third value at Sum memory address
		ldrsb 	r2,[r0,#3]	//Loads fourth value in Ary8
		ldr  	r3,[r1,#12]	//Loads fourth value in Ary32
		add  	r2,r3		//Add the third value of each Array
		str  	r2,[r4,#12]	//Stores sum in fourth value at Sum memory address
		b	 	.


		.data
Ary8: 	.byte 0xCC, 12, -3, 0xB
Ary32:	.word 10, -5, 0xFFFFFFF4, 0x77777777
Diff:	.word 0, 0, 0, 0
Sum:	.word 0, 0, 0, 0
		.end
