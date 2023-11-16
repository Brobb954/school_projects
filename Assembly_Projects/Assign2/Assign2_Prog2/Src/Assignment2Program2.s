// Memory alocation example
	.syntax unified
	.text
	.global main
main: 				// Register contents after instruction:
					//      My prediction        Actual value
	mov	r0,#10		// r0 =__10___________      __10__________
	ldr	r1,=pp		// r1 =__0x20000000___      __0x2000000___
	ldr	r2,[r1]		// r2 =__0x12345678___      __0x12345678__
	mov	r1,r0		// r1 =__10___________      __10__________
	add	r3,r2,r0	// r3 =__0x12345682___      __0x12345682__
	ldr	r1,=fr		// r1 =__0x2000000C___      __0x2000000c__
	ldr	r2,=ir		// r2 =__0x20000004___      __0x20000004__
	ldr	r3,[r1]		// r3 =__0x41_________      __0x41________
	ldr	r4,=tmp		// r4 =__0x2000001C___      __0x20000028__
	ldr	r5,[r2]		// r5 =__0x070605_____      __0x70605_____
	ldr	r1,=512		// r1 =__512__________      __0x200_______
	add	r2,r1,r5	// r2 =__0x070805_____      __0x70805_____
	sub r0,r6,r6	// r0 =__0____________      __0x0_________
	b	.			// Equivalent to Here b Here

// Data allocated and initialized in SRAM
		.data
pp:		.word	0x12345678
ir:		.byte	5,6,7
		.align
ts:		.word	0x0805
fr:		.byte	'A'
		.align
reg:	.word	18,-20,15,-10
mem:	.space 	8
tmp:	.word	0x1234,0x5678

	.end
