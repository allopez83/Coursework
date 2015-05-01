	.text
	.globl main

main:		li $v0,10		#sys call for exit
		syscall

		.data
nums:		.byte   4,3,2,1
		.half   8,7,6,5
		.word  1,2,3,4
