	.syntax unified
	.text
	.global main
main:
	// Load status, control, and periph values
	ldr		r1,=Status
	ldrb	r2,[r1]

	ldr		r3,=Control
	ldrb	r4,[r3]

	ldr		r5,=Periph
	ldrb	r6,[r5]

	// Check if bit 4 of status is 0
	tst		r2,0x10
	beq		set0

	orr		r4,0x04			// Set bit 2 of control to 1
	b		strc1
set0:
	bic		r4,0x08			//Set bit 3 of control to 0
strc1:
	strb	r4,[r3]			// Store changed bits

	// Check if bits 1 and 2 of status are 0
	tst		r2,0x06
	beq		set1


	orr		r4,0x40			//Set bit 6 of control to 1
	b		strc2
set1:
	bic		r4,0x20			//Set bit 5 of control to 0
strc2:
	strb	r4,[r3]			// Store changed bit


	// Replace bits 6-3 of periph with hex 5
	bic		r6,0x78			//Clear bits 6-3 in prep to replace
	orr		r6,0x28			//Replace cleared bits with 5s
	strb	r6,[r5]
	b		.

// Data section starts at 0x20000000
	.data
Status:		.byte	0xC1	//Allocates 8-bits of memory
Control:	.byte 	0xA8	//Allocates 8-bits of memory
Periph:		.byte	0xD2	//Allocates 8-bits of memory
			.end			//End of source file
