# b1 b2  g  r  s
# t0 t1 t2 t3 t4

# b1 - first byte - $t0
# b2 - second byte - $t1

# t5 - value to print
# t6 - possibly modified first bit
# t7 - possibly modified second bit
# s0 - return address, saved when necessary

# Print line for grs reference
li  $v0, 4
la  $a0, space1
syscall
la  $a0, grsTitle
syscall
la  $a0, newLine
syscall

# Rounding 01
li  $v0, 4   # print bit binary being rounded
la  $a0, bitBinary1
syscall
li  $t0, 0   # b1
li  $t1, 1   # b2
jal grsTest  # Test each grs combo

# Rounding 10
li  $v0, 4   # New line and print bit binary being rounded
la  $a0, newLine
syscall
la  $a0, bitBinary2
syscall
li  $t0, 1   # b1
li  $t1, 0   # b2
jal grsTest # Test each grs combo

# Rounding 11
li  $v0, 4   # New line and print bit binary being rounded
la  $a0, newLine
syscall
la  $a0, bitBinary3
syscall
li  $t0, 1   # b1
li  $t1, 1   # b2
jal grsTest # Test each grs combo

j   end       # end of program

grsTest: # Go through all guard round and sticky possibilities and calculate results
	move $s0, $ra # save the return addresss
	li $t2, 0
	li $t3, 0
	li $t4, 0
	jal round
	li $t2, 0
	li $t3, 0
	li $t4, 1
	jal round
	li $t2, 0
	li $t3, 1
	li $t4, 0
	jal round
	li $t2, 0
	li $t3, 1
	li $t4, 1
	jal round
	li $t2, 1
	li $t3, 0
	li $t4, 0
	jal round
	li $t2, 1
	li $t3, 0
	li $t4, 1
	jal round
	li $t2, 1
	li $t3, 1
	li $t4, 0
	jal round
	li $t2, 1
	li $t3, 1
	li $t4, 1
	jal round
	jr $s0

round: # Rounding operation
	move $t6, $t0       #proxy value of b1
	move $t7, $t1       #proxy value of b2
	beqz $t2, print     # No rounding occurs since 'g' is zero
rsTest: # Testing round and sticky if it's zero
	bnez $t3, increment # proceed if round is nonzero
	bnez $t4, increment # proceed if sticky is nonzero
	beqz $t1, print     # Even rounding; continue if number is odd
increment: # Increment b2 by 1
	addi $t7, $t7, 1    # increment
print: # Calculate and print out value
	sll  $t6, $t6, 1    # Convert 1st bit to decimal
	add  $t5, $t6, $t7  # Compute final value
	li   $v0, 1
	move $a0, $t5
	syscall             # print out result
	li   $v0, 4
	la $a0, space2
	syscall             # Format cleanup
	move $t6, $s7       # reset copied values
	move $t7, $s7
	jr   $ra            # Return to calling statement

end:

.data
space1:     .asciiz "    "
space2:     .asciiz "   "
newLine:    .asciiz "\n"

bitBinary1: .asciiz "01:  "
bitBinary2: .asciiz "10:  "
bitBinary3: .asciiz "11:  "

grsTitle:   .asciiz "000 001 010 011 100 101 110 111"