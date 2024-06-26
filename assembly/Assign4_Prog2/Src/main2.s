	.syntax unified
	.text
	.global main
main:
	ldr		r1,=String		// Loads string address

loop:
	ldrb	r2,[r1]			// Loads next char from string
	cmp		r2,#0			// Sets 0 flag if null
	beq		end				// If null char, terminate loop
	cmp		r2,#65			// Check if char is before 'A'
	blt		rdy				// Skip conversion if true
	cmp		r2,#90			// Check if char is after 'Z'
	bgt		rdy				// Skip conversion if true
	add		r2,0x20			// Convert to lowercase
	strb	r2,[r1]			// Store to string

rdy:
	add		r1,#1			// Move to next char
	b		loop			// Repeat loop

end:
	b		.				//bEnd

// Data section starts at 0x20000000
	.data
//String:	.asciz "xv^74paKdKc7"		// String to convert run 1
String: .asciz "SQVL#Z5xKGR2k#v&u8"	// String to convert run 2
