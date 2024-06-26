	.syntax unified
	.text
	.global main
main:
	ldr		r0,=STRG	// Load address of STRG
	ldr		r1,=PACK	// Load address of PACK
	add		r0,#7		// Set str pointer to least significant byte of STRG

loop:
	ldrb	r2,[r1]			//Load value of PACK
	add		r1,#1			// Increment address

	// Handle ones digit
	and		r3,r2, #0x0F	// Extract ones digit
	add		r3,#48			// Convert ones digit to ascii
	strb	r3,[r0],#-1		// Store ascii digit in STRG and decrement

	// Handle tens digit
	mov		r2,r2,lsr #4	// Shift right to get value of tens digit in r2
	add		r2,#48			// Convert tens digit to ascii equivalant
	strb	r2,[r0],#-1		// Store ascii digit into STRG and decrement

	cmp		r1,#0			// Compare r1 to 0
	beq		end				// End program if equal to 0
	b		loop			// Repeat loop

end:
	b		.				// Branch here



		.data
STRG:	.byte	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
PACK:	.word	0x13587609
		.end

