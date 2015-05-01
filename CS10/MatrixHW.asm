# Fix printMatrix same way as getMatrix was fixed

# Terms:
# m - matrix
# r - row

# $t0 - Reserved for matrix multiply
# $t1 - Reserved for matrix multiply
# $t2 - Reserved for matrix multiply
# $t3 - Reserved for matrix multiply
# $t4 - Reserved for matrix multiply

# $t5 - matrix to print, current position in a matrix (getRow and printMatrix)
# $t6 - unused
# $t7 - 1st counter for inner loop (printMatrix, getRow)
# $t8 - 2nd counter for outer loop (printMatrix, getRow)
# $t9 - Data position in matrix

# $s0 - Reserved for matrix multiply
# $s1 - Reserved for matrix multiply
# $s2 - Reserved for matrix multiply

# $s4 - dimension of matrix (6)
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

la    $t5, matrix1   # Position in matrix

# Print title
li    $v0, 4
la    $a0, prompt
syscall

############
# Matrix 1 #
############
la    $a0, matrixTitle1 # Print title
syscall

move  $t5, $s5      # Print m1
jal   printMatrix

############
# Matrix 2 #
############
la    $a0, matrixTitle2 # Print title
syscall

move  $t5, $s6      # Print m2
jal   printMatrix

# Matrix multiplication
jal matrixMult

# Print results
li    $v0, 4        # Results title
la    $a0, result
syscall
move $t5, $s7       # Result matrix
jal printMatrix

j end               # End of program

##############
# Procedures #
##############
##############
# matrixMult #
##############
matrixMult:   # Matrix Multiplication Code
	move $t3, $s7          # $t3 will point to elements in result matrix
	move $t4, $s4          # Matrix dimension
	
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L1:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L2:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L3:     mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $s6    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
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

#############
# getMatrix #
#############
getMatrix:    # User input for a matrix row using memory at $t5
	move $t8, $s4          # Give outer loop counter the matrix dimension
getRowOuter:  # Print a row each pass
	move $t7, $s4          # Give inner loop counter the matrix dimension
	# Print out divider for each row
	li    $v0, 4
	la    $a0, rowTitle
	syscall
getRowInner:  # Print an element each pass
	# Action
	li    $v0, 5           # Get data for an element
	syscall
	sw    $v0, ($t5)       # Store to element
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	# Looping instructions
	bgtz  $t7, getRowInner # Inner loop branch
	li    $v0, 4           # New line since this matrix row ends here
	la    $a0, newLine
	syscall
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, getRowOuter # Outer loop branch
	# Reset values
	sub $t5, $t5, $t5
	sub $t7, $t7, $t7
	sub $t8, $t8, $t8
	jr    $ra              # Return to caller
	
###############
# printMatrix #
###############
printMatrix:  # Print out the matrix with starting point stored in $t5
	move $t8, $s4          # Give outer loop counter the matrix dimension
printOuter:   # Print a row each pass
	move $t7, $s4          # Give inner loop counter the matrix dimension
printInner:   # Print an element each pass
	# Action
	li    $v0, 1           # Print matrix element
	lw    $a0, 0($t5)
	syscall
	li    $v0, 4           # Divider for neatness
	la    $a0, divider
	syscall
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	# Looping instructions
	bgtz  $t7, printInner  # Inner loop branch
	li    $v0, 4           # New line since this matrix row ends here
	la    $a0, newLine
	syscall
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, printOuter  # Outer loop branch
	jr    $ra              # Return to caller

	
j end               # Avoid junk code in case program falls through
##################
# Junk Code Area #
##################
# None; cleaned for exporting

##################
# End of program #
##################
end:

############################
# Variables and other data #
############################
.data

prompt:       .asciiz "Two 6x6 matrices will be multiplied. Insert the data for both matrices. Numbers only please."
result:       .asciiz "\nResult of first matrix multiplied by second matrix:\n"

matrixTitle1: .asciiz "\nFirst matrix\n"
matrixTitle2: .asciiz "\nSecond matrix\n"

rowTitle:         .asciiz "Row:\n"

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

