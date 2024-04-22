	.syntax unified
	.text
	.global main
main:	ldr	 r0,=N		//Loads the mem add of n into r0
		ldr  r1,[r0]	// Loads the value of N into r1
		ldr  r2,=Ary1	//Loads the mem add of Ary1 into r1
		ldr	 r3,=Ary2	//Loads the mem add of Ary2 into r2
		ldr	 r4,=Ary3	//Loads the mem add of Ary3	into r3
		ldr	 r5,=Ary4	//Loads the mem add of Ary4 into r4
loop:	ldr  r6,[r3,r1]	//Loads the value at slot n of Ary2
		ldr  r7,[r4,r1]	//Loads the value at slot n of Ary3
		ldr  r8,[r5,r1]	//Loads the value at slot n of Ary4
		sdiv r9,r6,r7	//Divides the first value of Ary2 and 3
		mul	 r10,r7,r8	//Multiplies the first value of Ary 3 and 4
		add  r9,r10		//Adds the product and qoutient of slot n
		str	 r9,[r2,r1]	//Stores the sum
		add	 r1,#4		//Increments n
		cmp	 r1,#12		//Compares n to 2 which would be the last item in each array
		blt  loop		//Repeats loop if r0 is less than 2
		b	 .



		.data
N:		.word	0
Ary1:	.word	0, 0, 0
Ary2:	.word	600, -500, 100
Ary3:	.word	24, 40, -20
Ary4:	.word	1400, -1400, 400
