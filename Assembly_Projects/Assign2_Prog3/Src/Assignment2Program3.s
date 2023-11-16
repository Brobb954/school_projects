	.syntax unified
	.text
	.global main
main:	mov r0, #5	//Moves 5 into r0
		mov r1, 13	//Moves 13 into r1
		add r2, r0, #5	//Adds k and 5 and puts it in r2
		add r1, r1, r0  //Adds 12 and 5 and puts it in r1
		add r1, r1, r2  //Adds 17 and 10 and puts it in r1
		b	.
		.end


