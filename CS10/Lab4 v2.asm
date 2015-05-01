# Length: The length is notably increases, but not unnacceptably. This program is significantly longer, but only because separate methods were created for unrolling two and three times.
# Register usage: When unrolled three times, all but one of the registers were used for the multiplication, significantly more restricting than the original multiply code.
# Conceptual complexity: There is little difficulty in the conceptual idea of unrolling the multiply code, but when creating the code, it can get quite messy the more it is unrolled.

# Terms:
# m - matrix
# r - row

# $t5 - matrix to print, current position in a matrix (getRow and printMatrix)
# $t6 - unused
# $t7 - 1st counter for inner loop (printMatrix, getRow)
# $t8 - 2nd counter for outer loop (printMatrix, getRow)
# $t9 - Data position in matrix
# $t0-t8 - used by matrix multiply

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
li    $s4, 6        # Dimension of matrix
la    $s5, matrix1  # Start of m1
la    $s6, matrix2  # Start of m2
la    $s7, matrix3  # Start of m3
la    $t5, matrix1  # Position in matrix

# Matrix multiplication using 2 times unrolled
jal   matrixMult2
li    $v0, 4        # Results title
la    $a0, result_unroll2
syscall
move  $t5, $s7      # Result matrix
jal   printMatrix
# Matrix multiplication using 3 times unrolled
jal   matrixMult3
li    $v0, 4        # Results title
la    $a0, result_unroll3
syscall
move  $t5, $s7      # Result matrix
jal   printMatrix
j     end

##############
# Procedures #
##############
##############
# matrixMult #
# unroll: 2  #
##############
matrixMult2:   # Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	sub   $t5, $t5, $t5
	sub   $t6, $t6, $t6
	sub   $t7, $t7, $t7
	sub   $t8, $t8, $t8
	sub   $t9, $t9, $t9
	# Load values required for matrixMult
	move  $t3, $s7         # $t3 will point to elements in result matrix
	move  $t4, $s4         # Matrix dimension
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L11:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L12:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# First operation for loop unroll - uses $t0 and $t1
	# Data loading for matrix 2
L13:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
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
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Second operation for loop unroll - uses $t5 and $t6
	# Data loading for matrix 2
	mul   $t5, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t5, $t5, $s1    # Find appropriate column by adding offset
	sll   $t5, $t5, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t5, $t5, $s6    # Add base address of matrix 2
	lw    $t5, ($t5)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t6, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t6, $t6, $s2    # Find appropriate column by adding offset
	sll   $t6, $t6, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t6, $t6, $s5    # Add base address of matrix 1
	lw    $t6, ($t6)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t5, $t5, $t6    # Multiply
	add   $t2, $t2, $t5    # Add result to matrix element
	sub   $t5, $t5, $t5    # Clean content of $t5 and $t6
	sub   $t6, $t6, $t6
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $t4, L13
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L12
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L11
	
	jr    $ra              # Mission accomplished
	
##############
# matrixMult #
# unroll: 3  #
##############
matrixMult3:   # Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	sub   $t5, $t5, $t5
	sub   $t6, $t6, $t6
	sub   $t7, $t7, $t7
	sub   $t8, $t8, $t8
	sub   $t9, $t9, $t9
	# Load values required for matrixMult
	move  $t3, $s7         # $t3 will point to elements in result matrix
	move  $t4, $s4         # Matrix dimension
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L21:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L22:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# First operation for loop unroll - uses $t0 and $t1
	# Data loading for matrix 2
L23:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
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
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Second operation for loop unroll - uses $t5 and $t6
	# Data loading for matrix 2
	mul   $t5, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t5, $t5, $s1    # Find appropriate column by adding offset
	sll   $t5, $t5, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t5, $t5, $s6    # Add base address of matrix 2
	lw    $t5, ($t5)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t6, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t6, $t6, $s2    # Find appropriate column by adding offset
	sll   $t6, $t6, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t6, $t6, $s5    # Add base address of matrix 1
	lw    $t6, ($t6)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t5, $t5, $t6    # Multiply
	add   $t2, $t2, $t5    # Add result to matrix element
	sub   $t5, $t5, $t5    # Clean content of $t5 and $t6
	sub   $t6, $t6, $t6
	addiu $s2, $s2, 1      # Increment
	# Third operation for loop unroll - uses $t7 and $t8
	# Data loading for matrix 2
	mul   $t7, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t7, $t7, $s1    # Find appropriate column by adding offset
	sll   $t7, $t7, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t7, $t7, $s6    # Add base address of matrix 2
	lw    $t7, ($t7)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t8, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t8, $t8, $s2    # Find appropriate column by adding offset
	sll   $t8, $t8, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t8, $t8, $s5    # Add base address of matrix 1
	lw    $t8, ($t8)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t7, $t7, $t8    # Multiply
	add   $t2, $t2, $t7    # Add result to matrix element
	sub   $t7, $t7, $t7    # Clean content of $t7 and $t8
	sub   $t8, $t8, $t8
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $t4, L23
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L22
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L21
	
	jr    $ra              # Mission accomplished

###############
# printMatrix #
###############
printMatrix:  # Print out the matrix with starting point stored in $t5
	li    $v0, 4           # New line for formatting
	la    $a0, newLine
	syscall
	move  $t8, $s4         # Give outer loop counter the matrix dimension
printOuter:   # Print a row each pass
	move  $t7, $s4         # Give inner loop counter the matrix dimension
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
# End of program #
##################
end:

############################
# Variables and other data #
############################
.data

result_unroll2:      .asciiz "\nMatrix multiplication with loop unrolled 2 times:\n"
result_unroll3:      .asciiz "\nMatrix multiplication with loop unrolled 3 times:\n"

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

