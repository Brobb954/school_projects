	.syntax unified
	.text
	.global main
main:
	ldr		r0,=LIST1	// Load address of LIST1
	mov		r1,#20		// Set r1 to length of LIST1
	bl		subneg		// Branch to subroutine to count negatives
	ldr		r2,=NEGS1	// Load address of NEGS1
	str		r0,[r2]		// Store returned count of negative numbers
	ldr		r0,=LIST2	// Load address of LIST2
	mov		r1,#12		// Set r1 to length of LIST2
	bl		subneg		// Branch to subroutine to count negatives
	ldr		r2,=NEGS2	// Load address of NEGS2
	str		r0,[r2]		// Store returned count of negative numbers
	b		.			// Branch here


subneg:
	mov		r2,#0		// Intialize negative counter
loop:
	ldr		r5,[r0],#4 	// Loads next value of LIST
	adds	r5,#0		// Determine if integer is negative
	bpl		notneg		// Branch if positive
	add		r2,#1		// Increment negative counter
	subs	r1,#1		// Subtract number in list from loop counter
	bne		loop		// Repeat loop if not equal to 0
back:
	mov		r0,r2		// Move negative count to r0
	bx		lr			//Branch back to main

notneg:
	subs	r1,#1		// Subtract number in list from loop counter
	beq		back		// Branch to main if equal to 0
	b		loop		// Repeat loop

		.data
LIST1:	.word	5, -8, 20, 15, -20, 83, 125, -107, 0, 35, 14, -19, -30, 0, 35, -8, -6, 22, 0, -1
NEGS1:	.word	0
LIST2:	.word	18, -200, -50, -25, 123, 5, -3, -2, 0, 0, -125, 177
NEGS2:	.word	0
		.end
