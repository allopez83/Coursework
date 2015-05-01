# Terms:
# m - matrix
# r - row

# $t0 - Reserved for matrix multiply
# $t1 - Reserved for matrix multiply
# $t2 - Reserved for matrix multiply
# $t3 - Reserved for matrix multiply
# $t4 - Reserved for matrix multiply

# $t5 - 1st counter for printMatrix (outer loop)
#		matrix to print with printMatrix
# $t6 - 2nd counter for printMatrix (inner loop)
# $t7 - position in matrix

# $s0 - Reserved for matrix multiply
# $s1 - Reserved for matrix multiply
# $s2 - Reserved for matrix multiply

# $s5 - start of m1
# $s6 - start of m2
# $s7 - start of m3 (results)

####################
# Start of program #
####################
# Save matrix information
li    $s4, 6         # Dimension of matrix
la    $s5, matrix1   # Start of m1
la    $s6, matrix2   # Start of m2
la    $s7, matrix3   # Start of m3

la    $t7, matrix1   # Position in matrix

# Print title
li    $v0, 4
la    $a0, prompt
syscall

############
# Matrix 1 #
############
la    $a0, matrixTitle1 # Print title
syscall
li    $v0, 4        # Get numbers for m1, r1
la    $a0, row1
syscall
jal   getRow
li    $v0, 4        # Get numbers for m1, r2
la    $a0, row2
syscall
jal   getRow
li    $v0, 4        # Get numbers for m1, r3
la    $a0, row3
syscall
jal   getRow
move  $t6, $s5      # Print m1
jal   printMatrix

# Save start of m2
move  $s6, $t7

############
# Matrix 2 #
############
li    $v0, 4        # Print title
la    $a0, matrixTitle2
syscall
li    $v0, 4        # Get numbers for m2, r1
la    $a0, row1
syscall
jal   getRow
li    $v0, 4        # Get numbers for m2, r2
la    $a0, row2
syscall
jal   getRow
li    $v0, 4        # Get numbers for m2, r3
la    $a0, row3
syscall
jal   getRow
move  $t6, $s6      # Print m2
jal   printMatrix

# Save start of m3
move  $s7, $t7

# Matrix multiplication
jal matrixMult

# Print results
li    $v0, 4        # Results title
la    $a0, result
syscall
move $t6, $s7       # Result matrix
jal printMatrix

j end               # End of program

##############
# Procedures #
##############
matrixMult:   # Matrix Multiplication Code
	move $t3, $s7          # $t3 will point to elements in result matrix
	move $t4, $s4          # Matrix dimension
	
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L1:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L2:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L3:     mul   $t0, $s2, 3      # Find the appropriate row by multiplying offset by 3
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $s6    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, 3      # Find the appropriate row by multiplying by 3
	addu  $t1, $t1, $s2    # Find appropriate column by adding offset
	sll   $t1, $t1, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t1, $t1, $s5    # Add base address of matrix 1
	lw    $t1, ($t1)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t0, $t0, $t1    # Multiply
	add   $t2, $t2, $t0    # Add result to matrix element
	move  $t0, $t9         # Clean content of $t0 and $t1
	move  $t1, $t9
	# Branch and store operations
	addiu $s2, $s2, 1      # Increment and branch as necessary
	bne   $s2, $t4, L3
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	move  $t2, $t9         # Clean content of $t3
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L2
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L1
	
	jr $ra                 # Mission accomplished
	
getRow:       # Get values for a row at $t7
	li    $v0, 5       # First
	syscall
	sw    $v0, ($t7)
	addi  $t7, $t7, 4
	li    $v0, 5       # Second
	syscall
	sw    $v0, ($t7)
	addi  $t7, $t7, 4
	li    $v0, 5       # Third
	syscall
	sw    $v0, ($t7)
	addi  $t7, $t7, 4
	jr    $ra
	
printMatrix:  # Print out the matrix with starting point stored in $t6
	# Prepare loop counters by giving them matrix dimensions
	move $t5, $s4
	move $t6, $s4
printLoop:
	# Print out a row
	li    $v0, 1       # Print matrix element
	lw    $a0, 0($t6)
	syscall
	li    $v0, 4
	la    $a0, divider
	syscall
	li    $v0, 1       # Print matrix element
	lw    $a0, 4($t6)
	syscall
	li    $v0, 4
	la    $a0, divider
	syscall
	li    $v0, 1       # Print matrix element
	lw    $a0, 8($t6)
	syscall
	li    $v0, 4       # New line since this matrix row ends here
	la    $a0, newLine
	syscall
	
	addi  $t6, $t6, 12  # Increment
	
	addi  $t5, $t5, -1
	bgtz  $t5, printLoop
	jr    $ra

j end               # Avoid junk code in case program falls through
##################
# Junk Code Area #
##################
# None; cleaned for exporting

##################
# End of program #
##################
end:

#####
.data
#####
prompt:       .asciiz "Two 3x3 matrices will be multiplied. Insert the data for both matrices. Numbers only please."
result:       .asciiz "\nResult of first matrix multiplied by second matrix:\n"

matrixTitle1: .asciiz "\nFirst matrix"
matrixTitle2: .asciiz "\nSecond matrix"

row1:         .asciiz "\nFirst row:\n"
row2:         .asciiz "\nSecond row:\n"
row3:         .asciiz "\nThird row:\n"

divider:      .asciiz ", "
newLine:      .asciiz "\n"

matrix1: .word  # m1 - multiply this
		01, 02, 03, 04, 05, 06
		07, 08, 09, 10, 11, 12
		13, 14, 15, 16, 17, 18
		19, 20, 21, 22, 23, 24
		25, 26, 27, 28, 29, 30
		31, 32, 33, 34, 35, 36

matrix2: .word  # m2 - multiply this
		01, 02, 03, 04, 05, 06
		07, 08, 09, 10, 11, 12
		13, 14, 15, 16, 17, 18
		19, 20, 21, 22, 23, 24
		25, 26, 27, 28, 29, 30
		31, 32, 33, 34, 35, 36
		
matrix3: .word  # m3 - store results here
		01, 02, 03, 04, 05, 06
		07, 08, 09, 10, 11, 12
		13, 14, 15, 16, 17, 18
		19, 20, 21, 22, 23, 24
		25, 26, 27, 28, 29, 30
		31, 32, 33, 34, 35, 36